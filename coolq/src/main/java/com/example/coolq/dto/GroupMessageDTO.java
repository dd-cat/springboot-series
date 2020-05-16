package com.example.coolq.dto;

import lombok.Data;

/**
 * 群消息
 */
@Data
public class GroupMessageDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 消息类型
     */
    private String messageType;
    /**
     * 消息子类型，正常消息是 normal，匿名消息是 anonymous，系统提示（如「管理员已禁止群内匿名聊天」）是 notice
     * normal、anonymous、notice
     *
     */
    private String subType;
    /**
     * 消息 ID
     */
    private Integer messageId;
    /**
     * 群号
     */
    private Integer groupId;
    /**
     * 发送者 QQ 号
     */
    private Integer userId;
    /**
     * 匿名信息，如果不是匿名消息则为 null
     */
    private Anonymous anonymous;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 原始消息内容
     */
    private String rawMessage;
    /**
     * 字体
     */
    private Integer font;
    /**
     * 发送人信息
     */
    private Sender sender;

    @Data
    public static class Sender {
        /**
         * 发送者 QQ 号
         */
        private Integer userId;
        /**
         * 昵称
         */
        private String nickname;
        /**
         * 群名片／备注
         */
        private String card;
        /**
         * 性别 male 或 female 或 unknown
         */
        private String sex;
        /**
         * 年龄
         */
        private Integer age;
        /**
         * 地区
         */
        private String area;
        /**
         * 成员等级
         */
        private String level;
        /**
         * 角色
         */
        private String role;
        /**
         * 专属头衔
         */
        private String title;

    }

    @Data
    public static class Anonymous {
        /**
         * 匿名用户 ID
         */
        private Integer id;
        /**
         * 匿名用户名称
         */
        private String name;
        /**
         * 匿名用户 flag，在调用禁言 API 时需要传入
         */
        private String flag;
    }

}
