package ua.com.javarush.tchaban.island_app.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Duck extends Herbivorous{
    Duck(){
        super(DUCK_WEIGHT, DUCK_MAX_COUNT_ON_FIELD, DUCK_SPEED, DUCK_KILOGRAMS_OF_FOOD);
    }
}
