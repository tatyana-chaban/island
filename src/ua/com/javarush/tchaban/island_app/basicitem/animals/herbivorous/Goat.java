package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Goat extends Herbivorous{
    public Goat(){
        speed = GOAT_SPEED;
        kilogramsOfFood = GOAT_KILOGRAMS_OF_FOOD;
        weight = GOAT_WEIGHT;
        maxCountOnField = GOAT_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Goat();
    }
}
