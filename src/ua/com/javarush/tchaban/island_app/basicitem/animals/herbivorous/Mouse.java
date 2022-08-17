package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Mouse extends Herbivorous{
    public Mouse(){
        super(MOUSE_WEIGHT, MOUSE_MAX_COUNT_ON_FIELD, MOUSE_SPEED, MOUSE_KILOGRAMS_OF_FOOD);
    }
}
