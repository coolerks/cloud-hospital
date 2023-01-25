package top.integer.yygh.cmn.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.integer.yygh.cmn.listener.DictExcelListener;
import top.integer.yygh.cmn.mapper.DictMapper;
import top.integer.yygh.model.cmn.Dict;
import top.integer.yygh.vo.cmn.DictEeVo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class DictService extends ServiceImpl<DictMapper, Dict> {
    @Autowired
    private DictExcelListener listener;

    public List<Dict> getDictByParentId(Long id) {
        return baseMapper.getAllByIdDictId(id);
    }

    public void processFileById(HttpServletResponse response, Long id) throws IOException {
        String fileName = URLEncoder.encode("-数据字典", StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=id:" + id + fileName + ".xlsx");
        processFile(response, getDictByParentId(id));
    }

    public void processAll(HttpServletResponse response) throws IOException {
        String fileName = URLEncoder.encode("所有的数据字典", StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        processFile(response, baseMapper.getAll());
    }

    public void processFile(HttpServletResponse response, List<Dict> dicts) throws IOException {
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        try (
                ServletOutputStream outputStream = response.getOutputStream();
                ExcelWriter excelWriter = EasyExcel.write(outputStream, DictEeVo.class).build();
        ) {
            WriteSheet writeSheet = EasyExcel.writerSheet("数据字典").build();
            List<DictEeVo> list = dicts.stream().map(dict -> {
                DictEeVo target = new DictEeVo();
                BeanUtils.copyProperties(dict, target);
                return target;
            }).toList();
            excelWriter.write(list, writeSheet);
        }
    }


    public void importDictData(MultipartFile multipartFile) throws IOException {
        try(ExcelReader excelReader = EasyExcel.read(multipartFile.getInputStream(), Dict.class, listener).build()) {
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        }
    }
}
