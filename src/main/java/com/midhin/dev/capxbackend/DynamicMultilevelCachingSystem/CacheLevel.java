package com.midhin.dev.capxbackend.DynamicMultilevelCachingSystem;

import java.util.*;

class CacheLevel {
    private final int size;
    private final String evictionPolicy;
    private final Map<String, String> cache;
    private final LinkedHashMap<String, Integer> frequencyMap; // For LFU
    private final List<String> lruOrder; // For LRU

    public CacheLevel(int size, String evictionPolicy) {
        this.size = size;
        this.evictionPolicy = evictionPolicy;
        this.cache = new HashMap<>();
        this.frequencyMap = new LinkedHashMap<>();
        this.lruOrder = new ArrayList<>();
    }

    public String get(String key) {
        if (!cache.containsKey(key)) {
            return null;
        }

        // Update frequency or LRU order
        if (evictionPolicy.equals("LFU")) {
            frequencyMap.put(key, frequencyMap.get(key) + 1);
        } else { // LRU
            lruOrder.remove(key);
            lruOrder.add(key);
        }
        return cache.get(key);
    }

    public void put(String key, String value) {
        if (cache.size() >= size) {
            evict();
        }
        cache.put(key, value);
        frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
        if (evictionPolicy.equals("LRU")) {
            lruOrder.add(key);
        }
    }

    private void evict() {
        String keyToEvict;
        if (evictionPolicy.equals("LRU")) {
            keyToEvict = lruOrder.remove(0); // Remove the first entry
        } else { // LFU
            keyToEvict = frequencyMap.entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .get().getKey(); // Get the least frequently used key
            frequencyMap.remove(keyToEvict);
        }
        cache.remove(keyToEvict);
    }

    public void display() {
        System.out.println("Cache: " + cache.toString());
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}