package com.company;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.Random;

public class Wall implements Runnable {
    Terminal board;
    int y;
    Player p;
    int isHit;
    char playercharacter = '\u263B';
    char wallcharacter = '\u2593';


    Wall(Terminal board, int y, Player p) {
        this.board = board;
        this.y = y;
        this.p = p;
    }

    public void run() {
        Random ran = new Random();
        Random ran2 = new Random();
        int hole;
        int score=0;
        int speed = 100;
        boolean gameon = true;
        int change = 1;
        int life=3;
        while (gameon) {
            boolean removeLife = false;
            int holeSize = 2;
            hole = ran.nextInt(16);
            int movingHole = ran2.nextInt(5);
            String scoreOutput="Score: " + score;
            String lifeOutput="Lives: " + life;
            board.applyForegroundColor(255,255,255);
            Output.ScreenPrint(3,25,scoreOutput,board);
            Output.ScreenPrint(3,26,lifeOutput,board);
            Output.printBorder(board);
            for (int x = 90; x > 2; x--) {
                board.applyForegroundColor(255,69,0);
                if (movingHole==1)
                    holeSize = 4;
                if(!(p.getY() >= hole && p.getY() <= hole+holeSize) && x == 3) {
                    isHit=1;
                    life--;
                    removeLife=true;
                    if (life==0) {
                        gameon = false;
                        p.die();
                        break;
                    }
                }
                board.applyForegroundColor(255,255,255);
                board.moveCursor(p.getX(), p.getY());
                board.putCharacter(playercharacter);
                board.applyForegroundColor(255,69,0);
                for (int counter = 0; counter <= 20; counter++) {
                    if (!(counter >= hole && counter <= hole+holeSize)) {
                        board.moveCursor(x, counter);
                        board.putCharacter(wallcharacter);
                    }
                }
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int counter = 0; counter <= 20; counter++) {
                    if (!(counter >= hole && counter <= hole+holeSize)) {
                        board.moveCursor(x, counter);
                        board.putCharacter(' ');
                    }
                }
                if (movingHole==1) {
                    hole += change;
                    if (hole == 19 || hole == 0)
                        change = (-1) * change;
                }
            }
            if (!removeLife)
                score++;
            if(!gameon)
                break;
            if (score%3==0)
                speed *=0.9;
        }
        GameOver.endGame(board, p.getY(), 3, score);
        }
    }
