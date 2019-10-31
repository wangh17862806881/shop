package com.fh.shop.admin.util;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.xssf.usermodel.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {


    public static XSSFWorkbook excelDownLoad(List dataList, String sheetName, String[] headNames, String[] props, Class clazz){
        //创建excel对象
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建单元格样式
        Map<String, XSSFCellStyle> styleMap = buildCellStyle(workbook);
        //创建sheet
        XSSFSheet sheet = workbook.createSheet(sheetName);
        //创建标题行
        buildHeadRow(headNames, sheet);
        //创建主体
        buildBody(dataList, props, clazz, styleMap, sheet);


        return workbook;
    }
    //创建样式
    private static Map<String, XSSFCellStyle> buildCellStyle(XSSFWorkbook workbook) {
        //创建单元格样式 double类型
        XSSFCellStyle doubleCellStyle = workbook.createCellStyle();
        doubleCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        //创建单元格样式  date类型
        XSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        //将样式放进Map里  方便传值
        Map<String,XSSFCellStyle> styleMap = new HashMap<>();
        styleMap.put("doubleCellStyle",doubleCellStyle);
        styleMap.put("dateCellStyle",dateCellStyle);
        return styleMap;
    }
    //创建导出主体
    private static void buildBody(List dataList, String[] props, Class clazz, Map<String, XSSFCellStyle> styleMap, XSSFSheet sheet) {
        //创建主体
        for (int i = 0; i <dataList.size() ; i++) {
            //获取当前要导出对象  获取属性值时使用
            Object obj = dataList.get(i);
            //创建要循环赋值的行 数据展示用
            XSSFRow row = sheet.createRow(i + 1);
            //循环创建单元格 并为单元格赋值  props是要导出的字段名/属性名 集合
            for (int j = 0; j <props.length ; j++) {
                //创建单元格
                XSSFCell cell = row.createCell(j);
                //使用获取要赋给单元格的值
                try {
                    //获取要导出属性
                    Field declaredField = clazz.getDeclaredField(props[j]);
                    //设置访问权限/暴力访问  因为属性是私有的 所以需要设置访问权限才能访问
                    declaredField.setAccessible(true);
                    //根据获取属性调用/获取 当前属性值
                    Object param = declaredField.get(obj);
                    //获取当前属性类型
                    Class<?> type = declaredField.getType();
                    //判断当前属性值 是什么类型  根据属性类型 不同处理方法
                    if(type == Integer.class){
                        cell.setCellValue(Integer.valueOf(String.valueOf(param)));
                    }
                    if(type == String.class){
                        cell.setCellValue(String.valueOf(param));
                    }
                    if(type == Double.class){
                        cell.setCellValue(Double.valueOf(String.valueOf(param)));
                    }
                    if(type == Date.class){
                        cell.setCellValue((Date)param);
                        cell.setCellStyle(styleMap.get("dateCellStyle"));
                    }
                    if(type == Long.class){
                        cell.setCellValue(Long.valueOf(String.valueOf(param)));
                    }
                    if(type == BigDecimal.class){
                        cell.setCellValue(((BigDecimal)param).doubleValue());
                        cell.setCellStyle(styleMap.get("doubleCellStyle"));
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    //创建标题行
    private static void buildHeadRow(String[] headNames, XSSFSheet sheet) {
        //创建标题行
        XSSFRow headRow = sheet.createRow(0);
        //为标题行赋值
        for (int i = 0; i < headNames.length; i++) {
            headRow.createCell(i).setCellValue(headNames[i]);
        }
    }




}
