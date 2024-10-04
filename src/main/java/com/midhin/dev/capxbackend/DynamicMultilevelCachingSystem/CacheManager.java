package com.midhin.dev.capxbackend.DynamicMultilevelCachingSystem;

import java.util.*;

class CacheManager {
    private final List<CacheLevel> cacheLevels = new ArrayList<>();

    public void addCacheLevel(int size, String evictionPolicy) {
        cacheLevels.add(new CacheLevel(size, evictionPolicy));
    }

    public String get(String key) {
        for (CacheLevel level : cacheLevels) {
            String value = level.get(key);
            if (value != null) {
                promoteToHigherLevels(key, value);
                return value;
            }
        }
        // Simulate fetching from main memory
        String valueFromMemory = "DataFromMemory"; // Simulated value
        put(key, valueFromMemory);
        return valueFromMemory;
    }

    private void promoteToHigherLevels(String key, String value) {
        for (int i = 0; i < cacheLevels.size(); i++) {
            CacheLevel level = cacheLevels.get(i);
            if (i == 0 || level.containsKey(key)) { // Skip L1 level, already contains it
                continue;
            }
            level.put(key, value);
        }
    }

    public void put(String key, String value) {
        if (!cacheLevels.isEmpty()) {
            cacheLevels.get(0).put(key, value); // Always insert into L1
        }
    }

    public void removeCacheLevel(int index) {
        if (index >= 0 && index < cacheLevels.size()) {
            cacheLevels.remove(index);
        }
    }

    public void displayCache() {
        for (int i = 0; i < cacheLevels.size(); i++) {
            System.out.print("L" + (i + 1) + " ");
            cacheLevels.get(i).display();
        }
    }
}
