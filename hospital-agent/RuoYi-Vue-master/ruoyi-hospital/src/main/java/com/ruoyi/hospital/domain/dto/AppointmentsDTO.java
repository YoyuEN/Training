package com.ruoyi.hospital.domain.dto;

import com.ruoyi.hospital.domain.Appointments;
import lombok.Data;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:45
 * @Description:
 */
@Data
public class AppointmentsDTO extends Appointments {
    private String hospitalName;
    private String deptName;
    private String appointmentDate;
    private String appointmentCount;
}
