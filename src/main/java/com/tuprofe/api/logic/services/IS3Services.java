/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.services;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author diegoprietotorres
 */
public interface IS3Services {
    
    public void uploadFile(MultipartFile input, String bucketName, String key) throws IOException;
}
