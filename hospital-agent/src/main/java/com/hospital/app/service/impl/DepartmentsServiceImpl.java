package com.hospital.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.app.dao.DepartmentsMapper;
import com.hospital.app.domain.Departments;
import com.hospital.app.service.DepartmentsService;
import org.springframework.stereotype.Service;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/17
 * @Time: 14:59
 * @Description:
 */
@Service
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments> implements DepartmentsService {
}
