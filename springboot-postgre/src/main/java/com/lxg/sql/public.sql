/*
 Navicat Premium Data Transfer

 Source Server         : My_PG
 Source Server Type    : PostgreSQL
 Source Server Version : 140004
 Source Host           : 192.168.56.102:5432
 Source Catalog        : postgres
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140004
 File Encoding         : 65001

 Date: 15/08/2022 14:19:16
*/


-- ----------------------------
-- Sequence structure for User_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."User_id_seq";
CREATE SEQUENCE "public"."User_id_seq"
    INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS "public"."student";
CREATE TABLE "public"."student" (
                                    "id" varchar COLLATE "pg_catalog"."default",
                                    "scores" int4[],
                                    "detail" jsonb,
                                    "school" jsonb
)
;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO "public"."student" VALUES ('1559054351975088129', '{99,72,88}', '{"姓名": "李四", "年龄": "23"}', '[{"小学": "广州小学"}, {"初中": "广州初中"}, {"高中": "广州高中"}]');
INSERT INTO "public"."student" VALUES ('1559054201080807426', '{97,108,103}', '{"姓名": "张三", "年龄": "21"}', '[{"小学": "深圳小学"}, {"初中": "深圳初中"}, {"高中": "深圳高中"}]');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "public"."user";
CREATE TABLE "public"."user" (
                                 "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL DEFAULT nextval('"User_id_seq"'::regclass),
                                 "username" varchar(20) COLLATE "pg_catalog"."default",
                                 "password" varchar(20) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO "public"."user" VALUES ('1557985624521191426', '张三', 'abc');

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."User_id_seq"
    OWNED BY "public"."user"."id";
SELECT setval('"public"."User_id_seq"', 2, false);

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD CONSTRAINT "User_pkey" PRIMARY KEY ("id");
