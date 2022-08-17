package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;

public abstract class Herbivorous extends Animal {
    public Herbivorous(double weight, int maxNumOfAnimals, int speed, double kilogramsOfFood) {
        super(weight, maxNumOfAnimals, speed, kilogramsOfFood);
    }
}
