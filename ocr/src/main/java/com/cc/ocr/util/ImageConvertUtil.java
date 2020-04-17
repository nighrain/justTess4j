package com.cc.ocr.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *    
 * Title         [图片转换工具类]
 * Author:       [..]
 * CreateDate:   [2019-11-06]  @_@ ~~
 * Version:      [v1.0]
 * Description:  [..]
 * <p></p>
 *  
 */
public class ImageConvertUtil {

    //将背景色变透明
    public static BufferedImage convert2RemoveBackground(BufferedImage source) {
        int w = source.getWidth();
        int h = source.getHeight();
        ImageIcon imageIcon_s = new ImageIcon(source);

        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
        g2d.drawImage(imageIcon_s.getImage(), 0, 0, imageIcon_s.getImageObserver());
        int alpha = 0;
        for (int i = bufferedImage.getMinX(); i < w; i++) {
            for (int j = bufferedImage.getMinY(); j < h; j++) {
                int rgb = bufferedImage.getRGB(i, j);
                //以背景色左上角最小像素做参考系
                int RGB = bufferedImage.getRGB(bufferedImage.getMinX(), bufferedImage.getMinY());
                int r = (rgb & 0xff0000) >> 16;
                int g = (rgb & 0xff00) >> 8;
                int b = (rgb & 0xff);
                int R = (RGB & 0xff0000) >> 16;
                int G = (RGB & 0xff00) >> 8;
                int B = (RGB & 0xff);
                //a为色差范围值，渐变色边缘处理，数值需要具体测试，50左右的效果比较可以  def  a = 45
                int a = 45;
                if (Math.abs(R - r) < a && Math.abs(G - g) < a && Math.abs(B - b) < a) {
                    alpha = 0;
                } else alpha = 255;
                rgb = (alpha << 24) | (rgb & 0x00ffffff);
                bufferedImage.setRGB(i, j, rgb);
            }
        }
//        g2d.drawImage(bufferedImage, 0, 0, imageIcon_s.getImageObserver());
        g2d.dispose();
        return bufferedImage;
    }

    //指定某种单色为透明
    public static BufferedImage convert2RemoveColor(BufferedImage source, Color color) {
        int w = source.getWidth();
        int h = source.getHeight();
        ImageIcon imageIcon_s = new ImageIcon(source);
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
        g2d.drawImage(imageIcon_s.getImage(), 0, 0, imageIcon_s.getImageObserver());
        int alpha = 0;
        for (int i = bufferedImage.getMinX(); i < w; i++) {
            for (int j = bufferedImage.getMinY(); j < h; j++) {
                int rgb = bufferedImage.getRGB(i, j);
                //以白色为例
                //int RGB = Color.WHITE.getRGB();
                int RGB = color.getRGB();
                int r = (rgb & 0xff0000) >> 16;
                int g = (rgb & 0xff00) >> 8;
                int b = (rgb & 0xff);
                int R = (RGB & 0xff0000) >> 16;
                int G = (RGB & 0xff00) >> 8;
                int B = (RGB & 0xff);
                int a = 15;
                if (Math.abs(R - r) < a && Math.abs(G - g) < a && Math.abs(B - b) < a) {
                    alpha = 0;
                } else
                    alpha = 255;
                rgb = (alpha << 24) | (rgb & 0x00ffffff);
                bufferedImage.setRGB(i, j, rgb);
            }
        }
//        g2d.drawImage(bufferedImage, 0, 0, imageIcon_s.getImageObserver());
        g2d.dispose();
        return bufferedImage;
    }

    //指定某种颜色替换成另一种
    public static BufferedImage convert2Color2Color(BufferedImage source, Color colorSrc, Color colorTar) {
        int w = source.getWidth();
        int h = source.getHeight();
        int minx = source.getMinTileX();
        int miny = source.getMinTileY();
        ImageIcon imageIcon_s = new ImageIcon(source);
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
        g2d.drawImage(imageIcon_s.getImage(), 0, 0, imageIcon_s.getImageObserver());

        for (int i = minx; i < w; i++) {
            for (int j = miny; j < h; j++) {
                int rgb = bufferedImage.getRGB(i, j);
                //以黑色为例
                //int RGB = Color.BLACK.getRGB();
                int RGB = colorSrc.getRGB();
                int r = (rgb & 0xff0000) >> 16;
                int g = (rgb & 0xff00) >> 8;
                int b = (rgb & 0xff);
                int R = (RGB & 0xff0000) >> 16;
                int G = (RGB & 0xff00) >> 8;
                int B = (RGB & 0xff);
                int a = 35;
                if (Math.abs(R - r) < a && Math.abs(G - g) < a && Math.abs(B - b) < a) {
                    //0xff0000是红色的十六进制代码
                    bufferedImage.setRGB(i, j, colorTar.getRGB());
                }
            }
        }
        return bufferedImage;
    }

    //去除/保留 一些矩形
    public static BufferedImage convert2RemoveRects(BufferedImage source, Color fillColor, boolean isHoldRect, Collection<Rectangle> rectangles) {
        int w = source.getWidth();
        int h = source.getHeight();
        int minx = source.getMinTileX();
        int miny = source.getMinTileY();
//        ImageIcon imageIcon_s = new ImageIcon(source);
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(source, 0, 0, w, h, null);

        for (int i = minx; i < w; i++) {
            for (int j = miny; j < h; j++) {
                boolean isIn = xyInRect(i, j, rectangles);
                if (isHoldRect != isIn) {
                    //空白
                    bufferedImage.setRGB(i, j, fillColor.getRGB());
                }
            }
        }
        return bufferedImage;
    }

    //去除/保留 一些矩形(比例形式)
    public static BufferedImage convert2RemoveRectsWithPercent(BufferedImage source, Color fillColor, boolean isHoldRect, Collection<Double[]> percentDoubles) {
        int w = source.getWidth();
        int h = source.getHeight();
        int minx = source.getMinTileX();
        int miny = source.getMinTileY();
//        ImageIcon imageIcon_s = new ImageIcon(source);
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(source, 0, 0, w, h, null);

        for (int i = minx; i < w; i++) {
            for (int j = miny; j < h; j++) {
//                boolean isIn = xyInRectScale(w, h, i, j, percentS);
                boolean isIn = xyInRectPercent(w, h, i, j, percentDoubles);
                if (isHoldRect != isIn) {
                    //空白
                    bufferedImage.setRGB(i, j, fillColor.getRGB());
                }
            }
        }
        return bufferedImage;
    }

    private static boolean xyInRectPercent(int totalWidth, int totalHeight, int x, int y, Collection<Double[]> percentDoubles) {
        //计算xy 是否在矩形内(比例形式)
        for (Double[] array : percentDoubles) {
            if (array[0] > 1 || array[1] > 1 || array[2] > 1 || array[3] > 1) {
                throw new RuntimeException("比例系数不能大于1");
            }
            if (array[0] + array[2] > 1 || array[1] + array[3] > 1) {
                throw new RuntimeException("同维的比例系数之和不能大于1");
            }
            Rectangle rect = new Rectangle((int) (array[0] * totalWidth), (int) (array[1] * totalHeight), (int) (array[2] * totalWidth), (int) (array[3] * totalHeight));
            if (rect.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    private static boolean xyInRect(int x, int y, Collection<Rectangle> rectangles) {
        //计算xy 是否在矩形内 左边/上边  true  右边/下边 false
//        Rectangle rectangle = new Rectangle(10, 10, 5, 5);
//        System.out.println("上"+rectangle.contains(12, 10));//上true
//        System.out.println("右"+rectangle.contains(15, 12));//右false
//        System.out.println("下"+rectangle.contains(12, 15));//下false
//        System.out.println("左"+rectangle.contains(10, 12));//左true
        for (Rectangle rect : rectangles) {
            if (rect.isEmpty()) {
                continue;
            }
            if (rect.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    public static BufferedImage removeBackground(BufferedImage source) {
        int w = source.getWidth();
        int h = source.getHeight();
        ImageIcon imageIcon_s = new ImageIcon(source);

        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = (Graphics2D) result.getGraphics();
        g2d.drawImage(imageIcon_s.getImage(), 0, 0, imageIcon_s.getImageObserver());

        //定义一个临界阈值
        int threshold = 300;

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                Color color = new Color(result.getRGB(x, y));
                int num = color.getRed() + color.getGreen() + color.getBlue();
                if (num >= threshold) {
                    result.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }

        for (int i = 1; i < w; i++) {
            Color color1 = new Color(result.getRGB(i, 1));
            int num1 = color1.getRed() + color1.getGreen() + color1.getBlue();
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    Color color = new Color(result.getRGB(x, y));

                    int num = color.getRed() + color.getGreen() + color.getBlue();
                    if (num == num1) {
                        result.setRGB(x, y, Color.BLACK.getRGB());
                    } else {
                        result.setRGB(x, y, Color.WHITE.getRGB());
                    }
                }
            }
        }
//        File file = new File("C:\\Users\\EDZ\\Desktop\\c1.png");
//        if (!file.exists()) {
//            File dir = file.getParentFile();
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        ImageIO.write(result, "png", file);
        return result;
    }
    
    public static void main(String[] args) throws IOException {

        /**获得桌面*/
        String home = System.getProperty("user.home");
        BufferedImage image = ImageIO.read(new File(home, "Desktop\\3.png")); // 1024 * 768

        /**三种基本转换*/
        BufferedImage bufferedImage1 = convert2RemoveBackground(image);
        BufferedImage bufferedImage2 = convert2RemoveColor(image,new Color(49, 54, 8));
        BufferedImage bufferedImage3 = convert2Color2Color(image,Color.BLACK,Color.RED);
        ImageIO.write(bufferedImage1, "png", new File(home,"Desktop\\out1.png"));
        ImageIO.write(bufferedImage2, "png", new File(home,"Desktop\\out2.png"));
        ImageIO.write(bufferedImage3, "png", new File(home,"Desktop\\out3.png"));


        /**矩形的操作*/
        ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        rectangles.add(new Rectangle(10, 10, 200, 50));
        rectangles.add(new Rectangle(200, 200, 50, 50));
        rectangles.add(new Rectangle(300, 500, 600, 75));
        BufferedImage bufferedImage4 = convert2RemoveRects(image, Color.LIGHT_GRAY, true, rectangles);
        ImageIO.write(bufferedImage4, "png", new File(home, "Desktop\\out4.png"));

        /**矩形的操作(比例)*/
        ArrayList<Double[]> doubles = new ArrayList<Double[]>();
        doubles.add(new Double[]{0.1, 0.1, 0.3, 0.3});
        doubles.add(new Double[]{0.3, 0.3, 0.5, 0.2});
        doubles.add(new Double[]{0.1, 0.7, 0.8, 0.2});
        BufferedImage bufferedImage5 = convert2RemoveRectsWithPercent(image, Color.LIGHT_GRAY, true, doubles);
        ImageIO.write(bufferedImage5, "png", new File(home, "Desktop\\out5.png"));

    }

}
