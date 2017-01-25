package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.EmailSQS;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.Token;
import com.tuprofe.api.entities.User;
import com.tuprofe.api.entities.enums.EnumTeacherState;
import com.tuprofe.api.logic.services.ISQSServices;
import com.tuprofe.api.logic.services.ISessionService;
import com.tuprofe.api.logic.services.ITeacherServices;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author diegoprietotorres
 */
@Service
@Qualifier("SessionService")
public class SessionService implements ISessionService {
    
    private static final Integer EXPIRATION_TOKEN = 3600000;
    
    @Autowired
    @Qualifier("TeacherServices")
    private ITeacherServices teacherServices;
    
    @Autowired
    @Qualifier("SQSServices")
    private ISQSServices sqsServices;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private StringRedisTemplate template;
    
    @Override
    public Token authenticateTeacher(String username, String password) {
        Teacher teacher = teacherServices.findByEmail(username);
        boolean match = bCryptPasswordEncoder.matches(password, teacher.getPassword());
        Token tokenObj;
        
        if (match) {
            UUID token = UUID.randomUUID();
            System.out.println(token.toString());
            template.opsForValue().set(token + "", username, EXPIRATION_TOKEN, TimeUnit.MILLISECONDS);
            tokenObj = new Token(token.toString(), username, teacher.getId(), EXPIRATION_TOKEN);
        } else {
            throw new TuProfeAPIException(TuProfeAPIException.BAD_CREDENTIALS);
        }
        
        return tokenObj;
    }
    
    @Override
    public Teacher signUpTeacher(Teacher teacher) {
        signUpProcess(teacher);
        teacher.setState(EnumTeacherState.SIGN_UP.getId());
        Teacher result = teacherServices.create(teacher);
        
        EmailSQS email = new EmailSQS();
        email.setTo("to");
        email.setSubject("subject");
        email.setBody("body");
        
        sqsServices.send(email);
        return result;
    }
    
    @Override
    public Teacher getAuthenticatedTeacher(String user) {
        String teacherUser = template.opsForValue().get(user);
        return teacherServices.findByEmail(teacherUser);
    }
    
    private void signUpProcess(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
    
}
