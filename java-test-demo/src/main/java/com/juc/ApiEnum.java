package com.juc;

import java.util.Objects;

/**
 * @Author wangshunxi
 * @Date 2019/12/30.
 * api接口枚举
 */
public class ApiEnum {
    /**
     * 审核状态
     */
    public enum ApiAuditStatus {
        UN_AUDITED("1", "未审核"),
        APPROVE("2", "通过"),
        REJECT("3", "驳回"),
        ;
        private String status;
        private String desc;

        ApiAuditStatus(String status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public static ApiAuditStatus valueOfEnum(String status) {
            if (Objects.equals(UN_AUDITED.getStatus(),status)) {
                return UN_AUDITED;
            } else if(Objects.equals(APPROVE.getStatus(),status)){
                return APPROVE;
            } else if (Objects.equals(REJECT.getStatus(), status)) {
                return REJECT;
            }
            return null;
        }

        public String getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 发布状态
     */
    public enum ApiPublishStatus {
        UN_PUBLISHED("1", "未发布"),
        PUBLISHED("2", "已发布"),
        OFF("3", "已下架"),
        ;
        private String status;
        private String desc;

        ApiPublishStatus(String status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public static ApiPublishStatus valueOfEnum(String status) {
            if (Objects.equals(UN_PUBLISHED.getStatus(),status)) {
                return UN_PUBLISHED;
            } else if(Objects.equals(PUBLISHED.getStatus(),status)){
                return PUBLISHED;
            } else if (Objects.equals(OFF.getStatus(), status)) {
                return OFF;
            }
            return null;
        }

        public String getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }
    }

}
