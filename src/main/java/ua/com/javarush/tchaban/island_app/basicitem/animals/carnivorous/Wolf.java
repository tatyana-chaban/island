package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Wolf extends Carnivorous {
    {
        foodPreferencesAndProbabilities.put("Horse", 10);
        foodPreferencesAndProbabilities.put("Deer", 15);
        foodPreferencesAndProbabilities.put("Rabbit", 60);
        foodPreferencesAndProbabilities.put("Mouse", 80);
        foodPreferencesAndProbabilities.put("Goat", 60);
        foodPreferencesAndProbabilities.put("Sheep", 70);
        foodPreferencesAndProbabilities.put("Boar", 15);
        foodPreferencesAndProbabilities.put("Buffalo", 10);
        foodPreferencesAndProbabilities.put("Duck", 40);
    }

    public Wolf(){
        name = "Wolf";
        speed = WOLF_SPEED;
        kilogramsToSaturate = WOLF_KILOGRAMS_OF_FOOD;
        weight = WOLF_WEIGHT;
        maxNumberPerLocation = WOLF_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Wolf();
    }
}
