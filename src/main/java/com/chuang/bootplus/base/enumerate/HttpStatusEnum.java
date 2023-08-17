package com.chuang.bootplus.base.enumerate;

/**
 * @author lcc
 * @create 2021-07-04
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 信鸥 统一网络请求状态码<br>
 * 该枚举可以按需定时补充<br>
 * 0-1000 common 错误码<br>
 * @author lizhongyuan
 */
@Getter
@AllArgsConstructor
public enum HttpStatusEnum implements HttpStatusCodeService {
    /**
     * 200 成功状态码
     */
    RE_CODE_SUCCESS("200", "成功"),
    /**
     * 100 网络异常状态码
     */
    RE_CODE_DATABASE_ERROR("100", "网络连接异常"),
    /**
     * 101 咨讯、活动等 topic 选择错误 或者选择的不在意料之中错误码
     */
    RE_CODE_TOPIC_ERROR("101", "所选主题不存在"),
    /**
     * 102 参数错误状态码
     */
    RE_CODE_PARAM_ERROR("102", "参数错误"),
    /**
     * 103 查询对象为空状态码
     */
    RE_CODE_NO_HAVE_OBJECT("103", "查询对象不存在"),
    /**
     * 104 文件过大
     */
    RE_CODE_FILE_TO_BIG("104", "文件过大"),
    /**
     * 105 创建token失败
     */
    RE_CODE_JWT_CREATE("105", "创建token失败"),
    /**
     * 106 该用户没有粉丝
     */
    RE_CODE_NO_FANS("106","该用户没有粉丝"),
    /**
     * 107 该用户没有关注
     */
    RE_CODE_NO_FOLLOWS("107","该用户没有关注"),
    /**
     * 1001 账号不存在状态码 需退出登录
     */
    RE_CODE_NO_HAVE_ACCOUNT("901", "账号不存在"),
    /**
     * 1002 登录过期状态码 需退出登录
     */
    RE_CODE_LOGIN_EXPIRED("902", "登录已过期,请重新登录!"),
    /**
     * 1003 请携带正确的token
     */
    RE_CODE_NO_HAVE_TOKEN("903", "请携带正确的token"),
    /**
     * 1004 账号密码错误状态码
     */
    RE_CODE_PASSWORD_ERROR("904", "账号密码错误");

    private String code;

    private String message;

    @Override
    public String getReCode() {
        return code;
    }

    @Override
    public String getReMsg() {
        return message;
    }
}
