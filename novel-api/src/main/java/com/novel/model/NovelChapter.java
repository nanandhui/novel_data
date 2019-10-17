package com.novel.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/09/27 16:55 
 */
public class NovelChapter implements Serializable {
    private int        id;
    private String     chapterName;
    private String     chapterURl;
    private String     chapterContent;
    private int        chapterNum;
    private BigDecimal chapterSize;
    private int title_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterURl() {
        return chapterURl;
    }

    public void setChapterURl(String chapterURl) {
        this.chapterURl = chapterURl;
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(int chapterNum) {
        this.chapterNum = chapterNum;
    }

    public BigDecimal getChapterSize() {
        return chapterSize;
    }

    public void setChapterSize(BigDecimal chapterSize) {
        this.chapterSize = chapterSize;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }
}
