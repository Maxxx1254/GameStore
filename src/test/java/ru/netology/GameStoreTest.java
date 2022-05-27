package ru.netology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStoreTest {
    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddZeroGame() {

        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);

        assertFalse(store.containsGame(game));
    }
    @Test
    public void testAddPlayTime() {
        GameStore store = new GameStore();
        store.addPlayTime("Vasa", 15);
        store.addPlayTime("Gena", 20);
        store.addPlayTime("Jan", 10);

        String expected = "Gena";
        String actual = store.getMostPlayer();

        assertArrayEquals(new String[]{expected}, new String[]{actual});
    }

    @Test
    public void testAddPlayTimeOne() {
        GameStore store = new GameStore();
        store.addPlayTime("Vasa", 15);

        String expected = "Vasa";
        String actual = store.getMostPlayer();

        assertArrayEquals(new String[]{expected}, new String[]{actual});
    }

    @Test
    public void testAddPlayTimeZero() {
        GameStore store = new GameStore();

        String actual = store.getMostPlayer();

        assertArrayEquals(new String[]{null}, new String[]{actual});
    }

    @Test
    public void shouldFindSumOnePlayer() {
        GameStore store = new GameStore();
        store.addPlayTime("Vasa", 15);

        int expected = 15;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindSumWithoutPlayers() {
        GameStore store = new GameStore();

        int expected = 0;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindSum() {
        GameStore store = new GameStore();
        store.addPlayTime("Vasa", 15);
        store.addPlayTime("Gena", 20);
        store.addPlayTime("Jan", 10);

        int expected = 45;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }
}