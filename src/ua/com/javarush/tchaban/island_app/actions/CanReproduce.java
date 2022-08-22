package ua.com.javarush.tchaban.island_app.actions;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;

import java.util.List;

public interface CanReproduce {
    BasicItem reproduce(Animal animal);
}
