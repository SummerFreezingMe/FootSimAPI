package com.footsim.controller;

import com.footsim.domain.dto.CoachDTO;
import com.footsim.domain.dto.TransferDTO;
import com.footsim.service.CoachService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@Tag(name = "Тренеры", description = "Методы, взаимодействующие с тренерами")
@RequestMapping("/api/coaches")
public class CoachController {
    private final CoachService coachService;


    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Изменяем существующих тренеров")
    public CoachDTO updateCoach(@RequestBody CoachDTO coach) {
        return coachService.update(coach);
    }

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Получаем экземпляр тренера по его Id")
    public CoachDTO getCoach(@PathVariable Long id) {
        return coachService.findOne(id);
    }

    @PostMapping(value = "/add")
    @Operation(summary = "Добавляем нового тренера")
    public CoachDTO addCoach(@RequestBody CoachDTO coach) {
        return coachService.save(coach);
    }


    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удаляем экземпляр тренера по его Id")
    public void deleteCoach(@PathVariable Long id) {
        coachService.delete(id);
    }

    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем всех тренеров")
    public List<CoachDTO> displayAllCoaches() {
        return coachService.findAll();
    }

    @PostMapping(value = "/transfer")
    @Operation(summary = "Проводим трансфер тренера")
    public CoachDTO transferCoach(@RequestBody TransferDTO transfer) {
        return coachService.transferCoach(transfer);
    }

    @PostMapping(value = "/release")
    @Operation(summary = "Уволняем тренера из команды")
    public CoachDTO releasePlayer(@RequestBody CoachDTO coach) {
        return coachService.releaseCoach(coach);
    }
}