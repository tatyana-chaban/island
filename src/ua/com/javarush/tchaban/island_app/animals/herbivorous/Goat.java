package ua.com.javarush.tchaban.island_app.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Goat extends Herbivorous{
    Goat(){
        super(GOAT_WEIGHT, GOAT_MAX_COUNT_ON_FIELD, GOAT_SPEED, GOAT_KILOGRAMS_OF_FOOD);
    }
}
