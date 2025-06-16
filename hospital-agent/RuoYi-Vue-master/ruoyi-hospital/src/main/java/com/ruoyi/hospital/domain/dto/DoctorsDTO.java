package com.ruoyi.hospital.domain.dto;

import com.ruoyi.hospital.domain.Doctors;
import lombok.Data;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/16
 * @Time: 9:31
 * @Description:
 */
@Data
public class DoctorsDTO extends Doctors {
    private String deptName;

}
