package ua.com.javarush.tchaban.island_app.basicitem.animals;
import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;


public abstract class Animal extends BasicItem {
    public Animal(double weight, int maxNumOfAnimals, int speed, double kilogramsOfFood){
        super(weight,maxNumOfAnimals,speed,kilogramsOfFood);
    }
}
