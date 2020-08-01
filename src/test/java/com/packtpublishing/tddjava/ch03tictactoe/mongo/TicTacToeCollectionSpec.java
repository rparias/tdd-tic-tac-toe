package com.packtpublishing.tddjava.ch03tictactoe.mongo;

import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TicTacToeCollectionSpec {

    private TicTacToeCollection collection;

    @Before
    public void before() throws UnknownHostException {
        collection = spy(new TicTacToeCollection());
    }

    @Test
    public void whenInstantiatedThenMongoHasDbNameTicTacToe() {
        assertThat(collection.getMongoCollection().getDBCollection().getDB().getName(), is("tic-tac-toe"));
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() {
        assertThat(collection.getMongoCollection().getName(), is("game"));
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave() {
        TicTacToeBean bean = new TicTacToeBean(3, 2, 1, 'Y');
        MongoCollection mongoCollection = mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.saveMove(bean);
        verify(mongoCollection, times(1)).save(bean);
    }
}
