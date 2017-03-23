package com.tuprofe.api;

import com.tuprofe.api.resources.SessionResource;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

/**
 *
 * @author diego
 */
@Aspect
@Component
public class AuthorizationAspect {
    
    @Autowired(required = false)
    private HttpServletRequest request;
    
    @Pointcut("within(com.tuprofe.api.resources..*)")
    public void restMethods() {};
 
    @Before("restMethods()")
    public void verifyAuthorization(JoinPoint joinPoint) {
        Set<String> avoid = new HashSet<>();
        avoid.add("loginTeacher");
        avoid.add("signupTeacher");
        avoid.add("forgotPasswordTeacher");
        avoid.add("recoveryPasswordTeacher");
        avoid.add("loginAdminUser");
        
        if (!avoid.contains(joinPoint.getSignature().getName())) {
            String token = request.getHeader(SessionResource.AUTH_HEADER_TOKEN);
            if (token == null) {
                //throw new BadCredentialsException(TuProfeAPIException.BAD_ACCESS_TOKEN);
            }
        }
    }
}
