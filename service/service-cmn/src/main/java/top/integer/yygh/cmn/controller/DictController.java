package top.integer.yygh.cmn.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.integer.yygh.cmn.service.DictService;
import top.integer.yygh.common.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/admin/cmn/dict")
@Api(tags = "数据字典")
public class DictController {
    @Autowired
    private DictService service;

    @GetMapping("/findChildData/{id}")
    public Result getDictById(@PathVariable Long id) {
        return Result.ok(service.getDictByParentId(id));
    }

    @GetMapping("/export/{id}")
    public void getFileById(HttpServletResponse response, @PathVariable Long id) throws IOException {
        service.processFileById(response, id);
    }

    @GetMapping("/exportAll")
    public void getAllFile(HttpServletResponse response) throws IOException {
        service.processAll(response);
    }

    @PostMapping("/import")
    public void importDictData(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        service.importDictData(multipartFile);
    }

}
