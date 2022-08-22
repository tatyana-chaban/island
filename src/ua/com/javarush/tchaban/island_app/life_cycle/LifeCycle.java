package ua.com.javarush.tchaban.island_app.life_cycle;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;
import ua.com.javarush.tchaban.island_app.island.Island;
import ua.com.javarush.tchaban.island_app.island.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LifeCycle {

    Island island = new Island();
    Map<Position, List<BasicItem>> map = island.generateIsland();

    public void printCell(Position position) {
        List<BasicItem> items = map.get(position);
        for (BasicItem item : items) {
            System.out.print(item.getClass().getSimpleName() + "...");
        }
    }

    public void startLifeCycle() {


    }

    public void eat() {

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
                if ((item instanceof Animal animal) && item.isAlive()) {
                    int MIN_NUMBER_FOR_REPRODUCE = 2;
                    int numberAnimalForReproduce = (int) basicItems.stream()
                            .filter(i -> i.getClass().equals(animal.getClass()))
                            .filter(i -> ! i.isReproduceThisTurn())
                            .count();
                    if (numberAnimalForReproduce >= MIN_NUMBER_FOR_REPRODUCE && enoughSpaceForNewItem(animal, initialPosition)) {
                        animal.setReproduceThisTurn(true);
                        Optional<BasicItem> any = basicItems.stream().filter(i -> i.getClass().equals(animal.getClass()))
                                .filter(i -> !i.isReproduceThisTurn())
                                .findAny();
                        any.ifPresent(t -> t.setReproduceThisTurn(true));
                        BasicItem newItem = animal.reproduce(animal);
                        newItem.setReproduceThisTurn(true);
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
}
