package com.ruoyi.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.hospital.domain.Appointments;

import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:49
 * @Description:
 */
public interface AppointmentsMapper extends BaseMapper<Appointments> {
    // 统计不同科室每天的预约人数
    List<Appointments> countAppointmentsByDeptNameAndDate();
    // 统计不同医生每天的预约人数
    List<Appointments> countAppointmentsByDoctorNameAndDate();
//    统计不同医院不同就诊时段的预约人数
    List<Appointments> countAppointmentsByHospitalNameAndTime();
//    统计不同科室不同就诊时段的预约人数
    List<Appointments> countAppointmentsByDeptNameAndTime();

}
