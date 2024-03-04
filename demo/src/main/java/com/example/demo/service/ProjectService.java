package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.model.ProjectSelectDemo;

import java.util.List;

public interface ProjectService {
    public Project create(Project project);

    public int update(Project project);

    public List<Project> getProjectLists(ProjectSelectDemo demo);

    public int delete(String ids);
}
