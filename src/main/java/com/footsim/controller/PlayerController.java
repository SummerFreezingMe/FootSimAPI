package com.footsim.controller;


import com.footsim.domain.dto.PlayerDTO;
import com.footsim.domain.dto.TransferDTO;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.service.impl.PlayerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Игроки", description = "Методы, взаимодействующие с игроками")
@RequestMapping("/player")
public class PlayerController {
    private final PlayerServiceImpl playerService;

    public PlayerController(PlayerServiceImpl psi) {
        this.playerService = psi;
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Изменяем существующих игроков")
    public PlayerDTO updatePlayer(@RequestBody PlayerDTO player) {
        return playerService.update(player);
    }

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Получаем экземпляр игрока по его Id")
    public PlayerDTO getPlayer(@PathVariable Long id) {
        return playerService.findOne(id);
    }

    @PostMapping(value = "/add")
    @Operation(summary = "Добавляем нового игрока")
    public PlayerDTO addPlayer(@RequestBody PlayerDTO player) {
        return playerService.save(player);
    }


    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удаляем экземпляра игрока по его Id")
    public void deletePlayer(@PathVariable Long id) {
        playerService.delete(id);
    }

    @GetMapping(value = "/get_all")
    @Operation(summary = "Отображаем всех игроков")
    public List<PlayerDTO> displayAllPlayers() {
        return playerService.findAll();
    }

    @GetMapping(value = "/switch_status/{id}/{status}")
    @Operation(summary = "Меняем статус игрока по его Id на указанный")
    public PlayerDTO switchPlayerStatus(@PathVariable Long id,
                                        @PathVariable PlayerStatus status) {
        return playerService.switchStatus(id,status);
    }
    @PostMapping(value = "/transfer")
    @Operation(summary = "Проводим трансфер игрока")
    public PlayerDTO transferPlayer(@RequestBody TransferDTO transfer) {
        return playerService.transferPlayer(transfer);
    }
}

