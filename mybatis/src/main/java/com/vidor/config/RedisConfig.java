package com.vidor.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
//@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class}) // 注意exclude
//@EnableAutoConfiguration
public class RedisConfig {

//    @Bean("userTokenRedisConnection")
//    public LettuceConnectionFactory userTokenRedisConnection() {
//        RedisStandaloneConfiguration server = new RedisStandaloneConfiguration();
//        server.setHostName("120.10.10.130");
//        server.setDatabase(10); // 指定数据库！
//        server.setPort(6378);
//        return new LettuceConnectionFactory(server);
//    }

//    @Bean("userTokenRedisTemplate")
//    public RedisTemplate<String, String> userTokenRedisTemplate() {
//        RedisTemplate<String, String> template = new RedisTemplate<String, String>(); //只能对字符串的键值操作
//        template.setConnectionFactory(userTokenRedisConnection());
//        return template;
//    }

//    @Bean("mybatisRedisConnection")
//    public JedisConnectionFactory mybatisRedisConnection() {
//        RedisStandaloneConfiguration server = new RedisStandaloneConfiguration();
//        server.setHostName("127.0.0.1");
//        server.setDatabase(3); // 指定数据库！
//        server.setPort(6379);
//        return new JedisConnectionFactory(server);
//    }

    @Bean("mybatisRedisConnection")
    public LettuceConnectionFactory mybatisRedisConnection() {
        RedisStandaloneConfiguration server = new RedisStandaloneConfiguration();
        server.setHostName("127.0.0.1");
        server.setDatabase(0); // 指定数据库！
        server.setPort(6379);
        return new LettuceConnectionFactory(server);
    }

    @Bean("mybatisRedisTemplate")
    public RedisTemplate<Object, Object> mybatisRedisTemplate(){
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(mybatisRedisConnection());

//        setKeyValueStrate(template);CacheKey cannot be cast to java.lang.String

        return template;
    }

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        setKeyValueStrate(template);
        return template;
    }

    private void setKeyValueStrate(RedisTemplate<String, Object> template) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
    }
}
