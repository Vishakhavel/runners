package com.vishakhavel.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
            throw new RunNotFoundException();
        } else {
            return (Run) run.get();
        }
    }

    //    post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Run run) {
        runRepository.create(run);
    }


//    put

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run, id);

    }

    //    delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }
}
