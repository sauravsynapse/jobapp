package com.jobex.jobapp.job.impl;

import com.jobex.jobapp.job.Job;
import com.jobex.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        if(job.getId()==null)
            job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for(Job job : jobs) {
            if (job.getId() == id) {
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        for(Job job : jobs) {
            if (job.getId() == id) {
                jobs.remove(job);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedjob) {
        for(Job job : jobs) {
            if (job.getId() == id) {
                job.setDescription(updatedjob.getDescription());
                job.setLocation(updatedjob.getLocation());
                job.setTitle(updatedjob.getTitle());
                job.setMaxSalary(updatedjob.getMaxSalary());
                job.setMinSalary(updatedjob.getMinSalary());
                job.setLocation(updatedjob.getLocation());
                return true;
            }
        }
        return false;
    }
}
