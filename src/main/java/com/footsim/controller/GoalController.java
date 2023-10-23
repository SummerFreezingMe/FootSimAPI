package com.footsim.controller;

import com.footsim.domain.dto.GoalDTO;
import com.footsim.domain.dto.TopActionsDTO;
import com.footsim.service.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@Tag(name = "Голы", description = "Методы, взаимодействующие с голами")
@RequestMapping(value = "/api/goals")
public class GoalController {
    private final GoalService goalService;

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Получаем экземпляр гола по его Id")
    public GoalDTO getGoal(@PathVariable Long id) {
        return goalService.findOne(id);
    }
    @PostMapping(value = "/add")
    @Operation(summary = "Добавляем новый билет")
    public GoalDTO addGoal(@RequestBody GoalDTO ticket) {
        return goalService.save(ticket);
    }


    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удаляем экземпляра гола по его Id")
    public void deleteGoal(@PathVariable Long id) {
        goalService.delete(id);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Изменяем существующий гол")
    public GoalDTO updateGoal(@RequestBody GoalDTO goal,
                              @PathVariable("id") Long id) {
        goal.setId(id);
        return goalService.partialUpdate(goal).orElseThrow(
                () -> new EntityNotFoundException("Goal not found with id:" + id)
        );
    }
    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем все голы")
    public List<GoalDTO> displayAllGoals() {
        return goalService.findAll();
    }

    @GetMapping(value = "/top_scorers/{id}")
    @Operation(summary = "Отображаем список бомбардиров определённого сезона")
    public List<TopActionsDTO> displayTopScorers(@PathVariable Long id) {
        return goalService.displayTopScorers(id);
    }

    @GetMapping(value = "/top_assistants/{id}")
    @Operation(summary = "Отображаем список ассистентов определённого сезона")
    public List<TopActionsDTO> displayTopAssistants(@PathVariable Long id) {
        return goalService.displayTopAssistants(id);
    }

}
