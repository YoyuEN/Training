package com.ruoyi.hospital.domain.dto;

import com.ruoyi.hospital.domain.Departments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/16
 * @Time: 8:35
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentsDTO extends Departments {
    private String hospitalsName;
}
