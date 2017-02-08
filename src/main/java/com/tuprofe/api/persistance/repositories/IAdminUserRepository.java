/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.persistance.repositories;

import com.tuprofe.api.entities.AdminUser;
import com.tuprofe.api.persistance.DAO;

/**
 *
 * @author diego
 */
public interface IAdminUserRepository extends DAO<AdminUser, String> {
    
    public AdminUser findByEmail(String email);
    
}
