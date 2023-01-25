package top.integer.yygh.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.integer.yygh.cmn.mapper.DictMapper;
import top.integer.yygh.model.cmn.Dict;

import java.util.ArrayList;
import java.util.List;

@Component
public class DictExcelListener implements ReadListener<Dict> {
    @Autowired
    private DictMapper mapper;

    private List<Dict> list = null;


    @Override
    public void invoke(Dict dict, AnalysisContext analysisContext) {
        if (list == null) {
            list = new ArrayList<>(300);
        }
        list.add(dict);
        if (list.size() == 300) {
//            System.out.println("list = " + list);
            mapper.insertBatch(list);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        List<Dict> list1 = list;
        list = null;
        mapper.insertBatch(list1);
    }
}
