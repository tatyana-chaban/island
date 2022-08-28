package ua.com.javarush.tchaban.island_app.basicitem.plants;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;

import static ua.com.javarush.tchaban.island_app.constants.ConstantsPlants.*;

public class Herb extends Plants{

    public Herb(){
        weight = HERB_WEIGHT;
        maxCountOnField = HERB_MAX_COUNT_ON_FIELD;
    }

    @Override
    public BasicItem makeCopy() {
        return new Herb();
    }
}
