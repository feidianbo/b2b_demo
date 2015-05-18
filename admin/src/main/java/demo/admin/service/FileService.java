package demo.admin.service;

import demo.admin.basic.exception.UnauthorizedException;
import demo.core.domain.FileRecord;
import demo.core.domain.UploadFileByAdmin;
import demo.core.persistence.FileRecordMapper;
import demo.core.persistence.UploadFileByAdminMapper;
import demo.core.service.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by joe on 2/8/15.
 */
@Component
public class FileService {
    @Autowired
    protected FileStore fileStore;
    @Autowired
    protected UploadFileByAdminMapper uploadFileByAdminMapper;
    @Autowired
    protected FileRecordMapper fileRecordMapper;
    @Autowired
    protected Session session;

    public String uploadPicture(MultipartFile file) throws FileStore.UnsupportedContentType, IOException {
        if(session.isLogined()) {
            UploadFileByAdmin upload=new UploadFileByAdmin(session.getAdmin().getId(), fileStore.uploadBy(file, fileStore.getPictureSuffix(file)));
            uploadFileByAdminMapper.insertUpload(upload);
            fileRecordMapper.insertRecord(new FileRecord(upload.getFilepath(),UploadFileByAdmin.tablename, upload.getId()));
            return upload.getFilepath();
        }
        throw new UnauthorizedException();
    }

    //上传首页banner图
    public String uploadIndexPicture(MultipartFile file,String fileOriginName) throws FileStore.UnsupportedContentType, IOException {
        if(session.isLogined()) {
            UploadFileByAdmin upload=new UploadFileByAdmin(session.getAdmin().getId(), fileStore.uploadIndexPicBy(file, fileOriginName,fileStore.getPictureSuffix(file)));
            uploadFileByAdminMapper.insertUpload(upload);
            fileRecordMapper.insertRecord(new FileRecord(upload.getFilepath(),UploadFileByAdmin.tablename, upload.getId()));
            return upload.getFilepath();
        }
        throw new UnauthorizedException();
    }

    //上传手机app文件
    public String uploadApp(MultipartFile file,String filename,String appType) throws FileStore.UnsupportedContentType, IOException {
        if(session.isLogined()) {
            UploadFileByAdmin upload=new UploadFileByAdmin(session.getAdmin().getId(), fileStore.uploadAppBy(file, filename,appType));
            uploadFileByAdminMapper.insertUpload(upload);
            fileRecordMapper.insertRecord(new FileRecord(upload.getFilepath(),UploadFileByAdmin.tablename, upload.getId()));
            return upload.getFilepath();
        }
        throw new UnauthorizedException();
    }
}


