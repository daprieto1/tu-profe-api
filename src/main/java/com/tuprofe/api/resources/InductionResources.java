package com.tuprofe.api.resources;

import com.tuprofe.api.entities.Induction;
import com.tuprofe.api.logic.services.IInductionServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/induction")
public class InductionResources {
    
    @Autowired
    @Qualifier("InductionServices")
    private IInductionServices inductionServices;

    @RequestMapping(method = RequestMethod.GET)
    public List<Induction> getAll() {
        return inductionServices.finadAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Induction create(@RequestBody Induction induction) {
        return inductionServices.create(induction);
    }

    @RequestMapping(value = "/active", method = RequestMethod.GET, produces = "application/json")
    public List<Induction> getAllActive() {
        return inductionServices.getActive();
    }

    @RequestMapping(value = "/take-place", method = RequestMethod.POST, produces = "application/json")
    public void takePlace(@RequestParam("teacherId") String teacherId, @RequestParam("inductionId") String inductionId) {
        inductionServices.takePlace(teacherId, inductionId);
    }
}
