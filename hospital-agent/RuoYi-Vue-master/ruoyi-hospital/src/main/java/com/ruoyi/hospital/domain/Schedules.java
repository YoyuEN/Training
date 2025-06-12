package com.ruoyi.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:42
 * @Description:
 */
/*
-- 5. 排班表
CREATE TABLE schedules
(
    schedule_id         INT PRIMARY KEY AUTO_INCREMENT,
    doctor_id           INT         NOT NULL COMMENT '医生ID',
    date                VARCHAR(20) NOT NULL COMMENT '排班日期',
    morning_start       TIME COMMENT '上午开始时间',
    morning_end         TIME COMMENT '上午结束时间',
    afternoon_start     TIME COMMENT '下午开始时间',
    afternoon_end       TIME COMMENT '下午结束时间',
    night_start         TIME COMMENT '夜间开始时间',
    night_end           TIME COMMENT '夜间结束时间',
    morning_quota       INT        DEFAULT 0 COMMENT '上午号源数量',
    afternoon_quota     INT        DEFAULT 0 COMMENT '下午号源数量',
    night_quota         INT        DEFAULT 0 COMMENT '夜间号源数量',
    morning_remaining   INT        DEFAULT 0 COMMENT '上午剩余号源',
    afternoon_remaining INT        DEFAULT 0 COMMENT '下午剩余号源',
    night_remaining     INT        DEFAULT 0 COMMENT '夜间剩余号源',
    create_time         DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time         DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted             TINYINT(1) DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）'
) COMMENT '医生排班表';
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedules implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer scheduleId;
    private Integer doctorId;
    private String date;
    private String morningStart;
    private String morningEnd;
    private String afternoonStart;
    private String afternoonEnd;
    private String nightStart;
    private String nightEnd;
    private Integer morningQuota;
    private Integer afternoonQuota;
    private Integer nightQuota;
    private Integer morningRemaining;
    private Integer afternoonRemaining;
    private Integer nightRemaining;
    private String createTime;
    private String updateTime;
    private Integer deleted;
}
