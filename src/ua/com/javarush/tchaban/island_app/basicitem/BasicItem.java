package ua.com.javarush.tchaban.island_app.basicitem;

import lombok.Data;

@Data
public abstract class BasicItem {
    private final double weight;
    private final int maxCountOnField;

    protected boolean isAlive = true;

    public BasicItem(double weight, int maxCountOnField){
        this.weight = weight;
        this.maxCountOnField = maxCountOnField;
    }
}
