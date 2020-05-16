package com.example.coolq.vo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 加好友请求响应
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class FriendRequestVO extends JsonVO {

    /**
     * 是否同意请求
     * default: null 不处理
     */
    private Boolean approve;
    /**
     * 添加后的好友备注（仅在同意时有效）
     * default: null 无备注
     */
    private String remark;

}
