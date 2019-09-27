package com.jlink.dao;

import com.jlink.entity.Holiday;

import java.util.Date;
import java.util.List;

public interface HolidayDao {
    int batchSave(List<Date> holidays, Integer festival);
    List<Holiday> query(Integer festival, Integer offset, Integer limit);
    int batchDelete(List<Integer> list);
}