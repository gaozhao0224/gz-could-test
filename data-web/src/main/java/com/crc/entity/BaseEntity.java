package com.crc.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
public abstract class BaseEntity {

//	@TableId
//	private String id;
	@TableId(value = "id",type = IdType.ID_WORKER_STR)
	private String id;

	private String creator;

	private String createtime;

	private String ts;

	private String modifier;

//	@TableLogic
//	private Integer dr;

	@Version
	private Integer version;

}
