package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entity.Domain;
import ch.zhaw.projectx.entity.Explanation;
import ch.zhaw.projectx.repository.ExplanationRepository;
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
@RequestMapping("/api/explanations")
public class ExplanationController {

    @Autowired
    private ExplanationRepository explanationRepository;

    @GetMapping
    public ResponseEntity<List<Explanation>> findAll() {
        List<Explanation> result = this.explanationRepository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Explanation>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Explanation>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Explanation> findById(@PathVariable("id") Long id) {
        Optional<Explanation> result = this.explanationRepository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<Explanation>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Explanation>(HttpStatus.NOT_FOUND);
        }

    }
}
