package ua.com.javarush.tchaban.island_app.animals.carnivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Fox extends Carnivorous{
    Fox(){
        super(FOX_WEIGHT, FOX_MAX_COUNT_ON_FIELD, FOX_SPEED, FOX_KILOGRAMS_OF_FOOD);
    }
}
