package ch.zhaw.projectx.service;

import ch.zhaw.projectx.dto.NaturalProofsDataset;
import ch.zhaw.projectx.dto.TheoremInfo;
import ch.zhaw.projectx.entity.Theorem;
import ch.zhaw.projectx.repository.BeliefRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class DataLoader {

    private final ObjectMapper objectMapper;
    private final BeliefRepository beliefRepository;

    public DataLoader(ObjectMapper objectMapper, BeliefRepository beliefRepository) {
        this.objectMapper = objectMapper;
        this.beliefRepository = beliefRepository;
    }

    @PostConstruct
    public void loadData() {
        /*
        Source:
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
            for (TheoremInfo theoremInfo : theorems) {
                Theorem theorem = new Theorem();
                theorem.setParentStatement(theoremInfo.getTitle());
                // Setting the complexity level here as mockaroo does not generate more than 1k records for free accounts
                theorem.setComplexityLevel(getRandomComplexityLevel());
                beliefRepository.save(theorem);
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
}