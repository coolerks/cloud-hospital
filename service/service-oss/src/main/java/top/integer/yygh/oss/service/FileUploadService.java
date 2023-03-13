package top.integer.yygh.oss.service;

import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.integer.yygh.oss.component.FileUploader;
import top.integer.yygh.oss.component.OssUploadComponent;

import java.io.IOException;

@Service
@RefreshScope
public class FileUploadService {
    @Autowired
    private OssUploadComponent ossUploadComponent;


    @Value("${oss.bucketName}")
    private String bucketName;


    public String upload(MultipartFile multipartFile) throws IOException {
        try(FileUploader fileUploader = ossUploadComponent.buildFileUploader()) {
            String url = fileUploader.upload(bucketName, multipartFile.getOriginalFilename(), multipartFile.getInputStream());
            return bucketName + '.' + ossUploadComponent.getEndpoint() + "/" + url;
        }
    }
}


