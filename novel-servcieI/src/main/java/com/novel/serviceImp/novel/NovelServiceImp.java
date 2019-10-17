package com.novel.serviceImp.novel;

import com.novel.Task.Task_ReadSite;
import com.novel.dao.CollectVersionDao;
import com.novel.dao.NovelSiteDao;
import com.novel.dao.NovelTitleDao;
import com.novel.model.NovelTitle;
import com.novel.model.Novel_Site;
import com.novel.service.NovelService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/09/25 19:13 
 */
@Service("novelService")
public class NovelServiceImp implements NovelService {
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;
    @Resource
    private NovelSiteDao           novelSiteDao;
    @Resource
    private AmqpTemplate           amqpTemplate;
    @Resource
    private NovelTitleDao          novelTitleDao;
    @Resource
    private CollectVersionDao collectVersionDao;

    @Override
    public boolean start() {
        try {
            List<Novel_Site> allModel = novelSiteDao.findAllModel();
            allModel.stream().forEach(l->{
                System.out.println("多线程处理数据采集(站点:"+l.getSite_url()+"):->start");
                taskExecutor.execute(new Task_ReadSite(l,amqpTemplate,novelTitleDao,collectVersionDao));
                System.out.println("多线程处理数据采集:->end");
            });
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<NovelTitle> findAllNovel() {
        List<NovelTitle> allModel = novelTitleDao.findAllModel();
        return allModel;
    }

    @Override
    public List<NovelTitle> findNovelsByCondition(NovelTitle novelTitle) {
        List<NovelTitle> model = novelTitleDao.findModel(novelTitle);
        return model;
    }
}
