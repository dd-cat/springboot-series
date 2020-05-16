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
            // QQ 号
            private Integer userId;
            // QQ 昵称
            private String nickname;
        }

        @Data
        public static class StrangerInfo {
            // QQ 号
            private Integer userId;
            // 昵称
            private String nickname;
            // 性别，male 或 female 或 unknown
            private String sex;
            // 年龄
            private Integer age;
        }

        @Data
        public static class Friend {
            // QQ 号
            private Integer userId;
            // QQ 昵称
            private String nickname;
            // 备注名
            private String remark;
        }

        @Data
        public static class Group {
            // 群号
            private Integer groupId;
            // 群名称
            private String groupName;
        }

        @Data
        public static class GroupInfo {
            // 群号
            private Integer groupId;
            // 群名称
            private String groupName;
            // 成员数
            private Integer memberCount;
            // 最大成员数（群容量）
            private Integer maxMemberCount;
        }

        @Data
        public static class GroupMemberInfo {
            // 群号
            private Integer groupId;
            // QQ 号
            private Integer userId;
            // 昵称
            private Integer nickname;
            // 群名片／备注
            private String card;
            // 性别，male 或 female 或 unknown
            private String sex;
            // 年龄
            private Integer age;
            // 地区
            private String area;
            // 加群时间戳
            private Integer joinTime;
            // 最后发言时间戳
            private Integer lastSentTime;
            // 成员等级
            private String level;
            // 角色，owner 或 admin 或 member
            private String role;
            // 是否不良记录成员
            private Boolean unfriendly;
            // 专属头衔
            private String title;
            // 专属头衔过期时间戳
            private Integer titleExpireTime;
            // 是否允许修改群名片
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
            // 是或否
            private Boolean yes;
        }

        @Data
        public static class Status {
            // 当前 QQ 在线，null 表示无法查询到在线状态
            private Boolean online;
            // HTTP API 插件状态符合预期，意味着插件已初始化，内部插件都在正常运行，且 QQ 在线
            private Boolean good;
        }

        @Data
        public static class VersionInfo {
            // 酷Q 根目录路径
            private String coolqDirectory;
            // 酷Q 版本，air 或 pro
            private String coolqEdition;
            // HTTP API 插件版本
            private String pluginVersion;
            // HTTP API 插件 build 号
            private Integer pluginBuildNumber;
            // HTTP API 插件编译配置，debug 或 release
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
            error("access token 未提供", res);
        }
        if (res.getHttpCode() == 403) {
            error("access token 不符合", res);
        }
        if (res.getHttpCode() == 406) {
            error("POST 请求的 Content-Type 不支持", res);
        }
        if (res.getHttpCode() == 400) {
            error("POST 请求的正文格式不正确", res);
        }
        if (res.getHttpCode() == 404) {
            error("API 不存在", res);
        }
        CoolqHttpRes coolqHttpRes = JSONObject.parseObject(res.getData(), CoolqHttpRes.class);
        if (coolqHttpRes.getStatus().equals(CoolqConstants.Status.FAILED)) {
            if (coolqHttpRes.getRetcode() == 100) {
                error("参数缺失或参数无效", res);
            }
            if (coolqHttpRes.getRetcode() == 102) {
                error("酷Q 函数返回的数据无效", res);
            }
            if (coolqHttpRes.getRetcode() == 103) {
                error("操作失败", res);
            }
            if (coolqHttpRes.getRetcode() == 104) {
                error("酷Q 提供的凭证（Cookie 和 CSRF Token）失效", res);
            }
            if (coolqHttpRes.getRetcode() == 201) {
                error("工作线程池未正确初始化（无法执行异步任务）", res);
            }
            error("未知错误", res);
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
