package com.novel.factory.novel_factory;

import com.novel.factory.novel_interface.AnalysisSite;
import com.novel.factory.novel_readSite.ReadSite_type1;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/09/27 15:37 
 */
public class Factory_Type1 {
    public static AnalysisSite produce(){
        AnalysisSite analysisSite = new ReadSite_type1();
        return analysisSite;
    }
}
