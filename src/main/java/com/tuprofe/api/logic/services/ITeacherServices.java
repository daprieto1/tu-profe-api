/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.services;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.CRUD;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author diegoprietotorres
 */
public interface ITeacherServices extends CRUD<Teacher, String> {
    
    public void uploadCurriculum(MultipartFile file) throws IOException;
}
