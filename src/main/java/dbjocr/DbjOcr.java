package dbjocr;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import dbjocr.model.Feature;
import dbjocr.model.Rect;
import dbjocr.utils.Preconditions;
import dbjocr.utils.Tools;

public class DbjOcr {
    public static void main(String[] args) {
    	// "./input/sudoku2.jpg"
    	String strFile = "./input/IMG_0844.jpg";
    	strFile = "./input/IMG_0878.jpg";
        try {
            BufferedImage image = ImageIO.read(new File(strFile));
            System.out.println("图像宽高:" + image.getWidth() + "," + image.getHeight());
            proc(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int[] proc(BufferedImage bimage) throws DbjOcrException {
        int[] result = new int[81];
        long st = System.currentTimeMillis();
        try {
            Rect src = Image.getSudokuRect(bimage);
            BufferedImage image = Image.cutImage(bimage, src);

            // 灰度化
            BufferedImage imageGray = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            imageGray.getGraphics().drawImage(image, 0, 0, null);
//            ImageIO.write(imageGray, "jpeg", new File("./output/sudoku_" + st + "_gray.jpg"));

            // 二值化
            BufferedImage imageBinary = new BufferedImage(imageGray.getWidth(), imageGray.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            imageBinary.getGraphics().drawImage(imageGray, 0, 0, null);
//            ImageIO.write(imageBinary, "jpeg", new File("./output/sudoku_" + st + "_binary.jpg"));

            // 白字黑底
            BufferedImage imageWrite = Image.negate(imageBinary);
//            ImageIO.write(imageWrite, "jpeg", new File("./output/sudoku_" + st + "_binarywrite.jpg"));

            // 膨胀
            BufferedImage imageDilate = Image.dilate(imageWrite);
//            ImageIO.write(imageDilate, "jpeg", new File("./output/sudoku_" + st + "_dilate.jpg"));

            // 连通
            int[] mask = new int[imageDilate.getWidth() * imageDilate.getHeight()];
            List<Rect> rectangles = new ArrayList<Rect>();
            Image.getConnectedArea(imageDilate, mask, rectangles, true, 100, false);

            List<Rect> del_rectangles = new ArrayList<Rect>();
            for (Rect rc : rectangles) {
                if (25 >= rc.getWidth() && rc.getWidth() > 5 && 35 >= rc.getHeight() && rc.getHeight() > 16) {
                	// 5 < 宽 <= 25 16 < 高 <= 35
                    rc.makeCutRect2();
                } else {
                    del_rectangles.add(rc);
                }
            }
            rectangles.removeAll(del_rectangles);
            if (Preconditions.isNotBlank(rectangles)) {
                Tools.drawRects(imageDilate, rectangles);
//                ImageIO.write(imageDilate, "jpeg", new File("./output/sudoku_" + st + "_connect.jpg"));
            }

            // 腐蚀
            BufferedImage imageErode = Image.erode(imageDilate);
//            ImageIO.write(imageErode, "jpeg", new File("./output/sudoku_" + st + "_erode.jpg"));

            System.out.println("件数:" + rectangles.size());
           if (rectangles.size() < 17) {
           	// 最少17个数字使得解答谜题只有一个答案。
               throw new DbjOcrException("不足够17个给出数。(" + rectangles.size() + ")");
           }

            ImageIO.write(bimage, "jpeg", new File("./output/sudoku_1_" + st + "_org.jpg"));
//            ImageIO.write(image, "jpeg", new File("./output/sudoku_2_" + st + "_cut.jpg"));
//            ImageIO.write(imageGray, "jpeg", new File("./output/sudoku_3_" + st + "_gray.jpg"));
//            ImageIO.write(imageBinary, "jpeg", new File("./output/sudoku_4_" + st + "_binary.jpg"));
//            ImageIO.write(imageWrite, "jpeg", new File("./output/sudoku_5_" + st + "_binarywrite.jpg"));
//            ImageIO.write(imageDilate, "jpeg", new File("./output/sudoku_6_" + st + "_connect.jpg"));
//            ImageIO.write(imageErode, "jpeg", new File("./output/sudoku_7_" + st + "_erode.jpg"));

            BufferedImage outImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D off = outImage.createGraphics();
            off.setColor(Color.WHITE);
            off.fillRect(0, 0, image.getWidth(), image.getHeight());
            
            off.setPaint(Color.RED);
            off.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 50));

            int w = imageErode.getWidth();
            int h = imageErode.getHeight();
            int step = h / 11;
            System.out.println(w + "," + h + "," + step );
            List<Feature> cuts = Image.cutImage(imageErode, rectangles);
            for (int idx = 0; idx < cuts.size(); idx ++ ) {
                Feature c = cuts.get(idx);
//                ImageIO.write(c.image, "jpeg", new File("./output/sudoku_8_" + st + "_connect_" + ( idx < 10 ? "0" : "" ) + idx + ".jpg"));
                Image.getFeature(c);
                Image.getFeature3(c); // bgblock
                Image.getFeature4(c); // across
                int[] iary = Image.getCharByFeature3(c);
System.out.println(Arrays.toString(iary));
            	String str = null;
            	if (iary.length == 1) {
            		str = "" + iary[0];
            	} else {
	                Hilditch hilditch = new Hilditch(c.image);
	                BufferedImage outputImage = hilditch.thin();
//	                ImageIO.write(outputImage, "jpg", new File("./output/sudoku_9_" + st + "_connect_hilditch" + ( idx < 10 ? "0" : "" ) + idx + ".jpg"));
	                c.image = outputImage;
	
	                Image.getFeature(c);
	                str = Image.getCharByFeature(c);
            	}

                c.x = (c.rect.x + c.rect.width) / step;
                if ((c.rect.x + c.rect.width) > (step * 7)) c.x --;
                c.y = (c.rect.y + c.rect.height + step) / step - 1;
                if (c.rect.y + c.rect.height > step * 3) c.y --;
                // System.out.println(c.x + "," + c.y + "=" + str + rectangles.get(idx));

                System.out.println("◆" + c);
                System.out.println("★" + str);

                if (str != null) {
                	result[(c.x - 1) + (c.y - 1) * 9] = Integer.parseInt(str);
	                off.drawString(str, c.rect.x, c.rect.y);
                }
            }
            ImageIO.write(outImage, "jpeg", new File("./output/sudoku_A_" + st + "_output.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
       System.out.println(Arrays.toString(result));

        return result;
    }
}
