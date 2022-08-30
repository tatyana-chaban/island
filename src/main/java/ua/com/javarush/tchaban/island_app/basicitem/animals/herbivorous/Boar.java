package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Boar extends Herbivorous{
    {
        foodPreferencesAndProbabilities.put("Herb", 100);
        foodPreferencesAndProbabilities.put("Caterpillar", 90);
        foodPreferencesAndProbabilities.put("Mouse", 50);
    }

    public Boar(){
        name = "Boar";
        speed = BOAR_SPEED;
        kilogramsToSaturate = BOAR_KILOGRAMS_OF_FOOD;
        weight = BOAR_WEIGHT;
        maxNumberPerLocation = BOAR_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Boar();
    }

}
