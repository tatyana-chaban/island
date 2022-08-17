package ua.com.javarush.tchaban.island_app.basicitem.plants;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;

public class Herb extends BasicItem {
    private static final double weight = 1;
    private static final int maxCountOnField = 200;
    private static final int speed = 0;
    private static final double kilogramsOfFood = 0;

    public Herb(){
        super(weight,maxCountOnField,speed,kilogramsOfFood);
    }

}
