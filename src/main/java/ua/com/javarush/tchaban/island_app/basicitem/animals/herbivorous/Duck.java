package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Duck extends Herbivorous{
    {
        foodPreferencesAndProbabilities.put("Herb", 100);
        foodPreferencesAndProbabilities.put("Caterpillar", 90);
    }

    public Duck(){
        name = "Duck";
        speed = DUCK_SPEED;
        kilogramsToSaturate = DUCK_KILOGRAMS_OF_FOOD;
        weight = DUCK_WEIGHT;
        maxNumberPerLocation = DUCK_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Duck();
    }
}
