package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entity.Theory;
import ch.zhaw.projectx.repository.TheoryRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theories")
public class TheoryController {

    @Autowired
    private TheoryRepository theoryRepository;

    @GetMapping
    public ResponseEntity<List<Theory>> findAll() {
        List<Theory> result = this.theoryRepository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Theory>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Theory>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theory> findById(@PathVariable("id") Long id) {
        Optional<Theory> result = this.theoryRepository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<Theory>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Theory>(HttpStatus.NOT_FOUND);
        }
    }
}
