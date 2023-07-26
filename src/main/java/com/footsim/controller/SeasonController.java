package com.footsim.controller;


import com.footsim.domain.dto.SeasonDTO;
import com.footsim.service.impl.SeasonServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Сезоны", description = "Методы, взаимодействующие с сезонами чемпионатов")
@RequestMapping(value = "/season")
public class SeasonController {
    private final SeasonServiceImpl seasonService;

    public SeasonController(SeasonServiceImpl ssi) {
        this.seasonService = ssi;
    }

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Получаем экземпляр сезона по его Id")
    public SeasonDTO getSeason(@PathVariable Long id) {
        return seasonService.findOne(id);
    }

    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем все сезоны")
    public List<SeasonDTO> getSeasons() {
        return seasonService.findAll();
    }

    @PostMapping(value = "/add")
    @Operation(summary = "Добавляем новый сезон")
    public SeasonDTO addSeason(@RequestBody SeasonDTO season) {
        return seasonService.save(season);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удаление экземпляра сезона по его Id")
    public void deleteSeason(@PathVariable Long id) {
        seasonService.delete(id);
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Изменяем существующие сезоны")
    public SeasonDTO updateSeason(@RequestBody SeasonDTO season) {
        return seasonService.update(season);
    }

    @GetMapping(value = "/init_season/{id}/{year}")
    @Operation(summary = "Инициализируем сезон лиги, где у каждой команды лиги 0 очков")
    public void initializeSeason(@PathVariable Long id,
                                           @PathVariable Integer year) {
         seasonService.initializeSeason(id,year);
    }
}
