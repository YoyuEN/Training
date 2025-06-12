package com.hospital.app.tools;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hospital.app.domain.Appointment;
import com.hospital.app.service.AppointmentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 外挂挂号业务工具
 */
@Component
public class AssistantTools {
    @Autowired
    private AppointmentService appointmentService;

    /**
     * 预约挂号
     */
    @Tool(name = "预约挂号", value = "调用该方法进行数据库数据插入操作，进行挂号")
    public String appointment(@P("挂号数据封装对象(Appointment)") Appointment appointment) {
        appointment.setId(UUID.randomUUID().toString());
        if (appointmentService.save(appointment)) {
            return "预约成功";
        }
        return "预约失败";
    }

    /**
     * 查询当前挂号人是否挂号，如果当天已经预约挂号就不能进行再次挂号
     */
    @Tool(name = "查询当前挂号人是否挂号", value = "查询当前挂号人是否挂号，如果当天已经预约挂号就不能进行再次挂号,也就是不能执行appointment方法进行挂号")
    public String queryAppointmentInfo(@P("身份证") String card) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getIdcard, card);
        Appointment appointment = appointmentService.getOne(queryWrapper);
        if (appointment == null) {
            return "该用户今天没有挂号可以进行预约挂号";
        }
        return appointment.getName()+"已预约挂号，请勿重复预约";

    }





    /**
     * 取消预约挂号，根据身份证取消挂号信息
     */
    @Tool(name = "取消预约挂号", value = "根据身份证取消预约挂号信息")
    public String cancelAppointment(@P("身份证") String card) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getIdcard, card);
        Appointment appointment = appointmentService.getOne(queryWrapper);
        if (appointment == null) {
            return "该用户没有预约挂号信息";
        }
        if (appointmentService.remove(queryWrapper)) {
            return "取消预约挂号成功";
        }
        return "取消预约挂号失败";
    }

}
