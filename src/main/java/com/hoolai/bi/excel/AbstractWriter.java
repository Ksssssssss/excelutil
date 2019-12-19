package com.hoolai.bi.excel;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.QueryInfo;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 10:10
 */

public abstract class AbstractWriter {

    public abstract void write(int index, ExcelWriter excelWriter, QueryInfo info);

    public abstract ExcelDatas produceDatas(QueryInfo info);

    public static class CategoryWriter{
        public CategoryWriter() {}

        public void categoryWrite(int index, ExcelWriter excelWriter, QueryInfo info, AbstractWriter[] writers){
           if (writers.length<=0){
               return;
           }
            for (AbstractWriter writer:writers) {
                writer.write(index, excelWriter, info);
            }
        }

    }
}
