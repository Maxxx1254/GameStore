package ru.netology;

import java.util.HashMap;
import java.util.Map;

public class Player {
    protected static String name;
    protected String[] gamesOfPlayer;
    /** информация о том, в какую игру сколько часов было сыграно
     ключ - игра
     значение - суммарное количество часов игры в эту игру */
    protected static Map<Game, Integer> playedTime = new HashMap<>();

    public Player(String name) {
        Player.name = name;
    }

    public static String getName() {
        return name;
    }

    /**
     * добавление игры игроку
     * если игра уже была, никаких изменений происходить не должно
     */
    public String installGame(String title) {
        for (int i = 1; i < ; i++) {
            if (Game.getTitle().equals(title)) {
            }
        }
        return title;
    }

    public String findByTitle(String title) {
        for (int i = 0; i < 2; i++) {
            if (Game.getTitle().equals(title)) {
                return title;
            }
        }
        return null;
    }
    /**
     * игрок играет в игру game на протяжении hours часов
     * об этом нужно сообщить объекту-каталогу игр, откуда была установлена игра
     * также надо обновить значения в мапе игрока, добавив проигранное количество часов
     * возвращает суммарное количество часов, проигранное в эту игру.
     * если игра не была установлена, то надо выкидывать RuntimeException
     */
    public void play(Game game, int hours) {
        if (findByTitle(Game.getTitle()) == null) {
            throw new NotFoundGame( "Not found games.");
        }
        game.getStore();
        playedTime.put(game, playedTime.getOrDefault(game, hours));
        playedTime.get(game);
    }

    /** Метод принимает жанр игры (одно из полей объекта игры) и
     суммирует время, проигранное во все игры этого жанра этим игроком */
    public static int sumGenre(String genre) {
        int sum = 0;
        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                sum += playedTime.get(game);
            } else {
                return sum;
            }
        }
        return sum;
    }

    /**
     * Метод принимает жанр и возвращает игру этого жанра, в которую играли больше всего
     * Если в игры этого жанра не играли, возвращается null
     */
    public Game getMostPopularGameByGenre() {
        int mostTime = 1;
        Game bestPlayer = null;
        for (Game genre : playedTime.keySet()) {
            int playerTime = playedTime.get(genre);
            if (playerTime > mostTime) {
                mostTime = playerTime;
                bestPlayer = genre;
            }
        }
        return bestPlayer;
    }
}