package com.example.coolq.dto;

import lombok.Data;

/**
 * 好友添加
 */
@Data
public class FriendAddDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 通知类型
     */
    private String noticeType;
    /**
     * 新添加好友 QQ 号
     */
    private Integer userId;

}
