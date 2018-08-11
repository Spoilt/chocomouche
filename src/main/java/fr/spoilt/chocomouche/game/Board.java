package fr.spoilt.chocomouche.game;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Log
@Component
public class Board {
    private Tile[] tiles;
    private int width;
    private int height;

    public void initBoard(int width, int height) {
        this.setHeight(height);
        this.setWidth(width);
        this.createBoard(this.getWidth() * this.getHeight());
        this.placeBombs(tiles.length / 6);
    }

    private void createBoard(int size) {
        tiles = new Tile[size];
        for (int i = 0; i < size; i++) {
            tiles[i] = new Tile();
        }
    }

    private void placeBombs(int ammo) {
        Random random = new Random();
        while (ammo >= 0) {
            int x;
            int y;

            do {
                x = random.nextInt(width);
                y = random.nextInt(height);
            } while (read(x, y).isPresent() && read(x, y).get().isBomb());
            Tile bombTile = new Tile(true);
            write(x, y, bombTile);

            Optional<Tile> tileW = read(x - 1, y);
            Optional<Tile> tileNW = read(x - 1, y - 1);
            Optional<Tile> tileN = read(x, y - 1);
            Optional<Tile> tileNE = read(x + 1, y - 1);
            Optional<Tile> tileE = read(x + 1, y);
            Optional<Tile> tileSE = read(x + 1, y + 1);
            Optional<Tile> tileS = read(x, y + 1);
            Optional<Tile> tileSW = read(x - 1, y + 1);

            tileW.ifPresent(tile -> tile.setHint(tile.getHint() + 1));
            tileNW.ifPresent(tile -> tile.setHint(tile.getHint() + 1));
            tileN.ifPresent(tile -> tile.setHint(tile.getHint() + 1));
            tileNE.ifPresent(tile -> tile.setHint(tile.getHint() + 1));
            tileE.ifPresent(tile -> tile.setHint(tile.getHint() + 1));
            tileSE.ifPresent(tile -> tile.setHint(tile.getHint() + 1));
            tileS.ifPresent(tile -> tile.setHint(tile.getHint() + 1));
            tileSW.ifPresent(tile -> tile.setHint(tile.getHint() + 1));

            write(x - 1, y, tileW.orElse(null));
            write(x - 1, y - 1, tileNW.orElse(null));
            write(x, y - 1, tileN.orElse(null));
            write(x + 1, y - 1, tileNE.orElse(null));
            write(x + 1, y, tileE.orElse(null));
            write(x + 1, y + 1, tileSE.orElse(null));
            write(x, y + 1, tileS.orElse(null));
            write(x - 1, y + 1, tileSW.orElse(null));

            ammo--;
        }
    }

    private Optional<Tile> read(int x, int y) {
        if (isIllegalPos(x, y)) {
            log.severe("Coordinates " + x + "/" + y + " are outside the board.");
            return Optional.empty();
        } else {
            return Optional.of(tiles[x + y * width]);
        }
    }

    private void write(int x, int y, Tile tile) {
        if (isIllegalPos(x, y) || tile == null) {
            log.severe("Coordinates " + x + "/" + y + " are outside the board.");
        } else {
            tiles[x + y * width] = tile;
        }
    }

    private boolean isIllegalPos(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    public void showBoard() {
        for (int i = 0; i < tiles.length; i++) {
            Tile tile = tiles[i];
            if (i%width == 0) {
                System.out.println();
            }
            System.out.print(tile.isBomb() ? "B " : tile.getHint() + " ");
        }
        System.out.println();
    }
}
