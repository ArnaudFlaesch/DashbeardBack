package com.esgi.controllers;

import com.esgi.model.PostIt;
import com.esgi.repositories.PostItRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Arnaud on 01/11/2016.
 */
@CrossOrigin
@RestController
@RequestMapping("/postit")
public class PostItController {

    @Autowired
    private PostItRepository postItRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PostIt> getDashboard() {
        return (postItRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public PostIt addPostIt(@RequestBody PostIt newPostIt) {
        return(postItRepository.save(newPostIt));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePostIt(@RequestBody PostIt updatedPostIt) {
        postItRepository.save(updatedPostIt);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removePostIt(@RequestParam Long idPostIt) {
        postItRepository.delete(idPostIt);
    }
}
