package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Dept;

import java.util.List;
import java.util.Map;


public interface DeptDao {
    boolean addDept(Dept dept);

    Dept findById(Long id);

    List<Dept> findAll();

    boolean delDept(Long id);

    boolean uptDept(Dept dept);

}
