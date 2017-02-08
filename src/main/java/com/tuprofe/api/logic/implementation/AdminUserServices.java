package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.AdminUser;
import com.tuprofe.api.logic.services.IAdminUserServices;
import com.tuprofe.api.persistance.repositories.IAdminUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
@Qualifier("AdminUserServices")
public class AdminUserServices implements IAdminUserServices{
    
    @Autowired
    @Qualifier("DynamoAdminUserRepository")
    private IAdminUserRepository adminUserRepository;

    @Override
    public AdminUser create(AdminUser adminUser) {
        if (adminUser == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NULL_PARAM);
        }
        
        return adminUserRepository.save(adminUser);
    }

    @Override
    public AdminUser find(String id) {
        if (id == null || "".equals(id)) {
            throw new TuProfeAPIException(TuProfeAPIException.EMPTY_PARAM);
        }

        AdminUser adminUser = adminUserRepository.find(id);

        if (adminUser == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NOT_FIND_ENTITY);
        }

        return adminUser;
    }

    @Override
    public AdminUser update(AdminUser entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(AdminUser entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AdminUser> finadAll() {
        return adminUserRepository.findAll();
    }

    @Override
    public AdminUser findByEmail(String email) {
        if (email == null || "".equals(email)) {
            throw new TuProfeAPIException(TuProfeAPIException.EMPTY_PARAM);
        }

        AdminUser adminUser = adminUserRepository.findByEmail(email);

        if (adminUser == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NOT_FIND_ENTITY);
        }

        return adminUser;
    }
    
}
