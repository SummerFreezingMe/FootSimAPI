package com.footsim.controller;

import com.footsim.domain.dto.LeagueDTO;
import com.footsim.service.impl.LeagueServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/league")
public class LeagueController {
    private final LeagueServiceImpl leagueService;

    public LeagueController(LeagueServiceImpl leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping(value = "/get/{id}")
     public LeagueDTO getLeague(@PathVariable Long id) {
        return leagueService.findOne(id).orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping(value = "/update")
    public LeagueDTO updateLeague(@RequestBody LeagueDTO league) {
        return leagueService.update(league);
    }

    @PostMapping(value = "/add")
    public LeagueDTO addLeague(@RequestBody LeagueDTO league) {
        return leagueService.save(league);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteLeague(@PathVariable Long id) {
        leagueService.delete(id);
    }
    @GetMapping(value = "/get_all")
    public List<LeagueDTO> displayAllLeagues() {
        return leagueService.findAll();
    }

}
