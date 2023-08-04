package com.footsim.controller;

import com.footsim.domain.dto.TeamDTO;
import com.footsim.service.impl.TeamServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name = "Клубы", description = "Методы, взаимодействующие с футбольными клубами")
@RequestMapping(value = "/teams")
public class TeamController {

    private final TeamServiceImpl teamService;

    public TeamController(TeamServiceImpl tsi) {
        this.teamService = tsi;
    }

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Получаем экземпляр команды по его Id")
    public TeamDTO getTeam(@PathVariable Long id) {
        return teamService.findOne(id);
    }

    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем все команды")
    public List<TeamDTO> getTeams() {
        return teamService.findAll();
    }

    @PostMapping(value = "/add")
    @Operation(summary = "Добавляем новую команду")
    public TeamDTO addTeam(@RequestBody TeamDTO team) {
        return teamService.save(team);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удаление экземпляра команды по его Id")
    public void deleteTeam(@PathVariable Long id) {
        teamService.delete(id);
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Изменяем существующую команду")
    public TeamDTO updateTeam(@RequestBody TeamDTO team) {
        return teamService.update(team);
    }
}
