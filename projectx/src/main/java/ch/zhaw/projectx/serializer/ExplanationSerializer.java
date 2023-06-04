package ch.zhaw.projectx.serializer;

import ch.zhaw.projectx.entity.Explanation;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
public class ExplanationSerializer extends JsonSerializer<Explanation> {

    @Override
    public void serialize(Explanation explanation, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("id",explanation.getId());
        objectNode.put("dateExplained",explanation.getDateExplained().toString());

        ObjectNode beliefNode = objectNode.putObject("belief");
        beliefNode.put("id", explanation.getBelief().getId());
        beliefNode.put("parentStatement", explanation.getBelief().getParentStatement());

        ObjectNode domainNode = objectNode.putObject("domain");
        domainNode.put("id", explanation.getDomain().getId());
        domainNode.put("name", explanation.getDomain().getName());
        domainNode.put("areaOfStudy", explanation.getDomain().getAreaOfStudy());

        jsonGenerator.writeObject(objectNode);
    }

    @Override
    public boolean isUnwrappingSerializer() {
        return true;
    }
}