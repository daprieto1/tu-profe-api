package com.tuprofe.api.resources;

import com.tuprofe.api.entities.AdminUser;
import com.tuprofe.api.logic.services.IAdminUserServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/admin-user")
public class AdminUserResource {
    
    @Autowired
    @Qualifier("AdminUserServices")
    private IAdminUserServices adminUserServices;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<AdminUser> get() {
        return adminUserServices.finadAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AdminUser get(@PathVariable("id") String id) {
        return adminUserServices.find(id);
    }
}
