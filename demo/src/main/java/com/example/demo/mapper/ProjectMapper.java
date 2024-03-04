package com.example.demo.mapper;

import com.example.demo.model.Project;
import com.example.demo.model.ProjectSelectDemo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    Project createProject(Project project);

    int updateProject(Project project);

    List<Project> getProjectLists(ProjectSelectDemo demo);

    int deleteProject(String ids);
}
