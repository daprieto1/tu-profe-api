package com.tuprofe.api.resources;

import com.tuprofe.api.entities.AdminUser;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.Token;
import com.tuprofe.api.entities.User;
import com.tuprofe.api.logic.services.ISessionService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author diegoprietotorres
 */
@RestController
@RequestMapping("/session")
public class SessionResource {

    public static final String AUTH_HEADER_TOKEN = "access_token";

    @Autowired
    @Qualifier("SessionService")
    private ISessionService sessionService;

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired
    private StringRedisTemplate template;

    @RequestMapping(value = "/teacher/login", method = RequestMethod.POST, produces = "application/json")
    public Token loginTeacher(@RequestParam("username") String username, @RequestParam("password") String password) {
        return sessionService.authenticateTeacher(username, password);
    }

    @RequestMapping(value = "/teacher/signup", method = RequestMethod.POST, produces = "application/json")
    public User signupTeacher(@RequestBody Teacher teacher) {
        return sessionService.signUpTeacher(teacher);
    }

    @RequestMapping(value = "/teacher/forgot-password", method = RequestMethod.POST, produces = "application/json")
    public void forgotPasswordTeacher(@RequestParam("email") String email) {
        sessionService.forgotPasswordTeacher(email);
    }

    @RequestMapping(value = "/teacher/recovery-password", method = RequestMethod.POST, produces = "application/json")
    public void recoveryPasswordTeacher(@RequestParam("nonce") String nonce, @RequestParam("password") String password) {
        sessionService.changePasswordTeacher(nonce, password);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET, produces = "application/json")
    public Teacher getAuthenticatedTeacher() {
        String token = request.getHeader(AUTH_HEADER_TOKEN);
        return sessionService.getAuthenticatedTeacher(token);
    }

    @RequestMapping(value = "/admin-user/login", method = RequestMethod.POST, produces = "application/json")
    public Token loginAdminUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        return sessionService.authenticate(AdminUser.class, username, password);
    }

    @RequestMapping(value = "/admin-user/signup", method = RequestMethod.POST, produces = "application/json")
    public User signupAdminUser(@RequestBody AdminUser adminUser) {
        return sessionService.signUp(AdminUser.class, adminUser);
    }
}
