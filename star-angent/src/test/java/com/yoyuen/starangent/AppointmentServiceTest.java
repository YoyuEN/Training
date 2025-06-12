package com.yoyuen.starangent;

import com.yoyuen.starangent.domain.Appointment;
import com.yoyuen.starangent.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/11
 * @Time: 11:24
 * @Description:
 */
@SpringBootTest
class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService ;
    @Test
    void testGetOne() {
        Appointment appointment = new Appointment() ;
        appointment.setName("风堇");
        appointment.setPath("存护");
        appointment.setElement("虚数");
        appointment.setRarity(5);
        appointment.setMaxLevel(80);
        appointment.setSkill1("虚数之握");
        appointment.setSkill2("护盾生成");
        appointment.setSkill3("虚数领域");
        appointment.setUltimate("星穹守护");
        appointmentService.save(appointment) ;

        Appointment appointmentDB = appointmentService .getOne(appointment) ;
        System.out.println (appointmentDB);
    }
    @Test
    void testSave() {
        Appointment appointment = new Appointment();
        appointment.setName("风堇");
        appointment.setPath("存护");
        appointment.setElement("虚数");
        appointment.setRarity(5);
        appointment.setMaxLevel(80);
        appointment.setSkill1("虚数之握");
        appointment.setSkill2("护盾生成");
        appointment.setSkill3("虚数领域");
        appointment.setUltimate("星穹守护");
        appointmentService.save(appointment) ;
    }
    @Test
    void testRemoveById() {
        appointmentService.removeById(1L) ;
    }
}
