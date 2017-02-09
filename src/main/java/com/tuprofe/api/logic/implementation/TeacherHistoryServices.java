/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.entities.TeacherHistory;
import com.tuprofe.api.logic.services.ITeacherHistoryServices;
import com.tuprofe.api.persistance.repositories.ITeacherHistoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
@Qualifier("TeacherHistoryServices")
public class TeacherHistoryServices implements ITeacherHistoryServices{
    
    @Autowired
    @Qualifier("DynamoTeacherHistoryRepository")
    private ITeacherHistoryRepository teacherHistoryRepository;

    @Override
    public TeacherHistory create(TeacherHistory teacherHistory) {
        return teacherHistoryRepository.save(teacherHistory);
    }

    @Override
    public TeacherHistory find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TeacherHistory update(TeacherHistory entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TeacherHistory entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TeacherHistory> finadAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
