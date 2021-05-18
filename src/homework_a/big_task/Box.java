package homework_a.big_task;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {

    private ArrayList<T> fruits;
    private float weight;

    public Box(T... items) {
        this.fruits = new ArrayList<>(Arrays.asList(items));
    }

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public ArrayList<T> getFruits() {
        return fruits;
    }

    private float getWeight() {
        for (int i = 0; i < fruits.size(); i++) {
            weight += fruits.get(i).getWeight();
        }
        return weight;
    }

    private void addFruits(T... items) {
        fruits.addAll(Arrays.asList(items));
    }

    private boolean compare(Box<?> box) {
        return Math.abs(box.getWeight() - this.getWeight()) < 0.00001f;
    }

    private void replaceFruits(Box<T> dest) {
        dest.getFruits().addAll(fruits);
        fruits.clear();
    }

}
