package ua.com.javarush.tchaban.island_app.life_cycle;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;
import ua.com.javarush.tchaban.island_app.island.IslandCreator;
import ua.com.javarush.tchaban.island_app.island.ItemsCreator;
import ua.com.javarush.tchaban.island_app.island.Position;
import ua.com.javarush.tchaban.island_app.statistics.IslandStatistics;

import java.util.*;
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
            System.out.print(item.getName() + "...");
        }
    }

    public void test() {
        statistics.getCurrentStatistics();
        for (int i = 0; i < 20; i++) {
            for (var pair : island.entrySet()) {
                eat(pair);
            }
            for (var pair : island.entrySet()) {
                reproduction(pair);
            }
            for (var pair : island.entrySet()) {
                moveAnimals(pair);
            }
            statistics.getCurrentStatistics();
        }
    }

    public void newDay() {
        startTheDay();
        mainActions();
        endTheDay();
    }

    public void startTheDay() {
        for (var pair : island.entrySet()) {
            List<BasicItem> items = pair.getValue();
            getHungry(items);
            checkSatiationLevel(items);
            removeWhoDied(items);
            itemsCreator.regenerationOfPlants(items);
        }
    }

    public void mainActions() {
        for (var pair : island.entrySet()) {
            eat(pair);
        }
        for (var pair : island.entrySet()) {
            reproduction(pair);
        }
        for (var pair : island.entrySet()) {
            moveAnimals(pair);
        }
    }

    public void endTheDay() {
        statistics.startCollecting();
        statistics.print();
        for (var pair : island.entrySet()) {
            List<BasicItem> items = pair.getValue();
            removeWhoDied(items);
            resetFlags(items);
        }
        statistics.reset();
    }

    public void eat(Map.Entry<Position, List<BasicItem>> pair) {
        List<BasicItem> basicItems = new CopyOnWriteArrayList<>(pair.getValue());
        for (BasicItem item : basicItems) {
            if ((item instanceof Animal animal) && item.isAlive()) {
                Optional<BasicItem> itemForEat = animal.searchForFood(basicItems);
                if (itemForEat.isPresent()) {
                    BasicItem eatenItem = itemForEat.get();
                    if (managedToEat(animal, eatenItem)) {
                        eatenItem.setAlive(false);
                        IslandStatistics.EATEN++;
                        double nutritionalValue = eatenItem.getWeight();
                        double currentSatiation = animal.getSatiation();
                        if (nutritionalValue >= animal.getKilogramsToSaturate()) {
                            animal.setSatiation(Animal.MAX_SATIATION);
                        } else {
                            double satiationAfterEat = currentSatiation + nutritionalValue;
                            animal.setSatiation(satiationAfterEat);
                        }
                        removeWhoDied(basicItems);
                    }
                }
            }
        }
        island.replace(pair.getKey(), pair.getValue(), basicItems);
    }


    public void moveAnimals(Map.Entry<Position, List<BasicItem>> pair) {
        Position initialPosition = pair.getKey();
        List<BasicItem> copyBasicItems = new CopyOnWriteArrayList<>(pair.getValue());
        for (BasicItem item : copyBasicItems) {
            if ((item instanceof Animal animal) && item.isAlive()) {
                Position somePosition;
                Position currentPosition = initialPosition;
                for (int i = 0; i < animal.getSpeed(); i++) {
                    somePosition = animal.move(currentPosition);
                    if (somePosition.isCorrect() && enoughSpaceForNewItem(item, somePosition)) {
                        currentPosition = somePosition;
                    }
                }
                if (!currentPosition.equals(initialPosition)) {
                    List<BasicItem> newLocation = island.get(currentPosition);
                    newLocation.add(animal);
                    copyBasicItems.remove(animal);
                }
            }
        }
        island.replace(initialPosition, island.get(initialPosition), copyBasicItems);
    }

    public void reproduction(Map.Entry<Position, List<BasicItem>> pair) {
        List<BasicItem> basicItems = pair.getValue();
        List<BasicItem> newList = new ArrayList<>(basicItems);
        for (BasicItem item : basicItems) {
            if ((item instanceof Animal animal) && item.isAlive() && !item.isReproduceThisTurn()) {
                if (enoughAnimalsToReproduce(animal, pair.getKey())
                        && enoughSpaceForNewItem(animal, pair.getKey())) {
                    animal.setReproduceThisTurn(true);
                    Optional<BasicItem> pairForReproduce = basicItems.stream()
                            .filter(basicItem -> basicItem.getName().equals(animal.getName()))
                            .filter(basicItem -> !basicItem.isReproduceThisTurn())
                            .findAny();
                    pairForReproduce.ifPresent(basicItem -> basicItem.setReproduceThisTurn(true));
                    BasicItem newItem = animal.makeCopy();
                    newItem.setReproduceThisTurn(true);
                    newItem.setNewborn(true);
                    newList.add(newItem);
                    IslandStatistics.NEWBORN++;
                }
            }
        }
        island.replace(pair.getKey(), pair.getValue(), newList);
    }

    private boolean enoughSpaceForNewItem(BasicItem sample, Position position) {
        List<BasicItem> itemsInPosition = island.get(position);
        int amountItemsOnLocation = (int) itemsInPosition.stream()
                .filter(item -> item.getName().equals(sample.getName()))
                .count();
        int amountAfterAddition = amountItemsOnLocation + 1;
        return (sample.getMaxNumberPerLocation() >= amountAfterAddition);
    }

    private boolean enoughAnimalsToReproduce(BasicItem sample, Position position) {
        List<BasicItem> itemsInPosition = island.get(position);
        int MIN_AMOUNT_FOR_REPRODUCE = 2;
        int amountAnimalsForReproduce = (int) itemsInPosition.stream()
                .filter(item -> item.getName().equals(sample.getName()))
                .filter(item -> !item.isReproduceThisTurn())
                .count();
        return (amountAnimalsForReproduce >= MIN_AMOUNT_FOR_REPRODUCE);
    }

    private void removeWhoDied(List<BasicItem> items) {
        items.removeIf(item -> !item.isAlive());
    }

    private boolean managedToEat(Animal hunter, BasicItem victim) {
        int ABSOLUTE_SUCCESS = 101;
        int percent = hunter.getFoodPreferencesAndProbabilities().get(victim.getName());
        int random = ThreadLocalRandom.current().nextInt(ABSOLUTE_SUCCESS);
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

    private void checkSatiationLevel(List<BasicItem> basicItems) {
        basicItems.forEach(item -> {
            if (item instanceof Animal animal) {
                double currentSatiation = animal.getSatiation();
                if (currentSatiation <= 0.0) {
                    animal.setAlive(false);
                    IslandStatistics.DIED_OF_HUNGER++;
                }
            }
        });
    }

    private void resetFlags(List<BasicItem> basicItems) {
        for (var item : basicItems) {
            item.setNewborn(false);
            item.setReproduceThisTurn(false);
        }
    }
}
