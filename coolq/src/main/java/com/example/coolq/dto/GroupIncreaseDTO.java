package com.example.coolq.dto;

import lombok.Data;

/**
 * 群成员增加
 */
@Data
public class GroupIncreaseDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 通知类型
     */
    private String noticeType;
    /**
     * approve、invite 事件子类型，分别表示管理员已同意入群、管理员邀请入群
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
     * 离开者 QQ 号
     */
    private Integer userId;

}
