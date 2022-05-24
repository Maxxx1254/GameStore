package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldSumGenresIfOneGameOneGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game = store.publishGame("Max Payne 1", "TPS");

        player.installGame("Max Payne 1");

        store.addPlayTime("Игрок", 50);

        player.play(game, 50);

        int expected = 50;
        int actual = Player.sumGenre("TPS");

        assertEquals(expected, actual);
    }


    @Test
    public void shouldSumGenreIfThreeGameOfOnePlayer() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = store.publishGame("Max Payne 1", "TPS");
        Game game2 = store.publishGame("Max Payne 2", "TPS");
        Game game3 = store.publishGame("Max Payne 3", "TPS");

        player.installGame("Max Payne 1");
        player.installGame("Max Payne 2");
        player.installGame("Max Payne 3");

        store.addPlayTime("Игрок", 50);
        store.addPlayTime("Игрок", 70);
        store.addPlayTime("Игрок", 60);

        player.play(game1, 50);
        player.play(game2, 70);
        player.play(game3, 60);

        int expected = 180;
        int actual = Player.sumGenre("TPS");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfRepeatOneGameOfOnePlayer() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game = store.publishGame("Max Payne 1", "TPS");
        player.installGame("Max Payne 1");

        store.addPlayTime("Игрок", 50);
        store.addPlayTime("Игрок", 51);


        player.play(game, 51);
        player.play(game, 50);


        int expected = 50;
        int actual = Player.sumGenre("TPS");

        assertEquals(expected, actual);
    }



    @Test
    public void shouldSumGenresIfMoreThanOneGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = store.publishGame("Max Payne 1", "TPS");
        Game game2 = store.publishGame("DOTA", "MOBA");
        Game game3 = store.publishGame("Counter-Strike", "FPS");
        Game game4 = store.publishGame("DOKA 2", "MOBA");

        player.installGame("Max Payne 1");
        player.installGame("DOTA");
        player.installGame("Counter-Strike");
        player.installGame("DOKA 2");

        store.addPlayTime("Игрок", 50);
        store.addPlayTime("Игрок", 70);
        store.addPlayTime("Игрок", 60);
        store.addPlayTime("Игрок", 80);

        player.play(game1, 50);
        player.play(game2, 70);
        player.play(game3, 60);
        player.play(game4, 80);

        int expected = 150;
        int actual = Player.sumGenre("MOBA");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMostPlayedGameByGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = store.publishGame("NFS Underground", "Racing");
        Game game2 = store.publishGame("Forza", "Racing");

        player.installGame("NFS Underground");
        player.installGame("Forza");

        player.play(game1, 15);
        player.play(game2, 20);

        Game actual = player.getMostPopularGameByGenre();

        assertEquals(game2, actual);
    }

    @Test
    public void shouldReturnNullIfYouSearchForGenreThatHasNotBeenPlayed() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = new Game("NFS Underground", "Racing", store);
        Game game2 = new Game("Forza", "Racing", store);

        player.installGame("NFS Underground");
        player.installGame("Forza");

        player.play(game1, 15);
        player.play(game2, 20);

        Game actual = player.getMostPopularGameByGenre();

        assertNull(actual);
    }

    @Test
    public void shouldThrowExceptionIfPlayGameThatIsNotInstalled() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = store.publishGame("Max Payne 1", "TPS");

        assertThrows(RuntimeException.class, () -> player.play(game1, 100));
    }
}