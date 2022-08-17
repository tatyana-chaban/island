package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Eagle extends Carnivorous{
    public Eagle(){
        super(EAGLE_WEIGHT, EAGLE_MAX_COUNT_ON_FIELD,EAGLE_SPEED,EAGLE_KILOGRAMS_OF_FOOD);
    }
}
