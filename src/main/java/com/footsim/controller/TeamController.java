package com.footsim.controller;

import com.footsim.domain.dto.TeamDTO;
import com.footsim.service.impl.TeamServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/team")
public class TeamController {

    private final TeamServiceImpl teamService;

    public TeamController(TeamServiceImpl tsi) {
        this.teamService = tsi;
    }

    @GetMapping(value = "/get/{id}")
    public TeamDTO getTeam(@PathVariable Long id) {
        return teamService.findOne(id);
    }

    @GetMapping(value = "/get_all")
    public List<TeamDTO> getTeams() {
        return teamService.findAll();
    }

    @PostMapping(value = "/add")
    public TeamDTO addTeam(@RequestBody TeamDTO team) {
        return teamService.save(team);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.delete(id);
    }

    @PutMapping(value = "/update")
    public TeamDTO updateTeam(@RequestBody TeamDTO team) {
        return teamService.update(team);
    }
}
