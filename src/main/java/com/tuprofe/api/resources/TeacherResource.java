package com.tuprofe.api.resources;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.services.ITeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @RequestMapping(method = RequestMethod.PUT)
    public Teacher update(@RequestBody Teacher teacher) {
        return teacherServices.update(teacher);
    }

    @RequestMapping(value = "/upload-curriculum", method = RequestMethod.POST)
    public String uploadCurriculum(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                teacherServices.uploadCurriculum(file);

                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
