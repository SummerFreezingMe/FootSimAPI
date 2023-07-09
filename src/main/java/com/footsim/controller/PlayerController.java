package com.footsim.controller;


import com.footsim.domain.dto.PlayerDTO;
import com.footsim.domain.dto.TransferDTO;
import com.footsim.domain.enumeration.PlayerStatus;
import com.footsim.service.impl.PlayerServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerServiceImpl playerService;

    public PlayerController(PlayerServiceImpl psi) {
        this.playerService = psi;
    }

    @PutMapping(value = "/update/{id}")
    public PlayerDTO updatePlayer(@RequestBody PlayerDTO player) {
        return playerService.update(player);
    }

    @GetMapping(value = "/get/{id}")
    public PlayerDTO getPlayer(@PathVariable Long id) {
        return playerService.findOne(id);
    }

    @PostMapping(value = "/add")
    public PlayerDTO addPlayer(@RequestBody PlayerDTO player) {
        return playerService.save(player);
    }


    @DeleteMapping(value = "/delete/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.delete(id);
    }

    @GetMapping(value = "/get_all")
    public List<PlayerDTO> displayAllPlayers() {
        return playerService.findAll();
    }

    @PostMapping(value = "/switch_status/{id}/{status}")
    public PlayerDTO switchPlayerStatus(@PathVariable Long id,
                                        @PathVariable PlayerStatus status) {
        return playerService.switchStatus(id,status);
    }
    @PostMapping(value = "/transfer")
    public PlayerDTO transferPlayer(@RequestBody TransferDTO transfer) {
        return playerService.transferPlayer(transfer);
    }
}

