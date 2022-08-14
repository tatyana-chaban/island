package ua.com.javarush.tchaban.island_app;

import ua.com.javarush.tchaban.island_app.animals.carnivorous.Bear;

public class Main {
    public static void main(String[] args) {
        Bear bear = new Bear();
        System.out.println(bear.getKilogramsOfFood());

    }
}
