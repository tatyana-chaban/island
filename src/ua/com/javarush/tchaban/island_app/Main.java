package ua.com.javarush.tchaban.island_app;

import ua.com.javarush.tchaban.island_app.island.Position;
import ua.com.javarush.tchaban.island_app.life_cycle.LifeCycle;


public class Main {
    public static void main(String[] args)  {
        LifeCycle lifeCycle = new LifeCycle();
        lifeCycle.printCell(new Position(5,5));
        System.out.println("-------------------------------");
        lifeCycle.startLifeCycle();
       // lifeCycle.printCell(new Position(5,5));
    }
}
