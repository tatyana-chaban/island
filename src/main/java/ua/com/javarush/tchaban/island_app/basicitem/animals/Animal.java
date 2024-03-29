package ua.com.javarush.tchaban.island_app.basicitem.animals;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.javarush.tchaban.island_app.actions.AnimalAbilities;
import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.island.Position;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class Animal extends BasicItem implements AnimalAbilities {
    public static final double MAX_SATIATION = 100.0;
    protected int speed;
    protected double kilogramsToSaturate;
    protected double satiation = MAX_SATIATION;

    protected Map<String, Integer> foodPreferencesAndProbabilities = new HashMap<>();

    @Override
    public Position move(Position currentPosition) {
        final int UP = 1;
        final int RIGHT = 2;
        final int DOWN = 3;
        final int LEFT = 4;

        int length = currentPosition.getLength();
        int weight = currentPosition.getWeight();
        int randomDirection = new Random().nextInt(1, 5);

        if (randomDirection == UP) {
            length--;
        } else if (randomDirection == RIGHT) {
            weight++;
        } else if (randomDirection == DOWN) {
            length++;
        } else if (randomDirection == LEFT) {
            weight--;
        }

        return new Position(length, weight);
    }

    @Override
    public Optional<BasicItem> searchForFood(List<BasicItem> availableItems) {
        return availableItems.stream()
                .filter(item -> foodPreferencesAndProbabilities.containsKey(item.getName()))
                .findAny();
    }
}
