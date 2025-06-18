package com.hospital.app.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:37
 * @Description:
 */
/*
* -- 3. 科室表
CREATE TABLE departments
(
    dept_id     INT PRIMARY KEY AUTO_INCREMENT,
    dept_name   VARCHAR(50) NOT NULL COMMENT '科室名称',
    hospital_id INT         NOT NULL COMMENT '所属医院ID',
    description TEXT COMMENT '科室简介',
    create_time DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted     TINYINT(1) DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）'
) COMMENT '科室信息表';
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departments implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private String deptId;
    private String deptName;
    private String hospitalId;
    private String description;
    private String createTime;
    private String updateTime;
    @TableLogic
    private Integer deleted;
}
