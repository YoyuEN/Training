package com.ruoyi.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:45
 * @Description:
 */
/*
* -- 6. 预约表
CREATE TABLE appointments
(
    appointment_id VARCHAR(32) PRIMARY KEY COMMENT '预约表（UUID生成）',
    patient_name   VARCHAR(20) COMMENT '患者名称',
    id_card        VARCHAR(20) COMMENT '身份证号',
    doctor_name    VARCHAR(20) COMMENT '医生名称',
    hospital_name  VARCHAR(20) COMMENT '医院名称',
    dept_name      VARCHAR(20) COMMENT '科室名称',
    time_slot      ENUM ('上午', '下午', '夜间') NOT NULL COMMENT '就诊时段',
    cancel_reason  VARCHAR(200) COMMENT '取消原因',
    create_time    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '预约时间',
    update_time    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '预约订单表';
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointments implements Serializable {
    private static final long serialVersionUID = 1L;
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
