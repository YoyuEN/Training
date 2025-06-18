package com.hospital.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.app.dao.DoctorsMapper;
import com.hospital.app.domain.Doctors;
import com.hospital.app.service.DoctorsService;
import org.springframework.stereotype.Service;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/17
 * @Time: 15:07
 * @Description:
 */
@Service
public class DoctorsServiceImpl extends ServiceImpl<DoctorsMapper, Doctors> implements DoctorsService {
}
