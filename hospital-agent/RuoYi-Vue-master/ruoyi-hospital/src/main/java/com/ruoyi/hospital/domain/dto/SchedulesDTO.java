package com.ruoyi.hospital.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.hospital.domain.Schedules;
import lombok.Data;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/12
 * @Time: 14:42
 * @Description:
 */
@Data
public class SchedulesDTO extends Schedules {
    //  医生名称
    @Excel(name = "医生名称")
    private String doctorName;
    //  科室名称
    @Excel(name = "科室名称")
    private String deptName;
}
