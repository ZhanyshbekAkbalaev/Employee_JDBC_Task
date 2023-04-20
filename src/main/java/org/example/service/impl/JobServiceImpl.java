package org.example.service.impl;

import org.example.dao.JobDao;
import org.example.dao.impl.JobDaoImpl;
import org.example.model.Job;
import org.example.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    JobDao dao = new JobDaoImpl();

    @Override
    public void createJobTable() {
        dao.createJobTable();
    }

    @Override
    public void addJob(Job job) {
        dao.addJob(job);
    }

    @Override
    public Job getJobById(Long jobId) {
        return dao.getJobById(jobId);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return dao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return dao.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
        dao.deleteDescriptionColumn();
    }
}
