package com.example.coolq.dto;

import lombok.Data;

/**
 * 生命周期
 */
@Data
public class LifecycleDTO {

    /**
     * 上报类型
     */
    private String postType;
    /**
     * 元事件类型
     */
    private String metaEventType;
    /**
     * 事件子类型，分别表示插件启用、插件停用
     * enable、disable
     */
    private String subType;

}
