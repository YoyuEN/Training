package com.hospital.app.tools;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hospital.app.dao.DepartmentsMapper;
import com.hospital.app.domain.Appointments;
import com.hospital.app.domain.Departments;
import com.hospital.app.domain.Doctors;
import com.hospital.app.domain.Schedules;
import com.hospital.app.service.AppointmentsService;
import com.hospital.app.service.DepartmentsService;
import com.hospital.app.service.DoctorsService;
import com.hospital.app.service.SchedulesService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * 外挂挂号业务工具
 */
@Component
public class AssistantTools {
    @Autowired
    private AppointmentsService appointmentService;

    @Autowired
    private DepartmentsService departmentsService;

    @Autowired
    private SchedulesService schedulesService;

    @Autowired
    private DoctorsService doctorsService;

    /**
     * 预约挂号
     */
    @Tool(name = "预约挂号", value = "预约挂号")
    public String appointment(@P("挂号数据封装对象") Appointments appointment) {
        appointment.setAppointmentId(UUID.randomUUID().toString());
        if (appointmentService.save(appointment)) {
            return "预约成功";
        }
        return "预约失败";
    }

    @Tool(name = "查询所有科室信息", value = "查询所有科室信息")
    public List<Departments> searchDepartments() {
        return departmentsService.list();
    }

    @Tool(name = "查询所有医生信息", value = "查询所有医生信息")
    public List<Doctors> searchDoctors() {
        return doctorsService.list();
    }

    @Tool(name = "医生排班查询", value = "根据医生名称进行排班查询")
    public String searchSchedulesDoctorName(@P("医生名称") String doctorName) {
        Schedules schedules = schedulesService.searchSchedulesDoctorName(doctorName);
        if (schedules != null) {
            return "你所查的医生可以正常排班，可以进行预约挂号";
        }
        return "医生没有排班时间，不能进行预约挂号";
    }


}
