package com.yoyuen.starangent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: YoyuEN
 * @日期: 2025/6/11
 * @时间: 10:39
 * @描述:
 */
/*
* CREATE TABLE `starrail_characters`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `name`       VARCHAR(255) NOT NULL,
    `rarity`     INT          NOT NULL,
    `path`       VARCHAR(255),
    `element`    VARCHAR(50),
    `max_level`  INT,
    `skill_1`    VARCHAR(255),
    `skill_2`    VARCHAR(255),
    `skill_3`    VARCHAR(255),
    `ultimate`   VARCHAR(255),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
* */
@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * 表示与`starrail_characters`表对应的预约实体。
 */
public class Appointment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 预约的唯一标识符。
     */
    private Integer id;

    /**
     * 角色名称。
     */
    private String name;

    /**
     * 角色的稀有度等级。
     */
    private Integer rarity;

    /**
     * 与角色关联的路径。
     */
    private String path;

    /**
     * 角色的元素类型。
     */
    private String element;

    /**
     * 角色可以达到的最高等级。
     */
    private Integer maxLevel;

    /**
     * 角色的第一技能。
     */
    private String skill1;

    /**
     * 角色的第二技能。
     */
    private String skill2;

    /**
     * 角色的第三技能。
     */
    private String skill3;

    /**
     * 角色的终极技能。
     */
    private String ultimate;

    /**
     * 记录创建的时间戳。
     */
    private String createdAt;

    /**
     * 记录最后更新的时间戳。
     */
    private String updatedAt;
}