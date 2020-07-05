package com.packtpublishing.tddjava.ch03tictactoe;

public class TicTacToe {

    private Character[][] board = {
            {'0', '0', '0'},
            {'0', '0', '0'},
            {'0', '0', '0'}};

    public void play(int xAxis, int yAxis) {
        checkXAxis(xAxis);
        checkYAxis(yAxis);
        setBox(xAxis, yAxis);
    }

    private void setBox(int xAxis, int yAxis) {
        if (board[xAxis-1][yAxis-1] != '0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[xAxis-1][yAxis-1] = 'X';
        }
    }

    private void checkYAxis(int yAxis) {
        if (yAxis < 1 || yAxis > 3) {
            throw new RuntimeException("Y is outside board");
        }
    }

    private void checkXAxis(int xAxis) {
        if (xAxis < 1 || xAxis > 3) {
            throw new RuntimeException("X is outside board");
        }
    }
}
