package com.edu.admin.education.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.edu.admin.education.model.ArtStudentImportInfoDto;

import java.io.IOException;
import java.io.InputStream;

public class TestExcel {

    public static void main(String[] args) {
        readExcel();
    }

    private static void readExcel() {
        InputStream inputStream = null;
        try {
            inputStream = getInputStream("改张军特长生 - 副本.xls");
            ExcelListener excelListener = new ExcelListener();
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, excelListener);
            reader.read(new Sheet(1, 1, ArtStudentImportInfoDto.class));

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

    private static InputStream getInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);

    }
}
