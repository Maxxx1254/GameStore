package ru.netology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStore {
    private final List<Game> games = new ArrayList<>();

    /**
     * Информация о том, какой игрок сколько играл в игры этого каталога
     * Ключ - имя игрока
     * Значение - суммарное количество часов в игры этого каталога
     */
    private final Map<String, Integer> playedTime = new HashMap<>();

    /**
     * Создание объекта игры с заданными заголовком и жанром
     * Каждый объект игры помнит объект каталога, которому она принадлежит
     */
    public Game publishGame(String title, String genre) {
        Game game = new Game(title, genre, this);
        games.add(game);
        return game;
    }

    /**
     * Проверяет наличие игры в каталоге и возвращает true
     * если игра есть и false иначе
     */
    public boolean containsGame(Game game) {
        for (Game value : games) {
            if (value.equals(game)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Регистрирует количество времени, которое проиграл игрок
     * за игрой этого каталога. Игрок задаётся по имени. Время должно
     * суммироваться с прошлым значением для этого игрока
     */
    public int addPlayTime(String playerName, int hours) {
        if (playedTime.containsKey(playerName)) {
            int sum = 0;
            for (int value : playedTime.values()) {
                sum += value + hours;
            }
            playedTime.put(playerName, sum);
        } else {
            playedTime.put(playerName, hours);
        }
        return getSumPlayedTime();
    }

    /**
     * Ищет имя игрока, который играл в игры этого каталога больше всего
     * времени. Если игроков нет, то возвращается null
     */
    public String getMostPlayer() {
        int mostTime = 1;
        String bestPlayer = null;
        for (String playerName : playedTime.keySet()) {
            int playerTime = playedTime.get(playerName);
            if (playerTime > mostTime) {
                mostTime = playerTime;
                bestPlayer = playerName;
            }
        }
        return bestPlayer;
    }

    /**
     * Суммирует общее количество времени всех игроков, проведённого
     * за играми этого каталога
     */
    public int getSumPlayedTime() {
        int sum = 0;
        for (int value : playedTime.values()) {
            sum += value;
        }
        return sum;
    }
}