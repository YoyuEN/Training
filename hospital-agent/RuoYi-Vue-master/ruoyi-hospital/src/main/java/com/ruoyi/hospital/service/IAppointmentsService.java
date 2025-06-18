package com.ruoyi.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.hospital.domain.Appointments;
import com.ruoyi.hospital.domain.dto.AppointmentsDTO;

import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:55
 * @Description:
 */
public interface IAppointmentsService extends IService<Appointments> {
    List<AppointmentsDTO> selectAppointmentsList(Appointments appointments);
    // 统计不同科室每天的预约人数
    List<Appointments> countAppointmentsByDeptNameAndDate();
    // 统计不同医生每天的预约人数
    List<Appointments> countAppointmentsByDoctorNameAndDate();
    //    统计不同医院不同就诊时段的预约人数
    List<Appointments> countAppointmentsByHospitalNameAndTime();
    //    统计不同科室不同就诊时段的预约人数
    List<Appointments> countAppointmentsByDeptNameAndTime();
}
