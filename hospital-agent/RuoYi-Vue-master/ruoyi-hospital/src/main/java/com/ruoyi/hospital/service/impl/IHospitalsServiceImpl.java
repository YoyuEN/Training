package com.ruoyi.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.hospital.domain.Departments;
import com.ruoyi.hospital.domain.Hospitals;
import com.ruoyi.hospital.mapper.HospitalsMapper;
import com.ruoyi.hospital.service.IDepartmentsService;
import com.ruoyi.hospital.service.IHospitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 15:01
 * @Description:
 */
@Service
public class IHospitalsServiceImpl extends ServiceImpl<HospitalsMapper, Hospitals> implements IHospitalsService {

    @Autowired
    private IDepartmentsService departmentsService;

    @Override
    public boolean edit(Hospitals hospitals) {
        List<Departments> departmentsList = hospitals.getDepartmentsList();
        departmentsService.updateBatchById(departmentsList);
        return super.updateById(hospitals);
    }

    @Override
    public List<Hospitals> selectHospitalsList(Hospitals hospitals) {
        LambdaQueryWrapper<Hospitals> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(hospitals.getHospitalName()), Hospitals::getHospitalName, hospitals.getHospitalName());
        return super.list(lambdaQueryWrapper); 
    }

    @Override
    public boolean add(Hospitals hospitals) {
        // 设置医院的主键
        hospitals.setHospitalId(UUID.fastUUID().toString());
        List<Departments> departmentsList = hospitals.getDepartmentsList();
        for (Departments departments : departmentsList) {
            departments.setDeptId(UUID.fastUUID().toString());
            departments.setHospitalId(hospitals.getHospitalId());
        }
        departmentsService.saveBatch(departmentsList);
        return super.save(hospitals);
    }

    @Override
    public Hospitals getInfo(String hospitalId) {
        Hospitals hospitals = super.getById(hospitalId);
        LambdaQueryWrapper<Departments> departmentsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        departmentsLambdaQueryWrapper.eq(Departments::getHospitalId, hospitalId);
        List<Departments> departmentsList = departmentsService.list(departmentsLambdaQueryWrapper);
        hospitals.setDepartmentsList(departmentsList);
        return hospitals;
    }

    @Override
    public Hospitals getHospitalByName(String hospitalName) {
        LambdaQueryWrapper<Hospitals> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Hospitals::getHospitalName, hospitalName);
        return super.getOne(lambdaQueryWrapper);
    }

}
