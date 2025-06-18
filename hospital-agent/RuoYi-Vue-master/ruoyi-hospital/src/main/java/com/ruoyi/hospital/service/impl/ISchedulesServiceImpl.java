package com.ruoyi.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.hospital.domain.Doctors;
import com.ruoyi.hospital.domain.Schedules;
import com.ruoyi.hospital.domain.dto.SchedulesDTO;
import com.ruoyi.hospital.mapper.SchedulesMapper;
import com.ruoyi.hospital.service.ISchedulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 15:03
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class ISchedulesServiceImpl extends ServiceImpl<SchedulesMapper, Schedules> implements ISchedulesService {
    private final IDepartmentsServiceImpl departmentsService;
    private final IDoctorsServiceImpl doctorsService;

    @Override
    public List<SchedulesDTO> selectSchedulesList(Schedules schedules) {
        List<SchedulesDTO> schedulesDTOS = new ArrayList<>();
        LambdaQueryWrapper<Schedules> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(schedules.getDoctorId()),
                Schedules::getDoctorId, schedules.getDoctorId());
        List<Schedules> schedulesList = list(lambdaQueryWrapper);
        schedulesList.forEach(item -> {
            SchedulesDTO schedulesDTO = new SchedulesDTO();
            BeanUtils.copyProperties(item,schedulesDTO);
            Doctors  doctors = doctorsService.getById(item.getDoctorId());
            schedulesDTO.setDoctorName(doctors.getName());
            schedulesDTO.setDeptName(departmentsService.getById(doctors.getDeptId()).getDeptName());
            schedulesDTOS.add(schedulesDTO);
        });
        return schedulesDTOS;
    }

}
