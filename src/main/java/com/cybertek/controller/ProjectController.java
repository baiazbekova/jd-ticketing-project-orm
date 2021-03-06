package com.cybertek.controller;


import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")

public class ProjectController {

    private ProjectService projectService;
    private UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("managers", userService.listAllByRole("manager"));

        return "/project/create";
    }


    @PostMapping("/create")
    public String inserProject(ProjectDTO project){

        projectService.save(project);
        project.setProjectStatus(Status.OPEN);

        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectcode}")
    public String deleteProject(@PathVariable("projectcode") String projectcode){
        projectService.delete(projectcode);
        return "redirect:/project/create";
    }



    @GetMapping("/complete/{projectcode}")
    public String completeUser(@PathVariable("projectcode") String projectcode ){
        projectService.complete(projectcode);
        return "redirect:/project/complete";
    }


    @GetMapping("/update/{projectcode}")
    public String editUser(@PathVariable("projectcode") String projectcode, Model model){
        model.addAttribute("project", projectService.getByProjectCode(projectcode));
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("managers", userService.listAllByRole("manager"));

        return "/project/update";
    }

    @PostMapping("/update/{projectcode}")
    public String updateUser(@PathVariable("projectcode") String projectcode,ProjectDTO projectDTO){

        projectService.update(projectDTO);
        return "redirect:/project/create";

    }

    @GetMapping("/manager/complete")
    public String getProjectByManager(Model model){
        List<ProjectDTO> projects = projectService.listAllProjectDetails();
        model.addAttribute("projects", projects);

        return "/manager/project-status";
    }
//
//    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager){
//
//        List<ProjectDTO> list = projectService
//                .findAll()
//                .stream()
//                .filter(x -> x.getAssignedManager().equals(manager))
//                .map(x -> {
//
//                    List<TaskDTO> taskList = taskService.findTasksByManager(manager);
//
//                    int completeCount = (int) taskList.stream().filter(t -> t.getProject().equals(x) &&  t.getTaskStatus() == Status.COMPLETE).count();
//                    int incompleteCount = (int) taskList.stream().filter(t -> t.getProject().equals(x) &&  t.getTaskStatus() != Status.COMPLETE).count();
//
//                    return new ProjectDTO(x.getProjectName(), x.getProjectCode(), userService.findByID(x.getAssignedManager().getUsername()),x.getStartDate(),
//                            x.getEndDate(), x.getProjectDetail(), x.getProjectStatus(), completeCount, incompleteCount);
//
//                }).collect(Collectors.toList());
//
//        return list;
//
//    }



}
