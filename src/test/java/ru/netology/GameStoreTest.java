package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameStoreTest {
    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    // другие ваши тесты

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


        String expected = null;
        String actual = store.getMostPlayer();

        assertArrayEquals(new String[]{expected}, new String[]{actual});
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