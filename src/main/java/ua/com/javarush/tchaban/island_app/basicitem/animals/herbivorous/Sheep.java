package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Sheep extends Herbivorous{
    public Sheep(){
        name = "Sheep";
        speed = SHEEP_SPEED;
        kilogramsToSaturate = SHEEP_KILOGRAMS_OF_FOOD;
        weight = SHEEP_WEIGHT;
        maxNumberPerLocation = SHEEP_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Sheep();
    }
}
