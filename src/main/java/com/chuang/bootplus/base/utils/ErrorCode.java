package com.chuang.bootplus.base.utils;

/**
 * @author hsy
 * @create 2021-07-19
 * @注意
 */
public class ErrorCode {

    /**
     * 系统错误码
     */
    public static final String RE_CODE_DATABASE_ERROR = "100";
    public static final String RE_MSG_DATABASE_ERROR = "报名过于火爆，请稍后再试";

    public static final String RE_CODE_OBJECT_NULL_ERROR = "101";
    public static final String RE_MSG_OBJECT_NULL_ERROR = "查询对象为空";

    public static final String RE_CODE_STR_NULL_ERROR = "102";
    public static final String RE_MSG_STR_NULL_ERROR = "内容不能为空";

    public static final String RE_CODE_STR_LOGIN_ERROR = "103";
    public static final String RE_MSG_STR_LOGIN_ERROR = "请登录";

    public static final String RE_CODE_NOT_HAVE_OBJECT_ERROR = "104";
    public static final String RE_MSG_NOT_HAVE_OBJECT_ERROR = "操作的内容不存在";

    public static final String RE_CODE_SERVICE_BUSY = "105";
    public static final String RE_MSG_SERVICE_BUSY = "服务器繁忙，请稍后再试!";

    public static final String RE_CODE_HYSTRIX = "106";
    public static final String RE_MSG_HYSTRIX = "触发熔断机制";

    public static final String RE_CODE_OPERATE_FREQUENTLY = "107";
    public static final String RE_MSG_OPERATE_FREQUENTLY = "报名过于火爆，请稍后再试";

    public static final String RE_CODE_LOGIN_BAN = "108";
    public static final String RE_MSG_LOGIN_BAN = "当前注册系统已关闭，具体开放时间请关注通知";


    //数据不存在
    public static final String RE_DATA_NOT_EXIST_ERROR_CODE = "3301";
    public static final String RE_DATA_NOT_EXIST_ERROR_MSG = "数据不存在！";

    public static final String RE_STUDENT_NOT_EXIST_ERROR_CODE = "3301";
    public static final String RE_STUDENT_NOT_EXIST_ERROR_MSG = "学生不存在！";

    //数据已经存在
    public static final String RE_DATA_EXIST_ERROR_CODE = "3302";
    public static final String RE_DATA_EXIST_ERROR_MSG = "数据已经存在！";

    public static final String RE_CODE_NO_HAVE_OBJECT = "3303";
    public static final String RE_MSG_NO_HAVE_OBJECT = "操作对象不存在";

    public static final String RE_CODE_NO_HAVE_DATA_TO_EXPORT = "3304";
    public static final String RE_MSG_NO_HAVE_DATA_TO_EXPORT = "没有可导出的数据";

    /**
     * 成功的返回码
     */
    public static final String RE_CODE_SUCCESS = "200";
    public static final String RE_MSG_SUCCESS = "成功";

    public static final String RE_CODE_PARAMETER_ERROR = "300";
    public static final String RE_MSG_PARAMETER_ERROR= "参数错误";

    public static final String RE_CODE_PARAMETER_FORMAT_ERROR = "301";


    public static final String RE_CODE_NO_HAVE_TOKEN = "1000";
    public static final String RE_MSG_NO_HAVE_TOKEN = "无token，请登录";

    public static final String RE_CODE_USER_ERROR = "1001";
    public static final String RE_MSG_USER_ERROR = "登录失效";

    public static final String RE_CODE_NO_HAVE_ACCOUNT = "1002";
    public static final String RE_MSG_NO_HAVE_ACCOUNT = "账号不存在";

    public static final String RE_CODE_LOGIN_EXPIRED = "1003";
    public static final String RE_MSG_LOGIN_EXPIRED = "登录已过期,请重新登录!";

    public static final String RE_CODE_HAVE_ACCOUNT = "1004";
    public static final String RE_MSG_HAVE_ACCOUNT = "账号已存在";

    public static final String RE_CODE_PASSWORD_ERROR = "1005";
    public static final String RE_MSG_PASSWORD_ERROR = "账号密码错误";

    public static final String RE_CODE_NO_ROLE = "1006";
    public static final String RE_MSG_NO_ROLE = "权限不足，不能操作";

    public static final String RE_CODE_JWT_NOT_MATCH = "1007";
    public static final String RE_MSG_JWT_NOT_MATCH = "登录无效，请重新登录";

    public static final String RE_CODE_OLD_PASSWORD_ERROR = "1008";
    public static final String RE_MSG_OLD_PASSWORD_ERROR = "原密码错误";

    public static final String RE_CODE_ONLY_ONE_LOGIN = "1009";
    public static final String RE_MSG_ONLY_ONE_LOGIN = "你被挤下线了";

    //导出
    public static final String RE_NO_DATA_EXPORT_CODE = "1010";
    public static final String RE_NO_DATA_EXPORT_MSG = "无数据可导出！";

    //账户余额不足
    public static final String ACCOUNT_BALANCE_NOT_ENOUGH_CODE = "1011";
    public static final String ACCOUNT_BALANCE_NOT_ENOUGH_MSG = "账户余额不足！";

    //微信授权
    public static final String WX_AUTH_ERROR_CODE = "1012";
    public static final String WX_AUTH_ERROR_MSG = "微信授权失败！";

    //删除七牛云文件失败
    public static final String RE_CODE_DELETE_QN_FILE_FAIL = "1013";
    public static final String RE_MSG_DELETE_QN_FILE_FAIL = "删除七牛云文件失败！";

    //文件容量已达上限
    public static final String RE_CODE_FILE_SIZE_ALREADY_OVERFLOW = "1014";
    public static final String RE_MSG_FILE_SIZE_ALREADY_OVERFLOW = "文件容量已达上限！";


    /** 手机号已经被使用 */
    public static final String RE_CODE_PHONE_ERROR = "1020";
    public static final String RE_MSG_PHONE_ERROR = "该手机已被使用";
    /** 发送过于频繁，请稍后再试 */
    public static final String RE_CODE_PHONE_LIMIT = "1021";
    public static final String RE_MSG_PHONE_LIMIT = "发送过于频繁，请稍后再试";
    /** 五分钟内发送次数超过上限 */
    public static final String RE_CODE_PHONE_LIMIT5 = "1022";
    public static final String RE_MSG_PHONE_LIMIT5 = "五分钟内发送次数超过上限";
    /** 24小时内发送次数超过上限 */
    public static final String RE_CODE_PHONE_LIMIT_DAY = "1023";
    public static final String RE_MSG_PHONE_LIMIT_DAY = "24小时内发送次数超过上限";
    /** 一小时内发送次数超过上限 */
    public static final String RE_CODE_PHONE_LIMIT_HOUR = "1024";
    public static final String RE_MSG_PHONE_LIMIT_HOUR = "1小时内发送次数超过上限";
    /** 一天内发送次数超过上限 */
    public static final String RE_CODE_PHONE_LIMIT_ONE_DAY = "1025";
    public static final String RE_MSG_PHONE_LIMIT_ONE_DAY = "一天内发送次数超过上限";
    /** 验证码发送失败 */
    public static final String RE_CODE_PHONE_PUSH_FAIL = "1026";
    public static final String RE_MSG_PHONE_PUSH_FAIL = "验证码发送失败";
    /** 验证码错误 */
    public static final String RE_CODE_PHONE_CODE_FAIL = "1027";
    public static final String RE_MSG_PHONE_CODE_FAIL = "验证码错误";
    /** 手机输入错误 */
    public static final String RE_CODE_MOBILE_MOBILE_NULL = "1028";
    public static final String RE_MSG_MOBILE_MOBILE_NULL = "手机输入号错误";
    /** 手机号已设置防骚扰 */
    public static final String RE_CODE_PHONE_FSR_FAIL = "1029";
    public static final String RE_MSG_PHONE_FSR_FAIL = "验证码发送失败,手机号已设置防骚扰";
    public static final String RE_MSG_MOBILE_MOBILE = "手机号未获取短信";

    //您不在少年宫招生范围内
    public static final String RE_CODE_ID_CARD_IS_NOT_EXISTS_IN_WHITELIST = "3001";
    public static final String RE_MSG_ID_CARD_IS_NOT_EXISTS_IN_WHITELIST = "您不在少年宫招生范围内！";

    //您不在此课程报名年龄范围内
    public static final String RE_CODE_AGE_IS_NOT_IN_CARGO_RANGE = "3002";
    public static final String RE_MSG_AGE_IS_NOT_IN_CARGO_RANGE = "您不在此课程报名年龄范围内！";

    //不在报名时间内
    public static final String RE_CODE_TIME_IS_NOT_IN_CARGO_RANGE = "3003";
    public static final String RE_MSG_TIME_IS_NOT_IN_CARGO_RANGE = "不在报名时间内！";

    //报名人数已满
    public static final String RE_CODE_STUDENTS_NUMBER_IS_FULL = "3004";
    public static final String RE_MSG_STUDENTS_NUMBER_IS_FULL = "报名人数已满！";

    //您的课程已报满两节
    public static final String RE_CODE_JOIN_COURSE_IS_EXCEED = "3005";
    public static final String RE_MSG_JOIN_COURSE_IS_EXCEED = "您的课程已报满两节！";

    //修改课程时父课必须相同
    public static final String RE_CODE_UPDATE_COURSE_FATHER_COURSE_ID_MUST_SAME = "3006";
    public static final String RE_MSG_UPDATE_COURSE_FATHER_COURSE_ID_MUST_SAME = "修改课程时父课必须相同！";

    //该课程拥有后续课程，请先删除后续课程
    public static final String RE_CODE_CHILDREN_COURSE_NOT_DELETE = "3007";
    public static final String RE_MSG_CHILDREN_COURSE_NOT_DELETE = "该课程拥有后续课程，请先删除后续课程！";

    //您不在此课程报名年龄范围内
    public static final String RE_CODE_GRADE_IS_NOT_IN_ACTIVITY_RANGE = "3008";
    public static final String RE_MSG_GRADE_IS_NOT_IN_ACTIVITY_RANGE = "此学生不在此活动报名年级范围内！";

    //您不在此课程报名年龄范围内
    public static final String RE_CODE_ID_CARD_ALREADY_EXISTS = "3009";
    public static final String RE_MSG_ID_CARD_ALREADY_EXISTS = "此身份证已经注册过！";

    //您已报名过此课程
    public static final String RE_CODE_JOIN_COURSE_IS_ALREADY_JOINED = "3010";
    public static final String RE_MSG_JOIN_COURSE_IS_ALREADY_JOINED = "您已报名过此课程！";

    //您已报名过此活动
    public static final String RE_CODE_JOIN_ACTIVITY_IS_ALREADY_JOINED = "3011";
    public static final String RE_MSG_JOIN_ACTIVITY_IS_ALREADY_JOINED = "您已报名过此活动！";

    //此学生不属于该学校
    public static final String RE_CODE_STUDENT_NOT_EXIST_IN_THIS_SCHOOL = "3012";
    public static final String RE_MSG_STUDENT_NOT_EXIST_IN_THIS_SCHOOL = "此学生不属于该学校！";

    //此课程不在报名或补录时间内
    public static final String RE_CODE_COURSE_SIGNIN_OUT_OF_TIME = "3013";
    public static final String RE_MSG_COURSE_SIGNIN_OUT_OF_TIME = "此课程不在报名或补录时间内！";

    //此学校不存在
    public static final String RE_CODE_SCHOOL_DOUSENT_EXISTS = "3014";
    public static final String RE_MSG_SCHOOL_DOUSENT_EXISTS = "此学校不存在！";

    //学校只能添加属于本学校的数据
    public static final String RE_CODE_SCHOOL_CAN_ONLY_ADD_OWN_SCHOOL = "3015";
    public static final String RE_MSG_SCHOOL_CAN_ONLY_ADD_OWN_SCHOOL = "学校只能添加属于本学校的数据！";

    //请报名社招课程
    public static final String RE_CODE_COURSE_TYPE_ERROR = "3016";
    public static final String RE_MSG_COURSE_TYPE_ERROR = "请报名社招课程！";

    //文件过大
    public static final String RE_CODE_FILE_UPLOAD_SIZE_TOO_LARGE = "5001";
    public static final String RE_MSG_FILE_UPLOAD_SIZE_TOO_LARGE = "文件过大！";

}
