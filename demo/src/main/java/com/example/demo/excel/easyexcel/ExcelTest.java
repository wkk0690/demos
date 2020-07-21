package com.example.demo.excel.easyexcel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.io.IOException;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

/**
 * @author 0X574B4B
 * @create 2018-09-27 14:21
 * @desc https://github.com/alibaba/easyexcel
 */
public class ExcelTest {

    public static void main(String[] args) {
        read();

        write();
    }

    /**
     * 读取xlsx文件
     */
    public static void read() {
        InputStream inputStream = null;
        try {
            //inputStream = getInputStream("aa.xlsx");
            inputStream = new FileInputStream("A:\\workspace\\idea\\demo\\src\\main\\java\\com\\example\\demo\\excel\\aa.xlsx");
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null,
                    new AnalysisEventListener<List<String>>() {
                        @Override
                        public void invoke(List<String> object, AnalysisContext context) {
                            System.out.println(
                                    "当前sheet:" + context.getCurrentSheet().getSheetNo() + " 当前行：" + context.getCurrentRowNum()
                                            + " data:" + object);
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext context) {

                        }
                    });

            reader.read();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写入xlsx文件
     */
    public static void write() {

        OutputStream out = null;
        try {
            out = new FileOutputStream("A:\\workspace\\idea\\demo\\src\\main\\java\\com\\example\\demo\\excel\\cc.xlsx");
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet");
            writer.write0(getListString(), sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<List<String>> getListString() {
        List list = new ArrayList();
        list.add("ooo1");
        list.add("ooo2");
        list.add("ooo3");
        list.add("ooo4");
        List list1 = new ArrayList();
        list1.add("ooo1");
        list1.add("ooo2");
        list1.add("ooo3");
        list1.add("ooo4");
        List<List<String>> ll = new ArrayList<List<String>>();
        ll.add(list);
        ll.add(list1);
        return ll;
    }

}
