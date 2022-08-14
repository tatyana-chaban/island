package ua.com.javarush.tchaban.island_app.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Sheep extends Herbivorous{
    Sheep(){
        super(SHEEP_WEIGHT, SHEEP_MAX_COUNT_ON_FIELD, SHEEP_SPEED, SHEEP_KILOGRAMS_OF_FOOD);
    }
}
