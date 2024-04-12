// EventController.java
package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.model.Event;
import com.example.springboot.service.EventService;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    EventService ev;

    @PostMapping("/poste")
    public ResponseEntity<Event> addElement(@RequestBody Event m) {
        Event evt = ev.create(m);
        return new ResponseEntity<>(evt, HttpStatus.CREATED);
    }

    @GetMapping("/gete")
    public ResponseEntity<List<Event>> showInfo() {
        return new ResponseEntity<>(ev.getAll(), HttpStatus.OK);
    }

    @GetMapping("/gete/{eid}")
    public ResponseEntity<Event> getById(@PathVariable Integer eid) {
        Event obj = ev.getId(eid);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping("/pute/{eid}")
    public ResponseEntity<Event> updateElement(@PathVariable("eid") int eid, @RequestBody Event e) {
        if (ev.update(eid, e)) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/dele/{eid}")
    public ResponseEntity<Boolean> delete(@PathVariable("eid") int eid) {
        if (ev.delete(eid)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/event/{offset}/{pagesize}")
    public List<Event> get(@PathVariable int offset, @PathVariable int pagesize) {
        return ev.page(pagesize, offset);
    }

    @GetMapping("/api/event/sortBy/{field}")
    public List<Event> sort(@PathVariable String field) {
        return ev.sort(field);
    }

    @GetMapping("getevent/{id}/{name}")
    public List<Event> displayAll(@PathVariable("id") int s, @PathVariable("name") String s1) {
        return ev.getDetails(s, s1);
    }

    @DeleteMapping("/deletenew/{id}")
    public String deleteInfo(@PathVariable("id") int s) {
        return ev.deleteempdetails(s) + "Deleted";
    }

    @PutMapping("/update/{id}/{id1}")
    public String updateInfo(@PathVariable("id") int s, @PathVariable("id1") int s1) {
        return ev.updateempdetails(s, s1) + "Updated";
    }

    @GetMapping("/query/{fname}")
    public ResponseEntity<Event> findCustByName(@PathVariable String fname) {
        try {
            Event sh = ev.findCustByName(fname);
            return new ResponseEntity<>(sh, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
