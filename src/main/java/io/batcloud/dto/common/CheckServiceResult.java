package io.batcloud.dto.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 服务检查返回对象
 * **/
@Getter
@Setter
public class CheckServiceResult{
	private String serviceName;
	private boolean isSuccess;
	private String msg;
	private long costTime;
	
	public CheckServiceResult(String serviceName, boolean isSuccess, String msg,long startTime) {
		super();
		this.serviceName = serviceName;
		this.isSuccess = isSuccess;
		this.msg = msg;
		this.costTime = System.currentTimeMillis() - startTime;
	}
}