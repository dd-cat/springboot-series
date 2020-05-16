package com.example.coolq.service;

import com.example.coolq.dto.*;
import com.example.coolq.vo.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageHandlerServiceImpl implements MessageHandlerService {

    CoolqHttpService coolqHttpService;

    @Override
    public PrivateMessageVO privateMessageHandler(PrivateMessageDTO privateMessageDTO) throws Exception {
        coolqHttpService.sendPrivateMsg(privateMessageDTO.getUserId(),
                "你已经成功启动了Coolq spring boot项目", true);
        return PrivateMessageVO.builder().build();
    }

    @Override
    public GroupMessageVO groupMessageHandler(GroupMessageDTO groupMessageDTO) throws Exception {
        return GroupMessageVO.builder().build();
    }

    @Override
    public DiscussMessageVO discussMessageHandler(DiscussMessageDTO discussMessageDTO) throws Exception {
        return DiscussMessageVO.builder().build();
    }

    @Override
    public JsonVO groupUploadHandler(GroupUploadDTO groupUploadDTO) throws Exception {
        return JsonVO.empty();
    }

    @Override
    public JsonVO groupAdminHandler(GroupAdminDTO groupAdminDTO) throws Exception {
        return JsonVO.empty();
    }

    @Override
    public JsonVO groupDecreaseHandler(GroupDecreaseDTO groupDecreaseDTO) throws Exception {
        return JsonVO.empty();
    }

    @Override
    public JsonVO groupIncreaseHandler(GroupIncreaseDTO groupIncreaseDTO) throws Exception {
        return JsonVO.empty();
    }

    @Override
    public JsonVO groupBanHandler(GroupBanDTO groupBanDTO) throws Exception {
        return JsonVO.empty();
    }

    @Override
    public JsonVO friendAddHandler(FriendAddDTO friendAddDTO) throws Exception {
        return JsonVO.empty();
    }

    @Override
    public FriendRequestVO friendRequestHandler(FriendRequestDTO friendRequestDTO) throws Exception {
        return FriendRequestVO.builder().build();
    }

    @Override
    public GroupRequestVO groupRequestHandler(GroupRequestDTO groupRequestDTO) throws Exception {
        return GroupRequestVO.builder().build();
    }

    @Override
    public JsonVO lifecycleHandler(LifecycleDTO lifecycleDTO) throws Exception {
        return JsonVO.empty();
    }

    @Override
    public JsonVO heartbeatHandler(HeartbeatDTO heartbeatDTO) throws Exception {
        return JsonVO.empty();
    }
}
