package com.example.coolq.dto;

import lombok.Data;

/**
 * 群管理员变动
 */
@Data
public class GroupAdminDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 消通知型
     */
    private String noticeType;
    /**
     * set、unset 事件子类型，分别表示设置和取消管理员
     */
    private String subType;
    /**
     * 群号
     */
    private Integer groupId;
    /**
     * 管理员 QQ 号
     */
    private Integer userId;

}
