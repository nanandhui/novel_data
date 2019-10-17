package com.novel.dao;

import com.novel.model.NovelTitle;

import java.util.List;

public interface NovelTitleDao {
    public int insertModel(NovelTitle novelTitle);
    public List<NovelTitle> findAllModel();
    public List<NovelTitle> findModel(NovelTitle novelTitle);
    public int updateModel(NovelTitle novelTitle);

}
