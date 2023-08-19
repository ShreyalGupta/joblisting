package com.sher.jobListing.controller;

import com.sher.jobListing.repositories.JobPostRepo;
import com.sher.jobListing.model.JobPost;
import com.sher.jobListing.repositories.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobPostController {

    @Autowired
    JobPostRepo repo;

    @Autowired
    SearchRepo searchRepo;

    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/allPosts")
    @CrossOrigin
    public List<JobPost> getAllPosts(){
        return repo.findAll();
    }

    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<JobPost> search(@PathVariable String text){
        return searchRepo.findByText(text);
    }

    @PostMapping("/post")
    @CrossOrigin
    public JobPost addJobPost(@RequestBody JobPost jobPost){
           return repo.save(jobPost);
    }


}
