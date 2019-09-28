package com.jlink.dao;

import com.jlink.entity.NumRunDeil;

import java.util.List;

public interface NumRunDeilDao {
    int save(NumRunDeil numRunDeil);
    int delete(NumRunDeil numRunDeil);
    List<NumRunDeil> query(Integer id, Integer offset, Integer limit);
    int count(Integer id);
    int deleteById(Integer id);
}