package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsCarnivorous.*;

public class Boa extends Carnivorous{
    {
        foodPreferencesAndProbabilities.put("Fox", 15);
        foodPreferencesAndProbabilities.put("Rabbit", 20);
        foodPreferencesAndProbabilities.put("Mouse", 40);
        foodPreferencesAndProbabilities.put("Duck", 10);
    }

    public Boa(){
        name = "Boa";
        speed = BOA_SPEED;
        kilogramsToSaturate = BOA_KILOGRAMS_OF_FOOD;
        weight = BOA_WEIGHT;
        maxNumberPerLocation = BOA_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Boa();
    }
}
