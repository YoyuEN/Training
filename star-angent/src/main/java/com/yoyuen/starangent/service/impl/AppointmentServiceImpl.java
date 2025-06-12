package com.yoyuen.starangent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyuen.starangent.mapper.AppointmentMapper;
import com.yoyuen.starangent.domain.Appointment;
import com.yoyuen.starangent.service.AppointmentService;
import org.springframework.stereotype.Service;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/11
 * @Time: 11:00
 * @Description:
 */
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {

    @Override
    public Appointment getOne(Appointment appointment) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getName, appointment.getName());
        queryWrapper.eq(Appointment::getPath, appointment.getPath());
        queryWrapper.eq(Appointment::getElement, appointment.getElement());
        queryWrapper.eq(Appointment::getRarity, appointment.getRarity());
        queryWrapper.eq(Appointment::getMaxLevel, appointment.getMaxLevel());
        queryWrapper.eq(Appointment::getSkill1, appointment.getSkill1());
        queryWrapper.eq(Appointment::getUltimate, appointment.getUltimate());
        queryWrapper.eq(Appointment::getSkill2, appointment.getSkill2());
        queryWrapper.eq(Appointment::getSkill3, appointment.getSkill3());
        queryWrapper.eq(Appointment::getCreatedAt, appointment.getCreatedAt());
        queryWrapper.eq(Appointment::getUpdatedAt, appointment.getUpdatedAt());
        return baseMapper.selectOne(queryWrapper);
    }
}
