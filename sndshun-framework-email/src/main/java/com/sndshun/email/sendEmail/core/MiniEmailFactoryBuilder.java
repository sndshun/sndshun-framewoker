package com.sndshun.email.sendEmail.core;

import cn.hutool.core.util.StrUtil;
import com.sndshun.email.sendEmail.config.MailConfig;
import com.sndshun.email.sendEmail.constant.SmtpHostEnum;
import com.sndshun.email.sendEmail.exception.ParameterException;

/**
 * 建造 {@link MiniEmail} 实例。
 *
 * @author thundzeng
 */
public class MiniEmailFactoryBuilder {

	/**
	 * 建造 MiniEmailFactory
	 * 于 V2.1.2 标注废弃，下版本会删除该方法。使用：{@link #build(MailConfig)} 代替
	 *
	 * @param debug    是否开启debug。
	 * @param username 发件人邮箱。
	 * @param password 发件人邮箱密码（qq邮箱、163邮箱需要的是邮箱授权码，新浪邮箱直接是邮箱登录密码）。
	 * @param smtpHostEnum 支持的邮箱Host。{@link SmtpHostEnum}
	 * @return MiniEmailFactory
	 */
	@Deprecated
	public MiniEmailFactory build(boolean debug, String username, String password, SmtpHostEnum smtpHostEnum) {
		MailConfig config = MailConfig.config(username, password)
				.setMailSmtpHost(smtpHostEnum)
				.setMailDebug(debug);

		return this.build(config);
	}

	/**
	 * 建造 MiniEmailFactory
	 *
	 * @param config 配置参数。
	 * @return MiniEmailFactory
	 */
	public MiniEmailFactory build(MailConfig config) {
		this.checkParameter(config);

		return new DefaultMiniEmailFactory(config);
	}

	/**
	 * 参数检查
	 *
	 * @param config 配置参数。
	 */
	private void checkParameter(MailConfig config) {
		if (StrUtil.isEmpty(config.getUsername()) || StrUtil.isEmpty(config.getPassword())) {
			throw new ParameterException("请填写完整的收件人信息");
		}
		if (null == config.getMailSmtpHost()) {
			throw new ParameterException("请选择邮件Host");
		}
	}
}
