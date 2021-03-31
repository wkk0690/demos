package com.example.demo;

import com.example.demo.util.HanyupinyinUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) throws  Exception {

        Map<String, String> map = new HashMap();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        for (Map.Entry<String,String> entry : map.entrySet()){
             String key = entry.getKey();
             String value = entry.getValue();
        }




//        demo1();
//        demo2();
//        demo3();
    }

    private static void demo1() {
        File source = new File("C:\\Users\\Lenovo\\Desktop\\a");
        File target = new File("C:\\Users\\Lenovo\\Desktop\\target1");
        File[] files = source.listFiles();
        for (File sFile : files) {
            String sname = sFile.getName();
            sname = sname.split("\\.")[1];
//            sname = HanyupinyinUtil.getPinyinString(sname);
            sFile.renameTo(new File(target.getAbsolutePath() + "\\" + sname));
//            File[] tFile = sFile.listFiles();
            /*for (File fFile : tFile) {
                String name = fFile.getName().replace("jpg", "png");
                name = name.replaceAll(" ", "");
                fFile.renameTo(new File(target.getAbsolutePath() + "\\" + sname + "\\" + name));

//                File newFile = new File(target.getAbsolutePath() + "\\" + name);
//                FileUtils.copyFile(fFile, newFile);

                //System.out.println(String.format("update mi_user set face_picture='%s' where mu_name = '%s';", "/pic/" + name, name.split(".")[0]));
//                System.out.println(String.format("update mi_member set picture='%s' where mu_name = '%s' and mi_id = '6d5f6515c7ac439082f1b332eaa9885e';", "/pic/" + name, name));
            }*/
        }
    }

    private static void demo2() {
        File target = new File("C:\\Users\\Lenovo\\Desktop\\target");
        File[] files = target.listFiles();
        for (File sFile : files) {
            String sname = sFile.getName();
            File[] tfiles = sFile.listFiles();
            for (File tfile : tfiles) {
                String[] split = tfile.getName().split("\\.");
                if(split.length > 1) {

                    String name = tfile.getName().split("\\.")[0];

                    String spinyin = HanyupinyinUtil.getPinyinString(sname);
                    String pinyin = HanyupinyinUtil.getPinyinString(name) + "." + tfile.getName().split("\\.")[1];
                    if(sname.equals("山西")) {
                        spinyin = "shanxii";
                    }
                    tfile.renameTo(new File(target.getAbsolutePath() + "\\" + sname + "\\" +pinyin));
//                    System.out.println(String.format("update mi_user set face_picture='%s' where mu_name = '%s' and mu_unit= '%s';", "/pic/"+spinyin+"/" + pinyin, name, sname));
                }
            }
        }
    }

    private static void demo3() {
        File target = new File("C:\\Users\\Lenovo\\Desktop\\target1");
        File[] files = target.listFiles();
        for (File sFile : files) {
            String sname = sFile.getName();
            File[] tfiles = sFile.listFiles();
            for (File tfile : tfiles) {
                String[] split = tfile.getName().split("\\.");
                if(split.length > 1) {

                    String name = tfile.getName().split("\\.")[0];

                    String spinyin = HanyupinyinUtil.getPinyinString(sname);
                    String pinyin = HanyupinyinUtil.getPinyinString(name) + "." + tfile.getName().split("\\.")[1];
                    if(sname.equals("山西")) {
                        spinyin = "shanxii";
                    }
//                    sFile.renameTo(new File(target.getAbsolutePath() + "\\" + sname + "\\" +tfile.getName().split("\\.")[0]));
                    System.out.println(String.format("update mi_user set face_picture='%s' where mu_name = '%s' and mu_unit= '%s';", "/pic/face/"+spinyin+"/" + pinyin, name, sname));
                }
            }
        }
    }
}
