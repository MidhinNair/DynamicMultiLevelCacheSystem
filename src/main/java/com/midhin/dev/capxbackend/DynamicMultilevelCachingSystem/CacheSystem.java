package com.midhin.dev.capxbackend.DynamicMultilevelCachingSystem;

public class CacheSystem {
    private final CacheManager cacheManager = new CacheManager();

    public void addCacheLevel(int size, String evictionPolicy) {
        cacheManager.addCacheLevel(size, evictionPolicy);
    }

    public String get(String key) {
        return cacheManager.get(key);
    }

    public void put(String key, String value) {
        cacheManager.put(key, value);
    }

    public void removeCacheLevel(int level) {
        cacheManager.removeCacheLevel(level - 1);
    }

    public void displayCache() {
        cacheManager.displayCache();
    }
}
