package com.edu.admin.education.utils;

import com.google.zxing.WriterException;

import java.io.IOException;

/**
 * @author mengqa
 * @create 2018-10-04 17:34
 **/
public class Qrest {

    public static void main(String[] args) {

        String content="http://www.qgtcs.cn/pages/search/index.html";
//        String content="http://www.baidu.com";
        String logUri="D:\\FFOutput\\log.jpg";
        String outFileUri="D:\\FFOutput\\a.jpg";
        int[]  size=new int[]{430,430};
        String format = "jpg";

        try {
            new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri,size);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
