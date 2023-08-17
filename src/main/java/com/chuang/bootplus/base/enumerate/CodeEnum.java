package com.chuang.bootplus.base.enumerate;

/**
 * @author hsy
 * @create 2021-07-19
 * @注意
 */
public enum CodeEnum implements HttpStatusCodeService {


    /**
     * 常量
     */
    YEAR("9", "插桩统计近10年数据"),

    /**
     * 返回码相关
     */
    RE_CODE_ADD_DATA_ERROR("2001", "添加数据失败"),
    RE_CODE_UPDATE_DATA_ERROR("2002", "修改数据失败"),
    RE_CODE_DELETE_DATA_ERROR("2003", "修改数据失败"),
    RE_CODE_DATA_NOT_EXIT_ERROR("2004","数据不存在"),
    RE_CODE_NULL_PARAM_ERROR("2005","参数不能为空"),
    RE_CODE_EXCEL_IMPORT_FORMAT_EXCEPTION("3001","请使用正确excel模板！"),
    RE_CODE_FILE_UPLOAD_SIZE_TOO_LARGE ("3002","文件过大！"),
    RE_CODE_EMPTY_LIST ("3003","数据为空"),
    RE_CODE_EXIST_ALREADY ("3004","数据已存在");


    private String code;
    private String message;

    @Override
    public String getReCode() {
        return this.code;
    }

    @Override
    public String getReMsg() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    CodeEnum(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}