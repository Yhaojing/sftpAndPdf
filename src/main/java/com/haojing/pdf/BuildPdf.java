package com.haojing.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hnzb on 16/1/7.
 */
public class BuildPdf {
    public static final String SRC = "/Users/hnzb/IdeaProjects/somethingaboutjava/src/main/resources/template.pdf";//模板地址

    public static final String DESTDIR = "/Users/hnzb/IdeaProjects/somethingaboutjava/src/main/resources/test.pdf";//结果存放地址

    public static final String TTF = "/Users/hnzb/IdeaProjects/somethingaboutjava/src/main/resources/simsun.ttf";//字体地址

    public void buildPdf(String name, String date) throws IOException, DocumentException {
        String []form = {"name", "date"};//pdf表单自己设置的名称
        PdfReader reader = new PdfReader(SRC);
        PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(DESTDIR));
        //设置中文字体
        BaseFont baseFont = BaseFont.createFont(TTF, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        BaseFont chinese = new Font(baseFont, 12, Font.NORMAL).getBaseFont();
        //time roman
        BaseFont roman9 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9f, Font.NORMAL).getBaseFont();

        AcroFields fields = pdfStamper.getAcroFields();
        fields.setFieldProperty(form[0], "textfont", chinese, null);
        fields.setFieldProperty(form[1], "textfont", roman9,  null);
        fields.setField(form[0], name);
        fields.setField(form[1], date);
        pdfStamper.setFormFlattening(true);
        pdfStamper.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException, DocumentException {
        BuildPdf b = new BuildPdf();
        b.buildPdf("郝晶", "2016/01/00");
    }

}
