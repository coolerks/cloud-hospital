package top.integer.yygh.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-cmn", path = "/admin/cmn/dict")
@Component
public interface DictFeignClient {
    @RequestMapping("/getname/{dictCode}/{value}")
    String getName(@PathVariable("dictCode") String dictCode, @PathVariable("value") String value);

    @RequestMapping("/getname/{value}")
    String getName(@PathVariable("value") String value);
}
