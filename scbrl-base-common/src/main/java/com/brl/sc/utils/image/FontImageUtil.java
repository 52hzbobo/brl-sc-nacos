package com.brl.sc.utils.image;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 文字生成图片
 */
public class FontImageUtil {


    /**
     *  将文字生成输出流
     * @param charactersStr	字符串
     * @param font	字体
     * @param imgType	文件类型
     * @param width	宽度
     * @param height	高度
     * @throws Exception
     */
    public static  void createImageToOutputStream(String charactersStr, Font font,  Integer width, Integer height,String imgType,ByteArrayOutputStream bs) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        g.setColor(new Color(99,184,255));
        // 先用黑色填充整张图片,也就是背景
        g.fillRect(0, 0, width, height);
        // 在换成白
        g.setColor(Color.WHITE);
        // 设置画笔字体
        g.setFont(font);
        /** 用于获得垂直居中y */
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(font);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        int charaWidth = fm.stringWidth(charactersStr);
        int x = (width - charaWidth) / 2;
        g.drawString(charactersStr, x, y);
        g.dispose();
        ImageOutputStream imgOut=ImageIO.createImageOutputStream(bs);
        ImageIO.write(image, imgType, imgOut);
    }

    /**
     * 将文字生成BufferedImage对象
     * @param charactersStr	字符串
     * @param font	字体
     * @param imgType	文件类型
     * @param width	宽度
     * @param height	高度
     * @throws Exception
     */
    public static  BufferedImage createBufferedImage(String charactersStr, Font font,  Integer width, Integer height,String imgType) throws Exception {
        ByteArrayOutputStream bs =new ByteArrayOutputStream();
        createImageToOutputStream(charactersStr, font,
                 width, height,imgType,bs);
        InputStream is=new ByteArrayInputStream(bs.toByteArray());
        BufferedImage img=ImageIO.read(is);
        return img;
    }


    /**
     * 根据str,font的样式以及输出文件目录
     * @param charactersStr	字符串
     * @param font	字体
     * @param outFilePath	输出文件位置
     * @param width	宽度
     * @param height	高度
     * @throws Exception
     */
    public static void createImageToFile(String charactersStr, Font font, Integer width, Integer height, String outFilePath) throws Exception {
        // 输出的图片格式
        String imgType=outFilePath.substring(outFilePath.lastIndexOf(".")+1);
        BufferedImage img=createBufferedImage(charactersStr, font, width, height, imgType);
        ImageIO.write(img, imgType, new File(outFilePath));
    }



    public static void main(String[] args) throws Exception {
        System.out.println("测试".charAt(0));
//        Font font=new Font("宋体", Font.BOLD, 30);
////        BufferedImage img=createBufferedImage("测试", font, 128, 128, "png");
//        createImageToFile("测", font, 128, 128, "D:/fsdafd.png");
    }

}
