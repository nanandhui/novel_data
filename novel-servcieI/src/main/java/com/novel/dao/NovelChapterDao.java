package com.novel.dao;

import com.novel.model.NovelChapter;

import java.util.List;

public interface NovelChapterDao {
    public int insertModel(NovelChapter novelChapter);
    public List<NovelChapter> findAllModel(int novel_id);
    public List<NovelChapter> findModel(NovelChapter novelChapter);
    //获取当前指定章节前两张后两张共5章内容(可作为缓存)
    public List<NovelChapter> findChaptersByChapterId(int chapter_id);

    public NovelChapter findOnlyChaptersByChapterId(int chapter_id);
    public void deleteByTitleId(int title_id);
}
