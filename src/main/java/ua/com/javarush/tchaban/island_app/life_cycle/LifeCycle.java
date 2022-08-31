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
    
    public void newDay() {
        startTheDay();
        mainActions();
        endTheDay();
    }

    private void startTheDay() {
        getHungry();
        checkSatiationLevel();
        revivalPlantOnTheIsland();
        removeWhoDied();
    }
    
    private void mainActions() {
        eat();
        reproduction();
        moveAnimals();
    }

    private void endTheDay() {
        statistics.getCurrentStatistics();
        resetFlags();
    }

    private void eat() {
        for (var pair : island.entrySet()) {
            List<BasicItem> basicItems = new CopyOnWriteArrayList<>(pair.getValue());
            for (BasicItem item : basicItems) {
                if ((item instanceof Animal animal) && item.isAlive()) {
                    Optional<BasicItem> itemForEat = animal.searchForFood(basicItems);
                    if (itemForEat.isPresent()) {
                        BasicItem eatenItem = itemForEat.get();
                        if (managedToEat(animal, eatenItem)) {
                            getSaturated(animal, eatenItem);
                            eatenItem.setAlive(false);
                            IslandStatistics.EATEN++;
                            basicItems.remove(eatenItem);
                        }
                    }
                }
            }
            island.replace(pair.getKey(), pair.getValue(), new ArrayList<>(basicItems));
        }
    }

    private void moveAnimals() {
        for (var pair : island.entrySet()) {
            Position initialPosition = pair.getKey();
            List<BasicItem> basicItems = pair.getValue();
            Iterator<BasicItem> iterator = basicItems.listIterator();
            if (iterator.hasNext()) {
                BasicItem item = iterator.next();
                if ((item instanceof Animal animal) && item.isAlive()) {
                    Position newPosition;
                    Position currentPosition = initialPosition;
                    for (int i = 0; i < animal.getSpeed(); i++) {
                        newPosition = animal.move(currentPosition);
                        if (newPosition.isCorrect() && enoughSpaceForNewItem(item, newPosition)) {
                            currentPosition = newPosition;
                        }
                    }
                    if (!currentPosition.equals(initialPosition)) {
                        List<BasicItem> newLocation = island.get(currentPosition);
                        newLocation.add(animal);
                        iterator.remove();
                    }
                }
            }
        }
    }

    private void reproduction() {
        for (var pair : island.entrySet()) {
            List<BasicItem> basicItems = pair.getValue();
            List<BasicItem> newList = new ArrayList<>(basicItems); 
            for (BasicItem item : basicItems) {
                if (canReproduce(item, pair.getKey())) {
                    item.setReproduceThisTurn(true);
                    if (findCouple(basicItems, item)) {
                        BasicItem newItem = item.makeCopy();
                        newItem.setReproduceThisTurn(true);
                        newItem.setNewborn(true);
                        newList.add(newItem);
                        IslandStatistics.NEWBORN++;
                    }
                }
            }
            island.replace(pair.getKey(), pair.getValue(), newList);
        }
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

    private boolean canReproduce(BasicItem item, Position position) {
        return item instanceof Animal animal
                && item.isAlive()
                && !item.isReproduceThisTurn()
                && enoughAnimalsToReproduce(animal, position)
                && enoughSpaceForNewItem(animal, position);
    }

    private boolean findCouple(List<BasicItem> basicItems, BasicItem item) {
        Optional<BasicItem> any = basicItems.stream()
                .filter(basicItem -> basicItem.getName().equals(item.getName()))
                .filter(basicItem -> !basicItem.isReproduceThisTurn())
                .findAny();
        if (any.isPresent()) {
            any.ifPresent(basicItem -> basicItem.setReproduceThisTurn(true));
            return true;
        }else {
            return false;
        }
    }

    private void removeWhoDied() {
        for (var pair : island.entrySet()) {
            List<BasicItem> items = pair.getValue();
            items.removeIf(item -> !item.isAlive());
        }
    }

    private boolean managedToEat(Animal hunter, BasicItem victim) {
        int ABSOLUTE_SUCCESS = 101;
        int percent = hunter.getFoodPreferencesAndProbabilities().get(victim.getName());
        int random = ThreadLocalRandom.current().nextInt(ABSOLUTE_SUCCESS);
        return random <= percent;
    }

    private void getSaturated(Animal animal, BasicItem eatenItem) {
        double eatenItemWeight = eatenItem.getWeight();
        double satiationAfterEat = calculateSatiationAfterEat(animal, eatenItemWeight);
        if (eatenItemWeight >= animal.getKilogramsToSaturate()) {
            animal.setSatiation(Animal.MAX_SATIATION);
        } else {
            animal.setSatiation(satiationAfterEat);
        }
    }

    private double calculateSatiationAfterEat(Animal animal, double weight) {
        return (weight * 100) / animal.getKilogramsToSaturate() + animal.getSatiation();
    }

    private void getHungry() {
        double energyToSustainLife = 25.0;
        for (var pair : island.entrySet()) {
            List<BasicItem> basicItems = pair.getValue();
            basicItems.forEach(item -> {
                if (item instanceof Animal animal) {
                    animal.setSatiation(animal.getSatiation() - energyToSustainLife);
                }
            });
        }
    }

    private void checkSatiationLevel() {
        for (var pair : island.entrySet()) {
            List<BasicItem> basicItems = pair.getValue();
            basicItems.forEach(item -> {
                if (item instanceof Animal animal) {
                    double currentSatiation = animal.getSatiation();
                    if (currentSatiation <= 0.0) {
                        animal.setAlive(false);
                        //  basicItems.remove(item);
                        IslandStatistics.DIED_OF_HUNGER++;
                    }
                }
            });
        }
    }

    private void revivalPlantOnTheIsland() {
        for (var pair : island.entrySet()) {
            List<BasicItem> items = pair.getValue();
            itemsCreator.regenerationOfPlants(items);

        }
    }

    private void resetFlags() {
        for (var pair : island.entrySet()) {
            for (var item : pair.getValue()) {
                item.setNewborn(false);
                item.setReproduceThisTurn(false);
            }
        }
    }
}
