package com.ruoyi.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.hospital.domain.Appointments;
import com.ruoyi.hospital.domain.dto.AppointmentsDTO;
import com.ruoyi.hospital.mapper.AppointmentsMapper;
import com.ruoyi.hospital.service.IAppointmentsService;
import com.ruoyi.hospital.service.IDepartmentsService;
import com.ruoyi.hospital.service.IHospitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 15:00
 * @Description:
 */
@Service
public class IAppointmentsServiceImpl extends ServiceImpl<AppointmentsMapper, Appointments> implements IAppointmentsService {
    @Autowired
    private IDepartmentsService departmentsService;

    @Autowired
    private IHospitalsService hospitalsService;
    @Override
    public List<AppointmentsDTO> selectAppointmentsList(Appointments appointments) {
        List<AppointmentsDTO> appointmentsDTOList = new ArrayList<>();
        LambdaQueryWrapper<Appointments> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //科室名称
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(appointments.getDeptName()),Appointments::getDeptName,appointments.getDeptName());
        //身份证号
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(appointments.getIdCard()),Appointments::getIdCard,appointments.getIdCard());
        //医生名称
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(appointments.getDoctorName()),Appointments::getDoctorName,appointments.getDoctorName());
        //患者名称
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(appointments.getPatientName()),Appointments::getPatientName,appointments.getPatientName());
        //医院名称
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(appointments.getHospitalName()),Appointments::getHospitalName,appointments.getHospitalName());

        List<Appointments> appointmentsList = list(lambdaQueryWrapper);
        appointmentsList.forEach(item -> {
            AppointmentsDTO appointmentsDTO = new AppointmentsDTO();
            BeanUtils.copyProperties(item,appointmentsDTO);
            appointmentsDTO.setDeptName(item.getDeptName());
            appointmentsDTO.setHospitalName(item.getHospitalName());
            appointmentsDTOList.add(appointmentsDTO);
        });
        return appointmentsDTOList;
    }

    @Override
    public List<Appointments> countAppointmentsByDeptNameAndDate() {
        return baseMapper.countAppointmentsByDeptNameAndDate();
    }

    @Override
    public List<Appointments> countAppointmentsByDeptNameAndTime() {
        return baseMapper.countAppointmentsByDeptNameAndTime();
    }

    @Override
    public List<Appointments> countAppointmentsByDoctorNameAndDate() {
        return baseMapper.countAppointmentsByDoctorNameAndDate();
    }

    @Override
    public List<Appointments> countAppointmentsByHospitalNameAndTime() {
        return baseMapper.countAppointmentsByHospitalNameAndTime();
    }
}
