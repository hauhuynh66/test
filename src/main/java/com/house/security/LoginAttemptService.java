package com.house.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {
    private int MAX_FAILURE_ATTEMPTS = 5;
    private LoadingCache<String,Integer> attemptsCache;

    public LoginAttemptService() {
        super();
        attemptsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });
    }
    public void onLoginSuccedded(String key){
        attemptsCache.invalidate(key);
    }
    public void onLoginFailed(String key){
        int attemps = 0;
        try {
            attemps = attemptsCache.get(key);
        }catch (ExecutionException e){
            attemps = 0;
        }
        attemps++;
        attemptsCache.put(key,attemps);
    }
    public boolean isBlocked(String key){
        try {
            return attemptsCache.get(key)>=MAX_FAILURE_ATTEMPTS;
        }catch (ExecutionException e){
            return false;
        }
    }
}
