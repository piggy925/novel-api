package com.mumu.novel.core.common.constant;

/**
 * 通用常量类
 *
 * @author mumu
 * @date 2022/10/05
 */
public class CommonConsts {

    /**
     * 性别枚举常量
     */
    public enum SexEnum {
        MALE(0, "男"),

        FEMALE(1, "女");

        SexEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private int code;

        private String desc;

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

}
