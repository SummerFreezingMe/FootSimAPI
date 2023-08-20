package com.footsim.controller;

import com.footsim.domain.dto.FoulDTO;
import com.footsim.service.impl.FoulServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@Tag(name = "Фолы", description = "Методы, взаимодействующие с фолами")
@RequestMapping(value = "/fouls")
public class FoulController {
    private final FoulServiceImpl foulService;

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

    @PutMapping(value = "/update")
    @Operation(summary = "Изменяем существующий фол")
    public FoulDTO updateFoul(@RequestBody FoulDTO foul) {
        return foulService.update(foul);
    }

    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем все фолы")
    public List<FoulDTO> displayAllFouls() {
        return foulService.findAll();
    }
}
