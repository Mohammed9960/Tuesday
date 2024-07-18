package com.crud.crudwithchangelog.redis;

import com.crud.crudwithchangelog.author.Author;
import com.crud.crudwithchangelog.book.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Book> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Book> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
}

    @Bean
    public RedisTemplate<String, Author> authorRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Author> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
