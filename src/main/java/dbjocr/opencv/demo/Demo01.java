package dbjocr.opencv.demo;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Demo01 {
    public static void main(String[] args) {
        // 这个必须要写,不写报java.lang.UnsatisfiedLinkError
         System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        File imgFile = new File("./input/test.jpg");
        String dest = "./output";
        Mat src = Imgcodecs.imread(imgFile.toString(), Imgcodecs.IMREAD_GRAYSCALE);

        Mat dst = new Mat();

        Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 13, 5);
        Imgcodecs.imwrite(dest + "/AdaptiveThreshold" + imgFile.getName(), dst);
    }
}
