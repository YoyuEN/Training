package com.hospital.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.app.domain.Doctors;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/17
 * @Time: 15:08
 * @Description:
 */
@Repository
@Mapper
public interface DoctorsMapper extends BaseMapper<Doctors> {
}
