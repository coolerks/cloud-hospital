package top.integer.yygh.oss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.integer.yygh.common.result.Result;
import top.integer.yygh.oss.service.FileUploadService;

import java.io.IOException;

@RestController
@RequestMapping("/api/oss")
public class FileUploadController {
    @Autowired
    private FileUploadService service;

    @PostMapping("/upload")
    public Result upload(MultipartFile multipartFile) throws IOException {
        return Result.ok(service.upload(multipartFile));
    }
}
