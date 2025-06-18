package com.hospital.app.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/*
* appointment_id
patient_name
id_card
doctor_name
hospital_name
dept_name
time_slot
cancel_reason
create_time
update_time
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointments implements Serializable {
    @TableId
    private String appointmentId;
    private String patientName;
    private String idCard;
    private String doctorName;
    private String hospitalName;
    private String deptName;
    private String timeSlot;
    private String cancelReason;
    private String createTime;
    private String updateTime;
}
