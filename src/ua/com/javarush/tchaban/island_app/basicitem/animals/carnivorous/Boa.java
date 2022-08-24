package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Boa extends Carnivorous{
    {
        foodPreferences.put("Fox", 15);
        foodPreferences.put("Rabbit", 20);
        foodPreferences.put("Mouse", 40);
        foodPreferences.put("Duck", 10);
    }

    public Boa(){
        speed = BOA_SPEED;
        kilogramsOfFood = BOA_KILOGRAMS_OF_FOOD;
        weight = BOA_WEIGHT;
        maxCountOnField = BOA_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Boa();
    }
}
