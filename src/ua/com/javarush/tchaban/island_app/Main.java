package ua.com.javarush.tchaban.island_app;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.island.ItemsCreator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       ItemsCreator animalCreator = new ItemsCreator();
        List<BasicItem> basicItemList = animalCreator.generateItems(animalCreator.getItems());
        for (var item: basicItemList) {
            System.out.println(item.getClass());
        }
    }
}
