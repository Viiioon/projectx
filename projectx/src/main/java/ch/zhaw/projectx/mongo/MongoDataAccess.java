package ch.zhaw.projectx.mongo;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MongoDataAccess {

    public ArrayList<Document> retrieveNodes() {
        MongoConnector connector = new MongoConnector();
        connector.connect();

        String domainCollection = "domain";
        MongoCollection<Document> collection = connector.getDatabase().getCollection(domainCollection);

        AggregateIterable<Document> aggregationNodes = collection.aggregate(
                Arrays.asList(new Document("$project",
                        new Document("_id", 0L)
                                .append("id", 1L)
                                .append("name", 1L)))

        );

        ArrayList<Document> nodes = aggregationNodes.into(new ArrayList<>());

        connector.disconnect();

        return nodes;
    }
}