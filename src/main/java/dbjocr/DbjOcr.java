package dbjocr;

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
        try {
            BufferedImage image = ImageIO.read(new File("./input/sudoku2.jpg"));
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
                    rc.makeCutRect2();
                } else {
                    del_rectangles.add(rc);
                }
            }
            rectangles.removeAll(del_rectangles);
            if (Preconditions.isNotBlank(rectangles)) {
                Tools.drawRects(imageDilate,rectangles);
//                ImageIO.write(imageDilate, "jpeg", new File("./output/sudoku_" + st + "_connect.jpg"));
            }

            // 腐蚀
            BufferedImage imageErode = Image.erode(imageDilate);
//            ImageIO.write(imageErode, "jpeg", new File("./output/sudoku_" + st + "_erode.jpg"));

            // System.out.println("件数:" + rectangles.size());
            if (rectangles.size() < 17) {
                throw new DbjOcrException("不足够17个给出数。(" + rectangles.size() + ")");
            }

            ImageIO.write(bimage, "jpeg", new File("./output/sudoku_" + st + "_org.jpg"));
            ImageIO.write(image, "jpeg", new File("./output/sudoku_" + st + "_cut.jpg"));
            ImageIO.write(imageGray, "jpeg", new File("./output/sudoku_" + st + "_gray.jpg"));
            ImageIO.write(imageBinary, "jpeg", new File("./output/sudoku_" + st + "_binary.jpg"));
            ImageIO.write(imageWrite, "jpeg", new File("./output/sudoku_" + st + "_binarywrite.jpg"));
            ImageIO.write(imageDilate, "jpeg", new File("./output/sudoku_" + st + "_connect.jpg"));
            ImageIO.write(imageErode, "jpeg", new File("./output/sudoku_" + st + "_erode.jpg"));

            int w = imageErode.getWidth();
            int h = imageErode.getHeight();
            int step = h / 11;
            System.out.println(w + "," + h + "," + step );
            List<Feature> cuts = Image.cutImage(imageErode, rectangles);
            for (int idx = 0; idx < cuts.size(); idx ++ ) {
                Feature c = cuts.get(idx);
                ImageIO.write(c.image, "jpeg", new File("./output/sudoku_" + st + "_connect_" + ( idx < 10 ? "0" : "" ) + idx + ".jpg"));
                
                Hilditch hilditch = new Hilditch(c.image);
                BufferedImage outputImage = hilditch.thin();
                ImageIO.write(outputImage, "jpg", new File("./output/sudoku" + st + "_connect_hilditch" + ( idx < 10 ? "0" : "" ) + idx + ".jpg"));
                c.image = outputImage;

                Image.getFeature(c);
                System.out.println(c);
                String str = Image.getCharByFeature(c);
                c.x = (c.rect.x + c.rect.width) / step;
                if ((c.rect.x + c.rect.width) > (step * 7)) c.x --;
                c.y = (c.rect.y + c.rect.height + step) / step - 1;
                if (c.rect.y + c.rect.height > step * 3) c.y --;
                // System.out.println(c.x + "," + c.y + "=" + str + rectangles.get(idx));
                System.out.println(str);
                result[(c.x - 1) + (c.y - 1) * 9] = Integer.parseInt(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       System.out.println(Arrays.toString(result));
        return result;
    }
}
