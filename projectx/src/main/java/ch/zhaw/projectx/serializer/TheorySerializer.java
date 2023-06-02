package ch.zhaw.projectx.serializer;

import ch.zhaw.projectx.entity.Belief;
import ch.zhaw.projectx.entity.Theorem;
import ch.zhaw.projectx.entity.Theory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

public class TheorySerializer extends JsonSerializer<Theory> {

    @Override
    public void serialize(Theory theory, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("id", theory.getId());
        objectNode.put("theory_statement", theory.getTheoryStatement());

        // Serialize the beliefs list with the parentStatement field included
        ArrayNode beliefsNode = objectNode.putArray("beliefs");
        for (Belief belief : theory.getBeliefs()) {
            ObjectNode beliefNode = beliefsNode.addObject();
            beliefNode.put("id", belief.getId());
            beliefNode.put("parentStatement", belief.getParentStatement());
            // Add any other fields you want to include
        }

        jsonGenerator.writeObject(objectNode);
    }
}