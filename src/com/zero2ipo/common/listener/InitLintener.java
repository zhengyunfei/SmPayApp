package com.zero2ipo.common.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by zhengyunfei on 2016/1/1.
 */
public class InitLintener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            System.out.println("spring 容器加载完毕=========");
        }
    }
}
