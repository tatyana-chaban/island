package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Caterpillar extends Herbivorous{
    public Caterpillar(){
        name = "Caterpillar";
        speed = CATERPILLAR_SPEED;
        kilogramsToSaturate = CATERPILLAR_KILOGRAMS_OF_FOOD;
        weight = CATERPILLAR_WEIGHT;
        maxNumberPerLocation = CATERPILLAR_MAX_COUNT_ON_FIELD;
    }

        @Override
        public BasicItem makeCopy() {
            return new Caterpillar();
        }
}
