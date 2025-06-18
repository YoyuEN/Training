package com.hospital.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.app.domain.Schedules;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/17
 * @Time: 14:54
 * @Description:
 */
@Repository
@Mapper
public interface SchedulesMapper extends BaseMapper<Schedules> {
}
