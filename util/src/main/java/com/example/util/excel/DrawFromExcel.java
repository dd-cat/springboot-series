package com.example.util.excel;

import com.example.util.excel.bean.Grid;
import com.example.util.excel.bean.UserCell;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import sun.awt.SunHints;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * width:pix=getColumnWidthInPixels*1.15
 * height:pix=getHeightInPoints*96/72
 * <p>
 * 本示例用来读取Excel报表文件，并力图将报表无差别转化成PNG图片
 * 使用POI读取Excel各项数据
 * 使用JAVA 2D绘制PNG
 * TODO 本示例基本实现了常见Excel的所有样式输出，但Office2007以后的版本添加了条件样式功能，，所以无法实现
 * 今后可以通过关键字标注等方法，在Excel中需要加入条件样式的单元格用注解标明，使用JAVA计算得出样因为POI的API无法读取条件样式式再绘制出来
 */
public class DrawFromExcel {
    /**
     * @param excelUrl excel路径
     * @param path     图片存储路径
     * @param filename 文件名
     * @return 返回一个图片路径  path+System.currentTimeMillis()+filename+".png"
     * @throws Exception 异常
     */
    public static String excelTOImage(String excelUrl, String path, String filename) throws Exception {
        //excel是否存在
        File file = new File(excelUrl);
        if (!file.exists()) {
            throw new Exception("file does not exist!");
        }
        int imageWidth = 0;
        int imageHeight = 0;

        Workbook wb = WorkbookFactory.create(file);
        Sheet sheet = wb.getSheetAt(0);//第一个工作表
        List<CellRangeAddress> rangeAddress = sheet.getMergedRegions(); // 获取整个sheet中合并单元格组合的集合

        //检查区域内是否存在数据
        int rowSize = sheet.getPhysicalNumberOfRows();
        int colSize = sheet.getRow(0).getPhysicalNumberOfCells();
        if (rowSize == 0 || colSize == 0) {
            throw new Exception("the row or col of the area is wrong!");
        }
        // 遍历需要扫描的区域
        UserCell[][] cells = new UserCell[rowSize][colSize];
        int[] rowPixPos = new int[rowSize + 1];
        rowPixPos[0] = 0;
        int[] colPixPos = new int[colSize + 1];
        colPixPos[0] = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                cells[i][j] = new UserCell();
                cells[i][j].setCell(sheet.getRow(i).getCell(j));
                cells[i][j].setRow(i);
                cells[i][j].setCol(j);
                boolean ifShow = !(sheet.isColumnHidden(j) || sheet.getRow(i).getZeroHeight());//行列不可以隐藏
                cells[i][j].setShow(ifShow);
                // 计算所求区域宽度
                float widthPix = !ifShow ? 0 : sheet.getColumnWidthInPixels(j);//如果该单元格是隐藏的，则置宽度为0
                if (i == 0) {
                    imageWidth += widthPix;
                }
                colPixPos[j + 1] = (int) (widthPix * 1.15 + colPixPos[j]);
            }
            // 计算所求区域高度
            boolean ifShow = !sheet.getRow(i).getZeroHeight();    //行序列不能隐藏
            float heightPoint = !ifShow ? 0 : sheet.getRow(i).getHeightInPoints(); // 如果该单元格是隐藏的，则置高度为0
            imageHeight += heightPoint;
            rowPixPos[i + 1] = (int) (heightPoint * 96 / 72) + rowPixPos[i];
        }
        System.out.println(imageHeight);
        System.out.println(imageWidth);
        imageHeight = imageHeight * 96 / 65;
        imageWidth = imageWidth * 115 / 100;
        wb.close();

        List<Grid> grids = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                Cell cell = cells[i][j].getCell();//当前表格
                Grid grid = new Grid();
                // 设置坐标和宽高
                grid.setX(colPixPos[j]);
                grid.setY(rowPixPos[i]);
                grid.setWidth(colPixPos[j + 1] - colPixPos[j]);
                grid.setHeight(rowPixPos[i + 1] - rowPixPos[i]);
                grid.setRow(cells[i][j].getRow());
                grid.setCol(cells[i][j].getCol());
                grid.setShow(cells[i][j].isShow());
                // 判断是否为合并单元格
                int[] isInMergedStatus = isInMerged(grid.getRow(), grid.getCol(), rangeAddress);

                if (isInMergedStatus[0] == 0 && isInMergedStatus[1] == 0) {
                    // 此单元格是合并单元格，并且不是第一个单元格，需要跳过本次循环，不进行绘制
                    continue;
                } else if (isInMergedStatus[0] != -1 && isInMergedStatus[1] != -1) {
                    // 此单元格是合并单元格，并且属于第一个单元格，则需要调整网格大小
                    int lastRowPos = Math.min(isInMergedStatus[0], rowSize - 1);
                    int lastColPos = Math.min(isInMergedStatus[1], colSize - 1);
                    grid.setWidth(colPixPos[lastColPos + 1] - colPixPos[j]);
                    grid.setHeight(rowPixPos[lastRowPos + 1] - rowPixPos[i]);

                }
                // 单元格背景颜色
                CellStyle cs = cell.getCellStyle();
                if (cs.getFillPatternEnum() == FillPatternType.SOLID_FOREGROUND)
                    grid.setBgColor(cell.getCellStyle().getFillForegroundColorColor());

                // 设置字体
                org.apache.poi.ss.usermodel.Font font = wb.getFontAt(cs.getFontIndex());
                grid.setFont(font);

                // 设置字体前景色
                if (font instanceof XSSFFont) {
                    XSSFFont xf = (XSSFFont) font;
                    grid.setFtColor(xf.getXSSFColor());
                }

                // 设置文本
                String strCell;
                if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        strCell = DateFormatUtils.format(cell.getDateCellValue(), "yyyy-MM-dd");
                    } else {
                        NumberFormat nf = NumberFormat.getInstance();
                        strCell = String.valueOf(nf.format(cell.getNumericCellValue())).replace(",", "");
                    }
                } else if (cell.getCellTypeEnum() == CellType.STRING) {
                    strCell = String.valueOf(cell.getStringCellValue());
                } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                    strCell = String.valueOf(cell.getBooleanCellValue());
                } else if (cell.getCellTypeEnum() == CellType.ERROR) {
                    strCell = "错误类型";
                } else {
                    strCell = "";
                }

                if (cell.getCellStyle().getDataFormatString().contains("0.00%")) {
                    try {
                        double dbCell = Double.parseDouble(strCell);
                        strCell = new DecimalFormat("#.00").format(dbCell * 100) + "%";
                    } catch (NumberFormatException ignored) {
                    }
                }

                grid.setText(strCell.matches("\\w*\\.0") ? strCell.substring(0, strCell.length() - 2) : strCell);

                grids.add(grid);
            }
        }

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // 平滑字体
        g2d.setRenderingHint(SunHints.KEY_ANTIALIASING, SunHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIASING, SunHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
        g2d.setRenderingHint(SunHints.KEY_STROKE_CONTROL, SunHints.VALUE_STROKE_DEFAULT);
        g2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIAS_LCD_CONTRAST, 140);
        g2d.setRenderingHint(SunHints.KEY_FRACTIONALMETRICS, SunHints.VALUE_FRACTIONALMETRICS_OFF);
        g2d.setRenderingHint(SunHints.KEY_RENDERING, SunHints.VALUE_RENDER_DEFAULT);

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, imageWidth, imageHeight);

        // 绘制表格
        for (Grid g : grids) {
            if (!g.isShow())
                continue;

            // 绘制背景色
            g2d.setColor(g.getBgColor() == null ? Color.white : g.getBgColor());
            g2d.fillRect(g.getX(), g.getY(), g.getWidth(), g.getHeight());

            // 绘制边框
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawRect(g.getX(), g.getY(), g.getWidth(), g.getHeight());

            // 绘制文字,居中显示
            g2d.setColor(g.getFtColor());
            Font font = g.getFont();
            FontMetrics fm = g2d.getFontMetrics(font);
            int strWidth = fm.stringWidth(g.getText());// 获取将要绘制的文字宽度
            g2d.setFont(font);
            g2d.drawString(g.getText(),
                    g.getX() + (g.getWidth() - strWidth) / 2,
                    g.getY() + (g.getHeight() - font.getSize()) / 2 + font.getSize());
        }

        g2d.dispose();
        filename = System.currentTimeMillis() + filename;
        String imgUrl = path + filename + ".png";
        ImageIO.write(image, "png", new File(imgUrl));
        System.out.println("Output to PNG file Success!");
        return imgUrl;
    }

    /**
     * 判断Excel中的单元格是否为合并单元格
     *
     * @param row          当前行号
     * @param col          当前列号
     * @param rangeAddress 整个sheet中合并单元格组合的集合
     * @return 如果不是合并单元格返回{-1,-1},如果是合并单元格并且是一个单元格返回{lastRow,lastCol},
     * 如果是合并单元格并且不是第一个格子返回{0,0}
     */
    private static int[] isInMerged(int row, int col, List<CellRangeAddress> rangeAddress) {
        int[] isInMergedStatus = {-1, -1};
        for (CellRangeAddress cra : rangeAddress) {
            if (row == cra.getFirstRow() && col == cra.getFirstColumn()) {
                isInMergedStatus[0] = cra.getLastRow();
                isInMergedStatus[1] = cra.getLastColumn();
                return isInMergedStatus;
            }
            if (row >= cra.getFirstRow() && row <= cra.getLastRow()) {
                if (col >= cra.getFirstColumn() && col <= cra.getLastColumn()) {
                    isInMergedStatus[0] = 0;
                    isInMergedStatus[1] = 0;
                    return isInMergedStatus;
                }
            }
        }
        return isInMergedStatus;
    }
}