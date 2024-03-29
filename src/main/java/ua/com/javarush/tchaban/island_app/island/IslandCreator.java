package ua.com.javarush.tchaban.island_app.island;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IslandCreator {

    public static final int LENGTH = 10;
    public static final int WIDTH = 10;

    private final ItemsCreator creator = new ItemsCreator();

    public Map<Position, List<BasicItem>> generate() {
        Map<Position, List<BasicItem>> island = new HashMap<>();
        for (int length = 0; length < LENGTH; length++) {
            for (int width = 0; width < WIDTH; width++) {
                island.put(new Position(length, width), creator.generateItems());
            }
        }
        return island;
    }
}
