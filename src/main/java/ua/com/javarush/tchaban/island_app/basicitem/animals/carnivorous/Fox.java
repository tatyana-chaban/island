package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Fox extends Carnivorous{
    {
        foodPreferencesAndProbabilities.put("Rabbit", 70);
        foodPreferencesAndProbabilities.put("Mouse", 90);
        foodPreferencesAndProbabilities.put("Duck", 60);
        foodPreferencesAndProbabilities.put("Caterpillar", 40);
    }

    public Fox(){
        name = "Fox";
        speed = FOX_SPEED;
        kilogramsToSaturate = FOX_KILOGRAMS_OF_FOOD;
        weight = FOX_WEIGHT;
        maxNumberPerLocation = FOX_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Fox();
    }
}
