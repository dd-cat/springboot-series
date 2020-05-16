package com.example.coolq.constants;

public class CoolqConstants {

    public static class Status {
        public static final String OK = "ok";
        public static final String ASYNC = "async";
        public static final String FAILED = "failed";
    }

    public static class PostType {
        public static final String MESSAGE = "message";
        public static final String NOTICE = "notice";
        public static final String REQUEST = "request";
        public static final String META_EVENT = "meta_event";
    }

    public static class MessageType {
        public static final String PRIVATE = "private";
        public static final String GROUP = "group";
        public static final String DISCUSS = "discuss";
    }

    public static class NoticeType {
        public static final String GROUP_UPLOAD = "group_upload";
        public static final String GROUP_ADMIN = "group_admin";
        public static final String GROUP_DECREASE = "group_decrease";
        public static final String GROUP_INCREASE = "group_increase";
        public static final String GROUP_BAN = "group_ban";
        public static final String FRIEND_ADD = "friend_add";
    }

    public static class MetaEventType {
        public static final String LIFECYCLE = "lifecycle";
        public static final String HEARTBEAT = "heartbeat";
    }

    public static class SubType {
        public static final String FRIEND = "friend";
        public static final String GROUP = "group";
        public static final String DISCUSS = "discuss";
        public static final String OTHER = "other";
        public static final String NORMAL = "normal";
        public static final String ANONYMOUS = "anonymous";
        public static final String NOTICE = "notice";
        public static final String SET = "set";
        public static final String UNSET = "unset";
        public static final String LEAVE = "leave";
        public static final String KICK = "kick";
        public static final String KICK_ME = "kick_me";
        public static final String APPROVE = "approve";
        public static final String INVITE = "invite";
        public static final String BAN = "ban";
        public static final String LIFT_BAN = "lift_ban";
        public static final String ADD = "add";
        public static final String ENABLE = "enable";
        public static final String DISABLE = "disable";
    }

    public static class RequestType {
        public static final String FRIEND = "friend";
        public static final String GROUP = "group";
    }

    public static class Sender {
        public static class Sex {
            public final String MALE = "male";
            public final String FEMALE = "female";
            public final String UNKNOWN = "unknown";
        }

        public static class Role {
            public final String OWNER = "owner";
            public final String ADMIN = "admin";
            public final String MEMBER = "member";
        }
    }

}
