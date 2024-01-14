package com.doping.quizservice.persistance.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import static com.doping.quizservice.contstant.CacheConstants.ALL_QUIZ_CACHE;
import static com.doping.quizservice.contstant.CacheConstants.ALL_STUDENT_CACHE;

@Service
@Slf4j
public class RedisService {

    @CacheEvict(value = ALL_QUIZ_CACHE)
    public void evictQuizCache() {
        log.info("All quiz cache evicted...");
    }

    @CacheEvict(value = ALL_STUDENT_CACHE)
    public void allStudentCacheEvict() {
        log.info("All student cache evicted...");
    }
}
