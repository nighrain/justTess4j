package com.cc.ocr.recycled;


import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

//https://segmentfault.com/a/1190000020780714
//https://segmentfault.com/a/1190000020786645

public class ImageUtil {

    //1 ： 通过selemium获取页面验证码元素
    /*
WebElement element = SeleniumUtil.findElement(driver,szsbEnum);
return new ByteArrayInputStream(((TakesScreenshot) element).getScreenshotAs(OutputType.BYTES));
     */


//    //2 ：对验证码图片进行处理
//    public BufferedImage cleanLinesInImage(BufferedImage oriBufferedImage)  throws IOException {
//        BufferedImage bufferedImage = oriBufferedImage;
//        int h = bufferedImage.getHeight();
//        int w = bufferedImage.getWidth();
//
//        // 灰度化
//        int[][] gray = new int[w][h];
//        for (int x = 0; x < w; x++)
//        {
//            for (int y = 0; y < h; y++)
//            {
//                int argb = bufferedImage.getRGB(x, y);
//                // 图像加亮（调整亮度识别率非常高）
//                int r = (int) (((argb >> 16) & 0xFF) * 1.1 + 30);
//                int g = (int) (((argb >> 8) & 0xFF) * 1.1 + 30);
//                int b = (int) (((argb >> 0) & 0xFF) * 1.1 + 30);
//                if (r >= 255)
//                {
//                    r = 255;
//                }
//                if (g >= 255)
//                {
//                    g = 255;
//                }
//                if (b >= 255)
//                {
//                    b = 255;
//                }
//                gray[x][y] = (int) Math
//                        .pow((Math.pow(r, 2.2) * 0.2973 + Math.pow(g, 2.2)
//                                * 0.6274 + Math.pow(b, 2.2) * 0.0753), 1 / 2.2);
//            }
//        }
//
//        // 二值化
//        int threshold = ostu(gray, w, h);
//        BufferedImage binaryBufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);
//        for (int x = 0; x < w; x++)
//        {
//            for (int y = 0; y < h; y++)
//            {
//                if (gray[x][y] > threshold)
//                {
//                    gray[x][y] |= 0x00FFFF;
//                } else
//                {
//                    gray[x][y] &= 0xFF0000;
//                }
//                binaryBufferedImage.setRGB(x, y, gray[x][y]);
//            }
//        }
//        File file = FileUtil.file(IdUtil.fastUUID()+".png");
//        ImageIO.write(binaryBufferedImage,"png",file);
//
//        //这里开始是利用opencv的api进行处理
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        Mat imread = Imgcodecs.imread(file.getAbsolutePath());
//        Mat target = new Mat();
////        Core.bitwise_not(imread,target);
//        Mat kelner = Imgproc.getStructuringElement(MORPH_RECT, new Size(3, 3), new Point(-1, -1));
//
//        //膨胀
//        Imgproc.dilate(imread,target,kelner);
//        //腐蚀
//        Imgproc.erode(target,target,kelner);
//        oriBufferedImage = mat2BufImg(target,".png");
//        file.delete();
//        return oriBufferedImage;
//    }
//
//    //3 ：调用OCR进行识别
//    public String getAuthCodeByOpencv(WebDriver driver, SeleniumUtil.SzsbEnum szsbEnum){
//        String code = "";
//        //获取网页验证码图片
//        try (InputStream inputStream = AuthCodeScreenShotUtil.getAuthCodeImageById(driver, szsbEnum)) {
//
//            try {
//                code = tesseract.doOCR(ImageCleanPlanOpencv.INSTANCE.clean(ImageIO.read(inputStream)));
//            } catch (TesseractException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            logger.warn(e.getMessage());
//        }
//        //当前验证码是数字类型 直接去除数字以外所有值
//        code = code.replaceAll("\\W", "");
//        return code;
//    }
}
