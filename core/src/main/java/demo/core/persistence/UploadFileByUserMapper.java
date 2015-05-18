package demo.core.persistence;

import demo.core.domain.UploadFileByUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

/**
 * Created by joe on 2/8/15.
 */
public interface UploadFileByUserMapper {
    @Insert("insert into uploadfilebyuser (userid, filepath, createtime) values (#{userid}, #{filepath}, now())")
    @Options(useGeneratedKeys=true)
    public void insertUpload(UploadFileByUser upload);

}
