package com.atguigu.springcloud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;

@RestController
public class DeptController {
    @Autowired
    private DeptService service;
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return service.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return service.list();
    }

    @RequestMapping(value = "/dept/del/{id}", method = RequestMethod.DELETE)
    public boolean del(@PathVariable("id") Long id) {
        return service.del(id);
    }

    /*@RequestMapping(value = "/dept/upt", method = RequestMethod.GET)
    public boolean upt(Map<String,Object> paramMap) {
        Dept dept = new Dept();
        dept.setDeptno((Long) paramMap.get("deptno"));
        dept.setDname((String) paramMap.get("dname"));
        return service.upt(dept);
    }*/

    @RequestMapping(value = "/dept/upt", method = RequestMethod.POST)
    public boolean upt(@RequestBody Dept dept) {
        return service.upt(dept);
    }

    //	@Autowired
//	private DiscoveryClient client;
    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = client.getServices();//盘点eureka中的微服务list
        System.out.println("**********" + list);

        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");//找一个"...."的微服务
        for (ServiceInstance element : srvList) {//打印相关信息
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }

}
