package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Boar extends Herbivorous{
    {
        foodPreferences.put("Herb", 100);
        foodPreferences.put("Caterpillar", 90);
        foodPreferences.put("Mouse", 50);
    }
    public Boar(){
        speed = BOAR_SPEED;
        kilogramsOfFood = BOAR_KILOGRAMS_OF_FOOD;
        weight = BOAR_WEIGHT;
        maxCountOnField = BOAR_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem newInstance() {
        return new Boar();
    }

}
