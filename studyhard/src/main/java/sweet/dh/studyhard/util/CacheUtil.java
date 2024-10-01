package sweet.dh.studyhard.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class CacheUtil {

    private final CacheManager cacheManager;

    public void printCacheContents(String cacheName) {
        System.out.println("printCacheContents() print start --------------------------------");
        Cache cache = cacheManager.getCache(cacheName);

        if (cache != null) {
            Iterator<Cache.Entry<Long, Object>> iterator = cache.iterator();
            while (iterator.hasNext()) {
                Cache.Entry<Long, Object> entry = iterator.next();
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        } else {
            System.out.println("캐시 '" + cacheName + "'을(를) 찾을 수 없습니다.");
        }
    }
}
