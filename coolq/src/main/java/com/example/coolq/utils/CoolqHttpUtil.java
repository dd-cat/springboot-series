package com.example.coolq.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.coolq.config.CoolqProperties;
import com.example.coolq.constants.CoolqConstants;
import com.example.coolq.exception.CoolqRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class CoolqHttpUtil {

    private static final Logger log = LoggerFactory.getLogger(CoolqHttpUtil.class);
    private static CoolqProperties coolqProperties;
    private CoolqProperties properties;

    @PostConstruct
    public void init() {
        coolqProperties = this.properties;
    }

    public static class Api {
        public static final String SEND_PRIVATE_MSG = "send_private_msg";
        public static final String SEND_GROUP_MSG = "send_group_msg";
        public static final String SEND_DISCUSS_MSG = "send_discuss_msg";
        public static final String SEND_MSG = "send_msg";
        public static final String DELETE_MSG = "delete_msg";
        public static final String SEND_LIKE = "send_like";
        public static final String SET_GROUP_KICK = "set_group_kick";
        public static final String SET_GROUP_BAN = "set_group_ban";
        public static final String SET_GROUP_ANONYMOUS_BAN = "set_group_anonymous_ban";
        public static final String SET_GROUP_WHOLE_BAN = "set_group_whole_ban";
        public static final String SET_GROUP_ADMIN = "set_group_admin";
        public static final String SET_GROUP_ANONYMOUS = "set_group_anonymous";
        public static final String SET_GROUP_CARD = "set_group_card";
        public static final String SET_GROUP_LEAVE = "set_group_leave";
        public static final String SET_GROUP_SPECIAL_TITLE = "set_group_special_title";
        public static final String SET_DISCUSS_LEAVE = "set_discuss_leave";
        public static final String SET_FRIEND_ADD_REQUEST = "set_friend_add_request";
        public static final String SET_GROUP_ADD_REQUEST = "set_group_add_request";
        public static final String GET_LOGIN_INFO = "get_login_info";
        public static final String GET_STRANGER_INFO = "get_stranger_info";
        public static final String GET_FRIEND_LIST = "get_friend_list";
        public static final String GET_GROUP_LIST = "get_group_list";
        public static final String GET_GROUP_INFO = "get_group_info";
        public static final String GET_GROUP_MEMBER_INFO = "get_group_member_info";
        public static final String GET_GROUP_MEMBER_LIST = "get_group_member_list";
        public static final String GET_COOKIES = "get_cookies";
        public static final String GET_CSRF_TOKEN = "get_csrf_token";
        public static final String GET_CREDENTIALS = "get_credentials";
        public static final String GET_RECORD = "get_record";
        public static final String GET_IMAGE = "get_image";
        public static final String CAN_SEND_IMAGE = "can_send_image";
        public static final String CAN_SEND_RECORD = "can_send_record";
        public static final String GET_STATUS = "get_status";
        public static final String GET_VERSION_INFO = "get_version_info";
        public static final String SET_RESTART_PLUGIN = "set_restart_plugin";
        public static final String CLEAN_DATA_DIR = "clean_data_dir";
        public static final String CLEAN_PLUGIN_LOG = "clean_plugin_log";
    }

    public static class ResData {
        @Data
        public static class Message {
            private Integer messageId;
        }

        @Data
        public static class LoginInfo {
            // QQ ???
            private Integer userId;
            // QQ ??????
            private String nickname;
        }

        @Data
        public static class StrangerInfo {
            // QQ ???
            private Integer userId;
            // ??????
            private String nickname;
            // ?????????male ??? female ??? unknown
            private String sex;
            // ??????
            private Integer age;
        }

        @Data
        public static class Friend {
            // QQ ???
            private Integer userId;
            // QQ ??????
            private String nickname;
            // ?????????
            private String remark;
        }

        @Data
        public static class Group {
            // ??????
            private Integer groupId;
            // ?????????
            private String groupName;
        }

        @Data
        public static class GroupInfo {
            // ??????
            private Integer groupId;
            // ?????????
            private String groupName;
            // ?????????
            private Integer memberCount;
            // ??????????????????????????????
            private Integer maxMemberCount;
        }

        @Data
        public static class GroupMemberInfo {
            // ??????
            private Integer groupId;
            // QQ ???
            private Integer userId;
            // ??????
            private Integer nickname;
            // ??????????????????
            private String card;
            // ?????????male ??? female ??? unknown
            private String sex;
            // ??????
            private Integer age;
            // ??????
            private String area;
            // ???????????????
            private Integer joinTime;
            // ?????????????????????
            private Integer lastSentTime;
            // ????????????
            private String level;
            // ?????????owner ??? admin ??? member
            private String role;
            // ????????????????????????
            private Boolean unfriendly;
            // ????????????
            private String title;
            // ???????????????????????????
            private Integer titleExpireTime;
            // ???????????????????????????
            private Boolean cardChangeable;
        }

        @Data
        public static class Cookies {
            private String cookies;
        }

        @Data
        public static class CsrfToken {
            private Integer token;
        }

        @Data
        public static class Credentials {
            private String cookies;
            private Integer csrfToken;
        }

        @Data
        public static class File {
            private String file;
        }

        @Data
        public static class Yes {
            // ?????????
            private Boolean yes;
        }

        @Data
        public static class Status {
            // ?????? QQ ?????????null ?????????????????????????????????
            private Boolean online;
            // HTTP API ????????????????????????????????????????????????????????????????????????????????????????????? QQ ??????
            private Boolean good;
        }

        @Data
        public static class VersionInfo {
            // ???Q ???????????????
            private String coolqDirectory;
            // ???Q ?????????air ??? pro
            private String coolqEdition;
            // HTTP API ????????????
            private String pluginVersion;
            // HTTP API ?????? build ???
            private Integer pluginBuildNumber;
            // HTTP API ?????????????????????debug ??? release
            private String pluginBuildConfiguration;
        }
    }

    @Data
    public static class CoolqHttpRes {
        private String status;
        private Integer retcode;
        private String data;
    }

    public static CoolqHttpRes request(String api, JSONObject body) throws Exception {
        HttpUtil.HttpRes res;
        try {
            if (body == null) {
                body = new JSONObject();
            }
            res = HttpUtil.post(coolqProperties.getUrl() + "/" + api, body.toString());
        } catch (IOException e) {
            throw new CoolqRequestException();
        }
        if (res.getHttpCode() == 401) {
            error("access token ?????????", res);
        }
        if (res.getHttpCode() == 403) {
            error("access token ?????????", res);
        }
        if (res.getHttpCode() == 406) {
            error("POST ????????? Content-Type ?????????", res);
        }
        if (res.getHttpCode() == 400) {
            error("POST ??????????????????????????????", res);
        }
        if (res.getHttpCode() == 404) {
            error("API ?????????", res);
        }
        CoolqHttpRes coolqHttpRes = JSONObject.parseObject(res.getData(), CoolqHttpRes.class);
        if (coolqHttpRes.getStatus().equals(CoolqConstants.Status.FAILED)) {
            if (coolqHttpRes.getRetcode() == 100) {
                error("???????????????????????????", res);
            }
            if (coolqHttpRes.getRetcode() == 102) {
                error("???Q ???????????????????????????", res);
            }
            if (coolqHttpRes.getRetcode() == 103) {
                error("????????????", res);
            }
            if (coolqHttpRes.getRetcode() == 104) {
                error("???Q ??????????????????Cookie ??? CSRF Token?????????", res);
            }
            if (coolqHttpRes.getRetcode() == 201) {
                error("???????????????????????????????????????????????????????????????", res);
            }
            error("????????????", res);
        }
        return coolqHttpRes;
    }

    public static <T> T request(String api, JSONObject body, Class<T> tClass) throws Exception {
        CoolqHttpRes coolqHttpRes = request(api, body);
        return JSONObject.parseObject(coolqHttpRes.getData(), tClass);
    }

    public static <T> List<T> requestList(String api, JSONObject body, Class<T> tClass) throws Exception {
        CoolqHttpRes coolqHttpRes = request(api, body);
        return JSONArray.parseArray(coolqHttpRes.getData(), tClass);
    }

    private static void error(String msg, HttpUtil.HttpRes res) throws CoolqRequestException {
        log.error(msg + " - " + res.getUrl() + " - " + res.getReq() + " - " + res.getData());
        throw new CoolqRequestException();
    }

}
