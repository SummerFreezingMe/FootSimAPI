package com.footsim.controller;

import com.footsim.domain.dto.FoulDTO;
import com.footsim.service.FoulService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@Tag(name = "Фолы", description = "Методы, взаимодействующие с фолами")
@RequestMapping(value = "/api/fouls")
public class FoulController {
    private final FoulService foulService;

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Получаем экземпляр фола по его Id")
    public FoulDTO getFoul(@PathVariable Long id) {
        return foulService.findOne(id);
    }

    @PostMapping(value = "/add")
    @Operation(summary = "Добавляем новый фол")
    public FoulDTO addFoul(@RequestBody FoulDTO ticket) {
        return foulService.save(ticket);
    }


    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удаляем экземпляра фола по его Id")
    public void deleteFoul(@PathVariable Long id) {
        foulService.delete(id);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Изменяем существующий фол")
    public FoulDTO updateFoul(@RequestBody FoulDTO foul,
                              @PathVariable("id") Long id) {
        foul.setId(id);
        return foulService.partialUpdate(foul).orElseThrow(
                () -> new EntityNotFoundException("Foul not found with id:" + id)
        );
    }

    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем все фолы")
    public List<FoulDTO> displayAllFouls() {
        return foulService.findAll();
    }
}
