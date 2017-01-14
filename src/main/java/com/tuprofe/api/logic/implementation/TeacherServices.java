package com.tuprofe.api.logic.implementation;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.services.ITeacherServices;
import com.tuprofe.api.persistance.repositories.ITeacherRepository;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author diegoprietotorres
 */
@Service
@Qualifier("TeacherServices")
public class TeacherServices implements ITeacherServices {

    @Autowired
    @Qualifier("DynamoTeacherRepository")
    ITeacherRepository teacherRepository;

    @Autowired
    private AmazonS3Client amazonS3Client;

    @Override
    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher find(String id) {
        return teacherRepository.find(id);
    }
    
    @Override
    public Teacher update(Teacher entity) {
        return teacherRepository.update(null, entity);
    }

    @Override
    public void delete(Teacher entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void uploadCurriculum(MultipartFile file) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());

        PutObjectRequest putObjectRequest = new PutObjectRequest("tuprofe/curriculum", "hoja", file.getInputStream(), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3Client.putObject(putObjectRequest);
    }

    

}
