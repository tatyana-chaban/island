package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Wolf extends Carnivorous {
    {
        foodPreferences.put("Horse", 10);
        foodPreferences.put("Deer", 15);
        foodPreferences.put("Rabbit", 60);
        foodPreferences.put("Mouse", 80);
        foodPreferences.put("Goat", 60);
        foodPreferences.put("Sheep", 70);
        foodPreferences.put("Boar", 15);
        foodPreferences.put("Buffalo", 10);
        foodPreferences.put("Duck", 40);
    }

    public Wolf(){
        speed = WOLF_SPEED;
        kilogramsOfFood = WOLF_KILOGRAMS_OF_FOOD;
        weight = WOLF_WEIGHT;
        maxCountOnField = WOLF_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Wolf();
    }
}
