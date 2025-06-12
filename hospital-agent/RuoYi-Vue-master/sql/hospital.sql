use hospital;
-- 2. 医院表
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

-- 3. 科室表
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

-- 4. 医生表
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

-- 6. 预约表
CREATE TABLE appointments
(
    appointment_id VARCHAR(32) PRIMARY KEY COMMENT '预约表（UUID生成）',
    patient_name   VARCHAR(20) COMMENT '患者名称',
    id_card        VARCHAR(20) COMMENT '身份证号',
    doctor_name    VARCHAR(20) COMMENT '医生名称',
    hospital_name  VARCHAR(20) COMMENT '医院名称',
    dept_name      VARCHAR(20) COMMENT '科室名称',
    time_slot      ENUM ('上午', '下午', '夜间') NOT NULL COMMENT '就诊时段',
    cancel_reason  VARCHAR(200) COMMENT '取消原因',
    create_time    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '预约时间',
    update_time    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '预约订单表';

-- 7. 就诊记录表
CREATE TABLE medical_records
(
    record_id         INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id    VARCHAR(32) NOT NULL COMMENT '关联预约订单号',
    patient_name      INT         NOT NULL COMMENT '患者名称',
    doctor_id         INT         NOT NULL COMMENT '医生ID',
    diagnosis         TEXT COMMENT '诊断结果',
    treatment_plan    TEXT COMMENT '治疗方案',
    prescription      TEXT COMMENT '处方内容',
    examination_items TEXT COMMENT '检查项目',
    visit_time        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '就诊时间',
    create_time       DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间'
) COMMENT '就诊记录表';


