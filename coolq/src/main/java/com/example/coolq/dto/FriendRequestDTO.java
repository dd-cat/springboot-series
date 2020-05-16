package com.example.coolq.dto;

import lombok.Data;

/**
 * 加好友请求
 */
@Data
public class FriendRequestDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * 发送请求的 QQ 号
     */
    private Integer userId;
    /**
     * 验证信息（可能包含 CQ 码，特殊字符被转义）
     */
    private String comment;
    /**
     * 请求 flag，在调用处理请求的 API 时需要传入
     */
    private String flag;

}
