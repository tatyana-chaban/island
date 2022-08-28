package ua.com.javarush.tchaban.island_app.statistics;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;
import ua.com.javarush.tchaban.island_app.basicitem.plants.Plants;
import ua.com.javarush.tchaban.island_app.island.Position;

import java.util.List;
import java.util.Map;

public class IslandStatistics {
    public static int NUMBER_OF_ITEMS = 0;
    public static int NUMBER_OF_ANIMALS = 0;
    public static int NUMBER_OF_PLANTS = 0;
    public static int NUMBER_OF_DEAD_ITEMS = 0;
    public static int NUMBER_OF_NEWBORN = 0;

    private final Map<Position, List<BasicItem>> island;

    public IslandStatistics(Map<Position, List<BasicItem>> island){
        this.island = island;
    }

    public void print() {
        System.out.println(
                "Количество существ на острове: " + NUMBER_OF_ITEMS + " из них:" +
                        "\n- растений: " + NUMBER_OF_PLANTS +
                        "\n- животных: " + NUMBER_OF_ANIMALS +
                        "\n    - умерших: " + NUMBER_OF_DEAD_ITEMS +
                        "\n    - новорожденных: " + NUMBER_OF_NEWBORN
        );

    }

    public void start() {
        for (Position initialPosition : island.keySet()) {
            List<BasicItem> basicItems = island.get(initialPosition);
            for (var item : basicItems) {
                NUMBER_OF_ITEMS++;
                if (item instanceof Animal) {
                    NUMBER_OF_ANIMALS++;
                }
                if (item instanceof Plants) {
                    NUMBER_OF_PLANTS++;
                }
                if (!item.isAlive()) {
                    NUMBER_OF_DEAD_ITEMS++;
                }
                if (item.isNewborn()) {
                    NUMBER_OF_NEWBORN++;
                }
            }
        }
    }
}

