package com.sher.jobListing.repositories;

import com.sher.jobListing.model.JobPost;

import java.util.List;

public interface SearchRepo {
   List<JobPost> findByText(String text);
}
