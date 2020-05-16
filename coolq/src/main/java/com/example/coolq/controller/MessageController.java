package com.example.coolq.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.coolq.constants.CoolqConstants;
import com.example.coolq.dto.*;
import com.example.coolq.service.MessageHandlerService;
import com.example.coolq.vo.JsonVO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private MessageHandlerService messageHandlerService;
    private static final String POST_TYPE = "post_type";
    private static final String MESSAGE_TYPE = "message_type";
    private static final String NOTICE_TYPE = "notice_type";
    private static final String REQUEST_TYPE = "request_type";
    private static final String META_EVENT_TYPE = "meta_event_type";

    @PostMapping("/receive")
    public JSONObject postMessage(HttpServletRequest request) throws Exception {
        String msg = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.info(msg);
        JSONObject req = JSON.parseObject(msg);
        if (req.containsKey(POST_TYPE)) {
            if (req.get(POST_TYPE).equals(CoolqConstants.PostType.MESSAGE)) {
                if (req.containsKey(MESSAGE_TYPE)) {
                    if (req.get(MESSAGE_TYPE).equals(CoolqConstants.MessageType.PRIVATE)) {
                        return messageHandlerService
                                .privateMessageHandler(JSONObject.parseObject(msg, PrivateMessageDTO.class)).toJson();
                    }
                    if (req.get(MESSAGE_TYPE).equals(CoolqConstants.MessageType.GROUP)) {
                        return messageHandlerService
                                .groupMessageHandler(JSONObject.parseObject(msg, GroupMessageDTO.class)).toJson();
                    }
                    if (req.get(MESSAGE_TYPE).equals(CoolqConstants.MessageType.DISCUSS)) {
                        return messageHandlerService
                                .discussMessageHandler(JSONObject.parseObject(msg, DiscussMessageDTO.class)).toJson();
                    }
                }
            }
            if (req.get(POST_TYPE).equals(CoolqConstants.PostType.NOTICE)) {
                if (req.containsKey(NOTICE_TYPE)) {
                    if (req.get(NOTICE_TYPE).equals(CoolqConstants.NoticeType.GROUP_UPLOAD)) {
                        return messageHandlerService
                                .groupUploadHandler(JSONObject.parseObject(msg, GroupUploadDTO.class)).toJson();
                    }
                    if (req.get(NOTICE_TYPE).equals(CoolqConstants.NoticeType.GROUP_ADMIN)) {
                        return messageHandlerService
                                .groupAdminHandler(JSONObject.parseObject(msg, GroupAdminDTO.class)).toJson();
                    }
                    if (req.get(NOTICE_TYPE).equals(CoolqConstants.NoticeType.GROUP_DECREASE)) {
                        return messageHandlerService
                                .groupDecreaseHandler(JSONObject.parseObject(msg, GroupDecreaseDTO.class)).toJson();
                    }
                    if (req.get(NOTICE_TYPE).equals(CoolqConstants.NoticeType.GROUP_INCREASE)) {
                        return messageHandlerService
                                .groupIncreaseHandler(JSONObject.parseObject(msg, GroupIncreaseDTO.class)).toJson();
                    }
                    if (req.get(NOTICE_TYPE).equals(CoolqConstants.NoticeType.GROUP_BAN)) {
                        return messageHandlerService
                                .groupBanHandler(JSONObject.parseObject(msg, GroupBanDTO.class)).toJson();
                    }
                    if (req.get(NOTICE_TYPE).equals(CoolqConstants.NoticeType.FRIEND_ADD)) {
                        return messageHandlerService
                                .friendAddHandler(JSONObject.parseObject(msg, FriendAddDTO.class)).toJson();
                    }
                }
            }
            if (req.get(POST_TYPE).equals(CoolqConstants.PostType.REQUEST)) {
                if (req.containsKey(REQUEST_TYPE)) {
                    if (req.get(REQUEST_TYPE).equals(CoolqConstants.RequestType.FRIEND)) {
                        return messageHandlerService
                                .friendRequestHandler(JSONObject.parseObject(msg, FriendRequestDTO.class)).toJson();
                    }
                    if (req.get(REQUEST_TYPE).equals(CoolqConstants.RequestType.GROUP)) {
                        return messageHandlerService
                                .groupRequestHandler(JSONObject.parseObject(msg, GroupRequestDTO.class)).toJson();
                    }
                }
            }
            if (req.get(POST_TYPE).equals(CoolqConstants.PostType.META_EVENT)) {
                if (req.containsKey(META_EVENT_TYPE)) {
                    if (req.get(META_EVENT_TYPE).equals(CoolqConstants.MetaEventType.LIFECYCLE)) {
                        return messageHandlerService
                                .lifecycleHandler(JSONObject.parseObject(msg, LifecycleDTO.class)).toJson();
                    }
                    if (req.get(META_EVENT_TYPE).equals(CoolqConstants.MetaEventType.HEARTBEAT)) {
                        return messageHandlerService
                                .heartbeatHandler(JSONObject.parseObject(msg, HeartbeatDTO.class)).toJson();
                    }
                }
            }
        }
        log.warn("未处理的消息类型: " + req.toString());
        return JsonVO.empty().toJson();
    }

}
