package com.sndshun.email.sendEmail.util;

import com.sndshun.email.sendEmail.config.MailConfig;
import com.sndshun.email.sendEmail.constant.EmailContentTypeEnum;
import com.sndshun.email.sendEmail.constant.SmtpHostEnum;
import com.sndshun.email.sendEmail.core.MiniEmail;
import com.sndshun.email.sendEmail.core.MiniEmailFactory;
import com.sndshun.email.sendEmail.core.MiniEmailFactoryBuilder;
import com.sndshun.template.service.TemplateModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 86136
 */
@Service
public class ToEmail {

    /**
     * 该邮箱修改为你需要测试的收件邮箱地址
     */
    private static final String TO_EMAIL = "3325622872@qq.com";
    /**
     * 发送邮件给多个收件人
     */
    private static final String[] TO_EMAILS = new String[]{"669307582@qq.com", "1919101871@qq.com",
        "3325622872@qq.com"};

    MiniEmailFactory miniEmailFactory;

    @Resource
    public TemplateModelService templateModelService;

    public void sendEmail() throws Exception {
        String html = templateModelService.getById(1725304864293183489L).getContext();
        // 创建工厂类
        miniEmailFactory = new MiniEmailFactoryBuilder().build(
            MailConfig.config("sndshun@foxmail.com", "sbavxvgjmqjxchcd")
                .setMailDebug(Boolean.TRUE)
                .setSenderNickname("自动备份")
                .setMailSmtpHost(SmtpHostEnum.SMTP_QQ)
        );

        MiniEmail miniEmail = miniEmailFactory.init();
        String sendSuccessToList = miniEmail
            .send(TO_EMAIL, "自动备份通知", EmailContentTypeEnum.HTML, html);
    }


}
