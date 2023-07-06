package com.footsim.controller;

import com.footsim.domain.dto.GoalDTO;
import com.footsim.service.impl.GoalServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goal")
public class GoalController {
    private final GoalServiceImpl goalService;

    public GoalController(GoalServiceImpl gsi) {
        this.goalService = gsi;
    }

    @GetMapping(value = "/get/{id}")
    public GoalDTO getGoal(@PathVariable Long id) {
        return goalService.findOne(id).orElseThrow(EntityNotFoundException::new);
    }
    @PostMapping(value = "/add")
    public GoalDTO addGoal(@RequestBody GoalDTO ticket) {
        return goalService.save(ticket);
    }


    @DeleteMapping(value = "/delete/{id}")
    public void deleteGoal(@PathVariable Long id) {
        goalService.delete(id);
    }

    @PutMapping(value = "/update")
    public GoalDTO updateGoal(@RequestBody GoalDTO goal) {
        return goalService.update(goal);
    }
    @GetMapping(value = "/get_all")
    public List<GoalDTO> displayAllGoals() {
        return goalService.findAll();
    }

}
