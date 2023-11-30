package com.sndshun.email.components;

import com.sndshun.email.sendEmail.config.MailConfig;
import com.sndshun.email.sendEmail.constant.SmtpHostEnum;
import com.sndshun.email.sendEmail.core.MiniEmail;
import com.sndshun.email.sendEmail.core.MiniEmailFactory;
import com.sndshun.email.sendEmail.core.MiniEmailFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EmailServer {
    private final MiniEmailFactory miniEmailFactory;

    public EmailServer() {
        // 创建工厂类
        miniEmailFactory = new MiniEmailFactoryBuilder().build(
                MailConfig.config("sndshun@foxmail.com", "sbavxvgjmqjxchcd")
                        .setMailDebug(Boolean.TRUE)
                        .setSenderNickname("管理员小爱")
                        .setMailSmtpHost(SmtpHostEnum.SMTP_QQ)
        );
    }

    @Bean
    public MiniEmail miniEmail() {
        return miniEmailFactory.init();
    }


}
