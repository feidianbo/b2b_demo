package demo.core.service;


import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by fanjun on 14-11-17
 */
public class FileStore {
    protected final String filePath;
    protected final File fileStoreDir;
    protected final File uploadDir;
    protected final File downloadDir;
    protected final File appDir;
    protected final File androidAppDir;
    public FileStore(String filePath){
        this.filePath=filePath;
        fileStoreDir = new File(filePath);
        if (!fileStoreDir.exists())
            fileStoreDir.mkdirs();
        uploadDir=new File(fileStoreDir, "upload");
        if(!uploadDir.exists())
            uploadDir.mkdir();
        downloadDir=new File(fileStoreDir, "download");
        if(!downloadDir.exists())
            downloadDir.mkdir();
        appDir = new File(fileStoreDir,"app");
        if(!appDir.exists())
            appDir.mkdir(); //app文件夹建android文件夹,为如果有苹果app预留空间
        androidAppDir = new File(appDir,"androidApp");
        androidAppDir.mkdir();
    }


    public String getFilePath() {
        return filePath;
    }

    public String uploadBy(MultipartFile file, String suffix) throws IOException {
        String filename = Hex.encodeHexString(DigestUtils.md5(file.getInputStream()))+"."+suffix;
        String filePath="/files/upload/"+filename;
        File target=new File(uploadDir, filename);
        if(!target.exists()){
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(target));
        }
        return filePath;
    }
    protected String getSuffixByContentType(String contentType){
        //兼容IE8文件上传
        if("image/pjpeg".equals(contentType)){
            contentType="image/jpeg";
        } else if("image/x-png".equals(contentType)){
            contentType="image/png";
        }
        try {
            MimeType type=MimeTypes.getDefaultMimeTypes().getRegisteredMimeType(contentType);
            if(type!=null)
                return type.getExtension();
        } catch (MimeTypeException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static class UnsupportedContentType extends Exception{}
    protected static List<String> pictureSuffixes= Arrays.asList(new String[]{"png", "jpg", "bmp", "gif", "pdf"});
    public String getPictureSuffix(MultipartFile file) throws UnsupportedContentType {
        String suffix=getSuffixByContentType(file.getContentType());
        if(suffix!=null)
            suffix = suffix.substring(1);
        if(suffix.equals("bmp")){
            suffix = "jpg";
        }
        if(suffix==null || !pictureSuffixes.contains(suffix))
            throw new UnsupportedContentType();
        return suffix;
    }
    public File getFileByFilePath(String filePath) throws Exception {
        if(filePath!=null &&filePath.startsWith("/files/")){
            return new File(fileStoreDir, filePath.substring("/files/".length()));
        }
        throw new Exception("file path is not right");
    }
    public String getDownloadFilePathByFileName(String filename){
        return "/files/download/"+filename;
    }
    public File getDownloadFileByFileName(String filename) throws Exception {
       return getFileByFilePath(getDownloadFilePathByFileName(filename));
    }
    public File copyToDownload(File file, String filename) throws Exception {
        File target=new File(downloadDir, filename);
        if(target.exists())
            throw new Exception("file exists");
        FileCopyUtils.copy(file, target);
        return target;
    }

    //上传首页图片,不需要MD5加密
    public String uploadIndexPicBy(MultipartFile file, String fileOriginName,String suffix) throws IOException {
        String filename = fileOriginName + "_" + new Date().getTime()+"."+suffix;
        String filePath="/files/upload/"+filename;
        File target=new File(uploadDir, filename);
        if(!target.exists()){
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(target));
        }
        return filePath;
    }

    //上传apk文件.app文件夹建android文件夹,为如果有苹果app预留空间
    public String uploadAppBy(MultipartFile file, String filename,String appType) throws IOException {
        File target = null;
        String filePath = null;
        if(appType.equals("android")) {
            String[] childrenFileArr = androidAppDir.list();
            for (int i = 0; i < childrenFileArr.length; i++) { //删除文件夹下的旧版本
                File childrenFile = new File("../files/app/androidApp/" + childrenFileArr[i]);
                childrenFile.delete();
            }

            filePath = "/files/app/androidApp/" + filename;
            target = new File(androidAppDir, filename);
        }
        if(!target.exists()){
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(target));
        }
        return filePath;
    }

}



