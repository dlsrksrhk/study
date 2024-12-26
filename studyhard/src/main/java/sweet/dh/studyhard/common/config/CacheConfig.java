package sweet.dh.studyhard.common.config;

import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URI;

@Configuration
@EnableCaching
public class CacheConfig {
//
//    @Bean
//    public org.springframework.cache.CacheManager cacheManager() throws Exception {
//        // Ehcache 3.x의 CachingProvider를 사용하여 CacheManager 생성
//        CachingProvider cachingProvider = Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
//        URI uri = getClass().getClassLoader().getResource("ehcache.xml").toURI();
//        ClassLoader classLoader = getClass().getClassLoader();
//
//        javax.cache.CacheManager jcacheManager = cachingProvider.getCacheManager(uri, classLoader);
//
//        return new JCacheCacheManager(jcacheManager);
//    }
}
