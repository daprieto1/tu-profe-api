package com.tuprofe.api.logic.services;

import com.tuprofe.api.entities.AdminUser;
import com.tuprofe.api.logic.CRUD;

/**
 *
 * @author diego
 */
public interface IAdminUserServices extends CRUD<AdminUser, String>{
    
    public AdminUser findByEmail(String email);
    
}
