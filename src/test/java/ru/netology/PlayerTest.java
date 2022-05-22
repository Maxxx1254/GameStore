package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
    public void shouldThrowExceptionIfPlayGameThatIsNotInstalled() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");


        Game game1 = new Game("Max Payne 1", "TPS", store);
        Game game2 = new Game("Max Payne 2", "TPS", store);
        Game game3 = new Game("Max Payne 3", "TPS", store);

        player.installGame(game2);
        player.installGame(game3);


        assertThrows(RuntimeException.class, () -> {
            player.play(game1, 100);
        });
    }

    @Test
    public void shouldSumGenreIfMultipleGames() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = new Game("Max Payne 1", "TPS", store);
        Game game2 = new Game("Max Payne 2", "TPS", store);
        Game game3 = new Game("Max Payne 3", "TPS", store);

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 500);
        player.play(game2, 100);
        player.play(game3, 100);

        int expected = 700;
        int actual = player.sumGenre("TPS");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumHoursPlayedInOneGame() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = new Game("Max Payne 1", "TPS", store);

        player.installGame(game1);

        player.play(game1, 25);
        player.play(game1, 25);

        int expected = 50;
        int actual = player.sumGenre("TPS");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenresIfMoreThanOneGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = new Game("Max Payne 1", "TPS", store);
        Game game2 = new Game("DOTA", "MOBA", store);
        Game game3 = new Game("Counter-Strike", "FPS", store);

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 50);
        player.play(game2, 50);
        player.play(game3, 60);

        int expected = 50;
        int actual = player.sumGenre("TPS");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMostPlayedGameByGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Игрок");

        Game game1 = new Game("NFS Underground", "Racing", store);
        Game game2 = new Game("Forza", "Racing", store);

        player.installGame(game1);
        player.installGame(game2);

        player.play(game1, 15);
        player.play(game2, 20);

        Game expected = game2;
        Game actual = player.mostPlayerByGenre("Racing");

        assertEquals(expected, actual);
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
}