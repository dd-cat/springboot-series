package com.example.coolq.service;

import com.example.coolq.dto.GroupMessageDTO;
import com.example.coolq.utils.CoolqHttpUtil;
import java.util.List;

public interface CoolqHttpService {

    /**
     * 发送私聊消息
     *
     * @param userId 对方QQ号
     * @param message 要发送的内容
     * @param autoEscape 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @return Message
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.Message sendPrivateMsg(Integer userId, String message, boolean autoEscape) throws Exception;

    /**
     * 发送群聊消息
     *
     * @param groupId 群号
     * @param message 要发送的内容
     * @param autoEscape 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @return Message
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.Message sendGroupMsg(Integer groupId, String message, boolean autoEscape) throws Exception;

    /**
     * 发送讨论组消息
     *
     * @param discussId 讨论组 ID（正常情况下看不到，需要从讨论组消息上报的数据中获得）
     * @param message 要发送的内容
     * @param autoEscape 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @return Message
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.Message sendDiscussMsg(Integer discussId, String message, boolean autoEscape) throws Exception;

    /**
     * 发送消息
     *
     * @param messageType 消息类型，支持 private、group、discuss，分别对应私聊、群组、讨论组，如不传入，则根据传入的 *_id 参数判断
     * @param userId 对方 QQ 号（消息类型为 private 时需要）
     * @param groupId 群号（消息类型为 group 时需要）
     * @param discussId 讨论组 ID（消息类型为 discuss 时需要）
     * @param message 要发送的内容
     * @param autoEscape 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @return Message
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.Message sendMsg(String messageType, Integer userId, Integer groupId, Integer discussId,
                                          String message, boolean autoEscape) throws Exception;

    /**
     * 撤回消息（Air 版本无法使用）
     *
     * @param messageId 消息 ID
     * @throws Exception e
     */
    void deleteMsg(Integer messageId) throws Exception;

    /**
     * 发送好友赞（Air 版本无法使用）
     *
     * @param userId 对方 QQ 号
     * @param times 赞的次数，每个好友每天最多 10 次
     * @throws Exception e
     */
    void sendLike(Integer userId, Integer times) throws Exception;

    /**
     * 群组踢人
     *
     * @param groupId 群号
     * @param userId 要踢的 QQ 号
     * @param rejectAddRequest 拒绝此人的加群请求
     * @throws Exception e
     */
    void setGroupKick(Integer groupId, Integer userId, boolean rejectAddRequest) throws Exception;

    /**
     * 群组单人禁言
     *
     * @param groupId 群号
     * @param userId 要禁言的 QQ 号
     * @param duration 禁言时长，单位秒，0 表示取消禁言
     * @throws Exception e
     */
    void setGroupBan(Integer groupId, Integer userId, Integer duration) throws Exception;

    /**
     * 群组匿名用户禁言
     * anonymous 和 anonymousFlag 两者任选其一传入即可，若都传入，则使用 anonymous
     *
     * @param groupId 群号
     * @param anonymous 可选，要禁言的匿名用户对象（群消息上报的 anonymous 字段）
     * @param anonymousFlag 可选，要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
     * @param duration 禁言时长，单位秒，无法取消匿名用户禁言
     * @throws Exception e
     */
    void setGroupAnonymousBan(Integer groupId, GroupMessageDTO.Anonymous anonymous, String anonymousFlag,
                              Integer duration) throws Exception;

    /**
     * 群组全员禁言
     *
     * @param groupId 群号
     * @param enable 是否禁言
     * @throws Exception e
     */
    void setGroupWholeBan(Integer groupId, boolean enable) throws Exception;

    /**
     * 群组设置管理员
     *
     * @param groupId 群号
     * @param userId 要设置管理员的 QQ 号
     * @param enable true 为设置，false 为取消
     * @throws Exception e
     */
    void setGroupAdmin(Integer groupId, Integer userId, boolean enable) throws Exception;

    /**
     * 群组匿名（Air 版本无法使用）
     *
     * @param groupId 群号
     * @param enable 是否允许匿名聊天
     * @throws Exception e
     */
    void setGroupAnonymous(Integer groupId, boolean enable) throws Exception;

    /**
     * 设置群名片（群备注）
     *
     * @param groupId 群号
     * @param userId 要设置的 QQ 号
     * @param card 群名片内容，不填或空字符串表示删除群名片
     * @throws Exception e
     */
    void setGroupCard(Integer groupId, Integer userId, String card) throws Exception;

    /**
     * 退出群组
     *
     * @param groupId 群号
     * @param isDismiss 是否解散，如果登录号是群主，则仅在此项为 true 时能够解散
     * @throws Exception e
     */
    void setGroupLeave(Integer groupId, boolean isDismiss) throws Exception;

    /**
     * 设置群组专属头衔（经测试无效）
     *
     * @param groupId 群号
     * @param userId 要设置的 QQ 号
     * @param specialTitle 专属头衔，不填或空字符串表示删除专属头衔
     * @param duration 专属头衔有效期，单位秒，-1 表示永久，不过此项似乎没有效果，可能是只有某些特殊的时间长度有效，有待测试
     * @throws Exception e
     */
    void setGroupSpecialTitle(Integer groupId, Integer userId, String specialTitle, Integer duration) throws Exception;

    /**
     * 退出讨论组
     *
     * @param discussId 讨论组 ID（正常情况下看不到，需要从讨论组消息上报的数据中获得）
     * @throws Exception e
     */
    void setDiscussLeave(Integer discussId) throws Exception;

    /**
     * 处理加好友请求
     *
     * @param flag 加好友请求的 flag（需从上报的数据中获得）
     * @param approve 是否同意请求
     * @param remark 添加后的好友备注（仅在同意时有效）
     * @throws Exception e
     */
    void setFriendAddRequest(String flag, boolean approve, String remark) throws Exception;

    /**
     * 处理加群请求／邀请
     *
     * @param flag 加群请求的 flag（需从上报的数据中获得）
     * @param subType add 或 invite，请求类型（需要和上报消息中的 sub_type 字段相符）
     * @param approve 是否同意请求／邀请
     * @param reason 拒绝理由（仅在拒绝时有效）
     * @throws Exception e
     */
    void setGroupAddRequest(String flag, String subType, boolean approve, String reason) throws Exception;

    /**
     * 获取登录号信息
     *
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.LoginInfo getLoginInfo() throws Exception;

    /**
     * 获取陌生人信息
     *
     * @param userId QQ 号
     * @param noCache 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.StrangerInfo getStrangerInfo
    (Integer userId, boolean noCache) throws Exception;

    /**
     * 获取好友列表
     *
     * @return CoolqHttpRes
     * @throws Exception e
     */
    List<CoolqHttpUtil.ResData.Friend> getFriendList() throws Exception;

    /**
     * 获取群列表
     *
     * @return CoolqHttpRes
     * @throws Exception e
     */
    List<CoolqHttpUtil.ResData.Group> getGroupList() throws Exception;

    /**
     * 获取群信息
     *
     * @param groupId 群号
     * @param noCache 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.GroupInfo getGroupInfo
    (Integer groupId, boolean noCache) throws Exception;

    /**
     * 获取群成员信息
     *
     * @param groupId 群号
     * @param userId QQ 号
     * @param noCache 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.GroupMemberInfo getGroupMemberInfo
    (Integer groupId, Integer userId, boolean noCache) throws Exception;

    /**
     * 获取群成员列表
     * 对于同一个群组的同一个成员，获取列表时和获取单独的成员信息时，某些字段可能有所不同，例如 area、title 等字段在获取列表时无法获得，
     * 具体应以单独的成员信息为准
     *
     * @param groupId 群号
     * @return CoolqHttpRes
     * @throws Exception e
     */
    List<CoolqHttpUtil.ResData.GroupMemberInfo> getGroupMemberList
    (Integer groupId) throws Exception;

    /**
     * 获取 Cookies
     *
     * @param domain 需要获取 cookies 的域名
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.Cookies getCookies(String domain) throws Exception;

    /**
     * 获取 CSRF Token
     *
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.CsrfToken getCsrfToken() throws Exception;

    /**
     * 获取 QQ 相关接口凭证
     *
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.Credentials getCredentials() throws Exception;

    /**
     * 获取语音
     * 其实并不是真的获取语音，而是转换语音到指定的格式，然后返回语音文件名（data\record 目录下）。注意，要使用此接口，需要安装 酷Q 的 语音组件。
     * @see <a href="https://cqp.cc/t/21132"语音组件</a
     *
     * @param file 收到的语音文件名（CQ 码的 file 参数），如 0B38145AA44505000B38145AA4450500.silk
     * @param outFormat 要转换到的格式，目前支持 mp3、amr、wma、m4a、spx、ogg、wav、flac
     * @param fullPath 是否返回文件的绝对路径（Windows 环境下建议使用，Docker 中不建议）
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.File getRecord
    (String file, String outFormat, String fullPath) throws Exception;

    /**
     * 获取图片
     *
     * @param file 收到的图片文件名（CQ 码的 file 参数），如 6B4DE3DFD1BD271E3297859D41C530F5.jpg
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.File getImage(String file) throws Exception;

    /**
     * 检查是否可以发送图片
     *
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.Yes canSendImage() throws Exception;

    /**
     * 检查是否可以发送语音
     *
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.Yes canSendRecord() throws Exception;

    /**
     * 获取插件运行状态
     *
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.Status getStatus() throws Exception;

    /**
     * 获取 酷Q 及 HTTP API 插件的版本信息
     *
     * @return CoolqHttpRes
     * @throws Exception e
     */
    CoolqHttpUtil.ResData.VersionInfo getVersionInfo() throws Exception;

    /**
     * 重启 HTTP API 插件
     *
     * @param delay 要延迟的毫秒数，如果默认情况下无法重启，可以尝试设置延迟为 2000 左右
     * @throws Exception e
     */
    void setRestartPlugin(Integer delay) throws Exception;

    /**
     * 清理数据目录
     *
     * @param dataDir 收到清理的目录名，支持 image、record、show、bface
     * @throws Exception e
     */
    void cleanDataDir(String dataDir) throws Exception;

    /**
     * 清理插件日志
     *
     * @throws Exception e
     */
    void cleanPluginLog() throws Exception;
}
