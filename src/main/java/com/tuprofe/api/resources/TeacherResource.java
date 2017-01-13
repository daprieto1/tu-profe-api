package com.tuprofe.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author diegoprietotorres
 */
@RestController
public class TeacherResource {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
