package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Deer extends Herbivorous{
    public Deer(){
        speed = DEER_SPEED;
        kilogramsOfFood = DEER_KILOGRAMS_OF_FOOD;
        weight = DEER_WEIGHT;
        maxCountOnField = DEER_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Caterpillar();
    }
}
