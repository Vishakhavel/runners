package com.vishakhavel.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;


@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    public List<Run> findAll() {
        return runs;
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(1, "run1", LocalDateTime.now(), LocalDateTime.now(), 3, Location.OUTDOOR));
        runs.add(new Run(2, "run2", LocalDateTime.now(), LocalDateTime.now(), 3, Location.INDOOR));
    }

    Optional findById(Integer id) {
        return runs.stream().filter(run -> run.id() == id).findFirst();
    }
}
