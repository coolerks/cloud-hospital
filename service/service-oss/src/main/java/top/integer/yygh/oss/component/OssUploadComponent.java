package top.integer.yygh.oss.component;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import top.integer.yygh.common.result.Result;

@Component
@RefreshScope
public class OssUploadComponent {
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.access.key.id}")
    private String accessKeyId;
    @Value("${oss.access.key.secret}")
    private String accessKeySecret;

    public FileUploader buildFileUploader() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return new FileUploader(ossClient);
    }

    public String getEndpoint() {
        return endpoint;
    }
}
