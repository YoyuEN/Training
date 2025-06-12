package com.ruoyi.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:47
 * @Description:
 */
/*
* -- 7. 就诊记录表
CREATE TABLE medical_records
(
    record_id         INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id    VARCHAR(32) NOT NULL COMMENT '关联预约订单号',
    patient_name      INT         NOT NULL COMMENT '患者名称',
    doctor_id         INT         NOT NULL COMMENT '医生ID',
    diagnosis         TEXT COMMENT '诊断结果',
    treatment_plan    TEXT COMMENT '治疗方案',
    prescription      TEXT COMMENT '处方内容',
    examination_items TEXT COMMENT '检查项目',
    visit_time        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '就诊时间',
    create_time       DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间'
) COMMENT '就诊记录表';

* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecords implements Serializable {
    @TableId
    private String recordId;
    private String appointmentId;
    private String patientName;
    private String doctorId;
    private String diagnosis;
    private String treatmentPlan;
    private String prescription;
    private String examinationItems;
    private String visitTime;
    private String createTime;
}
