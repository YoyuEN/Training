package com.ruoyi.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.hospital.domain.Hospitals;

import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:56
 * @Description:
 */
public interface IHospitalsService extends IService<Hospitals> {
    List<Hospitals> selectHospitalsList(Hospitals hospitals);
    boolean add(Hospitals hospitals);
}
