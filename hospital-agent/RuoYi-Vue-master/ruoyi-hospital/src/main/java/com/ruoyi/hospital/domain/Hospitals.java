package com.ruoyi.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:36
 * @Description:
 */
/*
* -- 2. 医院表
CREATE TABLE hospitals
(
    hospital_id    INT PRIMARY KEY AUTO_INCREMENT,
    hospital_name  VARCHAR(100)                                              NOT NULL COMMENT '医院名称',
    hospital_level ENUM ('三级甲等', '三级乙等', '二级甲等', '一级', '其他') NOT NULL COMMENT '医院等级',
    address        VARCHAR(200)                                              NOT NULL COMMENT '地址',
    phone          VARCHAR(20) COMMENT '联系电话',
    description    TEXT COMMENT '医院简介',
    create_time    DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time    DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted        TINYINT(1) DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）'
) COMMENT '医院信息表';
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospitals implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private String  hospitalId;
    private String hospitalName;
    private String hospitalLevel;
    private String address;
    private String phone;
    private String description;
    private String createTime;
    private String updateTime;
    @TableLogic
    private Integer deleted;

}
