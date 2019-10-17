package com.novel.handleException;

import com.novel.Tools.ResultTool;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/08/22 17:22 
 */
public class ExceptionAdvice {
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResultTool handleRuntimeException(Exception e) {
        ResultTool resultTool;
        if (e instanceof UniteException) {
            UniteException uniteException = (UniteException) e;
            resultTool = new ResultTool(uniteException.getCode(), uniteException.getMessage());
            return resultTool;
        }
        return new ResultTool(0, "未知异常");
    }
}
