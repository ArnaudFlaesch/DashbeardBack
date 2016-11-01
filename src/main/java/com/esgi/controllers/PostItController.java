package com.esgi.controllers;

import com.esgi.model.PostIt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Arnaud on 01/11/2016.
 */
@RestController
@RequestMapping("/postit")
public class PostItController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<PostIt> getDashboard() {
        return (null);
    }
}
