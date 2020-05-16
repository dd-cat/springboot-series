package com.example.coolq.vo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class GroupRequestVO extends JsonVO {

    /**
     * 是否同意请求
     * default: null 不处理
     */
    private Boolean approve;
    /**
     * 拒绝理由（仅在拒绝时有效）
     * default: null 无理由
     */
    private String reason;

}
