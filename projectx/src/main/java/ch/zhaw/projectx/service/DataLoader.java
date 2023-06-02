package ch.zhaw.projectx.service;

import ch.zhaw.projectx.dto.NaturalProofsDataset;
import ch.zhaw.projectx.dto.TheoremInfo;
import ch.zhaw.projectx.entity.Domain;
import ch.zhaw.projectx.entity.Theorem;
import ch.zhaw.projectx.model.DomainInfo;
import ch.zhaw.projectx.repository.BeliefRepository;
import ch.zhaw.projectx.repository.DomainRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

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
        @inproceedings{welleck2021naturalproofs,
        title={NaturalProofs: Mathematical Theorem Proving in Natural Language},
        author={Sean Welleck and Jiacheng Liu and Ronan Le Bras and Hannaneh Hajishirzi and Yejin Choi and Kyunghyun Cho},
        booktitle={Thirty-fifth Conference on Neural Information Processing Systems Datasets and Benchmarks Track (Round 1)},
        year={2021},
        url={https://openreview.net/forum?id=Jvxa8adr3iY}
        }
        from:
        https://github.com/wellecks/naturalproofs
         */
        try (InputStream inputStream = getClass().getResourceAsStream("/data/naturalproofs_proofwiki.json")) {
            NaturalProofsDataset naturalProofsDataset = objectMapper.readValue(inputStream, NaturalProofsDataset.class);
            List<TheoremInfo> theorems = naturalProofsDataset.getDataset().getTheorems();
            ArrayList<DomainInfo> domains = new ArrayList<DomainInfo>();

            for (TheoremInfo theoremInfo : theorems) {
                Theorem theorem = new Theorem();
                theorem.setParentStatement(theoremInfo.getTitle());
                theorem.setComplexityLevel(getRandomComplexityLevel());
                beliefRepository.save(theorem);

                extractDomainInfoFromData(theoremInfo,domains);
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

    public String getRandomComplexityLevel() {
        String[] complexityLevels = {"Beginner", "Intermediate", "Advanced", "Expert"};
        Random random = new Random();
        int randomIndex = random.nextInt(complexityLevels.length);
        return complexityLevels[randomIndex];
    }

    public void extractDomainInfoFromData(TheoremInfo data, ArrayList<DomainInfo> domainInfoList) {
        Domain domain = new Domain();
        // Apparently not every theorem has a top level category assigned, thus this check
        if (!data.getToplevel_categories().isEmpty()) {
            // Only the first element of the toplevelcategories will be extracted as its enough reasonable
            domain.setAreaOfStudy(data.getToplevel_categories().get(0));
        } else {
            domain.setAreaOfStudy("undefined");
        }
        for (String category:data.getCategories()) {
            domain.setName(category);
            // This check is needed to avoid any duplicate categories in the db
            if(!domainRepository.existsCategory(category)) {
                domainRepository.save(domain);
            }
        }
    }
}