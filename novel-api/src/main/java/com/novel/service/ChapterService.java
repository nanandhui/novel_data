package com.novel.service;

import com.novel.model.NovelChapter;

import java.util.List;

public interface ChapterService {
    public List<NovelChapter> findAllChapter(int novel_id);
    public List<NovelChapter> findChaptersByChapterId(int chapter_id);
    public NovelChapter findOnlyChaptersByChapterId(int chapter_id);
}
