package demo.core.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.pegdown.PegDownProcessor;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by joe on 1/11/15.
 */
public class PDF {
    private static PegDownProcessor processor = new PegDownProcessor();
    public static String markdownToHtml(String markdown){
        return processor.markdownToHtml(markdown);
    }
    public static File create(String markdown) throws IOException, DocumentException {
        String html="<html><head><style> body {font-family: SimSun;}</style></head><body>"+
                markdownToHtml(markdown) +
                "</body></html>";
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(html);
        renderer.layout();
        File file=File.createTempFile("pdf", "pdf");
        renderer.createPDF(new FileOutputStream(file));
        return file;
    }
}
