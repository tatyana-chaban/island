package ua.com.javarush.tchaban.island_app.island;

import ua.com.javarush.tchaban.island_app.basicitem.BasicItem;
import ua.com.javarush.tchaban.island_app.basicitem.animals.carnivorous.*;
import ua.com.javarush.tchaban.island_app.basicitem.animals.herbivorous.*;
import ua.com.javarush.tchaban.island_app.basicitem.plants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ItemsCreator {

    public List<BasicItem> getItemsTypes() {
        List<BasicItem> items = new ArrayList<>();
        items.add(new Bear());
        items.add(new Boa());
        items.add(new Eagle());
        items.add(new Fox());
        items.add(new Wolf());

        items.add(new Boar());
        items.add(new Buffalo());
        items.add(new Caterpillar());
        items.add(new Deer());
        items.add(new Duck());
        items.add(new Goat());
        items.add(new Horse());
        items.add(new Mouse());
        items.add(new Rabbit());
        items.add(new Sheep());

        items.add(new Herb());

        return items;
    }


    public List<BasicItem> generateItems(List<BasicItem> items) {
        List<BasicItem> basicItemList = new ArrayList<>();
        for (BasicItem item : items) {
            int count = item.getMaxCountOnField();
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int randomCount = random.nextInt(0, count);
            for (int i = 0; i <= randomCount; i++) {
                BasicItem someItem = item.newInstance();
                basicItemList.add(someItem);
            }
        }
        return basicItemList;
    }

}
