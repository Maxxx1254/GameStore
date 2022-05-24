package ru.netology;

public class NotFoundGame extends RuntimeException{
    public NotFoundGame(String s) {
        super(s);
    }
}
