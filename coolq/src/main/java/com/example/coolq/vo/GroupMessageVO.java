package com.example.coolq.vo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class GroupMessageVO extends JsonVO {

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
     * 是否要在回复开头 at 发送者（自动添加），发送者是匿名用户时无效
     * default: true
     */
    private Boolean atSender;
    /**
     * 撤回该条消息
     * default: false
     */
    private Boolean delete;
    /**
     * 把发送者踢出群组（需要登录号权限足够），不拒绝此人后续加群请求，发送者是匿名用户时无效
     * default: false
     */
    private Boolean kick;
    /**
     * 把发送者禁言 ban_duration 指定时长，对匿名用户也有效
     * default: false
     */
    private Boolean ban;
    /**
     * 禁言时长
     * default: 30
     */
    private Integer banDuration;

}
