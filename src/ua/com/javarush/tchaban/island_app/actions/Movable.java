package ua.com.javarush.tchaban.island_app.actions;

import ua.com.javarush.tchaban.island_app.island.Position;

public interface Movable {
    Position move(Position position);
}
