package ua.com.javarush.tchaban.island_app.life_cycle;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;
import ua.com.javarush.tchaban.island_app.island.Island;
import ua.com.javarush.tchaban.island_app.island.Position;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LifeCycle {

    Island island = new Island();
    Map<Position, List<BasicItem>> map = island.generateIsland();

    public void printCell(Position position) {
        List<BasicItem> items = map.get(position);
        for (BasicItem item: items) {
            System.out.println(item.getClass().getSimpleName());
        }
    }

    public void moveAnimals() {
        for (Position initialPosition : map.keySet()) {
            List<BasicItem> basicItems = map.get(initialPosition);
            for (BasicItem item : basicItems) {
                if ((item instanceof Animal animal) && item.isAlive()) {
                    if (!animal.isMovedInThisTurn()) {
                        Position somePosition;
                        Position currentPosition = initialPosition;
                        for (int i = 0; i < animal.getSpeed(); i++) {
                            somePosition = animal.move(currentPosition);
                            if (positionIsTrue(somePosition) && enoughSpaceForNewItem(item, somePosition)) {
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


                        ((Animal) item).setMovedInThisTurn(true);
                    }

                }
            }
            map.replace(initialPosition, map.get(initialPosition), removeWhoLeftLocation(basicItems));
        }

    }
// move this method into class Position (somePosition.isTrue or some.position.isCorrect)
    private boolean positionIsTrue(Position position) {
        return (position.getLength() >= 0 && position.getLength() < Island.LENGTH
                && position.getWeight() >= 0 && position.getWeight() < Island.WIDTH);
    }

    // it's too long getting out count of reflection, perhaps it is worth taking from the statistics
    private boolean enoughSpaceForNewItem(BasicItem item, Position position){
        List<BasicItem> itemsInPosition = map.get(position);
        int countItemsOnField = (int) itemsInPosition.stream()
                .filter(t -> t.getClass().equals(item.getClass()))
                .count();
        return  (item.getMaxCountOnField() >= (countItemsOnField + 1));
    }

    private List<BasicItem> removeWhoLeftLocation(List<BasicItem> items){
        items.removeIf(t -> t instanceof Animal && ((Animal) t).isLeftLocation());
        return items;
    }
}
