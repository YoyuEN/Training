package com.hospital.app.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:39
 * @Description:
 */
/*
* -- 4. 医生表
CREATE TABLE doctors
(
    doctor_id        INT PRIMARY KEY AUTO_INCREMENT,
    dept_id          INT                                                     NOT NULL COMMENT '所属科室ID',
    title            ENUM ('主任医师', '副主任医师', '主治医师', '住院医师') NOT NULL COMMENT '职称',
    specialty        VARCHAR(100) COMMENT '擅长领域',
    work_years       INT COMMENT '从业年限',
    consultation_fee DECIMAL(10, 2)                                          NOT NULL COMMENT '挂号费（元）',
    introduction     TEXT COMMENT '医生简介',
    create_time      DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time      DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted          TINYINT(1) DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）'
) COMMENT '医生信息表';
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctors implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private String doctorId;
    private String name;
    private String deptId;
    private String title;
    private String specialty;
    private Integer workYears;
    private Double consultationFee;
    private String introduction;
    private String createTime;
    private String updateTime;
    @TableLogic
    private Integer deleted;
}
