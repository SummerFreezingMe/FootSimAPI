package com.footsim.controller;

import com.footsim.domain.dto.ClubDTO;
import com.footsim.service.impl.ClubServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@Tag(name = "Клубы", description = "Методы, взаимодействующие с футбольными клубами")
@RequestMapping(value = "/clubs")
public class ClubController {

    private final ClubServiceImpl clubService;

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Получаем экземпляр команды по его Id")
    public ClubDTO getClub(@PathVariable Long id) {
        return clubService.findOne(id);
    }

    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем все команды")
    public List<ClubDTO> getClubs() {
        return clubService.findAll();
    }

    @PostMapping(value = "/add")
    @Operation(summary = "Добавляем новую команду")
    public ClubDTO addClub(@RequestBody ClubDTO club) {
        return clubService.save(club);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удаление экземпляра команды по его Id")
    public void deleteClub(@PathVariable Long id) {
        clubService.delete(id);
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Изменяем существующую команду")
    public ClubDTO updateClub(@RequestBody ClubDTO club) {
        return clubService.update(club);
    }
}