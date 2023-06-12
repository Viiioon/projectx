package ch.zhaw.projectx.serializer;

import ch.zhaw.projectx.entity.Explanation;
import ch.zhaw.projectx.entity.Theorem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

public class TheoremSerializer extends JsonSerializer<Theorem> {

    @Override
    public void serialize(Theorem theorem, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("id", theorem.getId());
        objectNode.put("parentStatement", theorem.getParentStatement());
        objectNode.put("proof", theorem.getProof());

        ArrayNode explanationsArray = objectNode.putArray("explanations");
        for (Explanation explanation : theorem.getExplanations()) {
            ObjectNode explanationNode = explanationsArray.addObject();
            explanationNode.put("id", explanation.getId());
            explanationNode.put("dateExplained", explanation.getDateExplained().toString());
        }

        jsonGenerator.writeObject(objectNode);
    }
}