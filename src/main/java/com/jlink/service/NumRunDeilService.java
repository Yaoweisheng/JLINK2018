package com.jlink.service;

import com.jlink.entity.NumRunDeil;

import java.util.List;

public interface NumRunDeilService {
    boolean save(NumRunDeil numRunDeil);
    boolean delete(NumRunDeil numRunDeil);
    List<NumRunDeil> query(Integer id, Integer page, Integer per);
    int count(Integer id);
    boolean deleteById(Integer id);
}
