package com.ruoyi.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.hospital.domain.Departments;
import com.ruoyi.hospital.domain.dto.DepartmentsDTO;

import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:55
 * @Description:
 */
public interface IDepartmentsService extends IService<Departments> {
    List<DepartmentsDTO> selectDepartmentsList(Departments departments);
}
