package com.yoyuen.starangent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yoyuen.starangent.domain.Appointment;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/11
 * @Time: 10:42
 * @Description:
 */
public interface AppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);
}
