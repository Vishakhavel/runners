package com.vishakhavel.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
//base mapping, all the uris inside will follow /api/runs
@RequestMapping("/api/runs")
public class RunController {
    private final RunRepository runRepository;

    //    dependency injection, since RunRepository is managed by spring, cos of the @Repository annotation !
    public RunController(RunRepository runRepository) {
//        passing RunRepository in the args tells spring to inject it.
        this.runRepository = runRepository;
    }

    //    base URI
    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    //     return the run  by id
    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional run = runRepository.findById(id);
        if (run.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "run not found");
        } else {
            return (Run) run.get();
        }
    }

}
