package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Mouse extends Herbivorous{
    {
        foodPreferences.put("Herb", 100);
        foodPreferences.put("Caterpillar", 90);
    }
    public Mouse(){
        speed = MOUSE_SPEED;
        kilogramsOfFood = MOUSE_KILOGRAMS_OF_FOOD;
        weight = MOUSE_WEIGHT;
        maxCountOnField = MOUSE_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Mouse();
    }

}
