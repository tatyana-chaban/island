package ua.com.javarush.tchaban.island_app.statistics;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;
import ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous.Carnivorous;
import ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous.Herbivorous;
import ua.com.javarush.tchaban.island_app.basicitem.plants.Plants;
import ua.com.javarush.tchaban.island_app.island.Position;
import java.util.List;
import java.util.Map;

public class IslandStatistics {
    public static int ITEMS = 0;
    public static int ANIMALS = 0;
    public static int HERBIVOROUS = 0;
    public static int CARNIVOROUS = 0;
    public static int PLANTS_REGENERATED = 0;
    public static int PLANTS = 0;
    public static int DIED_OF_HUNGER = 0;
    public static int NEWBORN = 0;
    public static int EATEN = 0;

    private final Map<Position, List<BasicItem>> island;

    public IslandStatistics(Map<Position, List<BasicItem>> island) {
        this.island = island;
    }

    public void print() {
        System.out.println(
                "Количество существ на острове: " + ITEMS +
                        "\n- растений восстановилось: " + PLANTS_REGENERATED +
                        "\n- растений в конце дня: " + PLANTS +
                        "\n- животных: " + ANIMALS +
                        "\n- травоядных: " + HERBIVOROUS +
                        "\n- хищников: " + CARNIVOROUS +
                        "\n- было съедено: " + EATEN +
                        "\n- умерло от голода: " + DIED_OF_HUNGER +
                        "\n- родилось: " + NEWBORN
        );
    }

    public void getCurrentStatistics() {
        startCollecting();
        print();
        reset();
    }

    public void startCollecting() {
        for (var pair : island.entrySet()) {
            for (var item : pair.getValue()) {
                ITEMS++;
                if (item instanceof Animal) {
                    ANIMALS++;
                    if (item instanceof Herbivorous) {
                        HERBIVOROUS++;
                    } else if (item instanceof Carnivorous) {
                        CARNIVOROUS++;
                    }
                } else if (item instanceof Plants) {
                    PLANTS++;
                }
            }
        }
    }

    public void reset() {
        ITEMS = 0;
        ANIMALS = 0;
        HERBIVOROUS = 0;
        CARNIVOROUS = 0;
        PLANTS_REGENERATED = 0;
        PLANTS = 0;
        DIED_OF_HUNGER = 0;
        NEWBORN = 0;
        EATEN = 0;
    }
}
