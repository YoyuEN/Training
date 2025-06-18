package com.hospital.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.app.dao.AppointmentsMapper;
import com.hospital.app.domain.Appointments;
import com.hospital.app.service.AppointmentsService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentsServiceImpl  extends ServiceImpl<AppointmentsMapper, Appointments> implements AppointmentsService {
}
