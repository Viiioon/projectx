package ch.zhaw.projectx.restcontroller;

import ch.zhaw.projectx.entity.Domain;
import ch.zhaw.projectx.mongo.CSVExporter;
import ch.zhaw.projectx.mongo.MongoDataAccess;
import ch.zhaw.projectx.repository.DomainRepository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/domains")
public class DomainController {

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private MongoDataAccess mongoDataAccess;

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

    @PostMapping
    public ResponseEntity<Domain> createDomain(@RequestBody Domain domain) {
        Domain domainToSave = new Domain();
        domainToSave.setName(domain.getName());
        domainToSave.setAreaOfStudy(domain.getAreaOfStudy());
        try {
            Domain _domain = domainRepository.save(domainToSave);
            return new ResponseEntity<>(_domain, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exportNodes")
    public ResponseEntity<String> exportNodes() {
        ArrayList<Document> dataList = mongoDataAccess.retrieveNodes();

        String filePath = System.getProperty("user.home") + "/OneDrive/Dokumente/ZHAW/Module/2023FS/Data Management/Project X/WP4/Data/nodes.csv";

        CSVExporter csvExporter = new CSVExporter();
        csvExporter.exportNodesToCSV(dataList, filePath);

        return ResponseEntity.ok("CSV export of nodes complete");
    }

    @GetMapping("/exportEdges")
    public ResponseEntity<String> exportEdges() {
        ArrayList<Document> dataList = mongoDataAccess.retrieveEdges();

        String filePath = System.getProperty("user.home") + "/OneDrive/Dokumente/ZHAW/Module/2023FS/Data Management/Project X/WP4/Data/edges.csv";

        CSVExporter csvExporter = new CSVExporter();
        csvExporter.exportEdgesToCSV(dataList, filePath);

        return ResponseEntity.ok("CSV export of edges complete");
    }
}