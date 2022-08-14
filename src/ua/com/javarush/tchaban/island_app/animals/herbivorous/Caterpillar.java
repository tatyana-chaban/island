package ua.com.javarush.tchaban.island_app.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Caterpillar extends Herbivorous{
    Caterpillar(){
        super(CATERPILLAR_WEIGHT, CATERPILLAR_MAX_COUNT_ON_FIELD, CATERPILLAR_SPEED, CATERPILLAR_KILOGRAMS_OF_FOOD);
    }
}
