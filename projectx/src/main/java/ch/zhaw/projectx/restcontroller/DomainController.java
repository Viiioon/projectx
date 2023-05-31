package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entity.Domain;
import ch.zhaw.projectx.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/domains")
public class DomainController {

    @Autowired
    private DomainRepository domainRepository;

    @GetMapping
    public ResponseEntity<List<Domain>> findAll() {
        List<Domain> result = this.domainRepository.findAll();

        if (!result.isEmpty()) {
            return new ResponseEntity<List<Domain>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Domain>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domain> findById(@PathVariable("id") Long id) {
        Optional<Domain> result = this.domainRepository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<Domain>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Domain>(HttpStatus.NOT_FOUND);
        }

    }
}