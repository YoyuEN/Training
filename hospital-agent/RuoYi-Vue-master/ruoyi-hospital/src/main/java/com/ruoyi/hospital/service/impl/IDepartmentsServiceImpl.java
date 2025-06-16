package com.ruoyi.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.hospital.domain.Departments;
import com.ruoyi.hospital.domain.dto.DepartmentsDTO;
import com.ruoyi.hospital.mapper.DepartmentsMapper;
import com.ruoyi.hospital.service.IDepartmentsService;
import com.ruoyi.hospital.service.IHospitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 15:01
 * @Description:
 */
@Service
public class IDepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments> implements IDepartmentsService {
    @Autowired
    private IHospitalsService hospitalsService;
    @Override
    public List<DepartmentsDTO> selectDepartmentsList(Departments departments) {
        //实例化集合
        List<DepartmentsDTO> departmentsDTOList = new ArrayList<>();

        LambdaQueryWrapper<Departments> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(departments.getDeptName()),Departments::getDeptName,departments.getDeptName());
        List<Departments> departmentsList = list(lambdaQueryWrapper);
        departmentsList.forEach(item -> {
            DepartmentsDTO departmentsDTO = new DepartmentsDTO();
            BeanUtils.copyProperties(item,departmentsDTO);
            departmentsDTO.setHospitalsName(hospitalsService.getInfo(item.getHospitalId()).getHospitalName());
            departmentsDTOList.add(departmentsDTO);
        });
        return departmentsDTOList;
    }


}
