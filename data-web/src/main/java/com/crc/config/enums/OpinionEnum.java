package com.crc.config.enums;

/**
 * @Author gz
 * 意见审核枚举
 */
public enum OpinionEnum {

        UN_AUDITED("1", "未审核"),
        APPROVE("2", "通过"),
        REJECT("3", "驳回"),
        AUDITED("4", "已审核"),

        ;

        private String status;
        private String desc;

        OpinionEnum(String status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public String getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }

}
