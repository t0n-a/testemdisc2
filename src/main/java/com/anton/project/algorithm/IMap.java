package com.anton.project.algorithm;

import java.util.ArrayList;
import java.util.List;

public class IMap {
    private List<String> keys;
    private List<Integer> values;

    public IMap(int initialCapacity) {
        keys = new ArrayList<>(initialCapacity);
        values = new ArrayList<>(initialCapacity);
    }

    public void put(String key, int value) {
        int index = keys.indexOf(key);
        if (index != -1) {
            values.set(index, value);
        } else {
            keys.add(key);
            values.add(value);
        }
    }

    public Integer get(String key) {
        int index = keys.indexOf(key);
        return index != -1 ? values.get(index) : null;
    }

    public boolean contains(String key) {
        return keys.contains(key);
    }

    public int size() {
        return keys.size();  // Returns the number of unique keys in the map
    }
}
