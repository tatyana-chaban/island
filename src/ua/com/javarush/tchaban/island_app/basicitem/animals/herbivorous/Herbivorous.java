package ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous;

import lombok.NoArgsConstructor;
import ua.com.javarush.tchaban.island_app.basicitem.animals.Animal;

@NoArgsConstructor
public abstract class Herbivorous extends Animal {
    {
        foodPreferences.put("Herb", 100);
    }

}
