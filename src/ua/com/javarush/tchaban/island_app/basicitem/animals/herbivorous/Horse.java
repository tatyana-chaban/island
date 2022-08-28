package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Horse extends Herbivorous{
    public Horse(){
        speed = HORSE_SPEED;
        kilogramsOfFood = HORSE_KILOGRAMS_OF_FOOD;
        weight = HORSE_WEIGHT;
        maxCountOnField = HORSE_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Horse();
    }
}
