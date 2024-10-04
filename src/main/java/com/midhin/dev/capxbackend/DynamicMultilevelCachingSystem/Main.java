package com.midhin.dev.capxbackend.DynamicMultilevelCachingSystem;

public class Main {
    public static void main(String[] args) {
        CacheSystem cacheSystem = new CacheSystem();

        // Add cache levels
        cacheSystem.addCacheLevel(3, "LRU"); // L1 Cache
        cacheSystem.addCacheLevel(2, "LFU"); // L2 Cache

        // Put some values
        cacheSystem.put("A", "1");
        cacheSystem.put("B", "2");
        cacheSystem.put("C", "3");
        System.out.println("Get A: " + cacheSystem.get("A")); // Should return "1"

        // Insert another value, which should evict "B" (LRU)
        cacheSystem.put("D", "4");
        System.out.println("Get C: " + cacheSystem.get("C")); // Should return "3"

        // Display cache levels
        cacheSystem.displayCache(); // Show current state of caches
    }
}