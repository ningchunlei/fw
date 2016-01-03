package com.sqsd.framework.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class PrimaryRedisConfig {
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisConnectionFactory factory = new JedisConnectionFactory();
		return factory;
	}

	@Primary
    @Bean
    public RedisOperations<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(redisConnectionFactory());
		return template;
    }
}
