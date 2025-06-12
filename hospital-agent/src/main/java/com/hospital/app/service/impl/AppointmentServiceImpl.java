package com.hospital.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.app.dao.AppointmentMapper;
import com.hospital.app.domain.Appointment;
import com.hospital.app.service.AppointmentService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl  extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {
}
