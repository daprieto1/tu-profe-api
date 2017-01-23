/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.Token;
import com.tuprofe.api.entities.User;
import com.tuprofe.api.entities.enums.EnumTeacherState;
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
    ITeacherServices teacherServices;

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
            tokenObj = new Token(token.toString(), username, EXPIRATION_TOKEN);
        } else {
            throw new TuProfeAPIException(TuProfeAPIException.BAD_CREDENTIALS);
        }

        return tokenObj;
    }

    @Override
    public Teacher signUpTeacher(Teacher teacher) {
        signUpProcess(teacher);
        teacher.setState(EnumTeacherState.SIGN_UP.getId());
        return teacherServices.create(teacher);
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
