package com.tuprofe.api.logic.services;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.CRUD;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author diegoprietotorres
 */
public interface ITeacherServices extends CRUD<Teacher, String> {
    
    public void uploadCurriculum(MultipartFile file, String teacherId);
}
