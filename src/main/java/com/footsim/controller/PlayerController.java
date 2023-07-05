package com.footsim.controller;


import com.footsim.domain.dto.PlayerDTO;
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

}

