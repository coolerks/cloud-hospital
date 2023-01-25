package top.integer.yygh.cmn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.integer.yygh.model.cmn.Dict;

import java.util.List;

@Mapper
public interface DictMapper extends BaseMapper<Dict> {
    @Select("select any_value(d.id) id,\n" +
            "       any_value(d.parent_id) parent_id,\n" +
            "       any_value(d.name) name,\n" +
            "       any_value(d.value) value,\n" +
            "       any_value(d.dict_code) dict_code,\n" +
            "       any_value(d.create_time) create_time,\n" +
            "       any_value(d.update_time) update_time,\n" +
            "       any_value(d.is_deleted) is_delete,\n" +
            "       count(d2.parent_id) > 0 has_children\n" +
            "from dict d\n" +
            "         left join dict d2 on d.id = d2.parent_id\n" +
            "where d.parent_id = #{id}\n" +
            "group by ifnull(d2.parent_id, d.id)")
    List<Dict> getAllByIdDictId(Long id);

    @Select("select any_value(d.id) id,\n" +
            "       any_value(d.parent_id) parent_id,\n" +
            "       any_value(d.name) name,\n" +
            "       any_value(d.value) value,\n" +
            "       any_value(d.dict_code) dict_code,\n" +
            "       any_value(d.create_time) create_time,\n" +
            "       any_value(d.update_time) update_time,\n" +
            "       any_value(d.is_deleted) is_delete,\n" +
            "       count(d2.parent_id) > 0 has_children\n" +
            "from dict d\n" +
            "         left join dict d2 on d.id = d2.parent_id\n" +
            "group by ifnull(d2.parent_id, d.id)")
    List<Dict> getAll();

    int insertBatch(List<Dict> list);
}
