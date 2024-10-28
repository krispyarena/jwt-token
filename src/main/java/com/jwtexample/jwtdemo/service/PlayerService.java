package com.jwtexample.jwtdemo.service;


import com.jwtexample.jwtdemo.model.Player;
import com.jwtexample.jwtdemo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player addPlayer(Player p){
        return playerRepository.save(p);
    }
    public Optional<Player> getPlayerById(Long id){
        return playerRepository.findById(id);
    }
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }
    public Player updatePlayer(Long id,Player playerDetails){
        return playerRepository.findById(id).map(p->{
            p.setName(playerDetails.getName());
            p.setGoals(playerDetails.getGoals());
            return playerRepository.save(p);
        }).orElseThrow(()-> new RuntimeException("Error Inserting Data"));
    }
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}