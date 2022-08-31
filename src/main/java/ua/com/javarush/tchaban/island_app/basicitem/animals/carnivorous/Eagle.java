package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Eagle extends Carnivorous{
    {
        foodPreferencesAndProbabilities.put("Fox", 10);
        foodPreferencesAndProbabilities.put("Rabbit", 90);
        foodPreferencesAndProbabilities.put("Mouse", 90);
        foodPreferencesAndProbabilities.put("Duck", 80);
    }

    public Eagle(){
        name = "Eagle";
        speed = EAGLE_SPEED;
        kilogramsToSaturate = EAGLE_KILOGRAMS_OF_FOOD;
        weight = EAGLE_WEIGHT;
        maxNumberPerLocation = EAGLE_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Eagle();
    }
}
