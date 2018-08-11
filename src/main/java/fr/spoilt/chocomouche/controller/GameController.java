package fr.spoilt.chocomouche.controller;

import fr.spoilt.chocomouche.game.Board;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class GameController {

    @Autowired
    private Board board;

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public Board test() {
        board.initBoard(8, 9);

        log.info(board.toString());
        return board;
    }
}
