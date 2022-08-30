package ua.com.javarush.tchaban.island_app.island;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous.*;
import ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous.*;
import ua.com.javarush.tchaban.island_app.basicitem.plants.*;
import ua.com.javarush.tchaban.island_app.statistics.IslandStatistics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ItemsCreator {
    private final List<BasicItem> typesOfItems = getItemsTypes();
    private final List<BasicItem> typesOfPlants = getPlantsTypes();

    public List<BasicItem> generateItems() {
        List<BasicItem> itemsOnField = new ArrayList<>();
        for (BasicItem item : typesOfItems) {
            int maxCountOnField = item.getMaxNumberPerLocation();
            int randomCount = ThreadLocalRandom.current().nextInt(0, maxCountOnField);
            for (int i = 0; i <= randomCount; i++) {
                BasicItem newItem = item.makeCopy();
                itemsOnField.add(newItem);
            }
        }
        return itemsOnField;
    }

    public void regenerationOfPlants(List<BasicItem> items) {
        for (BasicItem plant : typesOfPlants) {
            int maxCount = plant.getMaxNumberPerLocation() + 1;
            int countOfThisPlantInPosition = (int) items.stream()
                    .filter(item -> item.getName().equals(plant.getName()))
                    .count();
            int randomCount = ThreadLocalRandom.current().nextInt(0, maxCount);
            int finaCountOfPlant = countOfThisPlantInPosition + randomCount;
            if (finaCountOfPlant > plant.getMaxNumberPerLocation()) {
                finaCountOfPlant = plant.getMaxNumberPerLocation();
            }
            IslandStatistics.PLANTS_REGENERATED += finaCountOfPlant;
            for (int i = 0; i <= finaCountOfPlant; i++) {
                items.add(plant.makeCopy());
            }
        }
    }

    private List<BasicItem> getItemsTypes() {
        List<BasicItem> items = new ArrayList<>();
        items.add(new Bear());
        items.add(new Boa());
        items.add(new Eagle());
        items.add(new Fox());
        items.add(new Wolf());

        items.add(new Boar());
        items.add(new Buffalo());
        items.add(new Caterpillar());
        items.add(new Deer());
        items.add(new Duck());
        items.add(new Goat());
        items.add(new Horse());
        items.add(new Mouse());
        items.add(new Rabbit());
        items.add(new Sheep());

        items.add(new Herb());

        return items;
    }

    private List<BasicItem> getPlantsTypes() {
        List<BasicItem> plants = new ArrayList<>();
        plants.add(new Herb());
        return plants;
    }
}
