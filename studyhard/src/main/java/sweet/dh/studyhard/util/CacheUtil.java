package sweet.dh.studyhard.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CacheUtil {

    private final CacheManager cacheManager;

    public Map<String, Object> findCacheContents(String cacheName) {
        System.out.println("printCacheContents() print start --------------------------------");
        Cache<Object, Object> cache = cacheManager.getCache(cacheName, Object.class, Object.class);
        Map<String, Object> cacheContents = new HashMap<>();

        if (cache != null) {
            Iterator<Cache.Entry<Object, Object>> iterator = cache.iterator();
            while (iterator.hasNext()) {
                Cache.Entry<Object, Object> entry = iterator.next();
                cacheContents.put(entry.getKey()+"", entry.getValue());
            }
        }

        return cacheContents;
    }
}
