package demo.core.persistence;

import demo.core.domain.UploadFileByAdmin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by joe on 2/8/15.
 */
public interface UploadFileByAdminMapper {
    @Insert("insert into uploadfilebyadmin (adminid, filepath, createtime) values (#{adminid}, #{filepath}, now())")
    @Options(useGeneratedKeys=true)
    public void insertUpload(UploadFileByAdmin upload);
}
