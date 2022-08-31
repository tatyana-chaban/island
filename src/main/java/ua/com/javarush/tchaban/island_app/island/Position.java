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

    public boolean isCorrect() {
        return (length >= 0 && length < IslandCreator.LENGTH
                && weight >= 0 && weight < IslandCreator.WIDTH);
    }
}
