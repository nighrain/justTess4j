package com.cc.ocr.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *    
 * Title         [title]
 * Author:       [..]
 * CreateDate:   [2019-11-21--17:43]  @_@ ~~
 * Version:      [v1.0]
 * Description:  [..]
 * <p></p>
 *  
 */
public class ImageBinary {
    //https://www.cnblogs.com/gxclmx/p/6916515.html
    /*
二值化基本概念：通俗的讲就是把一副彩色图像处理成一副黑白图像，一般是作为后续复杂图像处理操作的预处理。
二值化算法思路：遍历图像的所有像素点，计算每个像素点的灰度值。
    通过迭代法收敛得到一个最佳阈值，灰度值大于最佳阈值的像素点设为白色，灰度值小于最佳阈值的像素点设为黑色。
    （我这里的二值化处理结果是，背景是白色，前景是黑色）
迭代法获取最佳阈值思路：
    1.设最小灰度值为Gmin，最大灰度值为Gmax，阈值初始化为T(0)=(Gmin+Gmax)/2。
    2.以阈值T(k)将图像分割为前景和背景，求出整个前景像素的平均灰度值Gf和整个背景像素的平均灰度值Gb，此时阈值T(k)=(Gf+Gb)/2 (k=0,1,2...);
    3.若此时T(k)=T(k+1)，那么此时收敛，得到最佳阈值。否则回到步骤2，直到阈值收敛到某一个值。
     */
    public static int[] getBinaryImg(int w, int h, int[] inputs) {
        int[] gray = new int[w * h];
        int[] newpixel = new int[w * h];
        for (int index = 0; index < w * h; index++) {
            int red = (inputs[index] & 0x00FF0000) >> 16;
            int green = (inputs[index] & 0x0000FF00) >> 8;
            int blue = inputs[index] & 0x000000FF;
            gray[index] = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
        }
        //求出最大灰度值zmax和最小灰度值zmin
        int Gmax = gray[0], Gmin = gray[0];
        for (int index = 0; index < w * h; index++) {
            if (gray[index] > Gmax) {
                Gmax = gray[index];
            }
            if (gray[index] < Gmin) {
                Gmin = gray[index];
            }
        }

        //获取灰度直方图
        int i, j, t, count1 = 0, count2 = 0, sum1 = 0, sum2 = 0;
        int bp, fp;
        int[] histogram = new int[256];
        for (t = Gmin; t <= Gmax; t++) {
            for (int index = 0; index < w * h; index++) {
                if (gray[index] == t)
                    histogram[t]++;
            }
        }

        /*
         * 迭代法求出最佳分割阈值
         * */
        int T = 0;
        int newT = (Gmax + Gmin) / 2;//初始阈值
        //求出背景和前景的平均灰度值bp和fp
        while (T != newT) {
            for (i = 0; i < T; i++) {
                count1 += histogram[i];//背景像素点的总个数
                sum1 += histogram[i] * i;//背景像素点的灰度总值
            }
            bp = (count1 == 0) ? 0 : (sum1 / count1);//背景像素点的平均灰度值

            for (j = i; j < histogram.length; j++) {
                count2 += histogram[j];//前景像素点的总个数
                sum2 += histogram[j] * j;//前景像素点的灰度总值
            }
            fp = (count2 == 0) ? 0 : (sum2 / count2);//前景像素点的平均灰度值
            T = newT;
            newT = (bp + fp) / 2;
        }
        int finestYzt = newT; //最佳阈值

        //二值化
        for (int index = 0; index < w * h; index++) {
            if (gray[index] > finestYzt) {
                newpixel[index] = Color.WHITE.getRGB();
            } else {
                newpixel[index] = Color.BLACK.getRGB();
            }
        }
        return newpixel;
    }


    public static BufferedImage getBinaryImg(BufferedImage source){
        int w = source.getWidth();
        int h = source.getHeight();

        int[] a = source.getRGB(0, 0, w, h, null, 0, w);
        int[] binaryImg = ImageBinary.getBinaryImg(w, h, a);

        BufferedImage result = new BufferedImage(w, h, source.getType());
        result.setRGB(0, 0, w, h, binaryImg, 0, w);
        return result;
    }
}
