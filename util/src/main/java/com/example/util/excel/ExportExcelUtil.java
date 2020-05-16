package com.example.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 基于POI的javaee导出Excel工具类
 */
public class ExportExcelUtil {
    // 2007 版本以上 最大支持1048576行
    public final static String EXCEl_FILE_2007 = "2007";
    // 2003 版本 最大支持65536 行
    public final static String EXCEL_FILE_2003 = "2003";
    //时间格式“yyyy-MM-dd HH:mm:ss” 文件名命名
    public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    public final static String route = "D:";

    /**
     * javaBean集合导出
     *
     * @param fileName 文件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合javabean规范
     * @param version  版本 默认2007
     * @return 文件全路径
     * @throws Exception 异常
     */
    public static <T> void exportBeanExcel(String fileName, String[] headers, Collection<T> dataList, String version) throws Exception {
        exportBeanExcel(null, fileName, headers, dataList, version);
    }

    /**
     * map集合导出
     *
     * @param fileName 文件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合map规范
     * @param version  版本 默认2007
     * @return 文件全路径
     * @throws Exception 异常
     */
    public static <T> void exportMapExcel(String fileName, String[] headers, Collection<T> dataList, String version) throws Exception {
        exportMapExcel(null, fileName, headers, dataList, version);
    }

    /**
     * javaBean集合导出
     *
     * @param title    大标题
     * @param fileName 文件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合javabean规范
     * @param version  版本 默认2007
     * @return 文件全路径
     * @throws Exception 异常
     */
    public static <T> String exportBeanExcel(String title, String fileName, String[] headers, Collection<T> dataList, String version) throws Exception {
        return exportBeanExcel(title, fileName, headers, dataList, null, version);
    }

    /**
     * map集合导出
     *
     * @param title    大标题
     * @param fileName 文件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合map规范
     * @param version  版本 默认2007
     * @return 文件全路径
     * @throws Exception 异常
     */
    public static <T> String exportMapExcel(String title, String fileName, String[] headers, Collection<T> dataList, String version) throws Exception {
        return exportMapExcel(title, fileName, headers, dataList, null, version);
    }

    /**
     * javaBean集合导出
     *
     * @param title    大标题
     * @param fileName 文件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合javabean规范
     * @param path     文件存放路径
     * @param version  版本 默认2007
     * @return 文件全路径
     * @throws Exception 异常
     */
    public static <T> String exportBeanExcel(String title, String fileName, String[] headers, Collection<T> dataList, String path, String version) throws Exception {
        //路径为空 默认D盘
        if (path == null || "".equals(path)) {
            path = route;
        }
        if (version == null || "".equals(version) || EXCEl_FILE_2007.equals(version)) {
            path += File.separator + fileName + sdf.format(new Date()) + ".xlsx";
            exportExcel2007(title, fileName, headers, dataList, path);
        } else {
            path += File.separator + fileName + sdf.format(new Date()) + ".xls";
            exportExcel2003(title, fileName, headers, dataList, path);
        }
        return path;
    }

    /**
     * map集合导出
     *
     * @param title    大标题
     * @param fileName 文件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合map规范
     * @param path     文件存放路径
     * @param version  版本 默认2007
     * @return 文件全路径
     * @throws Exception 异常
     */
    public static <T> String exportMapExcel(String title, String fileName, String[] headers, Collection<T> dataList, String path, String version) throws Exception {
        if (path == null || "".equals(path)) {
            path = "D:/" + fileName + sdf.format(new Date());
        }
        if (version == null || "".equals(version) || EXCEl_FILE_2007.equals(version)) {
            path += ".xlsx";
            exportMapExcel2007(title, fileName, headers, dataList, path);
        } else {
            path += ".xls";
            exportMapExcel2003(title, fileName, headers, dataList, path);
        }
        return path;

    }

    /**
     * 数据源是javaBean泛型的list 2007版
     *
     * @param title    标题 可以为空
     * @param fileName 件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字段名
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合javabean规范
     * @param path     文件路径
     * @param <T>      泛型
     * @throws Exception 异常
     */
    public static <T> void exportExcel2007(String title, String fileName, String[] headers, Collection<T> dataList, String path) throws Exception {
        // 标题数组
        String[] titleArray = new String[headers.length];
        // 字段名数组
        String[] fieldArray = new String[headers.length];
        for (int i = 0; i < headers.length; i++) {
            String[] tempArray = headers[i].split("#");// 临时数组 分割#
            titleArray[i] = tempArray[0];
            fieldArray[i] = tempArray[1];
        }
        // 创建一个Workbook，对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 在Workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(fileName);
        // 自动设置宽度
        //sheet.autoSizeColumn(0);
        //统一设置列宽
        sheet.setDefaultColumnWidth(20);

        int index = 0;//定义所创建行
        //创建大标题
        if (title != null && !"".equals(title)) {
            XSSFRow row = sheet.createRow(index++);//不要写成++index
            //设置单元格样式以及字体样式
            XSSFCellStyle style = ExportExcelUtil.getTitleStyle(wb);
            XSSFCell cell = row.createCell(0);// 创建标题第一列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1)); // 合并标题
            cell.setCellValue(title); // 设置值标题
            cell.setCellStyle(style); // 设置大标题样式
        }
        // 在sheet中添加标题行
        XSSFRow row = sheet.createRow(index++);// 行数从0开始
        // 设置标题样式
        XSSFCellStyle titleStyle = ExportExcelUtil.getColumnTopStyle(wb);
        // 为标题行赋值
        for (int i = 0; i < titleArray.length; i++) {
            XSSFCell titleCell = row.createCell(i);// 0号位被序号占用，所以需+1
            titleCell.setCellValue(titleArray[i]);
            titleCell.setCellStyle(titleStyle);
        }
        XSSFCellStyle dataStyle = ExportExcelUtil.getStyle(wb);
        // 遍历集合数据，产生数据行
        for (T item : dataList) {
            row = sheet.createRow(index++);
            // 利用反射，根据传过来的字段名数组，动态调用对应的getXxx()方法得到属性值
            for (int i = 0; i < fieldArray.length; i++) {
                XSSFCell dataCell = row.createCell(i);
                String fieldName = fieldArray[i];
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
                Class<?> tCls = item.getClass();// 泛型为Object以及所有Object的子类
                Method getMethod = tCls.getMethod(getMethodName);// 通过方法名得到对应的方法
                Object value = getMethod.invoke(item);// 动态调用方,得到属性值
                if (value != null) {
                    dataCell.setCellValue(value.toString());// 为当前列赋值
                }
                dataCell.setCellStyle(dataStyle);// 设置单元格样式
            }
        }
        FileOutputStream flout = new FileOutputStream(path);
        wb.write(flout);//写入数据
        flout.close();// 关闭输出流
    }


    /**
     * 数据源是map泛型的list 2007版
     *
     * @param title    标题 可以为空
     * @param fileName 件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字段名
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合javabean规范
     * @param path     文件路径
     * @param <T>      泛型
     * @throws Exception 异常
     */
    public static <T> void exportMapExcel2007(String title, String fileName, String[] headers, Collection<T> dataList, String path) throws Exception {
        // 创建一个webbook，对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 在webbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(fileName);
        int index = 0;//定义所创建行
        //创建大标题
        if (title != null && !"".equals(title)) {
            XSSFRow row = sheet.createRow(index++);//不要写成++index
            //设置单元格样式以及字体样式
            XSSFCellStyle style = ExportExcelUtil.getTitleStyle(wb);
            XSSFCell cell = row.createCell(0);// 创建标题第一列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1)); // 合并标题
            cell.setCellValue(title); // 设置值标题
            cell.setCellStyle(style); // 设置大标题样式
        }

        // 在sheet中添加表头第0,注意老版本poi对Excel的行数列数有限制short
        XSSFRow row = sheet.createRow(index++);
        //创建表头样式
        XSSFCellStyle titleStyle = ExportExcelUtil.getColumnTopStyle(wb);
        XSSFCell cell;
        for (int i = 0; i < headers.length; i++) { // 表头
            cell = row.createCell((short) i);
            cell.setCellValue(headers[i].split("#")[0]);
            cell.setCellStyle(titleStyle);
        }
        //创建数据行样式
        XSSFCellStyle dataStyle = ExportExcelUtil.getStyle(wb);
        List<Map<String, Object>> list = (List<Map<String, Object>>) dataList;
        for (Map<String, Object> map : list) {
            row = sheet.createRow(index++); // 创建一行
            for (int j = 0; j < headers.length; j++) {
                XSSFCell cells = row.createCell(j);//创建一个单元格
                cells.setCellValue(replace(map.get(headers[j].split("#")[1]).toString())); //设置单元格值
                cells.setCellStyle(dataStyle);// 设置单元格样式
            }
        }
        FileOutputStream flout = new FileOutputStream(path);
        wb.write(flout);
        flout.close();// 关闭流
    }

    /**
     * 数据源是javaBean泛型的list 2003版
     *
     * @param title    标题 可以为空
     * @param fileName 件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字段名
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合javabean规范
     * @param path     文件路径
     * @param <T>      泛型
     * @throws Exception 异常
     */
    public static <T> void exportExcel2003(String title, String fileName, String[] headers, Collection<T> dataList, String path) throws Exception {
        // 标题数组
        String[] titleArray = new String[headers.length];
        // 字段名数组
        String[] fieldArray = new String[headers.length];
        for (int i = 0; i < headers.length; i++) {
            String[] tempArray = headers[i].split("#");// 临时数组 分割#
            titleArray[i] = tempArray[0];
            fieldArray[i] = tempArray[1];
        }
        // 创建一个Workbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 在Workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(fileName);
        // 自动设置宽度
        //sheet.autoSizeColumn(0);
        //统一设置列宽
        sheet.setDefaultColumnWidth(20);

        int index = 0;//定义所创建行
        //创建大标题
        if (title != null && !"".equals(title)) {
            HSSFRow row = sheet.createRow(index++);//不要写成++index
            //设置单元格样式以及字体样式
            HSSFCellStyle style = ExportExcelUtil.getTitleStyle(wb);
            HSSFCell cell = row.createCell(0);// 创建标题第一列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1)); // 合并标题
            cell.setCellValue(title); // 设置值标题
            cell.setCellStyle(style); // 设置大标题样式
        }
        // 在sheet中添加标题行
        HSSFRow row = sheet.createRow(index++);// 行数从0开始
        // 设置标题样式
        HSSFCellStyle titleStyle = ExportExcelUtil.getColumnTopStyle(wb);
        // 为标题行赋值
        for (int i = 0; i < titleArray.length; i++) {
            HSSFCell titleCell = row.createCell(i);// 0号位被序号占用，所以需+1
            titleCell.setCellValue(titleArray[i]);
            titleCell.setCellStyle(titleStyle);
        }
        HSSFCellStyle dataStyle = ExportExcelUtil.getStyle(wb);
        // 遍历集合数据，产生数据行
        for (T item : dataList) {
            row = sheet.createRow(index++);
            // 利用反射，根据传过来的字段名数组，动态调用对应的getXxx()方法得到属性值
            for (int i = 0; i < fieldArray.length; i++) {
                HSSFCell dataCell = row.createCell(i);
                String fieldName = fieldArray[i];
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
                Class<?> tCls = item.getClass();// 泛型为Object以及所有Object的子类
                Method getMethod = tCls.getMethod(getMethodName);// 通过方法名得到对应的方法
                Object value = getMethod.invoke(item);// 动态调用方,得到属性值
                if (value != null) {
                    dataCell.setCellValue(value.toString());// 为当前列赋值
                }
                dataCell.setCellStyle(dataStyle);// 设置单元格样式
            }
        }
        FileOutputStream flout = new FileOutputStream(path);
        wb.write(flout);//写入数据
        flout.close();// 关闭输出流
    }

    /**
     * 数据源是map泛型的list 2007版
     *
     * @param title    标题 可以为空
     * @param fileName 件名
     * @param headers  excel表头数组，存放"编号#BIANH"格式字符串，"编号"为excel标题行， "BIANH"为对象字段名
     * @param dataList 数据集合，需与表头数组中的字段名一致，并且符合javabean规范
     * @param path     文件路径
     * @param <T>      泛型
     * @throws Exception 异常
     */
    public static <T> void exportMapExcel2003(String title, String fileName, String[] headers, Collection<T> dataList, String path) throws Exception {
        // 创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(fileName);
        int index = 0;//定义所创建行
        //创建大标题
        if (title != null && !"".equals(title)) {
            HSSFRow row = sheet.createRow(index++);//不要写成++index
            //设置单元格样式以及字体样式
            HSSFCellStyle style = ExportExcelUtil.getTitleStyle(wb);
            HSSFCell cell = row.createCell(0);// 创建标题第一列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1)); // 合并标题
            cell.setCellValue(title); // 设置值标题
            cell.setCellStyle(style); // 设置大标题样式
        }

        // 在sheet中添加表头第0,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(index++);
        //创建表头样式
        HSSFCellStyle titleStyle = ExportExcelUtil.getColumnTopStyle(wb);
        HSSFCell cell;
        for (int i = 0; i < headers.length; i++) { // 表头
            cell = row.createCell((short) i);
            cell.setCellValue(headers[i].split("#")[0]);
            cell.setCellStyle(titleStyle);
        }
        //创建数据行样式
        HSSFCellStyle dataStyle = ExportExcelUtil.getStyle(wb);
        List<Map<String, Object>> list = (List<Map<String, Object>>) dataList;
        for (Map<String, Object> map : list) {
            row = sheet.createRow(index++); // 创建一行
            for (int j = 0; j < headers.length; j++) {
                HSSFCell cells = row.createCell(j);//创建一个单元格
                cells.setCellValue(replace(map.get(headers[j].split("#")[1]).toString()));//设置单元格值
                cells.setCellStyle(dataStyle);// 设置单元格样式
            }
        }
        FileOutputStream flout = new FileOutputStream(path);
        wb.write(flout);
        flout.close();// 关闭流
    }

    /*
     * 大标题单元格样式 2007版
     */
    public static XSSFCellStyle getTitleStyle(XSSFWorkbook workbook) {
        // 设置字体
        XSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 16);
        // 字体加粗
        font.setBold(false);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        // 设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /*
     * 列头单元格样式 2007版
     */
    public static XSSFCellStyle getColumnTopStyle(XSSFWorkbook workbook) {
        // 设置字体
        XSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBold(false);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        // 设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(true);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /*
     * 列数据信息单元格样式 2007版
     */
    public static XSSFCellStyle getStyle(XSSFWorkbook workbook) {
        // 设置字体
        XSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 10);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        // 设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /*
     * 大标题单元格样式 2003版
     */
    public static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 16);
        // 字体加粗
        font.setBold(false);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        // 设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /*
     * 列头单元格样式 2003版
     */
    public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 11);
        // 字体加粗
        font.setBold(true);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        // 设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(true);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;

    }

    /*
     * 列数据信息单元格样式 2003版
     */
    public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        // 设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;

    }

    //处理特殊字符
    public static String replace(String str) {
        str = str.replace("&#39;", "'");
        str = str.replace("&rsquo;", "’");
        str = str.replace("&mdash;", "——");
        str = str.replace("&ndash;", "-");
        str = str.replace("&amp;", "&");
        str = str.replace("&lt;", "<");
        str = str.replace("&gt;", ">");
        str = str.replace("&nbsp;", " ");
        return str;
    }
}