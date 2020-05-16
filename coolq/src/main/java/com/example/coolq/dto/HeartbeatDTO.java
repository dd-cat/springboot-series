package com.example.coolq.dto;

import com.example.coolq.utils.CoolqHttpUtil;
import lombok.Data;

@Data
public class HeartbeatDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 元事件类型
     */
    private String metaEventType;
    /**
     * 状态信息
     */
    private CoolqHttpUtil.ResData.Status status;
}
