package com.example.coolq.vo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class DiscussMessageVO extends JsonVO {

    /**
     * 要回复的内容
     * default: 不回复
     */
    private String reply;
    /**
     * 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 reply 字段是字符串时有效
     * default: false
     */
    private Boolean autoEscape;

    /**
     * 是否要在回复开头 at 发送者（自动添加）
     * default: true
     */
    private Boolean atSender;

}
