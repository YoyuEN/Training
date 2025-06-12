/*
 Navicat Premium Data Transfer

 Source Server         : conn
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : star

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 12/06/2025 08:36:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('root', '17856', '9527');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `commentinformation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('吴洋', 'D:YoyuUser图片R-C.jpg', 'dwaouiu');
INSERT INTO `comment` VALUES ('周磊', 'null', '时代的呼叫');

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_two` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_three` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_four` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`group_name`) USING BTREE,
  INDEX `group_ibfk_1`(`role_name` ASC) USING BTREE,
  CONSTRAINT `group_ibfk_1` FOREIGN KEY (`role_name`) REFERENCES `role` (`role_name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('布洛妮娅', '0T队', '镜流', '花火', '停云');
INSERT INTO `group` VALUES ('彦卿', '刀刀暴击流', '花火', '砂金', '阮梅');
INSERT INTO `group` VALUES ('姬子', '单群兼顾队', '托帕&账账', '阮梅', '加拉赫');
INSERT INTO `group` VALUES ('克拉拉', '双核反击队', '托帕&账账', '霍霍', '花火');
INSERT INTO `group` VALUES ('克拉拉', '反击队', '停云', '花火', '罗刹');
INSERT INTO `group` VALUES ('姬子', '姬子火弱队', '阮梅', '花火', '停云');
INSERT INTO `group` VALUES ('姬子', '姬黑虚构队', '黑塔', '罗刹', '阮梅');
INSERT INTO `group` VALUES ('布洛妮娅', '布洛妮娅花火双拉队', '银枝', '花火', '砂金');
INSERT INTO `group` VALUES ('希儿', '希儿主C队', '银狼', '艾丝妲', '符玄');
INSERT INTO `group` VALUES ('希儿', '希儿量子队', '花火', '银狼', '符玄');
INSERT INTO `group` VALUES ('白露', '常规泛用奶', '丹恒', '艾丝妲', '黑塔');
INSERT INTO `group` VALUES ('杰帕德', '常规盾辅', '彦卿', '停云', '佩拉');
INSERT INTO `group` VALUES ('彦卿', '彦卿永冻点杀队', '银狼', '佩拉', '杰帕德');
INSERT INTO `group` VALUES ('瓦尔特', '控速队', '黄泉', '阮梅', '砂金');
INSERT INTO `group` VALUES ('景元', '景停花', '花火', '停云', '霍霍');
INSERT INTO `group` VALUES ('景元', '景元托帕双C队', '托帕&账账', '阮梅', '霍霍');
INSERT INTO `group` VALUES ('景元', '景黑虚构叙事队', '黑塔', '知更鸟', '砂金');
INSERT INTO `group` VALUES ('杰帕德', '杰帕德黄泉虚构队', '黄泉', '佩拉', '花火');
INSERT INTO `group` VALUES ('瓦尔特', '标准虚数队', '停云', '驭空', '罗刹');
INSERT INTO `group` VALUES ('白露', '百抗白露', '克拉拉', '停云', '驭空');
INSERT INTO `group` VALUES ('瓦尔特', '老杨单C队', '停云', '驭空', '罗刹');
INSERT INTO `group` VALUES ('希儿', '超速希儿虚构双拉玩法', '花火', '布洛妮娅', '符玄');
INSERT INTO `group` VALUES ('布洛妮娅', '量子风队', '希儿', '银狼', '符玄');
INSERT INTO `group` VALUES ('白露', '雷系通配奶', '景元', '托帕&账账', '阮梅');

-- ----------------------------
-- Table structure for lightcone
-- ----------------------------
DROP TABLE IF EXISTS `lightcone`;
CREATE TABLE `lightcone`  (
  `lightcone_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `skill` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade` int NULL DEFAULT NULL,
  PRIMARY KEY (`lightcone_name`) USING BTREE,
  INDEX `fate`(`fate` ASC) USING BTREE,
  INDEX `lightcone_name`(`lightcone_name` ASC) USING BTREE,
  CONSTRAINT `lightcone_ibfk_1` FOREIGN KEY (`fate`) REFERENCES `role` (`fate`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lightcone
-- ----------------------------
INSERT INTO `lightcone` VALUES ('于夜色中', '巡猎', '使装备者的暴击率提高【18%/21%/24%/27%/30%】。当装备者在战斗中速度大于100时，每超过10点，普攻和战技造成的伤害提高【6%/7%/8%/9%/10%】，同时终结技的暴击伤害提高【12%/14%/16%/18%/20%】，该效果可叠加6层。', 20);
INSERT INTO `lightcone` VALUES ('以世界之名', '虚无', '使装备者对陷入负面效果的敌方目标造成的伤害提高【24%/28%/32%/36%/40%】。当装备者施放战技时，装备者此次攻击的效果命中提高【18%/21%/24%/27%/30%】，攻击力提高【24%/28%/32%/36%/40%】。', 10);
INSERT INTO `lightcone` VALUES ('但战斗还未结束', '同谐', '使装备者的能量恢复效率提高【10%/12%/14%/16%/18%】，并在对我方目标施放终结技时恢复1个战技点。该效果每施放2次终结技可触发1次。当装备者施放战技后，使下一个行动的我方其他目标造成的伤害提高【30%/35%/40%/45%/50%】，持续1回合。', 32);
INSERT INTO `lightcone` VALUES ('制胜的瞬间', '存护', '使装备者的防御力提高【24%/28%/32%/36%/40%】，效果命中提高【24%/28%/32%/36%/40%】，同时使自身受到攻击的概率提高。当装备者受到攻击后，防御力额外提高【24%/28%/32%/36%/40%】，持续到自身回合结束。', 43);
INSERT INTO `lightcone` VALUES ('如泥酣眠', '巡猎', '使装备者的暴击伤害提高【30%/35%/40%/45%/50%】。当装备者的普攻或战技伤害未造成暴击时，使自身暴击率提高【36%/42%/48%/54%/60%】，持续1回合。该效果每3回合可以触发1次。', 2);
INSERT INTO `lightcone` VALUES ('拂晓之前', '智识', '使装备者暴击伤害提高【36%/42%/48%/54%/60%】。使装备者战技和终结技造成的伤害提高【18%/21%/24%/27%/30%】。当装备者施放战技或终结技后,获得【梦身】效果。触发追加攻击时，消耗【梦身】，使追加攻击造成的伤害提高【48%/56%/64%/72%/80%】。', 13);
INSERT INTO `lightcone` VALUES ('无可取代的东西', '毁灭', '使装备者的攻击力提高【24%/28%/32%/36%/40%】。当装备者消灭敌方目标或受到攻击后，立即回复等同于装备者攻击力【8%/9%/10%/11%/12%】的生命值，同时造成的伤害提高【24%/28%/32%/36%/40%】，持续到自身下个回合结束。该效果不可叠加，每回合只可触发1次。', 50);
INSERT INTO `lightcone` VALUES ('时节不居', '丰饶', '使装备者生命上限提高【18%/21%/24%/27%/30%】，治疗量提高【12%/14%/16%/18%/20%】。当装备者对我方目标提供治疗时，记录治疗量。当任意我方目标施放攻击后，根据记录治疗量的【36%/42%/48%/54%/60%】，对随机1个受到攻击的敌方目标造成基于装备者属性的附加伤害。该伤害不受加成影响，每回合最多结算1次。', 23);
INSERT INTO `lightcone` VALUES ('银河铁道之夜', '智识', '场上每有1个敌方目标，使装备者的攻击力提高【9%/10.5%/12%/13.5%/15%】，该效果最多叠加5层。当有敌方目标的弱点被击破时，装备者造成的伤害提高【30%/35%/40%/45%/50%】，持续1回合。', 32);

-- ----------------------------
-- Table structure for lightconedata
-- ----------------------------
DROP TABLE IF EXISTS `lightconedata`;
CREATE TABLE `lightconedata`  (
  `lightcone_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `health` int NULL DEFAULT NULL,
  `attack` int NULL DEFAULT NULL,
  `defence` int NULL DEFAULT NULL,
  INDEX `lightcone_name`(`lightcone_name` ASC) USING BTREE,
  CONSTRAINT `lightconedata_ibfk_1` FOREIGN KEY (`lightcone_name`) REFERENCES `lightcone` (`lightcone_name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lightconedata
-- ----------------------------
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '1级', 52, 26, 18);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '20级（晋级前）', 203, 101, 69);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '20级（晋级后）', 266, 133, 90);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '30级（晋级前）', 345, 172, 117);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '30级（晋级后）', 430, 215, 146);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '40级（晋级前）', 509, 254, 173);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '40级（晋级后）', 594, 297, 202);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '50级（晋级前）', 673, 336, 229);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '50级（晋级后）', 757, 378, 258);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '60级（晋级前）', 836, 418, 285);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '60级（晋级后）', 921, 460, 314);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '70级（晋级前）', 1000, 500, 341);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '70级（晋级后）', 1085, 542, 369);
INSERT INTO `lightconedata` VALUES ('无可取代的东西', '80级', 1164, 582, 396);
INSERT INTO `lightconedata` VALUES ('于夜色中', '1级', 48, 26, 21);
INSERT INTO `lightconedata` VALUES ('于夜色中', '20级（晋级前）', 184, 101, 80);
INSERT INTO `lightconedata` VALUES ('于夜色中', '20级（晋级后）', 242, 133, 106);
INSERT INTO `lightconedata` VALUES ('于夜色中', '30级（晋级前）', 314, 172, 137);
INSERT INTO `lightconedata` VALUES ('于夜色中', '30级（晋级后）', 391, 215, 171);
INSERT INTO `lightconedata` VALUES ('于夜色中', '40级（晋级前）', 463, 254, 202);
INSERT INTO `lightconedata` VALUES ('于夜色中', '40级（晋级后）', 540, 297, 236);
INSERT INTO `lightconedata` VALUES ('于夜色中', '50级（晋级前）', 612, 336, 267);
INSERT INTO `lightconedata` VALUES ('于夜色中', '50级（晋级后）', 688, 378, 301);
INSERT INTO `lightconedata` VALUES ('于夜色中', '60级（晋级前）', 760, 418, 332);
INSERT INTO `lightconedata` VALUES ('于夜色中', '60级（晋级后）', 837, 460, 366);
INSERT INTO `lightconedata` VALUES ('于夜色中', '70级（晋级前）', 909, 500, 397);
INSERT INTO `lightconedata` VALUES ('于夜色中', '70级（晋级后）', 986, 542, 431);
INSERT INTO `lightconedata` VALUES ('于夜色中', '80级', 1058, 582, 463);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '1级', 48, 26, 21);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '20级（晋级前）', 184, 101, 80);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '20级（晋级后）', 242, 133, 106);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '30级（晋级前）', 314, 172, 137);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '30级（晋级后）', 391, 215, 171);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '40级（晋级前）', 463, 254, 202);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '40级（晋级后）', 540, 297, 236);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '50级（晋级前）', 612, 336, 267);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '50级（晋级后）', 688, 378, 301);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '60级（晋级前）', 760, 418, 332);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '60级（晋级后）', 837, 460, 366);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '70级（晋级前）', 909, 500, 397);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '70级（晋级后）', 986, 542, 431);
INSERT INTO `lightconedata` VALUES ('如泥酣眠', '80级', 1058, 582, 463);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '1级', 52, 26, 18);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '20级（晋级前）', 203, 101, 69);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '20级（晋级后）', 266, 133, 90);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '30级（晋级前）', 345, 172, 117);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '30级（晋级后）', 430, 215, 146);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '40级（晋级前）', 509, 254, 173);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '40级（晋级后）', 594, 297, 202);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '50级（晋级前）', 673, 336, 229);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '50级（晋级后）', 757, 378, 258);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '60级（晋级前）', 836, 418, 285);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '60级（晋级后）', 921, 460, 314);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '70级（晋级前）', 1000, 500, 341);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '70级（晋级后）', 1085, 542, 369);
INSERT INTO `lightconedata` VALUES ('银河铁道之夜', '80级', 1164, 582, 396);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '1级', 48, 26, 21);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '20级（晋级前）', 184, 101, 80);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '20级（晋级后）', 242, 133, 106);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '30级（晋级前）', 314, 172, 137);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '30级（晋级后）', 391, 215, 171);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '40级（晋级前）', 463, 254, 202);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '40级（晋级后）', 540, 297, 236);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '50级（晋级前）', 612, 336, 267);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '50级（晋级后）', 688, 378, 301);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '60级（晋级前）', 760, 418, 332);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '60级（晋级后）', 837, 460, 366);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '70级（晋级前）', 909, 500, 397);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '70级（晋级后）', 986, 542, 431);
INSERT INTO `lightconedata` VALUES ('拂晓之前', '80级', 1058, 582, 463);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '1级', 52, 24, 21);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '20级（晋级前）', 203, 92, 80);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '20级（晋级后）', 266, 121, 106);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '30级（晋级前）', 345, 157, 137);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '30级（晋级后）', 430, 195, 171);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '40级（晋级前）', 509, 231, 202);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '40级（晋级后）', 594, 270, 236);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '50级（晋级前）', 673, 306, 267);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '50级（晋级后）', 757, 344, 301);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '60级（晋级前）', 836, 380, 332);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '60级（晋级后）', 921, 418, 366);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '70级（晋级前）', 1000, 454, 397);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '70级（晋级后）', 1058, 493, 431);
INSERT INTO `lightconedata` VALUES ('但战斗还未结束', '80级', 1164, 529, 463);
INSERT INTO `lightconedata` VALUES ('以世界之名', '1级', 48, 26, 21);
INSERT INTO `lightconedata` VALUES ('以世界之名', '20级（晋级前）', 184, 101, 80);
INSERT INTO `lightconedata` VALUES ('以世界之名', '20级（晋级后）', 242, 133, 106);
INSERT INTO `lightconedata` VALUES ('以世界之名', '30级（晋级前）', 314, 172, 137);
INSERT INTO `lightconedata` VALUES ('以世界之名', '30级（晋级后）', 391, 215, 171);
INSERT INTO `lightconedata` VALUES ('以世界之名', '40级（晋级前）', 463, 254, 202);
INSERT INTO `lightconedata` VALUES ('以世界之名', '40级（晋级后）', 540, 297, 236);
INSERT INTO `lightconedata` VALUES ('以世界之名', '50级（晋级前）', 612, 336, 267);
INSERT INTO `lightconedata` VALUES ('以世界之名', '50级（晋级后）', 688, 378, 301);
INSERT INTO `lightconedata` VALUES ('以世界之名', '60级（晋级前）', 760, 418, 332);
INSERT INTO `lightconedata` VALUES ('以世界之名', '60级（晋级后）', 837, 460, 366);
INSERT INTO `lightconedata` VALUES ('以世界之名', '70级（晋级前）', 909, 500, 397);
INSERT INTO `lightconedata` VALUES ('以世界之名', '70级（晋级后）', 986, 542, 431);
INSERT INTO `lightconedata` VALUES ('以世界之名', '80级', 1058, 582, 463);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '1级', 48, 21, 27);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '20级（晋级前）', 184, 83, 103);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '20级（晋级后）', 242, 109, 136);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '30级（晋级前）', 314, 141, 176);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '30级（晋级后）', 391, 176, 220);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '40级（晋级前）', 463, 208, 260);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '40级（晋级后）', 540, 243, 303);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '50级（晋级前）', 612, 275, 344);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '50级（晋级后）', 688, 309, 387);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '60级（晋级前）', 760, 342, 427);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '60级（晋级后）', 837, 376, 471);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '70级（晋级前）', 909, 409, 511);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '70级（晋级后）', 986, 443, 554);
INSERT INTO `lightconedata` VALUES ('制胜的瞬间', '80级', 1058, 476, 595);
INSERT INTO `lightconedata` VALUES ('时节不居', '1级', 57, 21, 21);
INSERT INTO `lightconedata` VALUES ('时节不居', '20级（晋级前）', 221, 83, 80);
INSERT INTO `lightconedata` VALUES ('时节不居', '20级（晋级后）', 290, 109, 106);
INSERT INTO `lightconedata` VALUES ('时节不居', '30级（晋级前）', 377, 141, 137);
INSERT INTO `lightconedata` VALUES ('时节不居', '30级（晋级后）', 469, 176, 171);
INSERT INTO `lightconedata` VALUES ('时节不居', '40级（晋级前）', 555, 208, 202);
INSERT INTO `lightconedata` VALUES ('时节不居', '40级（晋级后）', 648, 243, 236);
INSERT INTO `lightconedata` VALUES ('时节不居', '50级（晋级前）', 734, 275, 267);
INSERT INTO `lightconedata` VALUES ('时节不居', '50级（晋级后）', 826, 309, 301);
INSERT INTO `lightconedata` VALUES ('时节不居', '60级（晋级前）', 912, 342, 332);
INSERT INTO `lightconedata` VALUES ('时节不居', '60级（晋级后）', 1005, 376, 366);
INSERT INTO `lightconedata` VALUES ('时节不居', '70级（晋级前）', 1091, 409, 397);
INSERT INTO `lightconedata` VALUES ('时节不居', '70级（晋级后）', 1183, 443, 431);
INSERT INTO `lightconedata` VALUES ('时节不居', '80级', 1270, 476, 463);

-- ----------------------------
-- Table structure for lightconeupdate
-- ----------------------------
DROP TABLE IF EXISTS `lightconeupdate`;
CREATE TABLE `lightconeupdate`  (
  `lightcone_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `updatelevel` int NULL DEFAULT NULL,
  `credit_point` int NULL DEFAULT NULL,
  `two_ma` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `three_ma` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `four_ma` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  INDEX `lightcone_name`(`lightcone_name` ASC) USING BTREE,
  CONSTRAINT `lightconeupdate_ibfk_1` FOREIGN KEY (`lightcone_name`) REFERENCES `lightcone` (`lightcone_name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lightconeupdate
-- ----------------------------
INSERT INTO `lightconeupdate` VALUES ('无可取代的东西', 20, 5000, '古代零件*8', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('无可取代的东西', 30, 10000, '猎兽之矢*4+古代零件*12', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('无可取代的东西', 40, 20000, NULL, '无生残刃*4+古代转轴*8', NULL);
INSERT INTO `lightconeupdate` VALUES ('无可取代的东西', 50, 50000, NULL, '无生残刃*8+古代转轴*12', NULL);
INSERT INTO `lightconeupdate` VALUES ('无可取代的东西', 60, 100000, NULL, NULL, '净世残刃*5+古代引擎*6');
INSERT INTO `lightconeupdate` VALUES ('无可取代的东西', 70, 200000, NULL, NULL, '净世残刃*10+古代引擎*8');
INSERT INTO `lightconeupdate` VALUES ('于夜色中', 20, 5000, '掠夺的本能*8', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('于夜色中', 30, 10000, '破碎残刃*4+掠夺的本能*12', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('于夜色中', 40, 20000, NULL, '屠魔之矢*4+篡改的野心8', NULL);
INSERT INTO `lightconeupdate` VALUES ('于夜色中', 50, 50000, NULL, '屠魔之矢*8+篡改的野心*12', NULL);
INSERT INTO `lightconeupdate` VALUES ('于夜色中', 60, 100000, NULL, NULL, '逐星之矢*5+践踏的意志*6');
INSERT INTO `lightconeupdate` VALUES ('于夜色中', 70, 200000, NULL, NULL, '逐星之矢*10+践踏的意志*8');
INSERT INTO `lightconeupdate` VALUES ('如泥酣眠', 20, 5000, '掠夺的本能*8', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('如泥酣眠', 30, 10000, '猎兽之矢*4+掠夺的本能*12', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('如泥酣眠', 40, 20000, NULL, '屠魔之矢*4+篡改的野心*8', NULL);
INSERT INTO `lightconeupdate` VALUES ('如泥酣眠', 50, 50000, NULL, '屠魔之矢*8+篡改的野心*12', NULL);
INSERT INTO `lightconeupdate` VALUES ('如泥酣眠', 60, 100000, NULL, NULL, '逐星之矢*5+践踏的意志*6');
INSERT INTO `lightconeupdate` VALUES ('如泥酣眠', 70, 200000, NULL, NULL, '逐星之矢*10+践踏的意志*8');
INSERT INTO `lightconeupdate` VALUES ('银河铁道之夜', 20, 5000, '熄灭原核*8', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('银河铁道之夜', 30, 10000, '灵感之钥*4+熄灭原核*12', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('银河铁道之夜', 40, 20000, NULL, '启迪之钥*4+微光原核*8', NULL);
INSERT INTO `lightconeupdate` VALUES ('银河铁道之夜', 50, 50000, NULL, '启迪之钥*8+微光原核*12', NULL);
INSERT INTO `lightconeupdate` VALUES ('银河铁道之夜', 60, 100000, NULL, NULL, '智识之钥*5+蠢动原核*6');
INSERT INTO `lightconeupdate` VALUES ('银河铁道之夜', 70, 200000, NULL, NULL, '智识之钥*10+蠢动原核*8');
INSERT INTO `lightconeupdate` VALUES ('拂晓之前', 20, 5000, '永寿幼芽*8', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('拂晓之前', 30, 10000, '灵感之钥*4+永寿幼芽*12', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('拂晓之前', 40, 20000, NULL, '启迪之钥*4+永寿天华*8', NULL);
INSERT INTO `lightconeupdate` VALUES ('拂晓之前', 50, 50000, NULL, '启迪之钥*8+永寿天华*12', NULL);
INSERT INTO `lightconeupdate` VALUES ('拂晓之前', 60, 100000, NULL, NULL, '智识之钥*5+永寿荣枝*6');
INSERT INTO `lightconeupdate` VALUES ('拂晓之前', 70, 200000, NULL, NULL, '智识之钥*10+永寿荣枝*8');
INSERT INTO `lightconeupdate` VALUES ('但战斗还未结束', 20, 5000, '铁卫扣饰*8', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('但战斗还未结束', 30, 10000, '谐乐小调*4+铁卫扣饰*12', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('但战斗还未结束', 40, 20000, NULL, '家族颂歌*4+铁卫军徽*8', NULL);
INSERT INTO `lightconeupdate` VALUES ('但战斗还未结束', 50, 50000, NULL, '家族颂歌*8+铁卫军徽*12', NULL);
INSERT INTO `lightconeupdate` VALUES ('但战斗还未结束', 60, 100000, NULL, NULL, '群星乐章*5+铁卫勋章*6');
INSERT INTO `lightconeupdate` VALUES ('但战斗还未结束', 70, 200000, NULL, NULL, '群星乐章*10+铁卫勋章*8');
INSERT INTO `lightconeupdate` VALUES ('以世界之名', 20, 5000, '铁卫扣饰*8', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('以世界之名', 30, 10000, '暗淡黑曜*4+铁卫扣饰*12', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('以世界之名', 40, 20000, NULL, '虚空黑曜*4+铁卫军徽*8', NULL);
INSERT INTO `lightconeupdate` VALUES ('以世界之名', 50, 50000, NULL, '虚空黑曜*8+铁卫军徽*12', NULL);
INSERT INTO `lightconeupdate` VALUES ('以世界之名', 60, 100000, NULL, NULL, '沉沦黑曜*5+铁卫勋章*6');
INSERT INTO `lightconeupdate` VALUES ('以世界之名', 70, 200000, NULL, NULL, '沉沦黑曜*10+铁卫勋章*8');
INSERT INTO `lightconeupdate` VALUES ('制胜的瞬间', 20, 5000, '铁卫扣饰*8', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('制胜的瞬间', 30, 10000, '青铜的执着*4+铁卫扣饰*12', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('制胜的瞬间', 40, 20000, NULL, '寒铁的誓言*4+铁卫军徽*8', NULL);
INSERT INTO `lightconeupdate` VALUES ('制胜的瞬间', 50, 50000, NULL, '寒铁的誓言*8+铁卫军徽*12', NULL);
INSERT INTO `lightconeupdate` VALUES ('制胜的瞬间', 60, 100000, NULL, NULL, '琥珀的坚守*5+铁卫勋章*6');
INSERT INTO `lightconeupdate` VALUES ('制胜的瞬间', 70, 200000, NULL, NULL, '琥珀的坚守*10+铁卫勋章*8');
INSERT INTO `lightconeupdate` VALUES ('时节不居', 20, 5000, '熄灭原核*8', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('时节不居', 30, 10000, '丰饶之种*4+熄灭原核*12', NULL, NULL);
INSERT INTO `lightconeupdate` VALUES ('时节不居', 40, 20000, NULL, '生命之芽*4+微光原核*8', NULL);
INSERT INTO `lightconeupdate` VALUES ('时节不居', 50, 50000, NULL, '生命之芽*8+微光原核*12', NULL);
INSERT INTO `lightconeupdate` VALUES ('时节不居', 60, 100000, NULL, NULL, '永恒之花*5+蠢动原核*6');
INSERT INTO `lightconeupdate` VALUES ('时节不居', 70, 200000, NULL, NULL, '永恒之花*10+蠢动原核*8');

-- ----------------------------
-- Table structure for relic
-- ----------------------------
DROP TABLE IF EXISTS `relic`;
CREATE TABLE `relic`  (
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `relic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`role_name`) USING BTREE,
  INDEX `role_name`(`role_name` ASC) USING BTREE,
  CONSTRAINT `relic_ibfk_1` FOREIGN KEY (`role_name`) REFERENCES `role` (`role_name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relic
-- ----------------------------
INSERT INTO `relic` VALUES ('克拉拉', '街头出身的拳王');
INSERT INTO `relic` VALUES ('姬子', '毁烬焚骨的大公');
INSERT INTO `relic` VALUES ('布洛妮娅', '骇域漫游的信使');
INSERT INTO `relic` VALUES ('希儿', '繁星璀璨的天才');
INSERT INTO `relic` VALUES ('彦卿', '密林卧雪的猎人');
INSERT INTO `relic` VALUES ('景元', '毁烬焚骨的大公');
INSERT INTO `relic` VALUES ('杰帕德', '净庭教宗的圣骑士');
INSERT INTO `relic` VALUES ('瓦尔特', '死水深潜的先驱');
INSERT INTO `relic` VALUES ('白露', '宝命长存的莳者');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `grade` int NULL DEFAULT NULL,
  `fate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `attributes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `UID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_name` DESC, `fate` DESC) USING BTREE,
  INDEX `fate`(`fate` ASC) USING BTREE,
  INDEX `role_name`(`role_name` ASC) USING BTREE,
  INDEX `UID`(`UID` ASC) USING BTREE,
  CONSTRAINT `role_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('白露', 80, '丰饶', '雷', '9988776655');
INSERT INTO `role` VALUES ('瓦尔特', 80, '虚无', '虚数', '9988776655');
INSERT INTO `role` VALUES ('杰帕德', 80, '存护', '冰', '9988776655');
INSERT INTO `role` VALUES ('景元', 80, '智识', '雷', '9988776655');
INSERT INTO `role` VALUES ('彦卿', 80, '巡猎', '冰', '9988776655');
INSERT INTO `role` VALUES ('希儿', 80, '巡猎', '量子', '9988776655');
INSERT INTO `role` VALUES ('布洛妮娅', 80, '同谐', '风', '9988776655');
INSERT INTO `role` VALUES ('姬子', 30, '智识', '火', '9988776655');
INSERT INTO `role` VALUES ('克拉拉', 80, '毁灭', '物理', '9988776655');

-- ----------------------------
-- Table structure for roleupdate
-- ----------------------------
DROP TABLE IF EXISTS `roleupdate`;
CREATE TABLE `roleupdate`  (
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `twenty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `thirty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `forty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fifty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sixty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `seventy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_name` DESC) USING BTREE,
  INDEX `roleupdate_ibfk_1`(`role_name` ASC) USING BTREE,
  CONSTRAINT `roleupdate_ibfk_1` FOREIGN KEY (`role_name`) REFERENCES `role` (`role_name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roleupdate
-- ----------------------------
INSERT INTO `roleupdate` VALUES ('白露', '熄灭原核 *5+信用点 *4000', '熄灭原核 *15+信用点 *8000', '往日之影的雷冠 *3+微光原核 *6+信用点 *16000', '往日之影的雷冠 *7+微光原核 *9+信用点 *40000', '往日之影的雷冠 *20+蠢动原核 *6+信用点 *80000', '往日之影的雷冠 *35+蠢动原核 *9+信用点 *160000');
INSERT INTO `roleupdate` VALUES ('瓦尔特', '铁卫扣饰 *5+信用点 *4000', '铁卫扣饰 *10+信用点 *8000', '铁卫军徽 *6+往日之影的金饰 *3+信用点 *16000', '铁卫军徽 *9+往日之影的金饰 *7+信用点 *40000', '铁卫勋章 *6+往日之影的金饰 *20+信用点 *80000', '铁卫勋章 *9+往日之影的金饰 *35+信用点 *160000');
INSERT INTO `roleupdate` VALUES ('杰帕德', '铁卫扣饰 *5+信用点 *4000', '铁卫扣饰 *10+信用点 *8000', '铁卫军徽 *6+风雪之角 *3+信用点 *16000', '铁卫军徽 *9+风雪之角 *7+信用点 *40000', '铁卫勋章 *6+风雪之角 *20+信用点 *80000', '铁卫勋章 *9+风雪之角 *35+信用点 *160000');
INSERT INTO `roleupdate` VALUES ('景元', '永寿幼芽 *5+信用点 *4000', '永寿幼芽 *10+信用点 *8000', '永寿天华 *6+炼形者雷枝 *3+信用点 *16000', '永寿天华 *9+炼形者雷枝 *7+信用点 *40000', '永寿荣枝 *6+炼形者雷枝 *20+信用点 *80000', '永寿荣枝 *9+炼形者雷枝 *35+信用点 *160000');
INSERT INTO `roleupdate` VALUES ('彦卿', '掠夺的本能 *5+信用点 *4000', '掠夺的本能 *10+信用点 *8000', '苦寒晶壳 *3+篡改的野心 *6+信用点 *16000', '苦寒晶壳 *7+篡改的野心 *9+信用点 *40000', '苦寒晶壳 *20+践踏的意志 *6+信用点 *80000', '苦寒晶壳 *35+践踏的意志 *9+信用点 *160000');
INSERT INTO `roleupdate` VALUES ('希儿', '掠夺的本能 *5+信用点 *4000', '掠夺的本能 *10+信用点 *8000', '篡改的野心 *6+虚幻铸铁 *3+信用点 *16000', '篡改的野心 *9+虚幻铸铁 *7+信用点 *40000', '践踏的意志 *6+虚幻铸铁 *20+信用点 *80000', '践踏的意志 *9+虚幻铸铁 *35+信用点 *160000');
INSERT INTO `roleupdate` VALUES ('布洛妮娅', '铁卫扣饰 *5+信用点 *4000', '铁卫扣饰 *10+信用点 *8000', '暴风之眼 *3+铁卫军徽 *6+信用点 *16000', '暴风之眼 *7+铁卫军徽 *9+信用点 *40000', '暴风之眼 *20+铁卫勋章 *6+信用点 *80000', '暴风之眼 *35+铁卫勋章 *9+信用点 *160000');
INSERT INTO `roleupdate` VALUES ('姬子', '熄灭原核 *5+信用点 *4000', '熄灭原核 *10+信用点 *8000', '微光原核 *6+恒温晶壳 *3+信用点 *16000', '微光原核 *9+恒温晶壳 *7+信用点 *40000', '蠢动原核 *6+恒温晶壳 *20+信用点 *80000', '蠢动原核 *9+恒温晶壳 *35+信用点 *160000');
INSERT INTO `roleupdate` VALUES ('克拉拉', '古代零件 *5+信用点 *4000', '古代零件 *10+信用点 *8000', '古代转轴 *6+铁狼碎齿 *3+信用点 *16000', '古代转轴 *9+铁狼碎齿 *7+信用点 *40000', '古代引擎 *6+铁狼碎齿 *20+信用点 *80000', '古代引擎 *9+铁狼碎齿 *35+信用点 *160000');

-- ----------------------------
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill`  (
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `basicattack` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `finisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `talent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `secrettechniques` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `combatskills` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_name`) USING BTREE,
  CONSTRAINT `skill_ibfk_1` FOREIGN KEY (`role_name`) REFERENCES `role` (`role_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of skill
-- ----------------------------
INSERT INTO `skill` VALUES ('克拉拉', ' 我也想帮上忙', '是约定不是命令', '因为我们是家人', '胜利的小小代价', '史瓦罗在看着你');
INSERT INTO `skill` VALUES ('姬子', '100', '新finisher', '新talent', '新secrettechniques', '新combatskills');
INSERT INTO `skill` VALUES ('布洛妮娅', '驭风', '贝尔伯格进行曲', '先人一步', '在旗帜下', '作战再部署');
INSERT INTO `skill` VALUES ('彦卿', '霜锋点寒芒', '快雨燕相逐', '呼剑如影', '御剑真诀', '遥击三尺水');
INSERT INTO `skill` VALUES ('景元', '石火流光', '吾身光明', '斩勘神形', '摄召威灵', '紫霄震曜');
INSERT INTO `skill` VALUES ('杰帕德', '一意之拳', '永屹之壁', '不屈之身', '有情之证', '震慑之击');
INSERT INTO `skill` VALUES ('瓦尔特', '重力压制', '拟似黑洞', '时空扭曲', '画地为牢', '虚空断界');
INSERT INTO `skill` VALUES ('白露', '望、闻、问…蹴！', '匏蛟跃渊先雷音', '奔走悬壶济世长', '徜徉灵泽病恙短', '云吟乍涌坠珠露');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `captcha` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `UID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `region` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `UID`(`UID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1234567890', 'u123', 'password123', 'abc123', 'uid123', '男', 'region123', NULL);
INSERT INTO `user` VALUES ('13698765432', '刘敏', 'asdfgh5678', 'uid4', '5566778899', '女', '深圳', NULL);
INSERT INTO `user` VALUES ('13078901234', '吴洋', 'wy837293732', 'xyz789', '9988776655', '男', '苏州', 'D:\\YoyuUser\\图片\\R-C.jpg');
INSERT INTO `user` VALUES ('13167890123', '周磊', 'qazwsxedc1', 'uid9', '1122334455', '男', '天津', NULL);
INSERT INTO `user` VALUES ('13787654321', '张娜', 'qwerty1234', 'uid3', '1122334455', '女', '广州', NULL);
INSERT INTO `user` VALUES ('13976543210', '李伟', 'p4sw0rd123', 'uid1', '1234567890', '男', '北京', NULL);
INSERT INTO `user` VALUES ('13434567890', '杨秀英', 'poiuyt8765', 'uid6', '4455667788', '女', '南京', NULL);
INSERT INTO `user` VALUES ('13812345678', '王芳', 'abcde12345', 'uid2', '0987654321', '女', '上海', NULL);
INSERT INTO `user` VALUES ('13345678901', '赵丽', 'lkjhgfd123', 'uid7', '3344556677', '女', '成都', NULL);
INSERT INTO `user` VALUES ('13523456789', '陈静', 'zxcvbn4321', 'uid5', '6677889900', '女', '杭州', NULL);
INSERT INTO `user` VALUES ('13256789012', '黄强', 'mnbvcxz987', 'uid8', '2233445566', '男', '重庆', NULL);

SET FOREIGN_KEY_CHECKS = 1;
