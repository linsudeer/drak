package com.linsu.core.log.factory;

import java.util.TimerTask;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linsu.core.log.bean.LogSucceed;
import com.linsu.core.log.bean.LogType;
import com.linsu.core.support.SpringContextHolder;
import com.linsu.core.util.ToolUtil;
import com.linsu.modular.blog.model.LoginLog;
import com.linsu.modular.blog.model.OptLog;
import com.linsu.modular.blog.service.LoginLogService;
import com.linsu.modular.blog.service.OptLogService;

/**
 * 日志操作任务工厂
 * @author lisonglin
 * @date 2018年3月8日 下午5:14:15
 */
public class LogTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
    
    private static LoginLogService logService = SpringContextHolder.getBean("loginLogService");
    
    private static OptLogService optLogService = SpringContextHolder.getBean("optLogService");

    public static TimerTask loginLog(final String username, final String msg, final String ip, LogSucceed success) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog( LogType.LOGIN, "账号:" + username + "," + msg, ip,success);
                try {
                	logService.add(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录日志异常!", e);
                }
            }
        };
    }

    public static TimerTask exitLog(final Long userId, final String username, final String msg, final String ip, LogSucceed success) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog( LogType.EXIT, "账号:" + username + "," + msg, ip,success);
                try {
                	logService.add(loginLog);
                } catch (Exception e) {
                    logger.error("创建退出日志异常!", e);
                }
            }
        };
    }

    public static TimerTask bussinessLog(final Long userId, final String bussinessName, final String clazzName, final String methodName, final String msg) {
        return new TimerTask() {
            @Override
            public void run() {
                OptLog operationLog = LogFactory.createOptLog(LogType.BUSSINESS, userId, bussinessName, clazzName, methodName, msg, LogSucceed.SUCCESS);
                try {
                	optLogService.add(operationLog);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }

    public static TimerTask exceptionLog(final Long userId, final String userName, final Exception exception) {
        return new TimerTask() {
            @Override
            public void run() {
                String msg = ToolUtil.getExceptionMsg(exception);
                OptLog operationLog = LogFactory.createOptLog(
                        LogType.EXCEPTION, userId, userName, null, null, msg, LogSucceed.FAIL);
                try {
                	optLogService.add(operationLog);
                } catch (Exception e) {
                    logger.error("创建异常日志异常!", e);
                }
            }
        };
    }
}
