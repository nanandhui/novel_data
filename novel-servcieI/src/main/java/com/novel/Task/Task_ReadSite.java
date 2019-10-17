package com.novel.Task;

import com.alibaba.fastjson.JSONObject;
import com.novel.dao.CollectVersionDao;
import com.novel.dao.NovelTitleDao;
import com.novel.factory.novel_factory.Factory_Type1;
import com.novel.factory.novel_interface.AnalysisSite;
import com.novel.model.Novel_Site;
import com.novel.utils.NovelTool;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.util.StringUtils;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/09/26 10:51 
 */
public class Task_ReadSite implements Runnable {
    private Novel_Site    novelSite;
    private AmqpTemplate  amqpTemplate;
    private NovelTitleDao novelTitleDao;
    private CollectVersionDao collectVersionDao;

    public Novel_Site getNovelSite() {
        return novelSite;
    }

    public void setNovelSite(Novel_Site novelSite) {
        this.novelSite = novelSite;
    }

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public NovelTitleDao getNovelTitleDao() {
        return novelTitleDao;
    }

    public void setNovelTitleDao(NovelTitleDao novelTitleDao,CollectVersionDao collectVersionDao) {
        this.novelTitleDao = novelTitleDao;
    }

    public void setNovelTitleDao(NovelTitleDao novelTitleDao) {
        this.novelTitleDao = novelTitleDao;
    }

    public CollectVersionDao getCollectVersionDao() {
        return collectVersionDao;
    }

    public void setCollectVersionDao(CollectVersionDao collectVersionDao) {
        this.collectVersionDao = collectVersionDao;
    }

    public Task_ReadSite(Novel_Site novelSite, AmqpTemplate amqpTemplate, NovelTitleDao novelTitleDao,CollectVersionDao collectVersionDao) {
        this.novelSite = novelSite;
        this.amqpTemplate = amqpTemplate;
        this.novelTitleDao = novelTitleDao;
        this.collectVersionDao = collectVersionDao;
    }

    @Override
    public void run() {
        try {
            String site_encoding = novelSite.getSite_encoding();
            String result = NovelTool.readURL(novelSite.getSite_url(),site_encoding);
            AnalysisSite produce = Factory_Type1.produce();
            produce.analysis(novelSite.getId(),result,novelSite.getSite_url(),novelTitleDao, collectVersionDao,site_encoding);
            JSONObject conversion = new JSONObject();
            conversion.put("site_id",novelSite.getId());
            conversion.put("encoding", site_encoding);
            String s = JSONObject.toJSONString(conversion);
            //通过消息队列将结果发送给消费者
            if (!StringUtils.isEmpty(result)){
                amqpTemplate.convertAndSend("evanTest_exchange","readNovel_key",s);
            }else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
