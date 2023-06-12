package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entity.Belief;
import ch.zhaw.projectx.repository.BeliefRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beliefs")
public class BeliefController {

    @Autowired
    private BeliefRepository repository;

    @GetMapping
    public ResponseEntity<List<Belief>> findAll() {
        List<Belief> result = this.repository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Belief>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Belief>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Belief> findById(@PathVariable("id") Long id) {
        Optional<Belief> result = this.repository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<Belief>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Belief>(HttpStatus.NOT_FOUND);
        }
    }
}
