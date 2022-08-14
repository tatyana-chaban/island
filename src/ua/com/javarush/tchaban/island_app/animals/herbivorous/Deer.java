package ua.com.javarush.tchaban.island_app.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Deer extends Herbivorous{
    Deer(){
        super(DEER_WEIGHT, DEER_MAX_COUNT_ON_FIELD, DEER_SPEED, DUCK_KILOGRAMS_OF_FOOD);
    }
}
