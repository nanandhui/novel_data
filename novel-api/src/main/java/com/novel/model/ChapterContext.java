package com.novel.model;

import java.io.Serializable;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/10/09 15:08 
 */
public class ChapterContext implements Serializable {
    private int id;
    private int chaperDeatil_id;
    private int title_id;
    private String context;
    private String formatContxt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChaperDeatil_id() {
        return chaperDeatil_id;
    }

    public void setChaperDeatil_id(int chaperDeatil_id) {
        this.chaperDeatil_id = chaperDeatil_id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFormatContxt() {
        return formatContxt;
    }

    public void setFormatContxt(String formatContxt) {
        this.formatContxt = formatContxt;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }
}
