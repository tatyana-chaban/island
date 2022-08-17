package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Rabbit extends Herbivorous{
    public Rabbit(){
        super(RABBIT_WEIGHT, RABBIT_MAX_COUNT_ON_FIELD, RABBIT_SPEED, RABBIT_KILOGRAMS_OF_FOOD);
    }
}
