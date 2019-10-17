package com.novel.factory.novel_interface;

import com.novel.dao.CollectVersionDao;
import com.novel.dao.NovelTitleDao;

public interface AnalysisSite {
    public boolean analysis(int site_id, String result, String basicUrl, NovelTitleDao novelTitleDao, CollectVersionDao collectVersionDao,String site_encoding);
}
