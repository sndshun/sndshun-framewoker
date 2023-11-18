package com.sndshun.email.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.email.mapper.EmailModelMapper;
import com.sndshun.email.entity.EmailModelEntity;
import com.sndshun.email.service.EmailModelService;
import org.springframework.stereotype.Service;

/**
 * 邮件主体(EmailModel)表服务实现类
 *
 * @author sndshun
 * @since 2023-11-16 20:29:07
 */
@Service("emailModelService")
public class EmailModelServiceImpl extends ServiceImpl<EmailModelMapper, EmailModelEntity> implements EmailModelService {

}
