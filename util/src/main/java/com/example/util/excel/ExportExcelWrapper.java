package com.example.util.excel;

import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Collection;

public class ExportExcelWrapper extends ExportExcelUtil {
    /**
     * @param fileName 文件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字段名
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合map规范
     * @param version  版本 默认2007
     * @param response 响应
     * @throws Exception 异常
     */
    public void exportBeanExcel(String fileName, String[] headers, Collection<T> dataList, String version, HttpServletResponse response) throws Exception {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
            ExportExcelUtil.exportBeanExcel(fileName, headers, dataList, version);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName 文件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字段名
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合javabean规范
     * @param version  版本 默认2007
     * @param response 响应
     * @throws Exception 异常
     */
    public void exportMapExcel(String fileName, String[] headers, Collection<T> dataList, String version, HttpServletResponse response) throws Exception {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
            ExportExcelUtil.exportMapExcel(fileName, headers, dataList, version);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
