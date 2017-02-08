package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.AdminUser;
import com.tuprofe.api.entities.Email;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.Token;
import com.tuprofe.api.entities.User;
import com.tuprofe.api.entities.enums.EnumTeacherState;
import com.tuprofe.api.logic.services.IAdminUserServices;
import com.tuprofe.api.logic.services.IEmailServices;
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
    private static final Integer EXPIRATION_NONCE = 3600000;

    private static final String PREFIX_NONCE = "NONCE-";

    @Autowired
    @Qualifier("TeacherServices")
    private ITeacherServices teacherServices;

    @Autowired
    @Qualifier("AdminUserServices")
    private IAdminUserServices adminUserServices;

    @Autowired
    @Qualifier("SendGridServices")
    private IEmailServices emailServices;

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

        Email email = new Email();
        email.setTo("to");
        email.setSubject("subject");
        email.setBody("body");

        emailServices.sendSignUpTeacheMail(teacher);
        return result;
    }

    @Override
    public Teacher getAuthenticatedTeacher(String user) {
        String teacherUser = template.opsForValue().get(user);
        return teacherServices.findByEmail(teacherUser);
    }

    @Override
    public void forgotPasswordTeacher(String email) {
        Teacher teacher = teacherServices.findByEmail(email);
        UUID token = UUID.randomUUID();
        template.opsForValue().set(PREFIX_NONCE + token, email, EXPIRATION_NONCE, TimeUnit.MILLISECONDS);
        emailServices.sendForgotPasswordTeacheMail(token + "", email);
    }

    @Override
    public void changePasswordTeacher(String nonce, String password) {
        String email = template.opsForValue().get(PREFIX_NONCE + nonce);
        if (email == null || "".equals(email)) {
            throw new TuProfeAPIException(TuProfeAPIException.BAD_NONCE);
        } else {
            Teacher teacher = teacherServices.findByEmail(email);
            teacherServices.updatePassword(teacher.getId(), bCryptPasswordEncoder.encode(password));
            template.delete(PREFIX_NONCE + nonce);
        }
    }

    private void signUpProcess(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    @Override
    public Token authenticate(Class c, String username, String password) {
        User user = null;
        if (c.equals(Teacher.class)) {
            user = teacherServices.findByEmail(username);
        } else if (c.equals(AdminUser.class)) {
            user = adminUserServices.findByEmail(username);
        } else {
            throw new TuProfeAPIException(TuProfeAPIException.NOT_VALID_PARAMETER);
        }

        boolean match = bCryptPasswordEncoder.matches(password, user.getPassword());
        Token tokenObj;

        if (match) {
            UUID token = UUID.randomUUID();
            template.opsForValue().set(token + "", username, EXPIRATION_TOKEN, TimeUnit.MILLISECONDS);
            tokenObj = new Token(token.toString(), username, user.getId(), EXPIRATION_TOKEN);
        } else {
            throw new TuProfeAPIException(TuProfeAPIException.BAD_CREDENTIALS);
        }

        return tokenObj;
    }

    @Override
    public User signUp(Class c, User user) {
        User result;
        signUpProcess(user);

        if (c.equals(Teacher.class)) {
            user.setState(EnumTeacherState.SIGN_UP.getId());
            result = teacherServices.create((Teacher) user);
        } else if (c.equals(AdminUser.class)) {
            result = adminUserServices.create((AdminUser) user);
        } else {
            throw new TuProfeAPIException(TuProfeAPIException.NOT_VALID_PARAMETER);
        }

        return result;
    }

}
