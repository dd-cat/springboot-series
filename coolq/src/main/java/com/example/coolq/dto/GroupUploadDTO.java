package com.example.coolq.dto;

import lombok.Data;

/**
 * 群文件上传
 * 注意：仅群文件上传表现为事件，好友发送文件在 酷Q 中没有独立的事件，而是直接表现为好友消息，请注意在编写业务逻辑时进行判断。
 */
@Data
public class GroupUploadDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 通知类型
     */
    private String noticeType;
    /**
     * 群号
     */
    private Integer groupId;
    /**
     * 发送者 QQ 号
     */
    private Integer userId;
    /**
     * 文件信息
     */
    private File file;

    public static class File {
        /**
         * 文件 ID
         */
        private String id;
        /**
         * 文件名
         */
        private String name;
        /**
         * 文件大小（字节数）
         */
        private Integer size;
        /**
         * busid（目前不清楚有什么作用）
         */
        private Integer busid;
    }
}
