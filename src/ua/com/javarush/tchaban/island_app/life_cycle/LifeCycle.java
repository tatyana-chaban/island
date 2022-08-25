package ua.com.javarush.tchaban.island_app.life_cycle;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;
import ua.com.javarush.tchaban.island_app.basicitem.plants.Herb;
import ua.com.javarush.tchaban.island_app.constants.ConstantsPlants;
import ua.com.javarush.tchaban.island_app.island.Island;
import ua.com.javarush.tchaban.island_app.island.Position;
import ua.com.javarush.tchaban.island_app.statistics.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LifeCycle {

    Island island = new Island();
    Map<Position, List<BasicItem>> map = island.generate();
    Statistics statistics = new Statistics();

    public void printCell(Position position) {
        List<BasicItem> items = map.get(position);
        for (BasicItem item : items) {
            System.out.print(item.getClass().getSimpleName() + "...");
        }
    }

    public void startLifeCycle() {
        eat();
        statistics.start(map);
        statistics.print();


    }

    public void eat() {
        for (Position initialPosition : map.keySet()) {
            List<BasicItem> basicItems = map.get(initialPosition);
            List<BasicItem> newList = new CopyOnWriteArrayList<>(basicItems);
            for (BasicItem item : newList) {
                if ((item instanceof Animal animal) && item.isAlive()) {
                    Optional<BasicItem> itemForEat = animal.eat(newList);
                    if (itemForEat.isPresent()) {
                        BasicItem item1 = itemForEat.get();
                        if (managedToEat(animal, item1)) {
                            item1.setAlive(false);
                            double nutritionalValue = item1.getWeight();
                            double currentSatiation = animal.getSatiation();
                            if (nutritionalValue >= animal.getKilogramsOfFood()) {
                                animal.setSatiation(Animal.MAX_SATIATION);
                            } else {
                                double satiationAfterEat = currentSatiation + nutritionalValue;
                                animal.setSatiation(satiationAfterEat);
                            }
                      //      removeWhoWasEaten(newList);
                        }
                    }
                }
            }
            map.replace(initialPosition, map.get(initialPosition), newList);
        }
    }

    public void moveAnimals() {
        for (Position initialPosition : map.keySet()) {
            List<BasicItem> basicItems = map.get(initialPosition);
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
                        List<BasicItem> newLocation = map.get(currentPosition);
                        newLocation.add(animal);
                        animal.setLeftLocation(true);
                    }
                    animal.setMovedThisTurn(true);
                }
            }
            map.replace(initialPosition, map.get(initialPosition), removeWhoLeftLocation(basicItems));
        }

    }

    public void reproduction() {
        for (Position initialPosition : map.keySet()) {
            List<BasicItem> basicItems = map.get(initialPosition);
            List<BasicItem> newList = new ArrayList<>(basicItems);
            for (BasicItem item : basicItems) {
                if ((item instanceof Animal animal) && item.isAlive() && !item.isReproduceThisTurn()) {
                    int MIN_NUMBER_FOR_REPRODUCE = 2;
                    int numberAnimalForReproduce = (int) basicItems.stream()
                            .filter(i -> i.getClass().equals(animal.getClass()))
                            .filter(i -> !i.isReproduceThisTurn())
                            .count();
                    if (numberAnimalForReproduce >= MIN_NUMBER_FOR_REPRODUCE && enoughSpaceForNewItem(animal, initialPosition)) {
                        animal.setReproduceThisTurn(true);
                        Optional<BasicItem> any = basicItems.stream().filter(i -> i.getClass().equals(animal.getClass()))
                                .filter(i -> !i.isReproduceThisTurn())
                                .findAny();
                        any.ifPresent(t -> t.setReproduceThisTurn(true));
                        BasicItem newItem = animal.reproduce(animal);
                        newItem.setReproduceThisTurn(true);
                        newItem.setNewborn(true);
                        newList.add(newItem);
                    }
                }
            }
            map.replace(initialPosition, map.get(initialPosition), newList);

        }
    }

    // it's too long getting out count of reflection, perhaps it is worth taking from the statistics
    private boolean enoughSpaceForNewItem(BasicItem item, Position position) {
        List<BasicItem> itemsInPosition = map.get(position);
        int countItemsOnField = (int) itemsInPosition.stream()
                .filter(t -> t.getClass().equals(item.getClass()))
                .count();
        return (item.getMaxCountOnField() >= (countItemsOnField + 1));
    }

    private List<BasicItem> removeWhoLeftLocation(List<BasicItem> items) {
        items.removeIf(t -> t instanceof Animal && ((Animal) t).isLeftLocation());
        return items;
    }

    private List<BasicItem> removeWhoWasEaten(List<BasicItem> items) {
        items.removeIf(t -> !t.isAlive());
        return items;
    }

    private boolean managedToEat(Animal hunter, BasicItem victim) {
        int ABSOLUTE_SUCCESS = 101;
        int percent = hunter.getFoodPreferences().get(victim.getClass().getSimpleName());
        int random = ThreadLocalRandom.current().nextInt(0, ABSOLUTE_SUCCESS);
        return random <= percent;
    }

    private void getHungry(List<BasicItem> basicItems) {
        basicItems.forEach(t -> {
            if (t instanceof Animal) {
                ((Animal) t).setSatiation(((Animal) t).getSatiation() - 25);
            }
        });
    }

    private void revivalPlantsOnTheIsland() {
        int MAX_COUNT_OF_HERB = ConstantsPlants.HERB_MAX_COUNT_ON_FIELD;
        for (Position initialPosition : map.keySet()) {
            List<BasicItem> basicItems = map.get(initialPosition);
            int countOfPlantsInPosition = (int) basicItems.stream()
                    .filter(i -> i instanceof Herb)
                    .count();
            int randomGenerate = ThreadLocalRandom.current().nextInt(0, MAX_COUNT_OF_HERB + 1);
            int finaCountOfPlants = countOfPlantsInPosition + randomGenerate;
            if (finaCountOfPlants > MAX_COUNT_OF_HERB) {
                finaCountOfPlants = MAX_COUNT_OF_HERB;
            }
            for (int i = 0; i < finaCountOfPlants; i++) {
                basicItems.add(new Herb());
            }
        }
    }
}


