package com.novel.controller;

import com.novel.Tools.ResultTool;
import com.novel.model.NovelTitle;
import com.novel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/09/25 19:14 
 */
@Controller
@RequestMapping("data_collection")
public class NovelController {

    @Autowired(required = false)
    NovelService novelService;

    @RequestMapping("/start")
    @ResponseBody
    public ResultTool start(){
        ResultTool resultTool = new ResultTool();
        boolean flag = novelService.start();
        resultTool.setCode(200);
        resultTool.setMessage("success");
        resultTool.setData(flag);
        return resultTool;
    }

    @RequestMapping("/findAllNovel")
    @ResponseBody
    public ResultTool findAllNovel(){
        ResultTool resultTool = new ResultTool();
        List<NovelTitle> allNovel = novelService.findAllNovel();
        resultTool.setCode(200);
        resultTool.setMessage("success");
        resultTool.setData(allNovel);
        return resultTool;
    }

    @RequestMapping("/findNovelsByCondition")
    @ResponseBody
    public ResultTool findNovelsByCondition(NovelTitle novelTitle){
        ResultTool resultTool = new ResultTool();
        List<NovelTitle> allNovel = novelService.findNovelsByCondition(novelTitle);
        resultTool.setCode(200);
        resultTool.setMessage("success");
        resultTool.setData(allNovel);
        return resultTool;
    }

}
