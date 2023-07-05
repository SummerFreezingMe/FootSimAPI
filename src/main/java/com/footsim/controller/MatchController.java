package com.footsim.controller;

import com.footsim.domain.dto.MatchDTO;
import com.footsim.service.impl.MatchServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/match")
public class MatchController {
    private final MatchServiceImpl matchService;

    public MatchController(MatchServiceImpl msi) {
        this.matchService = msi;
    }

    @PostMapping(value = "/add")
    public MatchDTO addMatch(@RequestBody MatchDTO ticket) {
        return matchService.save(ticket);
    }

    @GetMapping(value = "/get/{id}")
    public MatchDTO getMatch(@PathVariable Long id) {
        return matchService.findOne(id).orElse(null);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteMatch(@PathVariable Long id) {
        matchService.delete(id);
    }

    @PutMapping(value = "/update")
    public MatchDTO updateMatch(@RequestBody MatchDTO match) {
        return matchService.update(match);
    }
    @GetMapping(value = "/get_all")
    public List<MatchDTO> displayAllMatches() {
        return matchService.findAll();
    }

}

