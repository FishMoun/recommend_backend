package com.hhu.initialiaze;

import com.hhu.recommender.ItemBasedRecommenderService;
import com.hhu.recommender.SVDRecommenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyStartupRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(MyStartupRunner.class);
    @Autowired
    private ItemBasedRecommenderService recommenderService; // 替换成你要调用方法的组件

    @Autowired
    private SVDRecommenderService svdRecommenderService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 在这里调用你要执行的方法
        svdRecommenderService.initialize();
        logger.info("处理成功！");
    }

}
