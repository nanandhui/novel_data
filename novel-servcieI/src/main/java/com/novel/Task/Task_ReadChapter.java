package com.novel.Task;

import com.novel.dao.CollectVersionDao;
import com.novel.dao.NovelChapterContextDao;
import com.novel.dao.NovelChapterDao;
import com.novel.dao.NovelTitleDao;
import com.novel.model.ChapterContext;
import com.novel.model.CollectVersion;
import com.novel.model.NovelChapter;
import com.novel.model.NovelTitle;
import com.novel.utils.NovelTool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/10/09 17:33 
 */
public class Task_ReadChapter implements Runnable{
    private List<NovelTitle>       list;
    private CollectVersionDao      collectVersionDao;
    private NovelTitleDao          novelTitleDao;
    private NovelChapterDao        novelChapterDao;
    private NovelChapterContextDao novelChapterContextDao;
    private String                 encoding;

    public List<NovelTitle> getList() {
        return list;
    }

    public void setList(List<NovelTitle> list) {
        this.list = list;
    }

    public CollectVersionDao getCollectVersionDao() {
        return collectVersionDao;
    }

    public void setCollectVersionDao(CollectVersionDao collectVersionDao) {
        this.collectVersionDao = collectVersionDao;
    }

    public NovelTitleDao getNovelTitleDao() {
        return novelTitleDao;
    }

    public void setNovelTitleDao(NovelTitleDao novelTitleDao) {
        this.novelTitleDao = novelTitleDao;
    }

    public NovelChapterDao getNovelChapterDao() {
        return novelChapterDao;
    }

    public void setNovelChapterDao(NovelChapterDao novelChapterDao) {
        this.novelChapterDao = novelChapterDao;
    }

    public NovelChapterContextDao getNovelChapterContextDao() {
        return novelChapterContextDao;
    }

    public void setNovelChapterContextDao(NovelChapterContextDao novelChapterContextDao) {
        this.novelChapterContextDao = novelChapterContextDao;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Task_ReadChapter(List<NovelTitle> list, CollectVersionDao collectVersionDao, NovelTitleDao novelTitleDao,
                            NovelChapterDao novelChapterDao, NovelChapterContextDao novelChapterContextDao, String encoding) {
        this.list = list;
        this.collectVersionDao = collectVersionDao;
        this.novelTitleDao = novelTitleDao;
        this.novelChapterDao = novelChapterDao;
        this.novelChapterContextDao = novelChapterContextDao;
        this.encoding = encoding;
    }

    @Override
    public void run() {
        while(list.size()>0){
            this.readContext();
        }
    }

    public void readContext(){
        String tnread_name = Thread.currentThread().getName();
        if (list.size()>0){
            NovelTitle t=null;
            synchronized (this){
                t = list.get(0);
                System.out.println("当前进程的名称:"+tnread_name+"(处理章节名称:"+t.getNovel_name()+")");
                list.remove(t);
            }
            CollectVersion collectVersion = new CollectVersion();
            collectVersion.setTitle_id(t.getId());
            List<CollectVersion> model = collectVersionDao.findModel(collectVersion);
            if (model!=null&&model.size()>0){
                collectVersion=model.get(0);
            }
            String collect_version = collectVersion.getCollect_version();
            if (collect_version!=null&&!collect_version.equals(t.getVersion())){
                //更新小说版本
                t.setVersion(collect_version);
                novelTitleDao.updateModel(t);

                //删除旧的数据
                novelChapterDao.deleteByTitleId(t.getId());

                //重新开始写入数据
                String s = NovelTool.readURL(t.getNovle_url(), encoding);
                Document chaptersDoc = Jsoup.parse(s);
                Elements chapterList = chaptersDoc.select(".chapterlist>dd>a[href]");
                int i=0;
                for (Element c:chapterList){
                    NovelChapter novelChapter = new NovelChapter();
                    String chapterName = c.text();
                    String href = c.attr("href");
                    href = StringUtils.isEmpty(href) ? null : (t.getNovle_url() + href);
                    novelChapter.setChapterName(chapterName);
                    novelChapter.setChapterURl(href);
                    novelChapter.setTitle_id(t.getId());
                    novelChapter.setChapterNum(i++);
                    novelChapter.setChapterSize(new BigDecimal(0));
                    novelChapterDao.insertModel(novelChapter);
                    //获取章节内容
                    //重新开始写入数据
                    String html = NovelTool.readURL(href, encoding);
                    Document chaptersContextDoc = Jsoup.parse(html);
                    Elements chapterContextList = chaptersContextDoc.select("#BookText");
                    StringBuffer context_deatil = new StringBuffer();
                    chapterContextList.stream().forEach(cc->{
                        String text = cc.text();
                        context_deatil.append(text);
                    });
                    ChapterContext chapterContext = new ChapterContext();
                    chapterContext.setContext(context_deatil.toString());
                    chapterContext.setChaperDeatil_id(novelChapter.getId());
                    chapterContext.setTitle_id(t.getId());
                    novelChapterContextDao.insertModel(chapterContext);
                }
            }
        }
    }
}
