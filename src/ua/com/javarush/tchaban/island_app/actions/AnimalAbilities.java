package ua.com.javarush.tchaban.island_app.actions;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;
import ua.com.javarush.tchaban.island_app.island.Position;

import java.util.List;
import java.util.Optional;

public interface AnimalAbilities {
    Optional<BasicItem> searchForFood(List<BasicItem> availableItems);
    Position move(Position currentPosition);
}
