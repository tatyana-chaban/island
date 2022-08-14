package ua.com.javarush.tchaban.island_app.animals.herbivorous;

import ua.com.javarush.tchaban.island_app.animals.Animal;

public abstract class Herbivorous extends Animal {
    Herbivorous(double weight, int maxNumOfAnimals, int speed, double kilogramsOfFood) {
        super(weight, maxNumOfAnimals, speed, kilogramsOfFood);
    }
}
