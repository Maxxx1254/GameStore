package ru.netology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    // другие ваши тесты

    @Test
    public void shouldSumGenresIfOneGameOneGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game = store.publishGame("Max Payne 1", "TPS");
        Game game1 = store.publishGame("DOKA2", "MOBA");

        player.installGame(game);

        player.play(game, 50);

        int expected = 50;
        int actual = player.sumGenre("TPS");

        assertEquals(expected, actual);
    }


    @Test
    public void shouldSumGenreIfThreeGameOfOnePlayer() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = store.publishGame("Max Payne 1", "TPS");
        Game game2 = store.publishGame("Max Payne 2", "TPS");
        Game game3 = store.publishGame("Max Payne 3", "TPS");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 50);
        player.play(game2, 70);
        player.play(game3, 60);

        int expected = 180;
        int actual = player.sumGenre("TPS");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfRepeatOneGameOfOnePlayer() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game = store.publishGame("Max Payne 1", "TPS");
        player.installGame(game);

        player.play(game, 51);
        player.play(game, 50);

        int expected = 101;
        int actual = player.sumGenre("TPS");

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

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);

        player.play(game1, 50);
        player.play(game2, 70);
        player.play(game3, 60);
        player.play(game4, 80);

        int expected = 150;
        int actual = player.sumGenre("MOBA");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMostPlayedGameByGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = store.publishGame("NFS Underground", "Racing");
        Game game2 = store.publishGame("Forza", "Racing");
        Game game3 = store.publishGame("DOKA", "MOBA");

        player.installGame(game1);
        player.installGame(game2);

        player.play(game1, 25);
        player.play(game2, 20);

        Game actual = player.mostPlayerByGenre(game2.getGenre());

        assertEquals(game1, actual);
    }

    @Test
    public void shouldReturnNullIfYouSearchForGenreThatHasNotBeenPlayed() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = new Game("NFS Underground", "Racing", store);
        Game game2 = new Game("Forza", "Racing", store);

        player.installGame(game1);
        player.installGame(game2);

        player.play(game1, 15);
        player.play(game2, 20);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Fighting");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionIfPlayGameThatIsNotInstalled() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = store.publishGame("Max Payne 1", "TPS");

        assertThrows(RuntimeException.class, () ->
                player.play(game1, 100));
    }
}