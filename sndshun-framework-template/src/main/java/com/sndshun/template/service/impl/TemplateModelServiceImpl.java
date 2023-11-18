package com.sndshun.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sndshun.commons.tools.FreemarkerUtils;
import com.sndshun.template.mapper.TemplateModelMapper;
import com.sndshun.template.entity.TemplateModelEntity;
import com.sndshun.template.service.TemplateModelService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板主体;(TemplateModel)表服务实现类
 *
 * @author sndshun
 * @since 2023-11-17 04:45:49
 */
@Service("templateModelService")
public class TemplateModelServiceImpl extends ServiceImpl<TemplateModelMapper, TemplateModelEntity> implements TemplateModelService {

}
