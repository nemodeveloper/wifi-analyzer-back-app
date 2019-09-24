package ru.nemodev.wifi.analyzer.config.service;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServiceConfig
{
    private final CacheManager cacheManager;

    public ServiceConfig(CacheManager cacheManager)
    {
        this.cacheManager = cacheManager;
    }
}
