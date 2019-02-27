package com.edu.admin.server.controller;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.edu.admin.education.enums.ResultEnum;
import com.edu.admin.education.excel.ExcelListener;
import com.edu.admin.education.exception.HumanResourceException;
import com.edu.admin.education.model.ArtStudentImportInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛图片上传控制器
 *
 * @author mengqa
 **/
@RestController
@RequestMapping("/api/upload")
@Slf4j
public class UploadExcelController {

    @Autowired
    private ExcelListener excelListener;

    @PostMapping(value = "/uploadExcel")
    public String upload(@RequestParam(value = "excelFile", required = false) MultipartFile file) {
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, excelListener);
            reader.read(new Sheet(1, 1, ArtStudentImportInfoDto.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new HumanResourceException(ResultEnum.IMPORT_ERROR);
        }
        return "abc";
    }
}
