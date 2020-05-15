package com.vidor;

import com.vidor.controller.UserController;
import com.vidor.mapper.UserMapper;
import com.vidor.pojo.User;
import org.apache.catalina.core.ApplicationContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MybatisApplicationTests {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    void contextLoads() throws Exception{
        //ServlerContext的getRealPath()
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //构造器模式：隐藏对象创建过程
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //工厂模式：解耦
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //代理模式：不需要修改代码，对已有方法进行加强
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();
        System.out.println(userList.toString());

    }

    @Test
    void testRedis() throws Exception{
        for (int i = 0;  i < 100; i++){
            ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET,"/user/getAUser/7")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
            );
            resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        }

//        MvcResult result = resultActions.andReturn();
    }

}
