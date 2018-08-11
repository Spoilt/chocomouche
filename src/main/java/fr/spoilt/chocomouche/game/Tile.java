package fr.spoilt.chocomouche.game;

import lombok.Data;

@Data
class Tile {
    private StateEnum state;
    private boolean bomb;
    private int hint;

    Tile(boolean placeBomb) {
        this.setState(StateEnum.HIDDEN);
        bomb = placeBomb;
    }

    Tile() {
        this.setState(StateEnum.HIDDEN);
        bomb = false;
    }
}
