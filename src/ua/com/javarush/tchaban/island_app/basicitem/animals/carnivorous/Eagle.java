package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Eagle extends Carnivorous{
    {
        foodPreferences.put("Fox", 10);
        foodPreferences.put("Rabbit", 90);
        foodPreferences.put("Mouse", 90);
        foodPreferences.put("Duck", 80);
    }

    public Eagle(){
        speed = EAGLE_SPEED;
        kilogramsOfFood = EAGLE_KILOGRAMS_OF_FOOD;
        weight = EAGLE_WEIGHT;
        maxCountOnField = EAGLE_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Eagle();
    }
}
