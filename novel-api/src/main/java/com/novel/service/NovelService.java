package com.novel.service;

import com.novel.model.NovelTitle;

import java.util.List;

public interface NovelService {
    //spider开始
    public boolean start();

    public List<NovelTitle> findAllNovel();

    public List<NovelTitle> findNovelsByCondition(NovelTitle novelTitle);

}
