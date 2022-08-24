package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import static ua.com.javarush.tchaban.island_app.constants.ConstantsHerbivorous.*;

public class Caterpillar extends Herbivorous{
    public Caterpillar(){
        speed = CATERPILLAR_SPEED;
        kilogramsOfFood = CATERPILLAR_KILOGRAMS_OF_FOOD;
        weight = CATERPILLAR_WEIGHT;
        maxCountOnField = CATERPILLAR_MAX_COUNT_ON_FIELD;
    }

        @Override
        public BasicItem newInstance() {
            return new Caterpillar();
        }

}
