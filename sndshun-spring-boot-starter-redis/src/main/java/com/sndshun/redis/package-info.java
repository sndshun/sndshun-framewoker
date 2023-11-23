package com.sndshun.redis;

// todo 引入redis作为三级缓存 在进程内缓存失效时查询redis
// 设计为可开关式 在spring @Cacheable 的基础上扩展
// todo redis操作api 提供