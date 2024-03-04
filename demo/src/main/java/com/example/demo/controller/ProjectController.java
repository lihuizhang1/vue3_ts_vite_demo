package com.example.demo.controller;

import com.example.demo.model.Project;
import com.example.demo.model.ProjectSelectDemo;
import com.example.demo.service.ProjectService;
import jakarta.annotation.Resource;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @ResponseBody
    @PostMapping("/getProjectLists")
    public List<Project> getProjectLists(@RequestBody ProjectSelectDemo demo) {
        return projectService.getProjectLists(demo);
    }

    @ResponseBody
    @PostMapping("/create")
    public Project create(@RequestBody Project project){
        return projectService.create(project);
    }

    @ResponseBody
    @PostMapping("/update")
    public int update(@RequestBody Project project) {
        return projectService.update(project);
    }

    @ResponseBody
    @PostMapping("/delete")
    public List<Project> delete(@RequestBody ProjectSelectDemo demo) {
        projectService.delete(demo.getIds());
        return projectService.getProjectLists(demo);
    }
}
