package com.hospital.app.service;

import com.hospital.app.domain.Doctors;
import com.hospital.app.domain.Schedules;

import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/17
 * @Time: 14:55
 * @Description:
 */
public interface SchedulesService{
    Schedules searchSchedulesDoctorName(String doctorName);
}
