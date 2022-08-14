package ua.com.javarush.tchaban.island_app.animals.carnivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Boa extends Carnivorous{
    Boa(){
        super(BOA_WEIGHT, BOA_MAX_COUNT_ON_FIELD,BOA_SPEED,BOA_KILOGRAMS_OF_FOOD);
    }
}
