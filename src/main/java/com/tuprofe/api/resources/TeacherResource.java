package com.tuprofe.api.resources;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.services.ITeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author diegoprietotorres
 */
@RestController
@RequestMapping("/teacher")
public class TeacherResource {

    @Autowired
    @Qualifier("TeacherServices")
    private ITeacherServices teacherServices;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Teacher get(@PathVariable("id") String id) {
        return teacherServices.find(id);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json"}, consumes = {"application/json"})
    public Teacher update(@RequestBody Teacher teacher) {
        return teacherServices.update(teacher);
    }

    @RequestMapping(value = "/accept-game-rules/{id}", method = RequestMethod.POST)
    public void acceptGameRules(@PathVariable("id") String id) {
        
    }

    @RequestMapping(value = "/upload-curriculum", method = RequestMethod.POST)
    public ResponseEntity<?> uploadCurriculum(@RequestParam("teacherId") String teacherId,
            @RequestParam("file") MultipartFile file) {
        teacherServices.uploadCurriculum(file, teacherId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/upload-photo", method = RequestMethod.POST)
    public ResponseEntity<?> uploadPhoto(@RequestParam("teacherId") String teacherId,
            @RequestParam("file") MultipartFile file) {
        teacherServices.uploadPhoto(file, teacherId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
