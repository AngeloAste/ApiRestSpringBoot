package com.challengercode.spring.apirest.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching// Habilita la caché en Spring Boot
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("percentageCache") {
            private final ConcurrentHashMap<Object, Object> cache = new ConcurrentHashMap<>();

            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(name, cache, false) {
                    @Override
                    public void put(Object key, Object value) {
                        super.put(key, value);
                        // Eliminar la caché después de 30 minutos
                        new Thread(() -> {
                            try {
                                TimeUnit.MINUTES.sleep(30);
                                evict(key);
                            } catch (InterruptedException ignored) {}
                        }).start();
                    }
                };
            }
        };
    }
}
