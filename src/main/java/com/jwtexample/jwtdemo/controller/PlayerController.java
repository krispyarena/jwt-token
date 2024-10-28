package com.jwtexample.jwtdemo.controller;

import com.jwtexample.jwtdemo.model.Player;
import com.jwtexample.jwtdemo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/add")
    public Player addPlayer(@RequestBody Player p){
        return playerService.addPlayer(p);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id){
        return playerService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/displayall")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id,@RequestBody Player playerDetails){
        try{
            Player newplayer=playerService.updatePlayer(id,playerDetails);
            return ResponseEntity.ok(newplayer);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
