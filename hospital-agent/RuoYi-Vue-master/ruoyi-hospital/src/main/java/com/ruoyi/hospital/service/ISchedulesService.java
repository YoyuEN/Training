package com.ruoyi.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.hospital.domain.Schedules;
import com.ruoyi.hospital.domain.dto.SchedulesDTO;

import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:57
 * @Description:
 */
public interface ISchedulesService extends IService<Schedules> {
    List<SchedulesDTO> selectSchedulesList(Schedules schedules);
}
