package ua.com.javarush.tchaban.island_app.animals.carnivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Bear extends Carnivorous{
    public Bear(){
        super(BEAR_WEIGHT, BEAR_MAX_COUNT_ON_FIELD,BEAR_SPEED,BEAR_KILOGRAMS_OF_FOOD);
    }

}
