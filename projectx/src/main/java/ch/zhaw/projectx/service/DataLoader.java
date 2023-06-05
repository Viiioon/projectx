package ch.zhaw.projectx.service;

import ch.zhaw.projectx.dto.NaturalProofsDataset;
import ch.zhaw.projectx.dto.Proof;
import ch.zhaw.projectx.dto.TheoremInfo;
import ch.zhaw.projectx.entity.Domain;
import ch.zhaw.projectx.entity.Theorem;
import ch.zhaw.projectx.repository.BeliefRepository;
import ch.zhaw.projectx.repository.DomainRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DataLoader {

    private final ObjectMapper objectMapper;
    private final BeliefRepository beliefRepository;
    private final DomainRepository domainRepository;

    public DataLoader(ObjectMapper objectMapper, BeliefRepository beliefRepository, DomainRepository domainRepository) {
        this.objectMapper = objectMapper;
        this.beliefRepository = beliefRepository;
        this.domainRepository = domainRepository;
    }

    @PostConstruct
    public void loadData() {
        /*
        Data Source:
        https://github.com/wellecks/naturalproofs
         */
        if(!beliefRepository.areBeliefsAlreadyInserted()) {
            try (InputStream inputStream = getClass().getResourceAsStream("/data/naturalproofs_proofwiki.json")) {
                NaturalProofsDataset naturalProofsDataset = objectMapper.readValue(inputStream, NaturalProofsDataset.class);
                List<TheoremInfo> theorems = naturalProofsDataset.getDataset().getTheorems();

                for (TheoremInfo theoremInfo : theorems) {
                    Theorem theorem = new Theorem();
                    theorem.setParentStatement(theoremInfo.getTitle());
                    StringBuilder concatenatedProofContent = new StringBuilder();
                    for (Proof proof : theoremInfo.getProofs()) {
                        concatenatedProofContent.append("Proof:\n");
                        concatenatedProofContent.append(String.join("\n", proof.getContents()));
                    }
                    theorem.setProof(concatenatedProofContent.toString());
                    beliefRepository.save(theorem);

                    extractDomainInfoFromData(theoremInfo);
                }
            } catch (JsonProcessingException e) {
                // Handle JSON processing exceptions
                System.err.println("Failed to process JSON:");
                e.printStackTrace();
            } catch (IOException e) {
                // Handle I/O exceptions
                System.err.println("Failed to read file:");
                e.printStackTrace();
            } catch (Exception e) {
                // Handle any other exceptions
                System.err.println("An error occurred:");
                e.printStackTrace();
            }
        }
    }

    public void extractDomainInfoFromData(TheoremInfo data) {
        Domain domain = new Domain();
        // Apparently not every theorem has a top level category assigned, thus this check
        if (!data.getToplevel_categories().isEmpty()) {
            // Only the first element of the top level categories will be extracted as its enough reasonable
            domain.setAreaOfStudy(data.getToplevel_categories().get(0));
        } else {
            domain.setAreaOfStudy("undefined");
        }
        for (String category:data.getCategories()) {
            // This check is needed to avoid any duplicate categories in the db
            if(!domainRepository.existsCategory(category)) {
                domain.setName(category);
                domainRepository.save(domain);
            }
        }
    }
}