package com.footsim.controller;


import com.footsim.domain.dto.SeasonDTO;
import com.footsim.service.impl.SeasonServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/season")
public class SeasonController {
    private final SeasonServiceImpl seasonService;

    public SeasonController(SeasonServiceImpl ssi) {
        this.seasonService = ssi;
    }

    @GetMapping(value = "/get/{id}")
    public SeasonDTO getSeason(@PathVariable Long id) {
        return seasonService.findOne(id);
    }

    @GetMapping(value = "/get_all")
    public List<SeasonDTO> getSeasons() {
        return seasonService.findAll();
    }

    @PostMapping(value = "/add")
    public SeasonDTO addSeason(@RequestBody SeasonDTO season) {
        return seasonService.save(season);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteSeason(@PathVariable Long id) {
        seasonService.delete(id);
    }

    @PutMapping(value = "/update")
    public SeasonDTO updateSeason(@RequestBody SeasonDTO season) {
        return seasonService.update(season);
    }
}
