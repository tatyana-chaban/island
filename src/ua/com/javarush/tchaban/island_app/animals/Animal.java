package ua.com.javarush.tchaban.island_app.animals;


import lombok.Data;

@Data
public abstract class Animal {
    private double weight;
    private int maxCountOnField;
    private int speed;
    private double kilogramsOfFood;

    public Animal(double weight, int maxNumOfAnimals, int speed, double kilogramsOfFood){
        this.weight = weight;
        this.maxCountOnField = maxNumOfAnimals;
        this.speed = speed;
        this.kilogramsOfFood = kilogramsOfFood;
    }
}
