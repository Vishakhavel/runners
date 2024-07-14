package com.vishakhavel.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class RunRepository {
    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        log.info("finding all runs");
        return jdbcClient.sql("select * from run").query(Run.class).list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM Run where id = :id").param("id", id).query(Run.class).optional();
    }

    public void create(Run run) {
        jdbcClient.sql("INSERT INTO Run (title, startedOn, completedOn, miles, location) VALUES (:title, :startedOn, :completedOn, :miles, :location)")
                .param("title", run.title())
                .param("startedOn", run.startedOn())
                .param("completedOn", run.completedOn())
                .param("miles", run.miles())
                .param("location", run.location().toString())
                .update();

//        Assert.state(updated == 1, "failed to create run" + run.title());
    }

    public void delete(Integer id) {
        jdbcClient.sql("DELETE FROM Run WHERE id = :id")
                .param("id", id)
                .update();

//        Assert.state(updated == 1, "failed to delete run" + run.title());
    }

    public void update(Run run, Integer id) {
        jdbcClient.sql("UPDATE Run SET title = :title, startedOn = :startedOn, completedOn = :completedOn, miles = :miles, location = :location WHERE id = :id")
                .param("id", id)
                .param("title", run.title())
                .param("startedOn", run.startedOn())
                .param("completedOn", run.completedOn())
                .param("miles", run.miles())
                .param("location", run.location().toString())
                .update();
    }

    public int count() {
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    } // for test

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("SELECT * FROM Run where location = :location").param("location", location).query(Run.class).list();
    }

}
