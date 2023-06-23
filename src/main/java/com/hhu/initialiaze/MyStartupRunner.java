package com.hhu.initialiaze;

import com.hhu.recommender.ItemBasedRecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyStartupRunner implements ApplicationRunner {

    @Autowired
    private ItemBasedRecommenderService recommenderService; // 替换成你要调用方法的组件

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 在这里调用你要执行的方法
        recommenderService.initialize();
    }
}
