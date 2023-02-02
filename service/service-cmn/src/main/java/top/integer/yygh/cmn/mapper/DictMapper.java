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


    @Select("select d1.id id, d1.parent_id parent, d1.name name, d1.dict_code dict_code, d1.create_time create_time, d1.update_time update_time, d1.is_deleted is_delete, 0 has_children from dict d1 join dict d2 on d1.parent_id = d2.id where d1.value = #{value} and d2.dict_code = #{dictCode}")
    Dict getDict(String value, String dictCode);
}
