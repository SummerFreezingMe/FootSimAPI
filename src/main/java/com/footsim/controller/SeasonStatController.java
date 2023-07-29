package com.footsim.controller;

import com.footsim.domain.dto.SeasonStatDTO;
import com.footsim.service.impl.SeasonStatServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Cтатистика сезонов", description = "Методы, взаимодействующие со статистикой сезонов")
@RequestMapping(value = "/season_stats")
public class SeasonStatController {
    private final SeasonStatServiceImpl seasonService;

    public SeasonStatController(SeasonStatServiceImpl seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Получаем экземпляр сезона по его Id")
    public SeasonStatDTO getSeason(@PathVariable Long id) {
        return seasonService.findOne(id);
    }

    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем все сезоны")
    public List<SeasonStatDTO> getSeasons() {
        return seasonService.findAll();
    }

    @PostMapping(value = "/add")
    @Operation(summary = "Добавляем новый сезон")
    public SeasonStatDTO addSeason(@RequestBody SeasonStatDTO season) {
        return seasonService.save(season);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удаление экземпляра сезона по его Id")
    public void deleteSeason(@PathVariable Long id) {
        seasonService.delete(id);
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Изменяем существующие сезоны")
    public SeasonStatDTO updateSeason(@RequestBody SeasonStatDTO season) {
        return seasonService.update(season);
    }

    @GetMapping(value = "/init_season/{id}")
    @Operation(summary = "Инициализируем сезон лиги, где у каждой команды лиги 0 очков")
    public ResponseEntity<?> initializeSeason(@PathVariable Long id) {
        return seasonService.initializeSeason(id);
    }
}
