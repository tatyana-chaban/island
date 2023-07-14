package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Bear extends Carnivorous{
    {
        foodPreferencesAndProbabilities.put("Boa", 80);
        foodPreferencesAndProbabilities.put("Horse", 40);
        foodPreferencesAndProbabilities.put("Deer", 80);
        foodPreferencesAndProbabilities.put("Rabbit", 80);
        foodPreferencesAndProbabilities.put("Mouse", 90);
        foodPreferencesAndProbabilities.put("Goat", 70);
        foodPreferencesAndProbabilities.put("Sheep", 70);
        foodPreferencesAndProbabilities.put("Boar", 50);
        foodPreferencesAndProbabilities.put("Buffalo", 20);
        foodPreferencesAndProbabilities.put("Duck", 10);
    }

    public Bear(){
        name = "Bear";
        speed = BEAR_SPEED;
        kilogramsToSaturate = BEAR_KILOGRAMS_OF_FOOD;
        weight = BEAR_WEIGHT;
        maxNumberPerLocation = BEAR_MAX_COUNT_ON_FIELD;
    }

    public BasicItem makeCopy(){
        return new Bear();
    }
}
