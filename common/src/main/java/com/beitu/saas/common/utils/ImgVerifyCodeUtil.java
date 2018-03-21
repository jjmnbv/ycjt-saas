package com.beitu.saas.common.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImgVerifyCodeUtil {

    private static Random random = new Random();
    private static final String RAND_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串

    public static final int WIDTH = 86;// 图片宽
    public static final int HEIGHT = 36;// 图片高
    public static final int LINE_SIZE = 40;// 干扰线数量
    public static final int CODE_NUM = 4;// 随机产生字符数量

    /*
     * 获得字体
     */
    public static Font getFont() {
        return new Font("Fixedsys", Font.ROMAN_BASELINE, 20);
    }

    /*
     * 获得颜色
     */
    public static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc -fc - 16);
        int g = fc + random.nextInt(bc -fc - 14);
        int b = fc + random.nextInt(bc -fc - 18);
//        return new Color(r, g, b);
        return new Color(255, 255, 255);
    }

    /*
     * 绘制字符串
     */
    public static String drowString(Graphics g) {
        String strCode = "";
        for (int i = 0; i < CODE_NUM; i++) {
            String rand = String.valueOf(String.valueOf(RAND_STRING.charAt(random.nextInt(RAND_STRING
                    .length()))));
            strCode = strCode + rand;
            g.setColor(new Color(80 , 80, 255));
            g.drawString(rand, 13 * i + 15, 25);
        }
        return strCode;
    }

    /*
     * 绘制干扰线
     */
    public static void drowLine(Graphics g) {
        for (int i = 0; i <= 80; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int xl = random.nextInt(13);
            int yl = random.nextInt(15);
            g.drawLine(x, y, x + xl, y + yl);
        }
    }


    /**
     * 生成随机图片
     */
    public static BufferedImage getRandcode() {

        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        g.setColor(getRandColor(100, 200));
        g.setFont(getFont());
        g.fillRect(0, 0, WIDTH, HEIGHT);
        drowLine(g);
        drowString(g);

        return image;
    }

}