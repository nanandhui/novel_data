package com.novel.factory.novel_readSite;

import com.novel.dao.CollectVersionDao;
import com.novel.dao.NovelTitleDao;
import com.novel.enums.NovelTypeEnum;
import com.novel.factory.novel_interface.AnalysisSite;
import com.novel.model.CollectVersion;
import com.novel.model.NovelTitle;
import com.novel.utils.NovelTool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/09/27 15:36 
 */
public class ReadSite_type1 implements AnalysisSite {

    @Override
    public boolean analysis(int site_id, String result, String basicUrl, NovelTitleDao novelTitleDao, CollectVersionDao collectVersionDao,String site_encoding) {
        //处理html文档
        Document doc = Jsoup.parse(result);
        //获取主页链接页面
        String filter="#subnav>ul>li>a[href]";
        divideTypeToReadNovel(site_id, basicUrl, novelTitleDao, collectVersionDao, site_encoding, doc,filter,1);
        //获取主页novels
        keepNovelToData(site_id, basicUrl, novelTitleDao, collectVersionDao, doc);
        return true;
    }

    /**
    * @description: 递归查询所有链接下的novel
    * @param: 
    * @return: 
    * @author: wang
    * @date: 2019-10-11
    */
    private void divideTypeToReadNovel(int site_id, String basicUrl, NovelTitleDao novelTitleDao, CollectVersionDao collectVersionDao,
                                       String site_encoding, Document doc,String filter,int recursionNum) {
        if (recursionNum>2){
            //此递归函数只执行2次
            return;
        }
        recursionNum++;
        Elements link_elements = doc.select(filter);
        for (Element l:link_elements){
            //获取链接页面中的novels
            String href = l.attr("href");
            href = StringUtils.isEmpty(href) ? null : basicUrl + href;
            String resultHtml = NovelTool.readURL(href, site_encoding);
            Document parseHtml = Jsoup.parse(resultHtml);
            //当前页面novels
            keepNovelToData(site_id, basicUrl, novelTitleDao, collectVersionDao, parseHtml);
            //获取更细分类
            String filter_type1=".details>.item-type>li>a[href]";
            divideTypeToReadNovel(site_id,basicUrl,novelTitleDao,collectVersionDao,
                    site_encoding, doc,filter_type1,recursionNum);

        }
    }

    private void keepNovelToData(int site_id, String basicUrl, NovelTitleDao novelTitleDao, CollectVersionDao collectVersionDao,
                                 Document doc) {
        Elements elements = doc.select(".item-list>li>a[href]");
        elements.stream().forEach(e->{
            Element p_element = e.previousElementSibling();
            String novel_type="";
            if (p_element!=null){
                novel_type = p_element.text();
            }
            Element n_element = e.nextElementSibling();
            String novel_author="";
            if (n_element!=null){
                novel_author = n_element.text();
            }
            System.out.println("-----------------------------------------------------");
            System.out.println("novel_type:"+novel_type+"/novel_author:"+novel_author);
            int index = NovelTypeEnum.getIndex(novel_type);
            String text = e.text();
            String href = e.attr("href");
            NovelTitle novelTitle = new NovelTitle();
            href = StringUtils.isEmpty(href) ? null : basicUrl + href;
            novelTitle.setNovle_url(href);
            novelTitle.setNovel_name(text);
            novelTitle.setNovel_type(index);
            novelTitle.setSite_id(site_id);
            novelTitle.setAuthor(novel_author);
            NovelTitle q_novelTitle = new NovelTitle();
            q_novelTitle.setNovel_name(text);
            List<NovelTitle> novelTitles = novelTitleDao.findModel(q_novelTitle);
            CollectVersion collectVersion = new CollectVersion();
            if (novelTitles.size()<=0){
                novelTitleDao.insertModel(novelTitle);
                collectVersion.setCollect_version(NovelTool.firstVersion("test"));
                collectVersion.setTitle_id(novelTitle.getId());
            }else{
                collectVersion.setCollect_version(NovelTool.maintenanceVersion(novelTitle.getVersion()));
                collectVersion.setTitle_id(novelTitles.get(0).getId());
            }
            collectVersionDao.insertModel(collectVersion);
        });
    }
}
