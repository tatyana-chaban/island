package ua.com.javarush.tchaban.island_app.basicitem;

import lombok.Data;

@Data
public abstract class BasicItem {
    protected double weight;
    protected int maxCountOnField;
    protected boolean isAlive = true;
    protected boolean reproduceThisTurn = false;

    public abstract BasicItem newInstance();
}
