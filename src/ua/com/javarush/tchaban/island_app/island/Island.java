package ua.com.javarush.tchaban.island_app.island;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Island {

    public static final int LENGTH = 100;
    public static final int WIDTH = 20;


    public Map<Position, List<BasicItem>> generateIsland() {
        Map<Position, List<BasicItem>> island = new HashMap<>();
        for (int length = 0; length < LENGTH; length++) {
            for (int width = 0; width < WIDTH; width++) {
                island.put(new Position(length, width), generateItems());
            }
        }
        return island;
    }


    private List<BasicItem> generateItems() {
        List<BasicItem> generated = new ArrayList<>();
        ItemsCreator creator = new ItemsCreator();
        var items = creator.getItems();
        try {
            generated = creator.generateItems(items);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace(); // throw own exception
        }
        return generated;
    }
}
