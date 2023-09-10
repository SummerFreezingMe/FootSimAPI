package com.footsim.controller;


import com.footsim.domain.dto.SeasonDTO;
import com.footsim.service.impl.SeasonServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@Tag(name = "Сезоны", description = "Методы, взаимодействующие с сезонами чемпионатов")
@RequestMapping(value = "/api/seasons")
public class SeasonController {
    private final SeasonServiceImpl seasonService;

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

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Изменяем существующие сезоны")
    public SeasonDTO updateSeason(@RequestBody SeasonDTO season,
                                  @PathVariable("id") Long id) {
        season.setId(id);
        return seasonService.partialUpdate(season).orElseThrow(
                () -> new EntityNotFoundException("Season not found with id:" + id)
        );
    }

}
