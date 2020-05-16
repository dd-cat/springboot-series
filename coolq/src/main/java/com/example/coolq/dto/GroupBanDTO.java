package com.example.coolq.dto;

import lombok.Data;

/**
 * 群禁言
 */
@Data
public class GroupBanDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 通知类型
     */
    private String noticeType;
    /**
     * ban、lift_ban 事件子类型，分别表示禁言、解除禁言
     */
    private String subType;
    /**
     * 群号
     */
    private Integer groupId;
    /**
     * 操作者 QQ 号
     */
    private Integer operatorId;
    /**
     * 被禁言 QQ 号
     */
    private Integer userId;
    /**
     * 禁言时长，单位秒
     */
    private Integer duration;

}
