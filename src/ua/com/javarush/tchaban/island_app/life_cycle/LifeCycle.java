package ua.com.javarush.tchaban.island_app.life_cycle;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;
import ua.com.javarush.tchaban.island_app.island.IslandCreator;
import ua.com.javarush.tchaban.island_app.island.ItemsCreator;
import ua.com.javarush.tchaban.island_app.island.Position;
import ua.com.javarush.tchaban.island_app.statistics.IslandStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LifeCycle {

    ItemsCreator itemsCreator = new ItemsCreator();
    IslandCreator islandCreator = new IslandCreator();
    Map<Position, List<BasicItem>> island = islandCreator.generate();
    IslandStatistics statistics = new IslandStatistics(island);


    public void printCell(Position position) {
        List<BasicItem> items = island.get(position);
        for (BasicItem item : items) {
            System.out.print(item.getClass().getSimpleName() + "...");
        }
    }

    public void startLifeCycle() {
        revivalPlantsOnTheIsland();

    }

    public void eat() {
        for (Position initialPosition : island.keySet()) {
            List<BasicItem> basicItems = island.get(initialPosition);
            List<BasicItem> newList = new CopyOnWriteArrayList<>(basicItems);
            for (BasicItem item : newList) {
                if ((item instanceof Animal animal) && item.isAlive()) {
                    Optional<BasicItem> itemForEat = animal.searchForFood(newList);
                    if (itemForEat.isPresent()) {
                        BasicItem eatenItem = itemForEat.get();
                        if (managedToEat(animal, eatenItem)) {
                            eatenItem.setAlive(false);
                            double nutritionalValue = eatenItem.getWeight();
                            double currentSatiation = animal.getSatiation();
                            if (nutritionalValue >= animal.getKilogramsOfFood()) {
                                animal.setSatiation(Animal.MAX_SATIATION);
                            } else {
                                double satiationAfterEat = currentSatiation + nutritionalValue;
                                animal.setSatiation(satiationAfterEat);
                            }
                            removeDeadItems(newList);
                        }
                    }
                }
            }
            island.replace(initialPosition, island.get(initialPosition), newList);
        }
    }

    public void moveAnimals() {
        for (Position initialPosition : island.keySet()) {
            List<BasicItem> basicItems = island.get(initialPosition);
            for (BasicItem item : basicItems) {
                if ((item instanceof Animal animal) && item.isAlive() && !animal.isMovedThisTurn()) {
                    Position somePosition;
                    Position currentPosition = initialPosition;
                    for (int i = 0; i < animal.getSpeed(); i++) {
                        somePosition = animal.move(currentPosition);
                        if (somePosition.isCorrect() && enoughSpaceForNewItem(item, somePosition)) {
                            currentPosition = somePosition;
                        }
                    }
                    if (currentPosition.equals(initialPosition)) {
                        animal.setLeftLocation(false);
                    } else {
                        List<BasicItem> newLocation = island.get(currentPosition);
                        newLocation.add(animal);
                        animal.setLeftLocation(true);
                    }
                    animal.setMovedThisTurn(true);
                }
            }
            island.replace(initialPosition, island.get(initialPosition), removeWhoLeftLocation(basicItems));
        }

    }

    public void reproduction() {
        for (Position initialPosition : island.keySet()) {
            List<BasicItem> basicItems = island.get(initialPosition);
            List<BasicItem> newList = new ArrayList<>(basicItems);
            for (BasicItem item : basicItems) {
                if ((item instanceof Animal animal) && item.isAlive() && !item.isReproduceThisTurn()) {
                    int MIN_AMOUNT_FOR_REPRODUCE = 2;
                    int amountAnimalForReproduce = (int) basicItems.stream()
                            .filter(basicItem -> basicItem.getClass().equals(animal.getClass()))
                            .filter(basicItem -> !basicItem.isReproduceThisTurn())
                            .count();
                    if (amountAnimalForReproduce >= MIN_AMOUNT_FOR_REPRODUCE
                            && enoughSpaceForNewItem(animal, initialPosition)) {
                        animal.setReproduceThisTurn(true);
                        Optional<BasicItem> pair = basicItems.stream()
                                .filter(basicItem -> basicItem.getClass().equals(animal.getClass()))
                                .filter(basicItem -> !basicItem.isReproduceThisTurn())
                                .findAny();
                        pair.ifPresent(basicItem -> basicItem.setReproduceThisTurn(true));
                        BasicItem newItem = animal.makeCopy();
                        newItem.setReproduceThisTurn(true);
                        newItem.setNewborn(true);
                        newList.add(newItem);
                    }
                }
            }
            island.replace(initialPosition, island.get(initialPosition), newList);

        }
    }

    // it's too long getting out count of reflection, perhaps it is worth taking from the statistics
    private boolean enoughSpaceForNewItem(BasicItem sample, Position position) {
        List<BasicItem> itemsInPosition = island.get(position);
        int amountItemsOnField = (int) itemsInPosition.stream()
                .filter(item -> item.getClass().equals(sample.getClass()))
                .count();
        int amountAfterAddition = amountItemsOnField + 1;
        return (sample.getMaxCountOnField() >= amountAfterAddition);

    }

    private List<BasicItem> removeWhoLeftLocation(List<BasicItem> items) {
        items.removeIf(item -> item instanceof Animal && ((Animal) item).isLeftLocation());
        return items;
    }

    private void removeDeadItems(List<BasicItem> items) {
        items.removeIf(item -> !item.isAlive());
    }

    private boolean managedToEat(Animal hunter, BasicItem victim) {
        int ABSOLUTE_SUCCESS = 101;
        int percent = hunter.getFoodPreferences().get(victim.getClass().getSimpleName());
        int random = ThreadLocalRandom.current().nextInt(0, ABSOLUTE_SUCCESS);
        return random <= percent;
    }

    private void getHungry(List<BasicItem> basicItems) {
        int energyToSustainLife = 25;
        basicItems.forEach(item -> {
            if (item instanceof Animal animal) {
                animal.setSatiation(animal.getSatiation() - energyToSustainLife);
            }
        });
    }

    private void revivalPlantsOnTheIsland() {
        for (Position initialPosition : island.keySet()) {
            List<BasicItem> basicItems = island.get(initialPosition);
            itemsCreator.regenerationOfPlants(basicItems);
        }
    }

    private void checkSatiationLevel(List<BasicItem> basicItems) {
        basicItems.forEach(item -> {
            if (item instanceof Animal animal) {
                double currentSatiation = animal.getSatiation();
                if (currentSatiation <= 0.0){
                    animal.setAlive(false);
                }
            }
        });
    }
}


