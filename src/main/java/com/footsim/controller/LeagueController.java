package com.footsim.controller;

import com.footsim.domain.dto.LeagueDTO;
import com.footsim.service.impl.LeagueServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name = "Лиги", description = "Методы, взаимодействующие с лигами/чемпионатами")
@RequestMapping(value = "/leagues")
public class LeagueController {
    private final LeagueServiceImpl leagueService;

    public LeagueController(LeagueServiceImpl leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Получаем экземпляр чемпионата по его Id")
     public LeagueDTO getLeague(@PathVariable Long id) {
        return leagueService.findOne(id);
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Изменяем существующую лигу")
    public LeagueDTO updateLeague(@RequestBody LeagueDTO league) {
        return leagueService.update(league);
    }

    @PostMapping(value = "/add")
    @Operation(summary = "Добавляем новую лигу")
    public LeagueDTO addLeague(@RequestBody LeagueDTO league) {
        return leagueService.save(league);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удаляем экземпляра лиги по его Id")
    public void deleteLeague(@PathVariable Long id) {
        leagueService.delete(id);
    }
    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем все лиги")
    public List<LeagueDTO> displayAllLeagues() {
        return leagueService.findAll();
    }

}
