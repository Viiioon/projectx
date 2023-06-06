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

    public ArrayList<Document> retrieveEdges() {
        MongoConnector connector = new MongoConnector();
        connector.connect();

        String domainCollection = "explanation";
        MongoCollection<Document> collection = connector.getDatabase().getCollection(domainCollection);

        AggregateIterable<Document> aggregationEdges = collection.aggregate(
                Arrays.asList(new Document("$lookup",
                                new Document("from", "explanation")
                                        .append("localField", "belief.id")
                                        .append("foreignField", "belief.id")
                                        .append("as", "matching_explanations")),
                        new Document("$unwind", "$matching_explanations"),
                        new Document("$match",
                                new Document("$expr",
                                        new Document("$lt", Arrays.asList("$domain.id", "$matching_explanations.domain.id")))),
                        new Document("$project",
                                new Document("_id", 0L)
                                        .append("smaller_domain_id", "$domain.id")
                                        .append("larger_domain_id", "$matching_explanations.domain.id")))
        );

        ArrayList<Document> edges = aggregationEdges.into(new ArrayList<>());

        connector.disconnect();

        return edges;
    }
}