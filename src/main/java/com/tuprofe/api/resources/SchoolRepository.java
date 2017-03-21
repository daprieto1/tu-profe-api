package com.tuprofe.api.resources;

import com.tuprofe.api.entities.School;
import com.tuprofe.api.logic.services.ISchoolServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author diegoprietotorres
 */
@RestController
@RequestMapping("/school")
public class SchoolRepository {

    @Autowired
    @Qualifier("SchoolServices")
    private ISchoolServices SchoolServices;

    @RequestMapping(method = RequestMethod.GET)
    public List<School> getAll() {
        return SchoolServices.finadAll();
    }
}
