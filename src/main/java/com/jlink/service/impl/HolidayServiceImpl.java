package com.jlink.service.impl;

import com.jlink.dao.HolidayDao;
import com.jlink.dto.HolidayObject;
import com.jlink.entity.Holiday;
import com.jlink.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {
    @Autowired
    private HolidayDao holidayDao;

    @Override
    public boolean batchSave(HolidayObject holidayObject) {
        return holidayDao.batchSave(holidayObject.getHolidayDates(), holidayObject.getFestival()) > 0;
    }

    @Override
    public List<Holiday> query(Integer festival, Integer page, Integer per) {
        return holidayDao.query(festival, (page-1)*per, per);
    }

    @Override
    public boolean batchDelete(List<Integer> list) {
        return holidayDao.batchDelete(list) > 0;
    }

    @Override
    public int count(Integer festival) {
        return holidayDao.count(festival);
    }
}
