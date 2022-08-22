package ua.com.javarush.tchaban.island_app.basicitem.animals;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.com.javarush.tchaban.island_app.actions.CanReproduce;
import ua.com.javarush.tchaban.island_app.actions.Movable;
import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.island.ItemsCreator;
import ua.com.javarush.tchaban.island_app.island.Position;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public abstract class Animal extends BasicItem implements Movable, CanReproduce {
    private final int speed;
    private final double kilogramsOfFood;
    protected boolean movedThisTurn = false;
    protected boolean leftLocation = false;


    private ItemsCreator creator = new ItemsCreator();


    public Animal(double weight, int maxNumOfAnimals, int speed, double kilogramsOfFood) {
        super(weight, maxNumOfAnimals);
        this.speed = speed;
        this.kilogramsOfFood = kilogramsOfFood;
    }

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
    public BasicItem reproduce(Animal animal) {
        BasicItem newAnimal = null;
        try {
            newAnimal = creator.generateOneItem(animal);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();// add own exception
        }
        return newAnimal; // use Optional
    }
}
