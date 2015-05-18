package demo.site.service;


import demo.core.domain.FileRecord;
import demo.core.domain.UploadFileByUser;
import demo.core.persistence.FileRecordMapper;
import demo.core.persistence.UploadFileByUserMapper;
import demo.core.service.FileStore;
import demo.site.basic.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by joe on 2/8/15.
 */
@Component
public class FileService {
    @Autowired
    protected FileStore fileStore;
    @Autowired
    protected Session session;
    @Autowired
    protected UploadFileByUserMapper uploadFileByUserMapper;
    @Autowired
    protected FileRecordMapper fileRecordMapper;

    public String uploadPicture(MultipartFile file) throws FileStore.UnsupportedContentType, IOException {
        if(session.isLogined()) {
            UploadFileByUser upload=new UploadFileByUser(session.getUser().getId(), fileStore.uploadBy(file, fileStore.getPictureSuffix(file)));
            uploadFileByUserMapper.insertUpload(upload);
            fileRecordMapper.insertRecord(new FileRecord(upload.getFilepath(), UploadFileByUser.tablename, upload.getId()));
            return upload.getFilepath();
        }
        throw new UnauthorizedException();
    }
    public File getDownloadFileByFileName(String filename) throws Exception {
        return fileStore.getDownloadFileByFileName(filename);
    }
    public File copyToDownload(File file, String filename) throws Exception {
        return fileStore.copyToDownload(file, filename);
    }

    public String uploadIndexPic(MultipartFile file) throws FileStore.UnsupportedContentType, IOException {
        if(session.isLogined()) {
            UploadFileByUser upload=new UploadFileByUser(session.getUser().getId(), fileStore.uploadBy(file, fileStore.getPictureSuffix(file)));
            uploadFileByUserMapper.insertUpload(upload);
            fileRecordMapper.insertRecord(new FileRecord(upload.getFilepath(), UploadFileByUser.tablename, upload.getId()));
            return upload.getFilepath();
        }
        throw new UnauthorizedException();
    }
}
