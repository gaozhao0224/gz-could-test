package com.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public abstract class BaseEntity {

	@TableId
	private String id;

	private String creator;

	@TableField(exist = false)
	private String creator_refname;

	private String createtime;

	private String ts;

	@TableLogic
	private Integer dr;

	@Version
	private Integer version;

}
