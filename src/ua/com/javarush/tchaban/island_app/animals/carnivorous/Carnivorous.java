package ua.com.javarush.tchaban.island_app.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.animals.Animal;

public abstract class Carnivorous extends Animal {
    Carnivorous(double weight, int maxNumOfAnimals, int speed, double kilogramsOfFood){
        super(weight,maxNumOfAnimals,speed,kilogramsOfFood);
    }

}
