package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Fox extends Carnivorous{
    {
        foodPreferences.put("Rabbit", 70);
        foodPreferences.put("Mouse", 90);
        foodPreferences.put("Duck", 60);
        foodPreferences.put("Caterpillar", 40);
    }

    public Fox(){
        speed = FOX_SPEED;
        kilogramsOfFood = FOX_KILOGRAMS_OF_FOOD;
        weight = FOX_WEIGHT;
        maxCountOnField = FOX_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Fox();
    }
}
