package com.ruoyi.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.hospital.domain.Doctors;
import com.ruoyi.hospital.domain.dto.DoctorsDTO;

import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:55
 * @Description:
 */
public interface IDoctorsService extends IService<Doctors> {
    List<DoctorsDTO> selectDoctorsList(Doctors doctors);
}
