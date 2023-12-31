package com.footsim.controller;

import com.footsim.domain.dto.MatchDTO;
import com.footsim.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@Tag(name = "Матчи", description = "Методы, взаимодействующие с матчами")
@RequestMapping(value = "/api/matches")
public class MatchController {
    private final MatchService matchService;

    @PostMapping(value = "/add")
    @Operation(summary = "Добавляем новый матч")
    public MatchDTO addMatch(@RequestBody MatchDTO ticket) {
        return matchService.save(ticket);
    }

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Получаем экземпляр матча по его Id")
    public MatchDTO getMatch(@PathVariable Long id) {
        return matchService.findOne(id).orElse(null);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удаляем экземпляра матча по его Id")
    public void deleteMatch(@PathVariable Long id) {
        matchService.delete(id);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Изменяем существующий матч")
    public MatchDTO updateMatch(@RequestBody MatchDTO match,
                                @PathVariable("id") Long id) {
        match.setId(id);
        return matchService.partialUpdate(match).orElseThrow(
                () -> new EntityNotFoundException("Match not found with id:" + id)
        );
    }
    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем все матчи")
    public List<MatchDTO> displayAllMatches() {
        return matchService.findAll();
    }

    @GetMapping(value = "/simulate/{id}")
    @Operation(summary = "Проводим симуляцию матча по его Id")
    public MatchDTO simulateMatch(@PathVariable Long id) {
        return matchService.simulateMatch(id);
    }

}

