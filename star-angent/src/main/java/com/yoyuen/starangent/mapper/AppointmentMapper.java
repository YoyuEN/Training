package com.yoyuen.starangent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yoyuen.starangent.domain.Appointment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/11
 * @Time: 10:41
 * @Description:
 */

@Mapper
@Repository
public interface AppointmentMapper extends BaseMapper<Appointment> {
}
