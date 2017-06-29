package com.company;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

public class Player implements Runnable {
    int x;
    int y;
    Terminal board;
    boolean running = true;
    public int hitstatus;
    char playercharacter = '\u263B';

    Player(int x, int y, Terminal board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public void die() {
        board.moveCursor(x, y);
        board.putCharacter(' ');
        this.running = false;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void run() {
        int tempY = 0;
        board.moveCursor(x, y);
        board.putCharacter('\u263B');
        boolean otherKeyPress;
        boolean donotremove;
        while (running) {
            Key key = board.readInput();
            otherKeyPress = false;
            donotremove = false;
            if (key != null) {
                tempY = y;
                switch (key.getKind()) {
                    case ArrowDown:
                        if (y < 20)
                            y++;
                        else
                            donotremove = true;
                        break;
                    case ArrowUp:
                        if (y > 0)
                            y--;
                        else
                            donotremove = true;
                        break;
                    default:
                        otherKeyPress = true;
                }
                board.applyForegroundColor(255, 255, 255);
                board.moveCursor(x, y);
                board.putCharacter(playercharacter);
                if (!otherKeyPress && !donotremove) {
                    board.moveCursor(x, tempY);
                    board.putCharacter(' ');
                }
            }
        }
    }
}