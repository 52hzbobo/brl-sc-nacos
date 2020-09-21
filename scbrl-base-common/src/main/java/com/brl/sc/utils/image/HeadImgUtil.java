package com.brl.sc.utils.image;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 图标处理
 */
@Slf4j
public class HeadImgUtil {



    /**
     * 载入图片
     * @param imgPath
     * @return
     */
    public static BufferedImage loadImage(String imgPath) {
        try {
            if(imgPath.startsWith("http"))  return ImageIO.read(new URL(imgPath));
            return ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            log.error("读取图片出错{}",e);
        }
        return null;
    }




    /**
     * 按比例压缩单张图片
     * @param width
     * @param height
     * @param sourceImage
     * @return
     * @throws Exception
     */
    public static BufferedImage zoom2(int width,int height,BufferedImage sourceImage) {
        if( sourceImage == null ){
            return sourceImage;
        }
        // 计算x轴y轴缩放比例--如需等比例缩放，在调用之前确保參数width和height是等比例变化的
        double ratiox  = (new Integer(width)).doubleValue()/ sourceImage.getWidth();
        double ratioy  = (new Integer(height)).doubleValue()/ sourceImage.getHeight();
        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratiox, ratioy), null);
        BufferedImage bufImg = op.filter(sourceImage, null);
        return bufImg;
    }





    /**
     * 同比例压缩多张图片，使图片形成相同大小
     * @param width
     * @param height
     * @param image
     * @return
     */
    public static BufferedImage[] handleLarge(Integer width, Integer height, BufferedImage[] image) {
        if(width==null || width==0){
            width=88;
        }
        if(height==null || height==0){
            height=88;
        }
        BufferedImage[] imgs = new BufferedImage[image.length];
        if(image.length>=5){
            width=35;
            height=35;
        }
        for (int i = 0; i < image.length; i++) {
            BufferedImage sourceImage = image[i];
            try {
                imgs[i] = zoom2(width, height, sourceImage);
            } catch (Exception e) {
                log.error("推送同比压缩图片出错{}",e);
            }
        }
        return imgs;
    }



    /**
     * 合成图片
     * @param image
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage buildImagetogeter(BufferedImage[] image,Integer width,Integer height) {
        //默认为128*128的图片
        if(width==null || width==0)  width=128;
        if(height==null || height==0)  height=128;
        //创建一个的图片
        BufferedImage tag = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        try {
            Graphics2D graphics = tag.createGraphics();
            //设置颜色为218，223，224
            graphics.setColor(new Color(218,223,224));
            //填充颜色
            graphics.fillRect(0, 0, width, height);
            int count = image.length;
            //根据不同的合成图片数量设置图片放置位置
            if(count == 1){
                int startX = 45;
                int startY = 45;
                BufferedImage b = image[0];
                graphics.drawImage(b, startX, startY, b.getWidth(), b.getHeight(), null);
            }else if(count == 2){
                int startX = 12;
                int startY = 45;
                BufferedImage b1 = image[0];
                graphics.drawImage(b1, startX, startY, b1.getWidth(), b1.getHeight(), null);
                BufferedImage b2 = image[1];
                startX = startX + b1.getWidth()+8;
                graphics.drawImage(b2, startX, startY, b2.getWidth(), b2.getHeight(), null);
            }else if(count == 3){
                int startX = 55;
                int startY = 10;
                BufferedImage b1 = image[0];
                graphics.drawImage(b1, startX, startY, b1.getWidth(), b1.getHeight(), null);
                BufferedImage b2 = image[1];
                startX = 13;
                startY = 15 + b1.getHeight() + 5;
                graphics.drawImage(b2, startX, startY, b2.getWidth(), b2.getHeight(), null);
                BufferedImage b3 = image[2];
                startX = startX + b2.getWidth()+5;
                graphics.drawImage(b3, startX, startY, b3.getWidth(), b3.getHeight(), null);
            }else if(count == 4){
                int startX = 12;
                int startY = 12;
                BufferedImage b1 = image[0];
                graphics.drawImage(b1, startX, startY, b1.getWidth(), b1.getHeight(), null);
                BufferedImage b2 = image[1];
                startX = 12 + b1.getWidth() + 4;
                graphics.drawImage(b2, startX, startY, b2.getWidth(), b2.getHeight(), null);
                BufferedImage b3 = image[2];
                startX = 12;
                startY = 12 + b2.getHeight() + 4;
                graphics.drawImage(b3, startX, startY, b3.getWidth(), b3.getHeight(), null);
                BufferedImage b4 = image[3];
                startX = 12 + b3.getWidth() + 4;
                graphics.drawImage(b4, startX, startY, b4.getWidth(), b4.getHeight(), null);
            }else if(count == 5){
                int startX = 25;
                int startY = 25;
                BufferedImage b1 = image[0];
                graphics.drawImage(b1, startX, startY, b1.getWidth(), b1.getHeight(), null);
                BufferedImage b2 = image[1];
                startX = startX + b1.getWidth()+5;
                graphics.drawImage(b2, startX, startY, b2.getWidth(), b2.getHeight(), null);
                startX = 6;
                startY = 6 + startY + b2.getHeight();
                for(int i = 2;i<count;i++){
                    BufferedImage b = image[i];
                    graphics.drawImage(b, startX, startY, b.getWidth(), b.getHeight(), null);
                    startX = startX + b.getWidth() + 5;
                }
            }else if(count == 6){
                int startX = 6;
                int startY = 25;
                for(int i = 0;i<count;i++){
                    BufferedImage b = image[i];
                    graphics.drawImage(b, startX, startY, b.getWidth(), b.getHeight(), null);
                    startX = startX + b.getWidth() + 5;
                    if((i+1)%3 == 0){
                        startY = startY + b.getHeight() + 5;
                        startX = 6;
                    }
                }
            }else if(count == 7){
                int startX = 45;
                int startY = 6;
                BufferedImage b = image[0];
                graphics.drawImage(b, startX, startY, b.getWidth(), b.getHeight(), null);
                startX = 6;
                startY = startY + 8 + b.getHeight();
                for(int i = 1;i<count;i++){
                    b = image[i];
                    graphics.drawImage(b, startX, startY, b.getWidth(), b.getHeight(), null);
                    startX = startX + b.getWidth() + 5;
                    if(i%3 == 0){
                        startY = startY + b.getHeight() + 5;
                        startX = 6;
                    }
                }
            }else if(count == 8){
                int startX = 25;
                int startY = 6;
                BufferedImage b1 = image[0];
                graphics.drawImage(b1, startX, startY, b1.getWidth(), b1.getHeight(), null);
                BufferedImage b2 = image[1];
                startX = startX + b1.getWidth()+5;
                graphics.drawImage(b2, startX, startY, b2.getWidth(), b2.getHeight(), null);
                startX = 6;
                startY = 6 + b2.getHeight() + 5;
                for(int i = 2;i<count;i++){
                    BufferedImage b = image[i];
                    graphics.drawImage(b, startX, startY, b.getWidth(), b.getHeight(), null);
                    startX = startX + b.getWidth() + 5;
                    if(i == 4){
                        startY = startY + b.getHeight() + 5;
                        startX = 6;
                    }
                }
            }else if(count == 9){
                int startX = 6;
                int startY = 6;
                for(int i = 0;i<count;i++){
                    BufferedImage b = image[i];
                    graphics.drawImage(b, startX, startY, b.getWidth(), b.getHeight(), null);
                    startX = startX + b.getWidth() + 5;
                    if((i+1)%3 == 0){
                        startY = startY + b.getHeight() + 5;
                        startX = 6;
                    }
                }
            }
            graphics.dispose();
        } catch (Exception e) {
            log.error("合成图片出错{}",e);
        }

        return tag;
    }




    /**
     * 根据输出地址生成最终图片
     * @param img
     * @param newImage
     */
    public static void writeImage( BufferedImage img,String newImage) {
        if (newImage != null && img != null) {
            String imgType=newImage.substring(newImage.lastIndexOf(".")+1);
            try {
                File outputfile = new File(newImage);
                ImageIO.write(img, imgType, outputfile);
            } catch (IOException e) {
                log.error("生成最终图片出错{}",e);
            }
        }
    }


    public static void main(String[] args) {
//		BufferedImage b1 = loadImageLocal("C:\\Users\\Admin\\Documents\\WeChat Files\\a010662716\\FileStorage\\File\\2020-04\\头像\\");
        try {
            //用于合成的图片数量
            int count = 4;
            BufferedImage[] image =new BufferedImage[count];
//            for(int i =0;i<count;i++){
                image[0]=loadImage("D:\\defaultUser.png");
                image[1]=loadImage("https://firechat-im.oss-cn-shanghai.aliyuncs.com/avatar/defaultUser.jpg");
                image[2]=loadImage("D:\\defaultUser.jpg");
                image[3]=loadImage("https://firechat-im.oss-cn-shanghai.aliyuncs.com/avatar/defaultGroup.jpg");
//            }
            writeImage(buildImagetogeter(handleLarge(50,50,image),128,128),"D:\\"+count+".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
