package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer {

        //private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";//通过微服务的名字找到对应的微服务
                                                          //
    /**
     * 使用 使用restTemplate访问restful接口非常的简单粗暴无脑。
     * (url, requestMap,ResponseBean.class)
     * 这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
     */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
        System.out.println(REST_URL_PREFIX);
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

    //del也可以使用put方式实现
    @RequestMapping(value = "/consumer/dept/del/{id}")
    public ResponseEntity<Boolean> del(@PathVariable("id") Long id) {
        return restTemplate.exchange(REST_URL_PREFIX + "/dept/del/" + id, HttpMethod.DELETE, null, boolean.class, id);
    }

    @RequestMapping(value = "/consumer/dept/upt")
    public boolean upt(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/upt", dept, Boolean.class);
    }


    // 测试@EnableDiscoveryClient,消费端可以调用服务发现
    @RequestMapping(value = "/consumer/dept/discovery")
    public Object discovery() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
    }

}
