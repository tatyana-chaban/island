package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Goat extends Herbivorous{
    public Goat(){
        super(GOAT_WEIGHT, GOAT_MAX_COUNT_ON_FIELD, GOAT_SPEED, GOAT_KILOGRAMS_OF_FOOD);
    }
}
