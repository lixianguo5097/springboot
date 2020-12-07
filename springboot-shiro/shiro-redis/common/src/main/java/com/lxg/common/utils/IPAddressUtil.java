package com.lxg.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 功能说明：IP地址工具
 *
 * @author zkj
 */
@Slf4j
public class IPAddressUtil {

    private IPAddressUtil() {
    }

    /**
     * 从发的request请求的头信息里获取客户端IP地址
     *
     * @param request
     * @return ip 客户端IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("getIpAddressErro-{}", e.fillInStackTrace());
                }
                ip = inet.getHostAddress();
            }
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

    /**
     * 获取本机ip地址
     *
     * @return
     */
    public static String getServerIpAddress() {
        String localIp = null;
        try {
            InetAddress address = InetAddress.getLocalHost();
            if (address != null) {
                localIp = address.getHostAddress();
            }
        } catch (UnknownHostException e) {
            log.error("error", e);
        }
        return localIp;
    }
}
