package com.crc.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.crc.entity.BaseEntity;
import lombok.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 用户对象 sys_user
 * gz
 */
@Data
public class SysUser extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 用户账号 */
    private String userName;
    /** 用户姓名 */
    private String fullName;

    private String userCode;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String phonenumber;

    /** 密码 */
    private String password;
    /** 密码 明文*/
    private String pwdPlaintext;

    /** 帐号状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;
    //1：超级管理员；2：录入员；3：检查员；4：其他；
    private String limits;

    //身份证号
    private String idCard;

    @TableField(exist = false)
    private List<HashMap<String,List>> itemCenter;
}
