# DynamicMultiLevelCacheSystem
This implementation provides a dynamic multilevel caching system with LRU and LFU eviction policies, enabling efficient data management across multiple levels.


## Objective
Design and implement a dynamic multilevel caching system that efficiently manages data across multiple cache levels.

## Features
- Supports multiple cache levels (L1, L2, etc.) with different sizes and eviction policies.
- Implements Least Recently Used (LRU) and Least Frequently Used (LFU) eviction policies.
- Dynamic addition and removal of cache levels at runtime.
- Retrieves data across cache levels, promoting it to higher levels when found in lower levels.
- Thread-safe implementation (concurrency is considered).

## How to Run
1. Compile the Java files in the `com.midhin.dev.capxbackend.DynamicMultilevelCachingSystem` directory.
2. Run the `Main` class to see sample operations.

## Example Usage
```java
CacheSystem cacheSystem = new CacheSystem();
cacheSystem.addCacheLevel(3, "LRU");
cacheSystem.addCacheLevel(2, "LFU");
cacheSystem.put("A", "1");
cacheSystem.get("A"); // Returns "1" from L1
cacheSystem.put("D", "4"); // Evicts LRU
cacheSystem.displayCache(); // Displays the current state of each cache level.
