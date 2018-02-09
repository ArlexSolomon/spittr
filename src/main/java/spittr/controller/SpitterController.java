package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spittr.role.domain.Spitter;
import spittr.role.service.SpitterService;

@RestController
@RequestMapping(value = "/rest/spitter")
@PropertySource("classpath:/application.properties")
public class SpitterController {
    @Autowired
    private SpitterService spitterService;
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Spitter create(@RequestBody Spitter spitter) {
    	return spitterService.create(spitter);
    }
}
