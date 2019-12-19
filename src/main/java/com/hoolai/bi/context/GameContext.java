package com.hoolai.bi.context;

import com.hoolai.bi.entiy.Game;
import com.hoolai.bi.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-12-03 10:44
 * 
 */

@Component
public class GameContext {
    @Autowired
    private GameMapper gameMapper;
    private Map<Integer,Game> games;

    public GameContext() {
    }

    @PostConstruct
    private void init(){
        List<Game> gameList = gameMapper.selectList(null);
        games = gameList.stream().collect(Collectors.toMap(Game::getGameid,game -> game));
    }

    public GameMapper getGameMapper() {
        return gameMapper;
    }

    public void setGameMapper(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    public Game get(int key){
        return games.get(key);
    }

    @Override
    public String toString() {
        return "GameContext{" +
                "gameMapper=" + gameMapper +
                ", games=" + games +
                '}';
    }

    public Map<Integer, Game> getGames() {
        return games;
    }

    public void setGames(Map<Integer, Game> games) {
        this.games = games;
    }
}
