package com.lxg.xss;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author LXG
 * @date 2020-4-17
 */

/**
 * @author wbs 防止XSS攻击
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private HttpServletRequest request;

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		String value = request.getParameter(name);
		if (!StringUtils.isEmpty(value)) {
			value = StringEscapeUtils.escapeHtml4(value);
		}
		return value;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] parameterValues = super.getParameterValues(name);
		if (parameterValues == null) {
			return null;
		}
		for (int i = 0; i < parameterValues.length; i++) {
			String value = parameterValues[i];
			// 这个过滤xss攻击的工具类，现在是借助第三方插件使用的。
			parameterValues[i] = StringEscapeUtils.escapeHtml4(value);
		}
		return parameterValues;
	}
}