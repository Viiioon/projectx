package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entity.Theorem;
import ch.zhaw.projectx.repository.TheoremRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theorems")
public class TheoremController {

    @Autowired
    private TheoremRepository theoremRepository;

    @GetMapping
    public ResponseEntity<List<Theorem>> findAll() {
        List<Theorem> result = this.theoremRepository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Theorem>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Theorem>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theorem> findById(@PathVariable("id") Long id) {
        Optional<Theorem> result = this.theoremRepository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<Theorem>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Theorem>(HttpStatus.NOT_FOUND);
        }
    }
}
