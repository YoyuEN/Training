package com.hospital.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.app.dao.SchedulesMapper;
import com.hospital.app.domain.Doctors;
import com.hospital.app.domain.Schedules;
import com.hospital.app.service.DoctorsService;
import com.hospital.app.service.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/17
 * @Time: 14:56
 * @Description:
 */
@Service
public class SchedulesServiceImpl extends ServiceImpl<SchedulesMapper, Schedules> implements SchedulesService {
    @Autowired
    private DoctorsService doctorsService;
    @Override
    public Schedules searchSchedulesDoctorName(String doctorName) {
        LambdaQueryWrapper<Doctors> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Doctors::getName, doctorName);
        Doctors doctors = doctorsService.getOne(lambdaQueryWrapper);
        return this.getOne(new LambdaQueryWrapper<Schedules>().eq(Schedules::getDoctorId, doctors.getDoctorId()));
    }
}
