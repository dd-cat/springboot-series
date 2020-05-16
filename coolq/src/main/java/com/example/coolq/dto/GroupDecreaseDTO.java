package com.example.coolq.dto;

import lombok.Data;

/**
 * 群成员减少
 */
@Data
public class GroupDecreaseDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 通知类型
     */
    private String noticeType;
    /**
     * leave、kick、kick_me 事件子类型，分别表示主动退群、成员被踢、登录号被踢
     */
    private String subType;
    /**
     * 群号
     */
    private Integer groupId;
    /**
     * 操作者 QQ 号（如果是主动退群，则和 user_id 相同）
     */
    private Integer operatorId;
    /**
     * 离开者 QQ 号
     */
    private Integer userId;

}
