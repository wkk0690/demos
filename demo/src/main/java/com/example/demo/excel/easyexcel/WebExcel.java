package com.example.demo.excel.easyexcel;

/**
 * @author 0X574B4B
 * @create 2018-09-27 15:01
 * @descriptions <p></p >
 */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jipengfei
 * @date 2018/05/25
 */
@RequestMapping("/api/dingtalk")
@RestController
public class WebExcel {

    @GetMapping("/a.htm")
    public void cooperation(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        try {

            String fileName = new String(("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                    .getBytes(), "UTF-8");
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet");
            writer.write0(getListString(), sheet1);
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.finish();
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private List<List<String>> getListString() {
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
