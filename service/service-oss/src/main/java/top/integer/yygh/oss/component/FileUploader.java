package top.integer.yygh.oss.component;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import lombok.AllArgsConstructor;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class FileUploader implements Closeable {
    private OSS ossClient;

    public String upload(String bucketName, String fileName, InputStream input) {
        String[] split = fileName.split("\\.");
        fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + split[split.length - 1];
        String fullPath = "oss/";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        fullPath += simpleDateFormat.format(new Date()) + fileName;
        ossClient.putObject(bucketName, fullPath, input);
        return fullPath;
    }

    @Override
    public void close() throws IOException {
        ossClient.shutdown();
    }
}
