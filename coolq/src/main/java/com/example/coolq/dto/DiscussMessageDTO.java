package com.example.coolq.dto;

import lombok.Data;

/**
 * 讨论组消息
 */
@Data
public class DiscussMessageDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 消息类型
     */
    private String messageType;
    /**
     * 消息 ID
     */
    private Integer messageId;
    /**
     * 讨论组 ID
     */
    private Integer discussId;
    /**
     * 发送者 QQ 号
     */
    private Integer userId;
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
         * 性别 male 或 female 或 unknown
         */
        private String sex;
        /**
         * 年龄
         */
        private Integer age;
    }

}
