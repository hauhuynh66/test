package com.house.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttempService {
    private int MAX_FAILURE_ATTEMPS = 5;
    private LoadingCache<String,Integer> attempsCache;

    public LoginAttempService() {
        super();
        attempsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });
    }
    public void onLoginSuccedded(String key){
        attempsCache.invalidate(key);
    }
    public void onLoginFailed(String key){
        int attemps = 0;
        try {
            attemps = attempsCache.get(key);
        }catch (ExecutionException e){
            attemps = 0;
        }
        attemps++;
        attempsCache.put(key,attemps);
    }
    public boolean isBlocked(String key){
        try {
            return attempsCache.get(key)>=MAX_FAILURE_ATTEMPS;
        }catch (ExecutionException e){
            return false;
        }
    }
}
