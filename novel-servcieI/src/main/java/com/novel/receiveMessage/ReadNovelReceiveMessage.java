package com.novel.receiveMessage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.novel.Task.Task_ReadChapter;
import com.novel.dao.CollectVersionDao;
import com.novel.dao.NovelChapterContextDao;
import com.novel.dao.NovelChapterDao;
import com.novel.dao.NovelTitleDao;
import com.novel.model.NovelTitle;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/09/26 14:36 
 */
public class ReadNovelReceiveMessage implements ChannelAwareMessageListener {
    @Resource
    NovelTitleDao novelTitleDao;

    @Resource
    NovelChapterDao novelChapterDao;

    @Resource
    CollectVersionDao collectVersionDao;

    @Resource
    NovelChapterContextDao novelChapterContextDao;

    @Resource
    ThreadPoolTaskExecutor taskExecutor;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String context = "";
        try {
            context = new String(message.getBody(), "utf-8");
            JSONObject object = JSON.parseObject(context);
            int site_id = object.getIntValue("site_id");
            String encoding = object.getString("encoding");
            NovelTitle novelTitle = new NovelTitle();
            novelTitle.setSite_id(site_id);
            List<NovelTitle> titles = novelTitleDao.findModel(novelTitle);
            //执行速度太慢，考虑多线程实现
            Task_ReadChapter task_readChapter = new Task_ReadChapter(titles, collectVersionDao, novelTitleDao, novelChapterDao,
                    novelChapterContextDao, encoding);
            for (int i=0;i<10;i++){
                taskExecutor.execute(task_readChapter);
            }

            // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            // nack返回false，并重新回到队列，就是重发机制，通常存在于消费失败后处理中；
            // 第三个参数与拒绝消息方法的第二个参数同理。即true重新进入队列，false则丢弃；
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            // 拒绝消息,即丢弃消息，消息不会重新回到队列,后面的参数为true则重入队列；为false则丢弃；
            // channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            e.printStackTrace();
        }
    }
}
