package com.cc.ocr;


import com.cc.ocr.util.ImageBinary;
import com.cc.ocr.util.ImageConvertUtil;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws TesseractException, IOException {
        String s1 = "C:\\Users\\EDZ\\Desktop\\c.png";
//        String s2 = "C:\\Users\\EDZ\\Desktop\\c1.png";
        String out = "C:\\Users\\EDZ\\Desktop\\c1out.png";
        String s3 = "C:\\Users\\EDZ\\Desktop\\a.jpg";
//        ImgUtils.removeBackground(s1, s2);


        BufferedImage bufferedImage1 = ImageConvertUtil.convert2RemoveBackground(ImageIO.read(new File(s1)));
        BufferedImage bufferedImage2 = ImageConvertUtil.removeBackground(ImageIO.read(new File(s1)));

        BufferedImage binaryImg = ImageBinary.getBinaryImg(ImageIO.read(new File(s1)));

        ImageIO.write(binaryImg, "png", new File(out));

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("D:\\myWork\\demo_wSpace\\justTess4j\\ocr\\tessdata");
        tesseract.setTessVariable("enable_new_segsearch", "0");
        tesseract.setTessVariable("tessedit_char_whitelist", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        //instance.setLanguage("chi_sim");
//        File imgDir = new File(s2);
        //long startTime = System.currentTimeMillis(); Tesseract.doOCR
//        String s = tesseract.doOCR(binaryImg);
//        System.out.println(s);
//        System.out.println(tesseract.doOCR(bufferedImage1));
//        System.out.println(tesseract.doOCR(bufferedImage2));
        System.out.println(tesseract.doOCR(ImageIO.read(new File(s3))));
        System.out.println(tesseract.doOCR(ImageIO.read(new File(s1))));

    }


}
