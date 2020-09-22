package com.atguigu.springcloud.service;

import java.util.List;

import com.atguigu.springcloud.entities.Dept;

public interface DeptService {
    boolean add(Dept dept);

    Dept get(Long id);

    List<Dept> list();

    boolean del(Long id);

    boolean upt(Dept dept);
}
