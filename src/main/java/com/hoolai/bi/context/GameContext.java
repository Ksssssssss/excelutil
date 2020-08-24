package com.hoolai.bi.context;

import com.hoolai.bi.entiy.Game;
import com.hoolai.bi.mapper.GameMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-12-03 10:44
 */

@Component
@Data
public class GameContext {
    @Autowired
    private GameMapper gameMapper;
    private Map<Integer, Game> games;

    public GameContext() {
    }

    @PostConstruct
    private void init() {
        List<Game> gameList = gameMapper.selectList(null);
        games = gameList.stream().collect(Collectors.toMap(Game::getGameid, game -> game));
    }

    public Game get(int key){
        return games.get(key);
    }
}
