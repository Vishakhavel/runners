package com.vishakhavel.runnerz.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class RunController {
    private  final RunRepository runRepository;
//    dependency injection, since RunRepository is managed by spring, cos of the @Repository annotation !
    public RunController(RunRepository runRepository){
//        passing RunRepository in the args tells spring to inject it.
            this.runRepository = runRepository;
    }
    @GetMapping("/api/runs")
    List<Run> findAll(){
        return runRepository.findAll();
    }

}
