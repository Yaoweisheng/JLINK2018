package com.jlink.service;

import com.jlink.dto.HolidayObject;
import com.jlink.entity.Holiday;

import java.util.List;

public interface HolidayService {
    boolean batchSave(HolidayObject holidayObject);
    List<Holiday> query(Integer festival, Integer page, Integer per);
    boolean batchDelete(List<Integer> list);
}
