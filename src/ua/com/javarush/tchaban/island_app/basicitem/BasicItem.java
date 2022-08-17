package ua.com.javarush.tchaban.island_app.basicitem;

import lombok.Data;

@Data
public abstract class BasicItem {
    private double weight;
    private int maxCountOnField;
    private int speed;
    private double kilogramsOfFood;

    public BasicItem(double weight, int maxCountOnField, int speed, double kilogramsOfFood){
        this.weight = weight;
        this.maxCountOnField = maxCountOnField;
        this.speed = speed;
        this.kilogramsOfFood = kilogramsOfFood;
    }
}
