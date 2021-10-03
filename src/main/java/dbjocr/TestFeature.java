package dbjocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dbjocr.model.Feature;

public class TestFeature {

	public static void main(String[] args) {
		ArrayList<String>  files = new ArrayList<String>();

//        files.add("./input/training/1/01.jpg");
//        files.add("./input/training/2/01.jpg");
//        files.add("./input/training/3/01.jpg");
//        files.add("./input/training/4/01.jpg");
//        files.add("./input/training/5/01.jpg");
//        files.add("./input/training/6/01.jpg");
//        files.add("./input/training/7/01.jpg");
//        files.add("./input/training/8/01.jpg");
//        files.add("./input/training/9/01.jpg");

//        files.add("./input/training/1/sudoku1632697438015_connect_hilditch26.jpg");
//        files.add("./input/training/2/sudoku1587461626778_connect_hilditch27.jpg");
//        files.add("./input/training/3/sudoku1587461626778_connect_hilditch11.jpg");
//        files.add("./input/training/4/sudoku1587461626778_connect_hilditch00.jpg");
//        files.add("./input/training/5/sudoku1587461626778_connect_hilditch05.jpg");
//        files.add("./input/training/6/sudoku1587461626778_connect_hilditch06.jpg");
//        files.add("./input/training/7/sudoku1587461626778_connect_hilditch14.jpg");
//        files.add("./input/training/8/sudoku1587461626778_connect_hilditch12.jpg");
//        files.add("./input/training/9/sudoku1587461626778_connect_hilditch13.jpg");

//        files.add("./input/training/1/02.jpg");
//        files.add("./input/training/1/sudoku1587461626778_connect_hilditch10.jpg");
//        files.add("./input/training/1/sudoku1587461626778_connect_hilditch20.jpg");
//        files.add("./input/training/1/sudoku1587461626778_connect_hilditch23.jpg");
//        files.add("./input/training/1/sudoku1587461626778_connect_hilditch26.jpg");
//        files.add("./input/training/1/sudoku1632697438015_connect_hilditch10.jpg");
//        files.add("./input/training/1/sudoku1632697438015_connect_hilditch20.jpg");
//        files.add("./input/training/1/sudoku1632697438015_connect_hilditch23.jpg");
//        files.add("./input/training/1/sudoku1632697438015_connect_hilditch26.jpg");
//        files.add("./input/training/1/sudoku1632719649427_connect_hilditch10.jpg");
//        files.add("./input/training/1/sudoku1632719649427_connect_hilditch20.jpg");
//        files.add("./input/training/1/sudoku1632719649427_connect_hilditch23.jpg");
//        files.add("./input/training/1/sudoku1632719649427_connect_hilditch26.jpg");
//        files.add("./input/training/1/sudoku1632720306813_connect_hilditch10.jpg");
//        files.add("./input/training/1/sudoku1632720306813_connect_hilditch20.jpg");
//        files.add("./input/training/1/sudoku1632720306813_connect_hilditch23.jpg");
//        files.add("./input/training/1/sudoku1632720306813_connect_hilditch26.jpg");
//        files.add("./input/training/1/sudoku1632721340017_connect_hilditch00.jpg");
//        files.add("./input/training/1/sudoku_9_1632723734752_connect_hilditch16.jpg");
//        files.add("./input/training/1/sudoku_9_1632783920330_connect_hilditch16.jpg");

//        files.add("./input/training/1/sudoku_1587461626778_connect_10.jpg");
//        files.add("./input/training/1/sudoku_1587461626778_connect_20.jpg");
//        files.add("./input/training/1/sudoku_1587461626778_connect_23.jpg");
//        files.add("./input/training/1/sudoku_1587461626778_connect_26.jpg");
//        files.add("./input/training/1/sudoku_1632697438015_connect_10.jpg");
//        files.add("./input/training/1/sudoku_1632697438015_connect_20.jpg");
//        files.add("./input/training/1/sudoku_1632697438015_connect_23.jpg");
//        files.add("./input/training/1/sudoku_1632697438015_connect_26.jpg");
//        files.add("./input/training/1/sudoku_1632719649427_connect_10.jpg");
//        files.add("./input/training/1/sudoku_1632719649427_connect_20.jpg");
//        files.add("./input/training/1/sudoku_1632719649427_connect_23.jpg");
//        files.add("./input/training/1/sudoku_1632719649427_connect_26.jpg");
//        files.add("./input/training/1/sudoku_1632720306813_connect_10.jpg");
//        files.add("./input/training/1/sudoku_1632720306813_connect_20.jpg");
//        files.add("./input/training/1/sudoku_1632720306813_connect_23.jpg");
//        files.add("./input/training/1/sudoku_1632720306813_connect_26.jpg");
//        files.add("./input/training/1/sudoku_1632721340017_connect_00.jpg");
//        files.add("./input/training/1/sudoku_8_1632723734752_connect_16.jpg");
//        files.add("./input/training/1/sudoku_8_1632783920330_connect_16.jpg");


//        files.add("./input/training/1/sudoku1587461626778_connect_hilditch10.jpg");
//        files.add("./input/training/1/sudoku1587461626778_connect_hilditch20.jpg");
//        files.add("./input/training/1/sudoku1587461626778_connect_hilditch23.jpg");
//        files.add("./input/training/1/sudoku1587461626778_connect_hilditch26.jpg");
//        files.add("./input/training/1/sudoku1632697438015_connect_hilditch10.jpg");
//        files.add("./input/training/1/sudoku1632697438015_connect_hilditch20.jpg");
//        files.add("./input/training/1/sudoku1632697438015_connect_hilditch23.jpg");
//        files.add("./input/training/1/sudoku1632697438015_connect_hilditch26.jpg");
//        files.add("./input/training/1/sudoku1632719649427_connect_hilditch10.jpg");
//        files.add("./input/training/1/sudoku1632719649427_connect_hilditch20.jpg");
//        files.add("./input/training/1/sudoku1632719649427_connect_hilditch23.jpg");
//        files.add("./input/training/1/sudoku1632719649427_connect_hilditch26.jpg");
//        files.add("./input/training/1/sudoku1632720306813_connect_hilditch10.jpg");
//        files.add("./input/training/1/sudoku1632720306813_connect_hilditch20.jpg");
//        files.add("./input/training/1/sudoku1632720306813_connect_hilditch23.jpg");
//        files.add("./input/training/1/sudoku1632720306813_connect_hilditch26.jpg");
//        files.add("./input/training/1/sudoku1632721340017_connect_hilditch00.jpg");
//        files.add("./input/training/1/sudoku_9_1632723734752_connect_hilditch16.jpg");
//        files.add("./input/training/1/sudoku_9_1632783920330_connect_hilditch16.jpg");
//        files.add("./input/training/2/sudoku1587461626778_connect_hilditch27.jpg");
//        files.add("./input/training/3/sudoku1587461626778_connect_hilditch11.jpg");
//        files.add("./input/training/3/sudoku1587461626778_connect_hilditch16.jpg");
//        files.add("./input/training/3/sudoku1587461626778_connect_hilditch21.jpg");
//        files.add("./input/training/3/sudoku1587461626778_connect_hilditch29.jpg");
//        files.add("./input/training/4/sudoku1587461626778_connect_hilditch00.jpg");
//        files.add("./input/training/5/sudoku1587461626778_connect_hilditch01.jpg");
//        files.add("./input/training/5/sudoku1587461626778_connect_hilditch05.jpg");
//        files.add("./input/training/5/sudoku1587461626778_connect_hilditch08.jpg");
//        files.add("./input/training/5/sudoku1587461626778_connect_hilditch24.jpg");
//        files.add("./input/training/6/sudoku1587461626778_connect_hilditch03.jpg");
//        files.add("./input/training/6/sudoku1587461626778_connect_hilditch06.jpg");
//        files.add("./input/training/6/sudoku1587461626778_connect_hilditch22.jpg");
//        files.add("./input/training/7/sudoku1587461626778_connect_hilditch09.jpg");
//        files.add("./input/training/7/sudoku1587461626778_connect_hilditch14.jpg");
//        files.add("./input/training/7/sudoku1587461626778_connect_hilditch17.jpg");
//        files.add("./input/training/7/sudoku1587461626778_connect_hilditch19.jpg");
//        files.add("./input/training/7/sudoku1587461626778_connect_hilditch25.jpg");
//        files.add("./input/training/8/sudoku1587461626778_connect_hilditch04.jpg");
//        files.add("./input/training/8/sudoku1587461626778_connect_hilditch12.jpg");
//        files.add("./input/training/8/sudoku1587461626778_connect_hilditch15.jpg");
//        files.add("./input/training/9/sudoku1587461626778_connect_hilditch02.jpg");
//        files.add("./input/training/9/sudoku1587461626778_connect_hilditch07.jpg");
//        files.add("./input/training/9/sudoku1587461626778_connect_hilditch13.jpg");
//        files.add("./input/training/9/sudoku1587461626778_connect_hilditch18.jpg");
//        files.add("./input/training/9/sudoku1587461626778_connect_hilditch28.jpg");


        files.add("./input/training/1/sudoku_1587461626778_connect_10.jpg");
        files.add("./input/training/1/sudoku_1587461626778_connect_20.jpg");
        files.add("./input/training/1/sudoku_1587461626778_connect_23.jpg");
        files.add("./input/training/1/sudoku_1587461626778_connect_26.jpg");
        files.add("./input/training/1/sudoku_1632697438015_connect_10.jpg");
        files.add("./input/training/1/sudoku_1632697438015_connect_20.jpg");
        files.add("./input/training/1/sudoku_1632697438015_connect_23.jpg");
        files.add("./input/training/1/sudoku_1632697438015_connect_26.jpg");
        files.add("./input/training/1/sudoku_1632719649427_connect_10.jpg");
        files.add("./input/training/1/sudoku_1632719649427_connect_20.jpg");
        files.add("./input/training/1/sudoku_1632719649427_connect_23.jpg");
        files.add("./input/training/1/sudoku_1632719649427_connect_26.jpg");
        files.add("./input/training/1/sudoku_1632720306813_connect_10.jpg");
        files.add("./input/training/1/sudoku_1632720306813_connect_20.jpg");
        files.add("./input/training/1/sudoku_1632720306813_connect_23.jpg");
        files.add("./input/training/1/sudoku_1632720306813_connect_26.jpg");
        files.add("./input/training/1/sudoku_1632721340017_connect_00.jpg");
        files.add("./input/training/1/sudoku_8_1632723734752_connect_16.jpg");
        files.add("./input/training/1/sudoku_8_1632783920330_connect_16.jpg");
        files.add("./input/training/2/sudoku_1587461626778_connect_27.jpg");
        files.add("./input/training/3/sudoku_1587461626778_connect_11.jpg");
        files.add("./input/training/3/sudoku_1587461626778_connect_16.jpg");
        files.add("./input/training/3/sudoku_1587461626778_connect_21.jpg");
        files.add("./input/training/3/sudoku_1587461626778_connect_29.jpg");
        files.add("./input/training/4/sudoku_1587461626778_connect_00.jpg");
        files.add("./input/training/5/sudoku_1587461626778_connect_01.jpg");
        files.add("./input/training/5/sudoku_1587461626778_connect_05.jpg");
        files.add("./input/training/5/sudoku_1587461626778_connect_08.jpg");
        files.add("./input/training/5/sudoku_1587461626778_connect_24.jpg");
        files.add("./input/training/6/sudoku_1587461626778_connect_03.jpg");
        files.add("./input/training/6/sudoku_1587461626778_connect_06.jpg");
        files.add("./input/training/6/sudoku_1587461626778_connect_22.jpg");
        files.add("./input/training/7/sudoku_1587461626778_connect_09.jpg");
        files.add("./input/training/7/sudoku_1587461626778_connect_14.jpg");
        files.add("./input/training/7/sudoku_1587461626778_connect_17.jpg");
        files.add("./input/training/7/sudoku_1587461626778_connect_19.jpg");
        files.add("./input/training/7/sudoku_1587461626778_connect_25.jpg");
        files.add("./input/training/8/sudoku_1587461626778_connect_04.jpg");
        files.add("./input/training/8/sudoku_1587461626778_connect_12.jpg");
        files.add("./input/training/8/sudoku_1587461626778_connect_15.jpg");
        files.add("./input/training/9/sudoku_1587461626778_connect_02.jpg");
        files.add("./input/training/9/sudoku_1587461626778_connect_07.jpg");
        files.add("./input/training/9/sudoku_1587461626778_connect_13.jpg");
        files.add("./input/training/9/sudoku_1587461626778_connect_18.jpg");
        files.add("./input/training/9/sudoku_1587461626778_connect_28.jpg");

        
		for (String f : files) {
			test(f);
		}
	}
	public static void test(String strFile) {
//		String strFile = "./output/sudoku_9_1632723734752_connect_hilditch05.jpg";
		System.out.print(strFile.substring(17, 18));
        try {
            BufferedImage image = ImageIO.read(new File(strFile));
//            System.out.println("图像宽高:Width=" + image.getWidth() + ",Height=" + image.getHeight());
            Feature  c = new Feature(image);
//            Image.getFeature(c);
//            Image.getFeature2(c);
//            Image.getFeature3(c); // 5, 6 , 1
            Image.getFeature4(c);
//            String str = Image.getCharByFeature(c);
//            String str = Image.getCharByFeature2(c);
            System.out.println("◆" + c);
//            System.out.println("★" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
