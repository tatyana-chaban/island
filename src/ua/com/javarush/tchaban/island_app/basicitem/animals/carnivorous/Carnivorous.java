package ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous;

import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;

public abstract class Carnivorous extends Animal {
    public Carnivorous(double weight, int maxNumOfAnimals, int speed, double kilogramsOfFood){
        super(weight,maxNumOfAnimals,speed,kilogramsOfFood);
    }

}
