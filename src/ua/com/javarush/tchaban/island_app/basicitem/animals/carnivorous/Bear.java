package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Bear extends Carnivorous{
    {
        foodPreferences.put("Boa", 80);
        foodPreferences.put("Horse", 40);
        foodPreferences.put("Deer", 80);
        foodPreferences.put("Rabbit", 80);
        foodPreferences.put("Mouse", 90);
        foodPreferences.put("Goat", 70);
        foodPreferences.put("Sheep", 70);
        foodPreferences.put("Boar", 50);
        foodPreferences.put("Buffalo", 20);
        foodPreferences.put("Duck", 10);
    }

    public Bear(){
        speed = BEAR_SPEED;
        kilogramsOfFood = BEAR_KILOGRAMS_OF_FOOD;
        weight = BEAR_WEIGHT;
        maxCountOnField = BEAR_MAX_COUNT_ON_FIELD;

    }

    public BasicItem newInstance(){
        return new Bear();
    }


}
