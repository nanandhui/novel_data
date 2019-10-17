package com.novel.dao;

import com.novel.model.Novel_Site;

import java.util.List;

public interface NovelSiteDao {
    public int insertModel(Novel_Site novel_site);
    public List<Novel_Site> findAllModel();
    public Novel_Site findModel(Novel_Site novel_site);
}
