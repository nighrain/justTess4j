package com.ocr.OtherDemo;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Tess4J {

    public static String executeTess4J(String imgUrl){
        String ocrResult = "";
        try{
            ITesseract instance = new Tesseract();
            instance.setDatapath("D:\\myWork\\demo_wSpace\\justSdo\\OcrImage-master\\");
            //instance.setLanguage("chi_sim");
            File imgDir = new File(imgUrl);
            //long startTime = System.currentTimeMillis(); Tesseract.doOCR
            ocrResult = instance.doOCR(imgDir);
        }catch (TesseractException e){
            e.printStackTrace();
        }
        return ocrResult;
    }

}
