package com.company;

import com.googlecode.lanterna.terminal.Terminal;

public class Output {
    public static void ScreenPrint(int x, int y, String message, Terminal board) {
        for (int i = 0; i < message.length(); i++) {
            board.moveCursor(x++, y);
            board.putCharacter(message.charAt(i));
        }
    }
    public static void printBoard(Terminal board){
        for(int i=0;i<92;i++){
            board.moveCursor(i, 21);
            board.putCharacter('\u2584');
        }
        for(int i=0;i<22;i++) {
            board.moveCursor(91, i);
            board.putCharacter('\u258C');
        }
    }
}