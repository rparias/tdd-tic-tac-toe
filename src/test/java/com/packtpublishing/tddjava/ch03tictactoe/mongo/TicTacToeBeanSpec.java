package com.packtpublishing.tddjava.ch03tictactoe.mongo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TicTacToeBeanSpec {

    private TicTacToeBean bean;
    private final int turn = 17;
    private final int x = 2;
    private final int y = 3;
    private final char player = 'X';

    @Before
    public void before() {
        bean = new TicTacToeBean(turn, x, y, player);
    }

    @Test
    public void whenInstantiatedThenIdIsStored() {
        assertThat(bean.getTurn(), is(turn));
    }

    @Test
    public void whenInstantiatedThenXIsStored() {
        assertThat(bean.getX(), is(x));
    }

    @Test
    public void whenInstantiatedThenYIsStored() {
        assertThat(bean.getY(), is(y));
    }

    @Test
    public void whenInstantiatedThenPlayerIsStored() {
        assertThat(bean.getPlayer(), is(player));
    }
}
