package com.packtpublishing.tddjava.ch03tictactoe;

import com.packtpublishing.tddjava.ch03tictactoe.mongo.TicTacToeBean;
import com.packtpublishing.tddjava.ch03tictactoe.mongo.TicTacToeCollection;

import java.net.UnknownHostException;

public class TicTacToe {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 3;
    private static final String RESULT_DRAW = "The result is draw";
    private static final String NO_WINNER = "No Winner";

    private TicTacToeCollection ticTacToeCollection;
    private int turn = 0;

    public TicTacToe() throws UnknownHostException {
        this(new TicTacToeCollection());
    }

    protected TicTacToe(TicTacToeCollection ticTacToeCollection) {
        this.ticTacToeCollection = ticTacToeCollection;
    }

    private Character[][] board = {
            {'0', '0', '0'},
            {'0', '0', '0'},
            {'0', '0', '0'}};

    private char lastPlayer = '0';
    private int playerTotal;

    public String play(int xAxis, int yAxis) {
        checkXAxis(xAxis);
        checkYAxis(yAxis);
        lastPlayer = nextPlayer();
        setBox(new TicTacToeBean(++turn, xAxis, yAxis, lastPlayer));

        if (isWin()) {
            return lastPlayer + " is the winner";
        } else if (isDraw()) {
            return RESULT_DRAW;
        }

        return NO_WINNER;
    }

    private boolean isWin() {

        playerTotal = lastPlayer * MAX_SIZE;

        for (int i = 0; i < MAX_SIZE; i++) {
            if (isHorizontalLine(i) || isVerticalLine(i) || isDiagonal()) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (int x = 0; x < MAX_SIZE; x++) {
            for (int y = 0; y < MAX_SIZE; y++) {
                if (board[x][y] == '0') return false;
            }
        }
        return true;
    }

    private boolean isHorizontalLine(int index) {
        return board[0][index] + board[1][index] + board[2][index] == playerTotal;
    }

    private boolean isVerticalLine(int index) {
        return board[index][0] + board[index][1] + board[index][2] == playerTotal;
    }

    private boolean isDiagonal() {
        return board[0][0] + board[1][1] + board[2][2] == playerTotal ||
                board[0][2] + board[1][1] + board[2][0] == playerTotal;
    }

    private void setBox(TicTacToeBean bean) {
        if (board[bean.getX() - 1][bean.getY() - 1] != '0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[bean.getX() - 1][bean.getY() - 1] = lastPlayer;
            if (!getTicTacToeCollection().saveMove(bean)) {
                throw new RuntimeException("Saving to DB failed");
            }
        }
    }

    private void checkYAxis(int yAxis) {
        if (yAxis < MIN_SIZE || yAxis > MAX_SIZE) {
            throw new RuntimeException("Y is outside board");
        }
    }

    private void checkXAxis(int xAxis) {
        if (xAxis < MIN_SIZE || xAxis > MAX_SIZE) {
            throw new RuntimeException("X is outside board");
        }
    }

    public char nextPlayer() {
        return lastPlayer == 'X' ? 'O' : 'X';
    }

    protected TicTacToeCollection getTicTacToeCollection() {
        return ticTacToeCollection;
    }
}
