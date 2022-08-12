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

 Date: 12/08/2022 15:02:31
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
  "serial_id" varchar COLLATE "pg_catalog"."default",
  "name" varchar[] COLLATE "pg_catalog"."default",
  "scores" int4[]
)
;

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
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."User_id_seq"
OWNED BY "public"."user"."id";
SELECT setval('"public"."User_id_seq"', 2, false);

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD CONSTRAINT "User_pkey" PRIMARY KEY ("id");
