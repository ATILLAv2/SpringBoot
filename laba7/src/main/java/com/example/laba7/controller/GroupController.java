package com.example.laba7.controller;

import com.example.laba7.dao.GroupRepository;
import com.example.laba7.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("groups", groupRepository.findAll());
        return "list-groups";
    }

    @GetMapping("/addGroupForm")
    public String showAddForm(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        return "add-group-form";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupRepository.save(group);
        return "redirect:/groups/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("groupId") Long id, Model model) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + id));
        model.addAttribute("group", group);
        return "add-group-form";
    }

    @GetMapping("/deleteGroup")
    public String deleteGroup(@RequestParam("groupId") Long id) {
        groupRepository.deleteById(id);
        return "redirect:/groups/list";
    }
}