package com.zw.tool;


public interface UtilConstants {

    /**
     * 公共常量
     */
    public static class Public {

    }


    /**
     * 返回状态常量
     */
    public static class ResponseCode {

        /**
         * 系统处理正常
         */
        public static final int SUCCESS_HEAD = 1;

        /**
         * 系统处理未知异常
         */
        public static final int EXCEPTION_HEAD = -1;



    }

    /**
     * 返回消息常量
     */
    public static class ResponseMsg {

        /**
         * 系统处理正常
         */
        public static final String SUCCESS = "成功";

        /**
         * 系统处理失败
         */
        public static final String FAILED = "处理异常";

        /**
         * 至少选择一个选项
         */
        public static final String ATLEAST_ONE = "至少选择一条记录";


        /**
         * 有重复数据
         */
        public static final String DUPLACTED_DATA = "有重复数据";

        /**
         * 缺少参数
         */
        public static final String PARAM_MISSING = "缺少参数";

        /**
         * 参数错误
         */
        public static final String PARAM_ERROR = "参数错误";

        /**
         * 生成编码成功
         */
        public static final String GENERATE_CODE_SUCCESS = "生成编码成功";

        /**
         * 请登录
         */
        public static final String NOT_LOGGED = "请登录";
        /**
         * 查无此货
         */
        public static final String NO_SUCH_GOOD = "查无此货";

    }

    public static class WarehouseState {
        /**
         * 启用
         */
        public static final int enabled = 1;
        /**
         * 不启用
         */
        public static final int disable = -1;
    }

}
