package com.sher.jobListing.repositories;

import com.sher.jobListing.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobPostRepo extends MongoRepository<JobPost, String> {

}
