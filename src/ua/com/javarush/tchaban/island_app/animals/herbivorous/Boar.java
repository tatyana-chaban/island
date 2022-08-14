package ua.com.javarush.tchaban.island_app.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Boar extends Herbivorous{
    Boar(){
        super(BOAR_WEIGHT, BOAR_MAX_COUNT_ON_FIELD, BOAR_SPEED, BOAR_KILOGRAMS_OF_FOOD);
    }
}
