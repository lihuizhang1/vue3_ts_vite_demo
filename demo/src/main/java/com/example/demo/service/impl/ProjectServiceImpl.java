package com.example.demo.service.impl;

import com.example.demo.mapper.ProjectMapper;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectSelectDemo;
import com.example.demo.service.ProjectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;
    @Override
    public Project create(Project project) {
        try {
            return projectMapper.createProject(project);
        }catch (Exception e){
            return project;
        }
    }

    @Override
    public int update(Project project) {
        return projectMapper.updateProject(project);
    }

    @Override
    public List<Project> getProjectLists(ProjectSelectDemo demo) {
        return projectMapper.getProjectLists(demo);
    }

    @Override
    public int delete(String ids) {
        return projectMapper.deleteProject(ids);
    }
}
