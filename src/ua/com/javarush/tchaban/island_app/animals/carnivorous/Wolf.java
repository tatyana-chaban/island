package ua.com.javarush.tchaban.island_app.animals.carnivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Wolf extends Carnivorous {
    Wolf(){
        super(WOLF_WEIGHT, WOLF_MAX_COUNT_ON_FIELD, WOLF_SPEED, WOLF_KILOGRAMS_OF_FOOD);
    }
}
