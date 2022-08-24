package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Sheep extends Herbivorous{
    public Sheep(){
        speed = SHEEP_SPEED;
        kilogramsOfFood = SHEEP_KILOGRAMS_OF_FOOD;
        weight = SHEEP_WEIGHT;
        maxCountOnField = SHEEP_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Sheep();
    }
}
