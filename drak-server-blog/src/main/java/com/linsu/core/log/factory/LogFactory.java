package com.linsu.core.log.factory;

import java.util.Date;

import com.linsu.core.log.bean.LogSucceed;
import com.linsu.core.log.bean.LogType;
import com.linsu.modular.blog.model.LoginLog;
import com.linsu.modular.blog.model.OptLog;

/**
 * 日志对象工厂
 * @author lisonglin
 * @date 2018年3月8日 下午4:12:24
 */
public class LogFactory {

	/**
	 * 创建操作日志对象
	 * @param logType 类型
	 * @param userId 用户id
	 * @param bizName 业务名称
	 * @param clzName 类名称
	 * @param methodName 方法名称
	 * @param msg 信息
	 * @param success 成功失败
	 * @return
	 */
	public static OptLog createOptLog(LogType logType, Long userId, String bizName, String clzName, String methodName, String msg, LogSucceed success){
		OptLog optLog = new OptLog();
		optLog.setLogType(logType.getMessage());
		optLog.setLogName(bizName);
		optLog.setLogUserId(userId);
		optLog.setLogClassname(clzName);
		optLog.setLogMethod(methodName);
		optLog.setLogSucceed(success.getMessage());
		optLog.setLogMessage(msg);
		optLog.setLogCreateTime(new Date());
		return optLog;
	}
	
	/**
     * 创建登录日志
     */
    public static LoginLog createLoginLog(LogType logType, String msg, String ip,LogSucceed success) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLogName(logType.getMessage());
        loginLog.setLogCreateTime(new Date());
        loginLog.setLogSucceed(success.getMessage());
        loginLog.setLogIp(ip);
        loginLog.setLogMessage(msg);
        return loginLog;
    }
}
