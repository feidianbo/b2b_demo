package demo.core.persistence;

import demo.core.domain.FileRecord;
import demo.core.domain.UploadFileByUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * Created by joe on 2/8/15.
 */
public interface FileRecordMapper {
    @Insert("insert into filerecords (filepath, tablename, tableid, createtime) values (#{filepath}, #{tablename}, #{tableid}, now())")
    @Options(useGeneratedKeys=true)
    public void insertRecord(FileRecord record);
}
