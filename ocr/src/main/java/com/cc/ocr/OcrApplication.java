package com.cc.ocr;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OcrApplication {

    public static void main(String[] args) {
        SpringApplication.run(OcrApplication.class, args);
    }

    @Bean
    public Tesseract getTesseract(){
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("D:\\myWork\\demo_wSpace\\justTess4j\\ocr\\tessdata");
        tesseract.setTessVariable("enable_new_segsearch", "0");
        tesseract.setTessVariable("tessedit_char_whitelist", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        return tesseract;
    }

}
