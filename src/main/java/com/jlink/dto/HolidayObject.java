package com.jlink.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class HolidayObject {
    private List<Date> holidayDates;
    private Integer festival;
    private List<Integer> ids;
}
