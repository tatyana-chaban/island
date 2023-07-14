package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Mouse extends Herbivorous{
    {
        foodPreferencesAndProbabilities.put("Herb", 100);
        foodPreferencesAndProbabilities.put("Caterpillar", 90);
    }
    public Mouse(){
        name = "Mouse";
        speed = MOUSE_SPEED;
        kilogramsToSaturate = MOUSE_KILOGRAMS_OF_FOOD;
        weight = MOUSE_WEIGHT;
        maxNumberPerLocation = MOUSE_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Mouse();
    }
}
