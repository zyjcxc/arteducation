package com.edu.admin.education.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.edu.admin.education.model.ArtStudentImportInfoDto;
import com.edu.admin.education.service.IArtStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ExcelListener extends AnalysisEventListener {

    private List<Object> datas = new ArrayList<>();

    @Autowired
    private IArtStudentService artStudentService;

    @Override
    public void invoke(Object o, AnalysisContext context) {
//        System.out.println("sheet:" + context.getCurrentSheet().getSheetNo() + ",row："
//                + context.getCurrentRowNum() + ",data:" + o);
        datas.add(o); //数据存储到list，供批量处理，或后续自己业务逻辑处理。
//        transObject(o);//根据自己业务做处理
    }

    private void transObject(Object object) {


    }

    private void toMysql() {
        //1、入库调用接口
        List<ArtStudentImportInfoDto> dtoList = new ArrayList<>();
        for (Object data : datas) {
            dtoList.add((ArtStudentImportInfoDto) data);
        }
        datas.clear();
        artStudentService.saveDatas(dtoList);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        toMysql();
        datas.clear();
    }
}
