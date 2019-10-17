package com.novel.serviceImp.novel;

import com.novel.dao.NovelChapterDao;
import com.novel.model.NovelChapter;
import com.novel.service.ChapterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/10/09 18:47 
 */
@Service("chapterService")
public class ChapterServiceImp implements ChapterService {
    @Resource
    NovelChapterDao novelChapterDao;

    @Override
    public List<NovelChapter> findAllChapter(int novel_id) {
        List<NovelChapter> allModel = novelChapterDao.findAllModel(novel_id);
        return allModel;
    }

    @Override
    public List<NovelChapter> findChaptersByChapterId(int chapter_id) {
        List<NovelChapter> novelChapters = novelChapterDao.findChaptersByChapterId(chapter_id);
        return novelChapters;
    }

    @Override
    public NovelChapter findOnlyChaptersByChapterId(int chapter_id) {
        NovelChapter novelChapter = novelChapterDao.findOnlyChaptersByChapterId(chapter_id);
        return novelChapter;
    }
}
