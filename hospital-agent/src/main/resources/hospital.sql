-- 创建数据库
CREATE DATABASE IF NOT EXISTS hospitals;
USE hospitals;

-- 1. 用户表（患者/医生/管理员）
-- CREATE TABLE users (
--     user_id INT PRIMARY KEY AUTO_INCREMENT,
--     username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
--     password VARCHAR(100) NOT NULL COMMENT '加密密码',
--     real_name VARCHAR(50) COMMENT '真实姓名',
--     id_card VARCHAR(18) UNIQUE COMMENT '身份证号（实名认证）',
--     phone VARCHAR(20) NOT NULL UNIQUE COMMENT '手机号',
--     user_type ENUM('patient', 'doctor', 'admin') NOT NULL COMMENT '用户类型',
--     create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
--     last_login_time DATETIME COMMENT '最后登录时间',
--     status TINYINT(1) DEFAULT 1 COMMENT '账号状态（1=正常，0=禁用）'
-- ) COMMENT '系统用户表';

-- 2. 医院表
CREATE TABLE hospitals (
    hospital_id INT PRIMARY KEY AUTO_INCREMENT,
    hospital_name VARCHAR(100) NOT NULL COMMENT '医院名称',
    hospital_level ENUM('三级甲等', '三级乙等', '二级甲等', '一级', '其他') NOT NULL COMMENT '医院等级',
    address VARCHAR(200) NOT NULL COMMENT '地址',
    phone VARCHAR(20) COMMENT '联系电话',
    description TEXT COMMENT '医院简介',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted TINYINT(1) DEFAULT 0 COMMENT '是否删除（1=已删除，0=未删除）'
) COMMENT '医院信息表';

-- 3. 科室表
CREATE TABLE departments (
    dept_id INT PRIMARY KEY AUTO_INCREMENT,
    dept_name VARCHAR(50) NOT NULL COMMENT '科室名称',
    hospital_id INT NOT NULL COMMENT '所属医院ID',
    description TEXT COMMENT '科室简介',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted TINYINT(1) DEFAULT 0 COMMENT '是否删除（1=已删除，0=未删除）'
) COMMENT '科室信息表';

-- 4. 医生表
CREATE TABLE doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    dept_id INT NOT NULL COMMENT '所属科室ID',
    title ENUM('主任医师', '副主任医师', '主治医师', '住院医师') NOT NULL COMMENT '职称',
    specialty VARCHAR(100) COMMENT '擅长领域',
    work_years INT COMMENT '从业年限',
    consultation_fee DECIMAL(10, 2) NOT NULL COMMENT '挂号费（元）',
    introduction TEXT COMMENT '医生简介',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted TINYINT(1) DEFAULT 0 COMMENT '是否删除（1=已删除，0=未删除）'
) COMMENT '医生信息表';

-- 5. 排班表
CREATE TABLE schedules (
    schedule_id INT PRIMARY KEY AUTO_INCREMENT ,
    doctor_id INT NOT NULL COMMENT '医生ID',
    date DATE NOT NULL COMMENT '排班日期',
    morning_start TIME COMMENT '上午开始时间',
    morning_end TIME COMMENT '上午结束时间',
    afternoon_start TIME COMMENT '下午开始时间',
    afternoon_end TIME COMMENT '下午结束时间',
    night_start TIME COMMENT '夜间开始时间',
    night_end TIME COMMENT '夜间结束时间',
    morning_quota INT DEFAULT 20 COMMENT '上午号源数量',
    afternoon_quota INT DEFAULT 20 COMMENT '下午号源数量',
    night_quota INT DEFAULT 10 COMMENT '夜间号源数量',
    morning_remaining INT DEFAULT 20 COMMENT '上午剩余号源',
    afternoon_remaining INT DEFAULT 20 COMMENT '下午剩余号源',
    night_remaining INT DEFAULT 10 COMMENT '夜间剩余号源',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT(1) DEFAULT 0 COMMENT '是否删除（1=已删除，0=未删除）'
) COMMENT '医生排班表';

-- 6. 预约表
CREATE TABLE appointments (
    appointment_id VARCHAR(50) PRIMARY KEY COMMENT '订单号（UUID生成）',
    patient_name INT NOT NULL COMMENT '患者名称',
    idcard VARCHAR(18) COMMENT '患者身份证号',
    doctor_id INT NOT NULL COMMENT '医生ID',
    schedule_id INT NOT NULL COMMENT '排班ID',
    hospital_id INT NOT NULL COMMENT '医院ID',
    dept_id INT NOT NULL COMMENT '科室ID',
    date DATE NOT NULL COMMENT '就诊日期',
    time_slot ENUM('上午', '下午', '夜间') NOT NULL COMMENT '就诊时段',
    order_status ENUM('待支付', '已预约', '已就诊', '已取消', '已过期', '已退号') NOT NULL DEFAULT '待支付' COMMENT '订单状态',
    payment_amount DECIMAL(10, 2) NOT NULL COMMENT '支付金额（元）',
    payment_time DATETIME COMMENT '支付时间',
    cancel_reason VARCHAR(200) COMMENT '取消原因',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '预约时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '预约订单表';

-- 7. 就诊记录表
CREATE TABLE medical_records (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id VARCHAR(32) NOT NULL COMMENT '关联预约订单号',
    patient_id INT NOT NULL COMMENT '患者ID',
    doctor_id INT NOT NULL COMMENT '医生ID',
    diagnosis TEXT COMMENT '诊断结果',
    treatment_plan TEXT COMMENT '治疗方案',
    prescription TEXT COMMENT '处方内容',
    examination_items TEXT COMMENT '检查项目',
    visit_time DATETIME NOT NULL COMMENT '就诊时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间'
) COMMENT '就诊记录表';


