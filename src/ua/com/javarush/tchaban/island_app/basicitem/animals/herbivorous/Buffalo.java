package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Buffalo extends Herbivorous{
    public Buffalo(){
        super(BUFFALO_WEIGHT, BOAR_MAX_COUNT_ON_FIELD, BUFFALO_SPEED, BUFFALO_KILOGRAMS_OF_FOOD);
    }
}
