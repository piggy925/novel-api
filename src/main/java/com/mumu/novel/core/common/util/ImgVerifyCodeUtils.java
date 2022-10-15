package com.mumu.novel.core.common.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

import lombok.experimental.UtilityClass;

/**
 * 图形验证码工具类
 * 生成随机校验码和对应的 Base64 编码后的图片
 *
 * @author mumu
 * @date 2022/10/09
 */
@UtilityClass
public class ImgVerifyCodeUtils {

    /**
     * 随机产生仅包含数字的字符串
     */
    private final String randomNum = "1234567890";

    /**
     * 验证码图片宽度
     */
    private final int width = 100;

    /**
     * 验证码图片高度
     */
    private final int height = 40;

    private final Random random = new Random();

    /**
     * 字体
     */
    private Font getFont() {
        return new Font("Fixed", Font.PLAIN, 23);
    }

    /**
     * 绘制验证码图片
     */
    public String genVerifyCodeImg(String verifyCode) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setColor(new Color(204, 204, 204));

        // 绘制干扰线
        int lineSize = 60;
        for (int i = 0; i < lineSize; i++) {
            drawLine(g);
        }
        // 绘制字符串
        drawVerifyCode(g, verifyCode);
        g.dispose();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", stream);
        return Base64.getEncoder().encodeToString(stream.toByteArray());
    }

    /**
     * 绘制字符串
     */
    private void drawVerifyCode(Graphics g, String verifyCode) {
        for (int i = 1; i <= verifyCode.length(); i++) {
            g.setFont(getFont());
            g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
            g.translate(random.nextInt(3), random.nextInt(3));
            g.drawString(String.valueOf(verifyCode.charAt(i - 1)), 13 * i, 23);
        }
    }

    /**
     * 绘制干扰线
     */
    private void drawLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 生成随机验证码
     *
     * @param num 验证码数字个数
     */
    public String genRandomVerifyCode(int num) {
        int randomSize = randomNum.length();
        StringBuilder verifyCode = new StringBuilder();
        for (int i = 0; i < num; i++) {
            verifyCode.append(randomNum.charAt(random.nextInt(randomSize)));
        }
        return verifyCode.toString();
    }

}
