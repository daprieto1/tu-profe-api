package com.tuprofe.api.resources;

import com.tuprofe.api.entities.University;
import com.tuprofe.api.logic.services.IUniversityServices;
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
@RequestMapping("/university")
public class UniversityRepository {

    @Autowired
    @Qualifier("UniversityServices")
    private IUniversityServices universityServices;

    @RequestMapping(method = RequestMethod.GET)
    public List<University> getAll() {
        return universityServices.finadAll();
    }
}
