package ua.com.javarush.tchaban.island_app.island;

import lombok.Data;

@Data
public class Position {
    int length;
    int weight;

    public Position(int length, int weight){
        this.length = length;
        this.weight = weight;
    }
}
