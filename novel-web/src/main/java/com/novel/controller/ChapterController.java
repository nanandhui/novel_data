package com.novel.controller;

import com.novel.Tools.ResultTool;
import com.novel.model.NovelChapter;
import com.novel.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/10/09 19:08 
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired(required = false)
    ChapterService chapterService;

    @RequestMapping("/findAllChapters")
    @ResponseBody
    public ResultTool findAllChapters(NovelChapter novelChapter){
        ResultTool resultTool = new ResultTool();
        List<NovelChapter> allChapter = chapterService.findAllChapter(novelChapter.getTitle_id());
        resultTool.setCode(200);
        resultTool.setMessage("success");
        resultTool.setData(allChapter);
        return resultTool;
    }

    @RequestMapping("/findChaptersByChapterId")
    @ResponseBody
    public ResultTool findChaptersByChapterId(NovelChapter novelChapter){
        ResultTool resultTool = new ResultTool();
        List<NovelChapter> allChapter = chapterService.findChaptersByChapterId(novelChapter.getId());
        resultTool.setCode(200);
        resultTool.setMessage("success");
        resultTool.setData(allChapter);
        return resultTool;
    }

    @RequestMapping("/findOnlyChaptersByChapterId")
    @ResponseBody
    public ResultTool findOnlyChaptersByChapterId(NovelChapter novelChapter){
        ResultTool resultTool = new ResultTool();
        NovelChapter novelChapterModel = chapterService.findOnlyChaptersByChapterId(novelChapter.getId());
        resultTool.setCode(200);
        resultTool.setMessage("success");
        resultTool.setData(novelChapterModel);
        return resultTool;
    }
}
