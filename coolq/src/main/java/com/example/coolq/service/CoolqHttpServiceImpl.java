package com.example.coolq.service;

import com.alibaba.fastjson.JSONObject;
import com.example.coolq.dto.GroupMessageDTO;
import com.example.coolq.utils.CoolqHttpUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoolqHttpServiceImpl implements CoolqHttpService {

    @Override
    public CoolqHttpUtil.ResData.Message sendPrivateMsg(Integer userId, String message, boolean autoEscape)
            throws Exception {
        JSONObject body = new JSONObject();
        body.put("user_id", userId);
        body.put("message", message);
        body.put("auto_escape", autoEscape);
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.SEND_PRIVATE_MSG, body, CoolqHttpUtil.ResData.Message.class);
    }

    @Override
    public CoolqHttpUtil.ResData.Message sendGroupMsg(Integer groupId, String message, boolean autoEscape)
            throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("message", message);
        body.put("auto_escape", autoEscape);
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.SEND_GROUP_MSG, body, CoolqHttpUtil.ResData.Message.class);
    }

    @Override
    public CoolqHttpUtil.ResData.Message sendDiscussMsg(Integer discussId, String message, boolean autoEscape)
            throws Exception {
        JSONObject body = new JSONObject();
        body.put("discuss_id", discussId);
        body.put("message", message);
        body.put("auto_escape", autoEscape);
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.SEND_DISCUSS_MSG, body, CoolqHttpUtil.ResData.Message.class);
    }

    @Override
    public CoolqHttpUtil.ResData.Message sendMsg(String messageType, Integer userId, Integer groupId, Integer discussId,
                                                 String message, boolean autoEscape) throws Exception {
        JSONObject body = new JSONObject();
        body.put("message_type", messageType);
        body.put("user_id", userId);
        body.put("group_id", groupId);
        body.put("discuss_id", discussId);
        body.put("message", message);
        body.put("auto_escape", autoEscape);
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.SEND_MSG, body, CoolqHttpUtil.ResData.Message.class);
    }

    @Override
    public void deleteMsg(Integer messageId) throws Exception {
        JSONObject body = new JSONObject();
        body.put("message_id", messageId);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.DELETE_MSG, body);
    }

    @Override
    public void sendLike(Integer userId, Integer times) throws Exception {
        JSONObject body = new JSONObject();
        body.put("user_id", userId);
        body.put("times", times);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SEND_LIKE, body);
    }

    @Override
    public void setGroupKick(Integer groupId, Integer userId, boolean rejectAddRequest) throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("user_id", userId);
        body.put("reject_add_request", rejectAddRequest);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_GROUP_KICK, body);
    }

    @Override
    public void setGroupBan(Integer groupId, Integer userId, Integer duration) throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("user_id", userId);
        body.put("duration", duration);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_GROUP_BAN, body);
    }

    @Override
    public void setGroupAnonymousBan(Integer groupId, GroupMessageDTO.Anonymous anonymous, String anonymousFlag,
                                     Integer duration) throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("anonymous", anonymous);
        body.put("anonymous_flag", anonymousFlag);
        body.put("duration", duration);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_GROUP_ANONYMOUS_BAN, body);
    }

    @Override
    public void setGroupWholeBan(Integer groupId, boolean enable) throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("enable", enable);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_GROUP_WHOLE_BAN, body);
    }

    @Override
    public void setGroupAdmin(Integer groupId, Integer userId, boolean enable) throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("user_id", userId);
        body.put("enable", enable);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_GROUP_ADMIN, body);
    }

    @Override
    public void setGroupAnonymous(Integer groupId, boolean enable) throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("enable", enable);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_GROUP_ANONYMOUS, body);
    }

    @Override
    public void setGroupCard(Integer groupId, Integer userId, String card) throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("user_id", userId);
        body.put("card", card);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_GROUP_CARD, body);
    }

    @Override
    public void setGroupLeave(Integer groupId, boolean isDismiss) throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("is_dismiss", isDismiss);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_GROUP_LEAVE, body);
    }

    @Override
    public void setGroupSpecialTitle(Integer groupId, Integer userId, String specialTitle, Integer duration)
            throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("user_id", userId);
        body.put("special_title", specialTitle);
        body.put("duration", duration);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_GROUP_SPECIAL_TITLE, body);
    }

    @Override
    public void setDiscussLeave(Integer discussId) throws Exception {
        JSONObject body = new JSONObject();
        body.put("discuss_id", discussId);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_DISCUSS_LEAVE, body);
    }

    @Override
    public void setFriendAddRequest(String flag, boolean approve, String remark) throws Exception {
        JSONObject body = new JSONObject();
        body.put("flag", flag);
        body.put("approve", approve);
        body.put("remark", remark);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_FRIEND_ADD_REQUEST, body);
    }

    @Override
    public void setGroupAddRequest(String flag, String subType, boolean approve, String reason) throws Exception {
        JSONObject body = new JSONObject();
        body.put("flag", flag);
        body.put("sub_type", subType);
        body.put("approve", approve);
        body.put("reason", reason);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_GROUP_ADD_REQUEST, body);
    }

    @Override
    public CoolqHttpUtil.ResData.LoginInfo getLoginInfo() throws Exception {
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_LOGIN_INFO, null, CoolqHttpUtil.ResData.LoginInfo.class);
    }

    @Override
    public CoolqHttpUtil.ResData.StrangerInfo getStrangerInfo(Integer userId, boolean noCache) throws Exception {
        JSONObject body = new JSONObject();
        body.put("user_id", userId);
        body.put("no_cache", noCache);
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_STRANGER_INFO, body, CoolqHttpUtil.ResData.StrangerInfo.class);
    }

    @Override
    public List<CoolqHttpUtil.ResData.Friend> getFriendList() throws Exception {
        return CoolqHttpUtil.requestList(CoolqHttpUtil.Api.GET_FRIEND_LIST, null, CoolqHttpUtil.ResData.Friend.class);
    }

    @Override
    public List<CoolqHttpUtil.ResData.Group> getGroupList() throws Exception {
        return CoolqHttpUtil.requestList(CoolqHttpUtil.Api.GET_GROUP_LIST, null, CoolqHttpUtil.ResData.Group.class);
    }

    @Override
    public CoolqHttpUtil.ResData.GroupInfo getGroupInfo(Integer groupId, boolean noCache) throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("no_cache", noCache);
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_GROUP_INFO, body, CoolqHttpUtil.ResData.GroupInfo.class);
    }

    @Override
    public CoolqHttpUtil.ResData.GroupMemberInfo getGroupMemberInfo(Integer groupId, Integer userId, boolean noCache)
            throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        body.put("user_id", userId);
        body.put("no_cache", noCache);
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_GROUP_MEMBER_INFO, body,
                CoolqHttpUtil.ResData.GroupMemberInfo.class);
    }

    @Override
    public List<CoolqHttpUtil.ResData.GroupMemberInfo> getGroupMemberList(Integer groupId) throws Exception {
        JSONObject body = new JSONObject();
        body.put("group_id", groupId);
        return CoolqHttpUtil.requestList(CoolqHttpUtil.Api.GET_GROUP_MEMBER_LIST, body,
                CoolqHttpUtil.ResData.GroupMemberInfo.class);
    }

    @Override
    public CoolqHttpUtil.ResData.Cookies getCookies(String domain) throws Exception {
        JSONObject body = new JSONObject();
        body.put("domain", domain);
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_COOKIES, body, CoolqHttpUtil.ResData.Cookies.class);
    }

    @Override
    public CoolqHttpUtil.ResData.CsrfToken getCsrfToken() throws Exception {
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_CSRF_TOKEN, null, CoolqHttpUtil.ResData.CsrfToken.class);
    }

    @Override
    public CoolqHttpUtil.ResData.Credentials getCredentials() throws Exception {
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_CREDENTIALS, null,
                CoolqHttpUtil.ResData.Credentials.class);
    }

    @Override
    public CoolqHttpUtil.ResData.File getRecord(String file, String outFormat, String fullPath) throws Exception {
        JSONObject body = new JSONObject();
        body.put("file", file);
        body.put("out_format", outFormat);
        body.put("full_path", fullPath);
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_RECORD, body, CoolqHttpUtil.ResData.File.class);
    }

    @Override
    public CoolqHttpUtil.ResData.File getImage(String file) throws Exception {
        JSONObject body = new JSONObject();
        body.put("file", file);
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_IMAGE, body, CoolqHttpUtil.ResData.File.class);
    }

    @Override
    public CoolqHttpUtil.ResData.Yes canSendImage() throws Exception {
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.CAN_SEND_IMAGE, null, CoolqHttpUtil.ResData.Yes.class);
    }

    @Override
    public CoolqHttpUtil.ResData.Yes canSendRecord() throws Exception {
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.CAN_SEND_RECORD, null, CoolqHttpUtil.ResData.Yes.class);
    }

    @Override
    public CoolqHttpUtil.ResData.Status getStatus() throws Exception {
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_STATUS, null, CoolqHttpUtil.ResData.Status.class);
    }

    @Override
    public CoolqHttpUtil.ResData.VersionInfo getVersionInfo() throws Exception {
        return CoolqHttpUtil.request(CoolqHttpUtil.Api.GET_VERSION_INFO, null,
                CoolqHttpUtil.ResData.VersionInfo.class);
    }

    @Override
    public void setRestartPlugin(Integer delay) throws Exception {
        JSONObject body = new JSONObject();
        body.put("delay", delay);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.SET_RESTART_PLUGIN, body);
    }

    @Override
    public void cleanDataDir(String dataDir) throws Exception {
        JSONObject body = new JSONObject();
        body.put("data_dir", dataDir);
        CoolqHttpUtil.request(CoolqHttpUtil.Api.CLEAN_DATA_DIR, body);
    }

    @Override
    public void cleanPluginLog() throws Exception {
        CoolqHttpUtil.request(CoolqHttpUtil.Api.CLEAN_PLUGIN_LOG, null);
    }

}
