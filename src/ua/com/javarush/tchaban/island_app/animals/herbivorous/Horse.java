package ua.com.javarush.tchaban.island_app.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Horse extends Herbivorous{
    Horse(){
        super(HORSE_WEIGHT, HORSE_MAX_COUNT_ON_FIELD, HORSE_SPEED, HORSE_KILOGRAMS_OF_FOOD);
    }
}
