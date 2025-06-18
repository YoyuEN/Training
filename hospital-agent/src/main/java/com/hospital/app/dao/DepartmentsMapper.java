package com.hospital.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.app.domain.Departments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/17
 * @Time: 14:46
 * @Description:
 */
@Repository
@Mapper
public interface DepartmentsMapper extends BaseMapper<Departments> {
}
