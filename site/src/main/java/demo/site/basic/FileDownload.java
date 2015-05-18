package demo.site.basic;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by jack on 14/12/26.
 */
public class FileDownload {
    public static void doDownloadFile(File file, HttpServletResponse response) throws IOException {
        try {
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
