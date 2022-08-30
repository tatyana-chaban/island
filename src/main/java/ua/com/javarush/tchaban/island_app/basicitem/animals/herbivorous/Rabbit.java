package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Rabbit extends Herbivorous{
    public Rabbit(){
        name = "Rabbit";
        speed = RABBIT_SPEED;
        kilogramsToSaturate = RABBIT_KILOGRAMS_OF_FOOD;
        weight = RABBIT_WEIGHT;
        maxNumberPerLocation = RABBIT_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Rabbit();
    }
}
