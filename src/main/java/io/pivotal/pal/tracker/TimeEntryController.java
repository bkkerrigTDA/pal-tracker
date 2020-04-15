package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository tr) {
        this.timeEntryRepository = tr;
    }

    @PostMapping(path="/time-entries", consumes = "application/json")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry t) {
        TimeEntry x = timeEntryRepository.create(t);
        ResponseEntity<TimeEntry> r = new ResponseEntity<>(x, HttpStatus.CREATED);
        return r;
    }

    @GetMapping(path="/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {
        TimeEntry x = timeEntryRepository.find(id);
        HttpStatus status = HttpStatus.OK;
        if(x == null) {
            status = HttpStatus.NOT_FOUND;
        }
        ResponseEntity<TimeEntry> r = new ResponseEntity<>(x, status);
        return r;
    }

    @GetMapping(path="/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @RequestMapping (path="/time-entries/{id}", method=RequestMethod.PUT)
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long id, @RequestBody TimeEntry t) {
        TimeEntry x = timeEntryRepository.update(id, t);
        HttpStatus status = HttpStatus.OK;
        if(x == null) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(x, status);
    }

    @RequestMapping (path="/time-entries/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long id) {
        TimeEntry x = timeEntryRepository.delete(id);
        return new ResponseEntity<>(x, HttpStatus.NO_CONTENT);
    }
}

