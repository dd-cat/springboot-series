package com.example.coolq.service;

import com.example.coolq.dto.*;
import com.example.coolq.vo.*;

public interface MessageHandlerService {

    /**
     * 处理私聊消息
     *
     * @param privateMessageDTO 私聊消息
     * @return 私聊消息回复
     * @throws Exception e
     */
    PrivateMessageVO privateMessageHandler(PrivateMessageDTO privateMessageDTO) throws Exception;

    /**
     * 处理群聊消息
     *
     * @param groupMessageDTO 群聊消息
     * @return 群消息回复
     * @throws Exception e
     */
    GroupMessageVO groupMessageHandler(GroupMessageDTO groupMessageDTO) throws Exception;

    /**
     * 处理讨论组消息
     *
     * @param discussMessageDTO 讨论组消息
     * @return 讨论组回复
     * @throws Exception e
     */
    DiscussMessageVO discussMessageHandler(DiscussMessageDTO discussMessageDTO) throws Exception;

    /**
     * 处理群文件上传
     *
     * @param groupUploadDTO 群文件上传
     * @return json
     * @throws Exception e
     */
    JsonVO groupUploadHandler(GroupUploadDTO groupUploadDTO) throws Exception;

    /**
     * 处理群管理员变动
     *
     * @param groupAdminDTO 群管理员变动
     * @return json
     * @throws Exception e
     */
    JsonVO groupAdminHandler(GroupAdminDTO groupAdminDTO) throws Exception;

    /**
     * 处理群成员减少
     *
     * @param groupDecreaseDTO 群成员减少
     * @return json
     * @throws Exception e
     */
    JsonVO groupDecreaseHandler(GroupDecreaseDTO groupDecreaseDTO) throws Exception;

    /**
     * 处理群成员增加
     *
     * @param groupIncreaseDTO 群成员增加
     * @return json
     * @throws Exception e
     */
    JsonVO groupIncreaseHandler(GroupIncreaseDTO groupIncreaseDTO) throws Exception;

    /**
     * 处理群禁言
     *
     * @param groupBanDTO 群禁言
     * @return json
     * @throws Exception e
     */
    JsonVO groupBanHandler(GroupBanDTO groupBanDTO) throws Exception;

    /**
     * 处理好友添加
     *
     * @param friendAddDTO 好友添加
     * @return json
     * @throws Exception e
     */
    JsonVO friendAddHandler(FriendAddDTO friendAddDTO) throws Exception;

    /**
     * 处理加好友请求
     *
     * @param friendRequestDTO 加好友请求
     * @return 加好友请求回复
     * @throws Exception e
     */
    FriendRequestVO friendRequestHandler(FriendRequestDTO friendRequestDTO) throws Exception;

    /**
     * 处理加群请求／邀请
     *
     * @param groupRequestDTO 加群请求／邀请
     * @return 加群请求／邀请回复
     * @throws Exception e
     */
    GroupRequestVO groupRequestHandler(GroupRequestDTO groupRequestDTO) throws Exception;

    /**
     * 处理生命周期
     *
     * @param lifecycleDTO 生命周期
     * @return json
     * @throws Exception e
     */
    JsonVO lifecycleHandler(LifecycleDTO lifecycleDTO) throws Exception;

    /**
     * 处理心跳
     *
     * @param heartbeatDTO 心跳
     * @return json
     * @throws Exception e
     */
    JsonVO heartbeatHandler(HeartbeatDTO heartbeatDTO) throws Exception;

}
