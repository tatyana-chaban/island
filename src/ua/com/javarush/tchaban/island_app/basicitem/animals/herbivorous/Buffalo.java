package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Buffalo extends Herbivorous{
    public Buffalo(){
        speed = BUFFALO_SPEED;
        kilogramsOfFood = BUFFALO_KILOGRAMS_OF_FOOD;
        weight = BUFFALO_WEIGHT;
        maxCountOnField = BUFFALO_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Buffalo();
    }
}
