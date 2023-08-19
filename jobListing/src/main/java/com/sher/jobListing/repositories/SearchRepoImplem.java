package com.sher.jobListing.repositories;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sher.jobListing.model.JobPost;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SearchRepoImplem implements SearchRepo {

    @Autowired
    MongoClient client;

//        return MongoClientSettings.builder()
//                .retryWrites(true)
//                .applyToConnectionPoolSettings(poolSettings -> poolSettings
//            .minSize(5)
//            .maxSize(300)
//                        .maxConnectionIdleTime(0, TimeUnit.MILLISECONDS))
//            .applyToSocketSettings(socketSettings -> socketSettings
//            .connectTimeout(1, TimeUnit.MINUTES)
//            .readTimeout(1, TimeUnit.MINUTES))
//            .build();
//} private MongoClientSettings getMongoClientSettings() {


    @Autowired
    MongoConverter converter;

    @Override
    public List<JobPost> findByText(String text) {
        final List<JobPost> jobPostList = new ArrayList<>();
        MongoDatabase database = client.getDatabase("joblisting");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("text",
                                new Document("query", text)
                                .append("path", Arrays.asList("techs","desc","profile")))),
                                new Document("$sort",
                                new Document("exp", 1L)),
                                new Document("$limit", 2L)));

        result.forEach(doc -> jobPostList.add(converter.read(JobPost.class, doc)));
        return jobPostList;
    }
}
