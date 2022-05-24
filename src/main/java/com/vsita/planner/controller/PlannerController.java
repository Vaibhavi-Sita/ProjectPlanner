package com.vsita.planner.controller;
import com.vsita.planner.model.PlannerData;
import com.vsita.planner.repository.PlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081") //https://www.baeldung.com/spring-cors

@RestController
/*  simplify the creation of RESTful web services.
    It's a convenient annotation that combines @Controller
    and @ResponseBody, which eliminates the need to annotate every
    request handling method of the controller class with the
    @ResponseBody annotation.

    source: https://www.baeldung.com/spring-controller-vs-restcontroller
 */

@RequestMapping("/home") // map web requests to Spring Controller methods
                         // https://www.baeldung.com/spring-requestmapping

public class PlannerController {

    @Autowired
    PlannerRepository plannerRepository;

    @GetMapping("/plans")
    public ResponseEntity<List<PlannerData>> // represents the whole HTTP response: status code, headers, and body

    getAllPlans(@RequestParam(required = false) String title) {
        try {
            List<PlannerData> plans = new ArrayList<PlannerData>();

            if (title == null)
                plannerRepository.findAll().forEach(plans::add);
            else
                plannerRepository.findByTitleContaining(title).forEach(plans::add);

            if (plans.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(plans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/plans/{id}")
    public ResponseEntity<PlannerData> getPlansById(@PathVariable("id") long id) {
        Optional<PlannerData> PlannerDataData = plannerRepository.findById(id);

        if (PlannerDataData.isPresent()) {
            return new ResponseEntity<>(PlannerDataData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/plans")
    public ResponseEntity<PlannerData> createPlans(@RequestBody PlannerData PlannerData) {
        try {
            Timestamp timestamp = Timestamp.from(Instant.now());
            PlannerData _PlannerData = plannerRepository
                    .save(new PlannerData(PlannerData.getTitle(), PlannerData.getDescription(), false, timestamp));
            return new ResponseEntity<>(_PlannerData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/plans/{id}")
    public ResponseEntity<PlannerData> updatePlans(@PathVariable("id") long id, @RequestBody PlannerData PlannerData) {
        Optional<PlannerData> plannerData = plannerRepository.findById(id);

        if (plannerData.isPresent()) {
            Timestamp timestamp = Timestamp.from(Instant.now());
            PlannerData _plannerData = plannerData.get();
            _plannerData.setTitle(PlannerData.getTitle());
            _plannerData.setDescription(PlannerData.getDescription());
            _plannerData.setDone(PlannerData.isDone());
            _plannerData.setLastUpdated(timestamp);

            return new ResponseEntity<>(plannerRepository.save(_plannerData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/plans/{id}")
    public ResponseEntity<HttpStatus> deletePlans(@PathVariable("id") long id) {
        try {
            plannerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/plans")
    public ResponseEntity<HttpStatus> deleteAllPlans() {
        try {
            plannerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/plans/done")
    public ResponseEntity<List<PlannerData>> findByDone() {
        try {
            List<PlannerData> plans = plannerRepository.findByDone(true);

            if (plans.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(plans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
