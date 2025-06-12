package com.hospital.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment   implements Serializable {
    private String id;  //  数据库主键。使用UUID32位字符串
    private String name; //  患者姓名
    private String idcard;//  患者身份证号
    private  String department; //  科室
    private String date;//  预约日期
    private String doctorname;// 医生姓名
}
