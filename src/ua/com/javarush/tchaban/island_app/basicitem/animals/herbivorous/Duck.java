package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Duck extends Herbivorous{
    {
        foodPreferences.put("Herb", 100);
        foodPreferences.put("Caterpillar", 90);
    }

    public Duck(){
        speed = DUCK_SPEED;
        kilogramsOfFood = DUCK_KILOGRAMS_OF_FOOD;
        weight = DUCK_WEIGHT;
        maxCountOnField = DUCK_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Duck();
    }

}
