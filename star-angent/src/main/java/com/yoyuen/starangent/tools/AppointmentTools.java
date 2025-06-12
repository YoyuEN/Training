package com.yoyuen.starangent.tools;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yoyuen.starangent.domain.Appointment;
import com.yoyuen.starangent.service.AppointmentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/11
 * @Time: 9:54
 * @Description:
 */
@Component
public class AppointmentTools {

    @Autowired
    private AppointmentService appointmentService;

    @Tool(name = "保存角色", value = "根据参数，先执行工具方法queryDepartment查询是否可保存，并直\n" +
            "接给用户回答是否可保存，并让用户确认所有角色信息，用户确认后再进行保存。")
    public String appointment(@P("角色封装对象（）Appointment") Appointment appointment) {
        if (appointmentService.save(appointment)) {
            return "角色保存成功";
        }
        return "保存角色失败";
    }

    @Tool(name = "取消角色", value = "根据参数，取消角色信息，并返回取消结果")
    public String cancelAppointment(@P("角色名称") String name) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getName, name);
        Appointment appointment = appointmentService.getOne(queryWrapper);
        if (appointment != null) {
            //删除预约记录
            if (appointmentService.removeById(appointment.getId())) {
                return "删除角色成功";
            } else {
                return "删除角色失败";
            }
        }
        //取消失败
        return "您没有该角色信息，请核对角色信息";
    }

    @Tool(name = "查询是否该角色" , value= "根据角色姓名查找当前角色是否存在")
    public String queryDepartment(@P(value = "角色名称") String name) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getName, name);
        Appointment appointment = appointmentService.getOne(queryWrapper);
        if (appointment == null) {
            return "您没有保存过该角色，可以进行保存";
        }
        return "您已经保存过该角色，请核对角色名称";

    }
}
