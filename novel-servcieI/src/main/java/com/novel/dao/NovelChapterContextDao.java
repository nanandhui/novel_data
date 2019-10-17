package com.novel.dao;

import com.novel.model.ChapterContext;
import java.util.List;

public interface NovelChapterContextDao {
    public int insertModel(ChapterContext chapterContext);
    public List<ChapterContext> findAllModel();
    public List<ChapterContext> findModel(ChapterContext chapterContext);
    public void deleteByTitleId(int title_id);
}
