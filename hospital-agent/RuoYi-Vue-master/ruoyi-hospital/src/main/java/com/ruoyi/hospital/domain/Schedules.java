package com.ruoyi.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
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
    //  排班ID
    private String scheduleId;
    //  医生ID
    private String doctorId;
    //  排班日期
    @Excel(name = "排班日期")
    private String date;
    @Excel(name = "上午开始时间")
    //  上午开始时间
    private String morningStart;
    //  上午结束时间
    @Excel(name = "上午结束时间")
    private String morningEnd;
    //  下午开始时间
    @Excel(name = "下午开始时间")
    private String afternoonStart;
    //  下午结束时间
    @Excel(name = "下午结束时间")
    private String afternoonEnd;
    //  夜间开始时间
    @Excel(name = "夜间开始时间")
    private String nightStart;
    //  夜间结束时间
    @Excel(name = "夜间结束时间")
    private String nightEnd;
    //  上午号源数量
    @Excel(name = "上午号源数量")
    private String morningQuota;
    //  下午号源数量
    @Excel(name = "下午号源数量")
    private String afternoonQuota;
    //  夜间号源数量
    @Excel(name = "夜间号源数量")
    private String nightQuota;
    //  上午剩余号源
    @Excel(name = "上午剩余号源")
    private String morningRemaining;
    //  下午剩余号源
    @Excel(name = "下午剩余号源")
    private String afternoonRemaining;
    //  夜间剩余号源
    @Excel(name = "夜间剩余号源")
    private String nightRemaining;
    //  创建时间
    private String createTime;
    //  更新时间
    private String updateTime;
    //  删除标志（0=未删除，1=已删除）
    private Integer deleted;
}
