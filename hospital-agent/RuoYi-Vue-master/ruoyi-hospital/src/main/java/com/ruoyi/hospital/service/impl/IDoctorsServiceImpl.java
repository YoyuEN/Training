package com.ruoyi.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.hospital.domain.Doctors;
import com.ruoyi.hospital.domain.dto.DoctorsDTO;
import com.ruoyi.hospital.mapper.DoctorsMapper;
import com.ruoyi.hospital.service.IDepartmentsService;
import com.ruoyi.hospital.service.IDoctorsService;
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
public class IDoctorsServiceImpl extends ServiceImpl<DoctorsMapper, Doctors> implements IDoctorsService {
    @Autowired
    private IDepartmentsService departmentsService;

    @Override
    public List<DoctorsDTO> selectDoctorsList(Doctors doctors) {
        List<DoctorsDTO> doctorsDTOList = new ArrayList<>();
        LambdaQueryWrapper<Doctors> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(doctors.getName()),Doctors::getName,doctors.getName());
        List<Doctors> doctorsList = list(lambdaQueryWrapper);
        doctorsList.forEach(item -> {
            DoctorsDTO doctorsDTO = new DoctorsDTO();
            BeanUtils.copyProperties(item,doctorsDTO);
            //根据科室的ID查询对象，获得科室的名称
            doctorsDTO.setDeptName(departmentsService.getById(item.getDeptId()).getDeptName());
            doctorsDTOList.add(doctorsDTO);
        });
        return doctorsDTOList;
    }
}
