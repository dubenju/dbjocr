package dbjocr;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbjocr.model.Feature;
import dbjocr.model.PixelNode;
import dbjocr.model.Rect;
import image.URGBColor;

public class Image {
    public static BufferedImage gray_01(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_GRAY);
        bufImage.getGraphics().drawImage(image, 0, 0, null);
        return bufImage;
    }

    /**
     * NG
     * @param image
     * @return
     */
    public static BufferedImage gray_02(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_USHORT_GRAY);
        bufImage.getGraphics().drawImage(image, 0, 0, null);
        return bufImage;
    }
    
    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;
    }
    public static BufferedImage gray_03(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                final int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
//                System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                bufImage.setRGB(i, j, newPixel);
            }
        }
        return bufImage;
    }
    /**
     *  加权平均法
     *  根据重要性及其它指标，将三个分量以不同的权值进行加权平均。由于人眼对绿色的敏感最高，对蓝色敏感最低，因此，按下式对RGB三分量进行加权平均能得到较合理的灰度图像。
     * @param image
     * @return
     */
    public static BufferedImage gray_03b(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                final int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.299 * r + 0.578 * g + 0.114 * b);
//                System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                bufImage.setRGB(i, j, newPixel);
            }
        }
        return bufImage;
    }
    /**
     *  加权平均法
     *  根据重要性及其它指标，将三个分量以不同的权值进行加权平均。由于人眼对绿色的敏感最高，对蓝色敏感最低，因此，按下式对RGB三分量进行加权平均能得到较合理的灰度图像。
     * https://en.wikipedia.org/wiki/Grayscale
     * @param image
     * @return
     */
    public static BufferedImage gray_03c(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                final int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
//                System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                bufImage.setRGB(i, j, newPixel);
            }
        }
        return bufImage;
    }
    /**
     *  加权平均法
     *  根据重要性及其它指标，将三个分量以不同的权值进行加权平均。由于人眼对绿色的敏感最高，对蓝色敏感最低，因此，按下式对RGB三分量进行加权平均能得到较合理的灰度图像。
     * https://en.wikipedia.org/wiki/Grayscale
     * @param image
     * @return
     */
    public static BufferedImage gray_03d(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                final int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.2627 * r + 0.6780 * g + 0.0593 * b);
//                System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                bufImage.setRGB(i, j, newPixel);
            }
        }
        return bufImage;
    }
    /**
     * 图像灰度化处理
     * 分量法
     * 将彩色图像中的三分量的亮度作为三个灰度图像的灰度值，可根据应用需要选取一种灰度图像。
     * @param image
     * @return
     */
    public static BufferedImage gray_04(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                final int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = r;
                // System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                bufImage.setRGB(i, j, newPixel);
            }
        }
        return bufImage;
    }
    public static BufferedImage gray_05(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                final int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = g;
                // System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                bufImage.setRGB(i, j, newPixel);
            }
        }
        return bufImage;
    }
    public static BufferedImage gray_06(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                final int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = b;
                // System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                bufImage.setRGB(i, j, newPixel);
            }
        }
        return bufImage;
    }
    
    /**
     * 最大值法
     * 将彩色图像中的三分量亮度的最大值作为灰度图的灰度值。
     * @param image
     * @return
     */
    public static BufferedImage gray_07(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                final int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = Math.max(Math.max(r, g), b);
                // System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                bufImage.setRGB(i, j, newPixel);
            }
        }
        return bufImage;
    }
    
    /**
     * 平均值法
     * 将彩色图像中的三分量亮度求平均得到一个灰度值。
     * @param image
     * @return
     */
    public static BufferedImage gray_08(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                final int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (r + b + g) / 3;
                // System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                bufImage.setRGB(i, j, newPixel);
            }
        }
        return bufImage;
    }
    
    private static BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel dstCM) {
        if ( dstCM == null ) {
            dstCM = src.getColorModel();
        }
        return new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(src.getWidth(), src.getHeight()), dstCM.isAlphaPremultiplied(), null);
    }
    public static BufferedImage negate(BufferedImage image) {
        BufferedImage src = image;
        BufferedImage dest = createCompatibleDestImage( src, null );
        int width = src.getWidth();
        int height = src.getHeight();

        int[] inPixels = new int[width*height];
        int[] outPixels = new int[width*height];
        URGBColor.getRGB(src, 0, 0, width, height, inPixels );

        // calculate means of pixel
        int index = 0;
        double redSum = 0, greenSum = 0, blueSum = 0;
        double total = height * width;
        for(int row=0; row<height; row++) {
            int ta = 0, tr = 0, tg = 0, tb = 0;
            for(int col=0; col<width; col++) {
                index = row * width + col;
                ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                tr = 255 - tr;
                tg = 255 - tg;
                tb = 255 - tb;
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
            }
        }
        URGBColor.setRGB( dest, 0, 0, width, height, outPixels );
        return dest;
    }

    /////// 二值化
    /**
     * 二值化（英语：Binarization）是图像分割的一种最简单的方法。
     * 二值化可以把灰度图像转换成二值图像。
     * 把大于某个临界灰度值的像素灰度设为灰度極大值，把小于这个值的像素灰度设为灰度極小值，从而实现二值化。
     * 根据阈值选取的不同，二值化的算法分为固定阈值和自适应阈值。 
     * 比较常用的二值化方法则有：双峰法、P参数法、迭代法和OTSU法等。
     * @param image
     * @return
     */
    public static BufferedImage binarize_01(BufferedImage image) {
        
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        // Create a grayscale copy, no need to calculate the luminance manually
        BufferedImage bufImage = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_BINARY);
        bufImage.getGraphics().drawImage(image, 0, 0, null);
        return bufImage;
    }

    /**
     * 全局固定阈值
     * 对RGB彩色图像灰度化以后，扫描图像的每个像素值，值小于127的将像素值设为0(黑色)，
     * 值大于等于127的像素值设为255(白色)。该方法的好处是计算量少速度快。
     * 缺点更多首先阈值为127没有任何理由可以解释，
     * 其次完全不考虑图像的像素分布情况与像素值特征。
     * 可以说该方法是史最弱智的二值处理方法一点也不为过。
     * @param src
     * @return
     */
    public static BufferedImage binarize_127(BufferedImage src) {
        BufferedImage dest = null;
        int width = src.getWidth();
        int height = src.getHeight();

        if ( dest == null ) {
            dest = createCompatibleDestImage( src, null );
        }
        //src = super.binarize(src, dest);

        int[] inPixels = new int[width*height];
        int[] outPixels = new int[width*height];
        URGBColor.getRGB(src, 0, 0, width, height, inPixels );

        // calculate means of pixel
        int index = 0;
        int means = (int)(255 / 2);
        System.out.println(" threshold average value = " + means);

        // dithering
        for(int row=0; row<height; row++) {
            int ta = 0, tr = 0, tg = 0, tb = 0;
            for(int col=0; col<width; col++) {
                index = row * width + col;
                ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                if(tr >=means) {
                    tr = tg = tb = 255;
                } else {
                    tr = tg = tb = 0;
                }
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
            }
        }
        URGBColor.setRGB( dest, 0, 0, width, height, outPixels );
        return dest;
    }

    /**
     * 计算像素的平均值K，扫描图像的每个像素值如像素值大于K像素值设为255(白色)，值小于等于K像素值设为0(黑色)。
     * 该方法相比方法一，阈值的选取稍微有点智商，可以解释。
     * 但是使用平均值作为二值化阈值同样有个致命的缺点，可能导致部分对象像素或者背景像素丢失。二值化结果不能真实反映源图像信息。
     * @param src
     * @return
     */
    public static BufferedImage average_binarize(BufferedImage src) {
        BufferedImage dest = null;
        int width = src.getWidth();
        int height = src.getHeight();

        if ( dest == null ) {
            dest = createCompatibleDestImage( src, null );
        }
        //src = super.binarize(src, dest);

        int[] inPixels = new int[width*height];
        int[] outPixels = new int[width*height];
        URGBColor.getRGB(src, 0, 0, width, height, inPixels );

        // calculate means of pixel
        int index = 0;
        double redSum = 0, greenSum = 0, blueSum = 0;
        double total = height * width;
        for(int row=0; row<height; row++) {
            int ta = 0, tr = 0, tg = 0, tb = 0;
            for(int col=0; col<width; col++) {
                index = row * width + col;
                ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                redSum += tr;
                greenSum += tg;
                blueSum +=tb;
            }
        }
        int means = (int)(redSum / total);
        System.out.println(" threshold average value = " + means);

        // dithering
        for(int row=0; row<height; row++) {
            int ta = 0, tr = 0, tg = 0, tb = 0;
            for(int col=0; col<width; col++) {
                index = row * width + col;
                ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                if(tr >=means) {
                    tr = tg = tb = 255;
                } else {
                    tr = tg = tb = 0;
                }
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
            }
        }
        URGBColor.setRGB( dest, 0, 0, width, height, outPixels );
        return dest;
    }
    
    

    /**
     * 局部自适应阈值则是根据像素的邻域块的像素值分布来确定该像素位置上的二值化阈值。
     * 这样做的好处在于每百个像素位置处的二度值化阈值不是固定不变的，而是由其周围邻域像素的分布来决定的。
     * 亮度较高的图像区域的二值化阈值通常会较高，而亮度较低的图像区域的二值化阈值则会相适应地变小。
     * 不同亮度、知对比度、纹理的局部图像区域将会拥有相对应的局部二值化阈值。
     * 经常用的局部自适应阈值有：
     * 1）局部邻域块的均值；
     * 　　1、首先要获取每个像素点的灰度值。
     * 　　2、定义一个阀值。
     * 　　3、将每个像素点的灰度值和它周围的8个像素点的灰度值相叠加再除以9，然后和阀值进行比较。
     * 　　4、大于阀值则设为黑色，小雨则为白色。
     * 2）局部邻域块的高斯加权和。★★★★
     * @param args
     * @throws IOException
     */
    public static BufferedImage binarize_02(BufferedImage bi) {

        // 获取当前图片的高,宽,ARGB
        int h = bi.getHeight();
        int w = bi.getWidth();
        int rgb = bi.getRGB(0, 0);
        int arr[][] = new int[w][h];

        // 获取图片每一像素点的灰度值
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                // getRGB()返回默认的RGB颜色模型(十进制)
                arr[i][j] = getImageRgb(bi.getRGB(i, j));//该点的灰度值
            }

        }
        
        BufferedImage bufferedImage=new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);//  构造一个类型为预定义图像类型之一的 BufferedImage，TYPE_BYTE_BINARY（表示一个不透明的以字节打包的 1、2 或 4 位图像。）
        int FZ=130;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if(getGray(arr,i,j,w,h) > FZ){
                    int black=new Color(255,255,255).getRGB();
                    bufferedImage.setRGB(i, j, black);
                }else{
                    int white=new Color(0,0,0).getRGB();
                    bufferedImage.setRGB(i, j, white);
                }
            }
            
        }
        return bufferedImage;
    }

    private static int getImageRgb(int i) {
        String argb = Integer.toHexString(i);// 将十进制的颜色值转为十六进制
        // argb分别代表透明,红,绿,蓝 分别占16进制2位
        int r = Integer.parseInt(argb.substring(2, 4),16);//后面参数为使用进制
        int g = Integer.parseInt(argb.substring(4, 6),16);
        int b = Integer.parseInt(argb.substring(6, 8),16);
        int result=(int)((r+g+b)/3);
        return result;
    }
    
    
    
    //自己加周围8个灰度值再除以9，算出其相对灰度值 
     public static int  getGray(int gray[][], int x, int y, int w, int h)  
        {  
            int rs = gray[x][y]  
                            + (x == 0 ? 255 : gray[x - 1][y])  
                            + (x == 0 || y == 0 ? 255 : gray[x - 1][y - 1])  
                            + (x == 0 || y == h - 1 ? 255 : gray[x - 1][y + 1])  
                            + (y == 0 ? 255 : gray[x][y - 1])  
                            + (y == h - 1 ? 255 : gray[x][y + 1])  
                            + (x == w - 1 ? 255 : gray[x + 1][ y])  
                            + (x == w - 1 || y == 0 ? 255 : gray[x + 1][y - 1])  
                            + (x == w - 1 || y == h - 1 ? 255 : gray[x + 1][y + 1]);  
            return rs / 9;  
        }  
    
     /**
      * 使用直方图方法来寻找二值化阈值，直方图是图像的重要特质，直方图方法选择二值
      * 化阈值主要是发现图像的两个最高的峰，然后在阈值取值在两个峰之间的峰谷最低处。
      * 该方法相对前面两种方法而言稍微精准一点点。结果也更让人可以接受。★★★★
      */

     
     
     
    /**
     * 使用近似一维Means方法寻找二值化阈值，该方法的大致步骤如下：
     * 1.      一个初始化阈值T，可以自己设置或者根据随机方法生成。
     * 2.      根据阈值图每个像素数据P(n,m)分为对象像素数据G1与背景像素数据G2。(n为行，m为列)
     * 3.      G1的平均值是m1, G2的平均值是m2
     * 4.      一个新的阈值T’ = (m1 + m2)/2
     * 5.      回到第二步，用新的阈值继续分像素数据为对象与背景像素数据，继续2～4步，
     * 直到计算出来的新阈值等于上一次阈值。
     * @param src
     * @return
     */
    public static BufferedImage thresholdBinary(BufferedImage src) {
        BufferedImage dest = null;
        int width = src.getWidth();
        int height = src.getHeight();
 
        if ( dest == null )
            dest = createCompatibleDestImage( src, null );
 
        int[] inPixels = new int[width*height];
        int[] outPixels = new int[width*height];
        // src = super.filter(src, null); // we need to create new one
        URGBColor.getRGB( src, 0, 0, width, height, inPixels );
        int index = 0;
        int means = getThreshold(inPixels, height, width);
        for(int row=0; row<height; row++) {
            int ta = 0, tr = 0, tg = 0, tb = 0;
            for(int col=0; col<width; col++) {
                index = row * width + col;
                ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                if(tr > means) {
                    tr = tg = tb = 255; //white
                } else {
                    tr = tg = tb = 0; // black
                }
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
            }
        }
        URGBColor.setRGB( dest, 0, 0, width, height, outPixels );
        return dest;
    }
 
    private static int getThreshold(int[] inPixels, int height, int width) {
        // maybe this value can reduce the calculation consume; 
        int inithreshold = 127;
        int finalthreshold = 0;
        int temp[] = new int[inPixels.length];
        for(int index=0; index<inPixels.length; index++) {
            temp[index] = (inPixels[index] >> 16) & 0xff;
        }
        List<Integer> sub1 = new ArrayList<Integer>();
        List<Integer> sub2 = new ArrayList<Integer>();
        int means1 = 0, means2 = 0;
        while(finalthreshold != inithreshold) {
            finalthreshold = inithreshold;
            for(int i=0; i<temp.length; i++) {
                if(temp[i] <= inithreshold) {
                    sub1.add(temp[i]);
                } else {
                    sub2.add(temp[i]);
                }
            }
            means1 = getMeans(sub1);
            means2 = getMeans(sub2);
            sub1.clear();
            sub2.clear();
            inithreshold = (means1 + means2) / 2;
        }
        long start = System.currentTimeMillis();
        System.out.println("Final threshold  = " + finalthreshold);
        long endTime = System.currentTimeMillis() - start;
        System.out.println("Time consumes : " + endTime);
        return finalthreshold;
    }
 
    private static int getMeans(List<Integer> data) {
        int result = 0;
        int size = data.size();
        for(Integer i : data) {
            result += i;
        }
        return (result/size);
    }

    
    /**
     * OTSU的中心思想是阈值T应使目标与背景两类的类间方差最大。
     * 对于一幅图像，设当前景与背景的分割阈值为t时，
     * 前景点占图像比例为w0，均值为u0，
     * 背景点占图像比例为w1，均值为u1。
     * 则整个图像的均值为u = w0*u0+w1*u1。
     * 建立目标函数g(t)=w0*(u0-u)^2+w1*(u1-u)^2，
     * g(t)就是当分割阈值为t时的类间方差表达式。
     * OTSU算法使得g(t)取得全局最大值，当g(t)为最大时所对应的t称为最佳阈值。
     * OTSU算法又称为最大类间方差法。
     * @param src
     * @return
     */
    public static BufferedImage otsu_threshold(BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();
        BufferedImage dest = null;
        if ( dest == null )
            dest = createCompatibleDestImage( src, null );
        // 图像灰度化
        int[] inPixels = new int[width*height];
        int[] outPixels = new int[width*height];
        URGBColor.getRGB( src, 0, 0, width, height, inPixels );
        int index = 0;
        for(int row=0; row<height; row++) {
            int ta = 0, tr = 0, tg = 0, tb = 0;
            for(int col=0; col<width; col++) {
                index = row * width + col;
                ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                int gray= (int)(0.299 *tr + 0.587*tg + 0.114*tb);
                inPixels[index]  = (ta << 24) | (gray << 16) | (gray << 8) | gray;
            }
        }
        // 获取直方图
        int[] histogram = new int[256];
        for(int row=0; row<height; row++) {
            int tr = 0;
            for(int col=0; col<width; col++) {
                index = row * width + col;
                tr = (inPixels[index] >> 16) & 0xff;
                histogram[tr]++;
            }
        }
        // 图像二值化 - OTSU 阈值化方法
        double total = width * height;
        double[] variances = new double[256];
        for(int i=0; i<variances.length; i++)
        {
            double bw = 0;
            double bmeans = 0;
            double bvariance = 0;
            double count = 0;
            for(int t=0; t<i; t++)
            {
                count += histogram[t];
                bmeans += histogram[t] * t;
            }
            bw = count / total;
            bmeans = (count == 0) ? 0 :(bmeans / count);
            for(int t=0; t<i; t++)
            {
                bvariance += (Math.pow((t-bmeans),2) * histogram[t]);
            }
            bvariance = (count == 0) ? 0 : (bvariance / count);
            double fw = 0;
            double fmeans = 0;
            double fvariance = 0;
            count = 0;
            for(int t=i; t<histogram.length; t++)
            {
                count += histogram[t];
                fmeans += histogram[t] * t;
            }
            fw = count / total;
            fmeans = (count == 0) ? 0 : (fmeans / count);
            for(int t=i; t<histogram.length; t++)
            {
                fvariance += (Math.pow((t-fmeans),2) * histogram[t]);
            }
            fvariance = (count == 0) ? 0 : (fvariance / count);
            variances[i] = bw * bvariance + fw * fvariance;
        }

        // find the minimum within class variance
        double min = variances[0];
        int threshold = 0;
        for(int m=1; m<variances.length; m++)
        {
            if(min > variances[m]){
                threshold = m;
                min = variances[m];
            }
        }
        // 二值化
        System.out.println("final threshold value : " + threshold);
        for(int row=0; row<height; row++) {
            for(int col=0; col<width; col++) {
                index = row * width + col;
                int gray = (inPixels[index] >> 8) & 0xff;
                if(gray > threshold)
                {
                    gray = 255;
                    outPixels[index]  = (0xff << 24) | (gray << 16) | (gray << 8) | gray;
                }
                else
                {
                    gray = 0;
                    outPixels[index]  = (0xff << 24) | (gray << 16) | (gray << 8) | gray;
                }
                
            }
        }
        URGBColor.setRGB(dest, 0, 0, width, height, outPixels );
        return dest;
    }
    


    public static BufferedImage getHistogram(BufferedImage srcImage) {
        int size = 280;
        BufferedImage histogramImage = new BufferedImage(size,size, BufferedImage.TYPE_INT_RGB);
        int[] inPixels = new int[srcImage.getWidth()*srcImage.getHeight()];
        int[] intensity = new int[256];
        for(int i=0; i<intensity.length; i++) {
            intensity[i] = 0;
        }
        URGBColor.getRGB( srcImage, 0, 0, srcImage.getWidth(), srcImage.getHeight(), inPixels );
        int index = 0;
        for(int row=0; row<srcImage.getHeight(); row++) {
            int ta = 0, tr = 0, tg = 0, tb = 0;
            for(int col=0; col < srcImage.getWidth(); col++) {
                index = row * srcImage.getWidth() + col;
                ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                int gray = (int)(0.299 * (double)tr + 0.587 * (double)tg + 0.114 * (double)tb);
                intensity[gray]++;
            }
        }
        
        // draw XY Axis lines
        Graphics2D g2d = histogramImage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, size, size);
        g2d.setColor(Color.BLACK);
        g2d.drawLine(5, 250, 265, 250);
        g2d.drawLine(5, 250, 5, 5);
        
        // scale to 200
        g2d.setColor(Color.BLACK);
        int max = findMaxValue(intensity);
        float rate = 200.0f/((float)max);
        int offset = 2;
        for(int i=0; i<intensity.length; i++) {
            int frequency = (int)(intensity[i] * rate);
            g2d.drawLine(5 + offset + i, 250, 5 + offset + i, 250-frequency);
        }
        
        // X Axis Gray intensity
        g2d.setColor(Color.BLACK);
        g2d.drawString("Gray Intensity", 100, 270);
        return histogramImage;
    }
    
    private static int findMaxValue(int[] intensity) {
        int max = -1;
        for(int i=0; i<intensity.length; i++) {
            if(max < intensity[i]) {
                max = intensity[i];
            }
        }
        return max;
    }


    
    /**
     * 腐蚀
     */
    public static BufferedImage erode(BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();
        Color backgroundColor = Color.BLACK;
        BufferedImage dest = null;
        if ( dest == null )
            dest = createCompatibleDestImage( src, null );

        int[]  inPixels = new int[width*height];
        int[] outPixels = new int[width*height];

        URGBColor.getRGB( src, 0, 0, width, height, inPixels );
        int index = 0, index1 = 0, newRow = 0, newCol = 0;
        int ta1 = 0, tr1 = 0, tg1 = 0, tb1 = 0;
        for(int row = 0; row < height; row ++) {
            int ta = 0, tr = 0, tg = 0, tb = 0;
            for(int col = 0; col < width; col ++) {
                index = row * width + col;
                ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >>  8) & 0xff;
                tb =  inPixels[index]        & 0xff;
                boolean erosion = false;
                for(int offsetY = -1; offsetY <= 1; offsetY ++) {
                    for(int offsetX = -1; offsetX <= 1; offsetX ++) {
                        if(offsetY == 0 && offsetX == 0) {
                            continue;
                        }
                        newRow = row + offsetY;
                        newCol = col + offsetX;
                        if(newRow < 0 || newRow >= height) {
                            newRow = 0;
                        }
                        if(newCol < 0 || newCol >= width) {
                            newCol = 0;
                        }
                        index1 = newRow * width + newCol;
                        ta1 = (inPixels[index1] >> 24) & 0xff;
                        tr1 = (inPixels[index1] >> 16) & 0xff;
                        tg1 = (inPixels[index1] >>  8) & 0xff;
                        tb1 =  inPixels[index1]        & 0xff;
                        if(tr1 == backgroundColor.getRed() && tg1 == tb1) {
                            erosion = true;
                            break;
                        }
                    }
                    if(erosion){
                        break;
                    }
                }

                if(erosion) {
                    tr = tg = tb = backgroundColor.getRed();
                } else {
                    tr = tg = tb = 255 - backgroundColor.getRed();
                }
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
            }
        }
        URGBColor.setRGB( dest, 0, 0, width, height, outPixels );
        return dest;
    }
    
    /**
     * 膨胀
     */
    public static BufferedImage dilate(BufferedImage src) {
        int  width = src.getWidth();
        int height = src.getHeight();
        Color forgeColor = Color.WHITE;
        BufferedImage dest = null;
        if ( dest == null ) {
            dest = createCompatibleDestImage( src, null );
        }

        int[]  inPixels = new int[width*height];
        int[] outPixels = new int[width*height];

        URGBColor.getRGB( src, 0, 0, width, height, inPixels );
        int index = 0, index1 = 0, newRow = 0, newCol = 0;
        int ta1 = 0, tr1 = 0, tg1 = 0, tb1 = 0;
        for(int row = 0; row < height; row ++) {
            int ta = 0, tr = 0, tg = 0, tb = 0;
            for(int col = 0; col < width; col ++) {
                index = row * width + col;
                ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >>  8) & 0xff;
                tb =  inPixels[index]        & 0xff;
                boolean dilation = false;
                for(int offsetY = -1; offsetY <= 1; offsetY ++) {
                    for(int offsetX = -1; offsetX <= 1; offsetX ++) {
                        if(offsetY == 0 && offsetX == 0) {
                            continue;
                        }
                        newRow = row + offsetY;
                        newCol = col + offsetX;
                        if(newRow < 0 || newRow >= height) {
                            newRow = 0;
                        }
                        if(newCol < 0 || newCol >= width) {
                            newCol = 0;
                        }
                        index1 = newRow * width + newCol;
                        ta1 = (inPixels[index1] >> 24) & 0xff;
                        tr1 = (inPixels[index1] >> 16) & 0xff;
                        tg1 = (inPixels[index1] >>  8) & 0xff;
                        tb1 =  inPixels[index1]        & 0xff;
                        if(tr1 == forgeColor.getRed() && tg1 == tb1) {
                            dilation = true;
                            break;
                        }
                    }
                    if(dilation){
                        break;
                    }
                }

                if(dilation) {
                    tr = tg = tb = forgeColor.getRed();
                } else {
                    tr = tg = tb = 255 - forgeColor.getRed();
//                    tr = 255;
//                    tg = 0;
//                    tb = 0;
                }
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
            }
        }
        URGBColor.setRGB( dest, 0, 0, width, height, outPixels );
        return dest;
    }
    
    public static List<Feature> cutImage(BufferedImage in, List<Rect> rects) {
         List<Feature> result = new ArrayList<Feature>();
         for (Rect rc : rects) {
             BufferedImage im = new BufferedImage(rc.cut_width, rc.cut_height, BufferedImage.TYPE_BYTE_BINARY);
             im.getGraphics().drawImage(in, 0 - rc.cut_x, 0 -  rc.cut_y, null);
             result.add(new Feature(im, rc));
         }
         return result;
    }
    
    public static BufferedImage cutImage(BufferedImage in, Rect rc) {
//        List<BufferedImage> result = new ArrayList<BufferedImage>();
//        for (Rect rc : rects) {
            BufferedImage im = new BufferedImage(rc.cut_width, rc.cut_height, BufferedImage.TYPE_BYTE_BINARY);
            im.getGraphics().drawImage(in, 0 - rc.cut_x, 0 -  rc.cut_y, null);
//            result.add(im);
//        }
        return im;
   }

    /**
     * 16×16 or 24×24
     * @param image
     * @return
     */
    public static String getPatten(BufferedImage image) {
        StringBuffer result = new StringBuffer();
        for (int j = 1; j < image.getHeight(); j = j + 2) {
//            System.out.print("[");
            result.append("[");
            for (int i = 1; i < image.getWidth(); i = i + 2) {
                final int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >>  8) & 0xff;
                final int b =  color        & 0xff;

//                System.out.println(i + " : " + j + "(" + r + "," + g + ","  + b + ")" + (r > 127 ? 1 : 0));
                //System.out.print((r > 127 ? "*" : " ") + "");
                result.append((r > 127 ? "*" : " "));
//                int newPixel = colorToRGB(255, gray, gray, gray);
//                bufImage.setRGB(i, j, newPixel);
            }
//            System.out.println("]");
            result.append("]\n");
        }
        return result.toString();
    }

    /**
     * 靠右对齐判断，误差全部放到左边。
     * @param image
     * @return
     */
    public static Feature getFeature(Feature result) {

        StringBuffer buf = new StringBuffer();
        int split_x_cnt = 5;
        int split_y_cnt = 5;
        int x_line = split_x_cnt - 1;
        int y_line = split_y_cnt - 1;

        int offset_block = 1;// Block
        int offset_line_y = offset_block + split_x_cnt * split_y_cnt; // liney
        int offset_line_x = offset_line_y + x_line; // line x

        /* 0, x1 line1_x x1 + 2 x2 line2_x */
        result.fd_offset_x_block = new int[split_x_cnt];
        result.fd_offset_y_block = new int[split_y_cnt];
        result.fd_offset_x_line = new int[x_line];
        result.fd_offset_y_line = new int[y_line];
        /*
         * step * count + (count - 1) =length
         * step = (length + 1) / count - 1;
         */
        int split_x_step = (result.image.getWidth() + 1) / split_x_cnt - 1;
        if (split_x_step <= 0) { split_x_step = 1; }
        x_line --;
        for (int idx = result.image.getWidth() - 1, block = split_x_cnt - 1; idx >= 0 && block >= 0; ) {
            result.fd_offset_x_block[block] = idx;
            block --;
            idx = idx - split_x_step;
            if (idx >= 0 && x_line >= 0) {
                result.fd_offset_x_line[x_line] = idx;
                x_line --;
            }
            idx --;
         }
//        System.out.println(Arrays.toString(offset_x_block));
//        System.out.println(Arrays.toString(offset_x_line));

        int split_y_step = (result.image.getHeight() + 1) / split_y_cnt - 1;
        if (split_y_step <= 0) { split_y_step = 1; }
        y_line --;
        for (int idy = result.image.getHeight() - 1, block = split_y_cnt - 1; idy >= 0 && block >= 0; ) {
            result.fd_offset_y_block[block] = idy;
            block --;
            idy = idy - split_y_step;
            if (idy >= 0 && y_line >= 0) {
                result.fd_offset_y_line[y_line] = idy;
                y_line --;
            }
            idy --;
        }
//        System.out.println(Arrays.toString(offset_y_block));
//        System.out.println(Arrays.toString(offset_y_line));
//        System.out.println("image.width=" + image.getWidth() + ",image.height=" + image.getHeight() + ",xstep=" + split_x_step + ",ystep=" + split_y_step);

        int offset_x = 0;
        int offset_y = 0;
        int cursor_block_x = 0;
        int cursor_block_y = 0;
        int cursor_line_x = 0;
        int cursor_line_y = 0;
        int offset_b = 0;

        int flag_line = 0;
        int flag = 0; /* 0:none,1:block,2:liney,4:linex,6:linexy */ 
//        System.out.println("[x,y(bx,by)(lx,ly)f]");
        for (int j = 0; j < result.image.getHeight(); j ++) {
            buf.append("[");
            if (j == result.fd_offset_y_line [cursor_line_y]) {
//                System.out.print("→");
                flag_line = 4; // linex
            } else {
//                System.out.print("　");
                flag_line = 1; // block
            }
            if (j > result.fd_offset_y_block[cursor_block_y]) {
                cursor_block_y ++;
                if (cursor_block_y >= result.fd_offset_y_block.length) {
                    cursor_block_y = result.fd_offset_y_block.length - 1;
                }
            }
            if (j > result.fd_offset_y_line [cursor_line_y] ) {
                cursor_line_y ++;
                if (cursor_line_y >= result.fd_offset_y_line.length) {
                    cursor_line_y = result.fd_offset_y_line.length - 1;
                }
            }

            cursor_block_x = 0;
            cursor_line_x = 0;
            for (int i = 0; i < result.image.getWidth(); i ++) {
                flag = flag_line;
                if (i == result.fd_offset_x_line [cursor_line_x]) {
//                    System.out.print("↓");
                    if (flag == 4) flag = 6; // linexy
                    else flag = 2; // liney
                } else {
                    if(flag != 4) flag = 1; // block
                }
                if (i > result.fd_offset_x_block[cursor_block_x]) {
                    cursor_block_x ++;
                    if (cursor_block_x >= result.fd_offset_x_block.length) {
                        cursor_block_x = result.fd_offset_x_block.length - 1;
                    }
                }
                if (i > result.fd_offset_x_line [cursor_line_x] ) {
                    cursor_line_x ++;
                    if (cursor_line_x >= result.fd_offset_x_line.length) {
                        cursor_line_x = result.fd_offset_x_line.length - 1;
                    }
                }

                if (flag == 1) { offset_b = offset_block + cursor_block_y * split_x_cnt + cursor_block_x; } // block
                if (flag == 2) { offset_y = offset_line_y + cursor_line_x; } // liney
                if (flag == 4 || flag == 6) { offset_x = offset_line_x + cursor_line_y; } // linex

                final int color = result.image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >>  8) & 0xff;
                final int b =  color        & 0xff;
                String colorr = (r > 127 ? "*" : " ");

                // Feature 25 Blocks
                if ("*".equals(colorr)) {
                    result.features[0] ++; // total
                    if (flag == 1) {
                        // block
                        result.features[offset_b] ++;
                    }
                    if (flag == 2) {
                        // liney
                        result.features[offset_y] ++;
                    }
                    if (flag == 4 || flag == 6) {
                        // linex
                        result.features[offset_x] ++;
                    }
                }

//                System.out.print("[" + (i <= 9 ? "0" : "") + i + "," + (j <= 9 ? "0" : "") + j + 
//                        "(" + cursor_block_x + "," + cursor_block_y +")(" + 
//                              cursor_line_x  + "," + cursor_line_y + ")" + flag + 
//                        "(" + (offset_b <= 9 ? "0" : "") + offset_b + "," + 
//                              (offset_y <= 9 ? "0" : "") + offset_y + "," + 
//                              (offset_x <= 9 ? "0" : "") + offset_x + ")" + colorr + "]");

                buf.append(colorr + "\t");
//                buf.append(colorr);
            }
//            System.out.println();
            buf.append("]\n");
        }
        result.strImg = buf.toString();
        return result;
    }
    
    /**
     * 判断处理的前后顺序对结果的正确与否有影响。
     * @param feature
     * @return
     */
    public static String getCharByFeature(Feature feature) {
        String result = null;
        if (feature.features[1] == 0 &&
                feature.features[2] == 0 &&
                feature.features[6] == 0 &&
                feature.features[13] == 0 &&
                feature.features[21] == 0 &&
                feature.features[22] == 0 &&
                feature.features[23] == 0 &&
                feature.features[25] == 0) {
                    result = "4";
                    return result;
            }
            if (feature.features[6] == 0 &&
                feature.features[7] == 0 &&
                feature.features[11] == 0 &&
                feature.features[12] == 0 &&
                feature.features[15] == 0 &&
                feature.features[16] == 0 &&
                feature.features[19] == 0 &&
                feature.features[20] == 0 &&
                feature.features[24] == 0 &&
                feature.features[25] == 0) {
                    result = "7";
                    return result;
            }
            if (feature.features[7] == 0 &&
                feature.features[8] == 0 &&
                feature.features[11] == 0 &&
                feature.features[12] == 0 &&
                feature.features[19] == 0 &&
                feature.features[20] == 0) {
                    result = "2";
                    return result;
            }
        if (feature.features[18] == 0) {
            /*
             * 11111
             * 11011
             * 00011
             * 00011
             * 00011
             */
            if (feature.features[11] == 0 &&
                feature.features[12] == 0 &&
                feature.features[13] == 0 &&
                feature.features[16] == 0 &&
                feature.features[17] == 0 &&
                feature.features[18] == 0 &&
                feature.features[21] == 0 &&
                feature.features[22] == 0 &&
                feature.features[23] == 0 ) {
                result = "1";
                return result;
            }
            if (feature.features[6] == 0 && // 2020/04/21
                feature.features[7] == 0 &&
                feature.features[8] == 0 &&
                feature.features[11] == 0 &&
                feature.features[17] == 0 &&
                feature.features[18] == 0&&
                feature.features[19] == 0) { // 2020/04/21
                    result = "3";
                    return result;
            }
            // 2020/04/21 ↓↓↓
            if (feature.features[10] == 0 &&
                feature.features[13] == 0 &&
                feature.features[14] == 0 &&
                feature.features[18] == 0 &&
                feature.features[19] == 0) {
                result = "6";
                if ((feature.features[11] == 0 || feature.features[16] == 0) &&
                     feature.features[6] != 0 && feature.features[21] != 0) {
                    result = "5";
                }
                return result;
            }
            // 2020/04/21 ↑↑↑
            if (feature.features[10] == 0 &&
                feature.features[13] == 0 &&
                feature.features[17] == 0 &&
                feature.features[18] == 0 &&
                feature.features[19] == 0) {
                result = "5";
//                if(feature.features[25] == 0) {
//                    // TODO:不是共同特征，不具备通用性。
//                    result = "6";
//                }
                return result;
            }
            if (feature.features[7] == 0 &&
                feature.features[8] == 0 &&
                feature.features[9] == 0 &&
                feature.features[17] == 0 &&
                feature.features[18] == 0 &&
                feature.features[25] == 0) {
                    result = "9";
                    return result;
            }
            if (feature.features[8] == 0 &&
                feature.features[17] == 0 &&
                feature.features[18] == 0 &&
                feature.features[19] == 0) {
                    result = "8";
                    return result;
            }
            result = "6";
            return result;
        }
        return result;
    }
    public static Rect getSudokuRect(BufferedImage image) {
        Rect result = new Rect();
        int w = image.getWidth();
        int h = image.getHeight();
        int step = h / 11;
        int start = (w - h + step ) / 2;
        result.x = start - 2;
        result.y = step / 2;
        result.width = step * 10;
        result.height = step * 10;
        result.makeCutRect();
        return result;
    }

    /**
     * 前提二值化
     * @param binary
     * @param labelMask
     * @param rectangles
     * @param drawBounding
     * @param numOfPixels filterNoise = true,max Number
     * @param filterNoise false
     * @return
     */
    public static int getConnectedArea(BufferedImage binary, int[] labelMask,
            List<Rect> rectangles, boolean drawBounding, int numOfPixels, boolean filterNoise) {
        int width  = binary.getWidth();
        int height = binary.getHeight();
        int[] data = new int[width * height];
        int type = binary.getType();
        if ( type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB ) {
            data = (int [])binary.getRaster().getDataElements(0, 0, width, height, data);
        } else {
            data = binary.getRGB(0, 0, width, height, data, 0, width);
        }

        int[] labels = new int[(width * height) / 2];
        Arrays.fill(labels, -1);

        int[] pixels = new int[width * height];
        Arrays.fill(pixels, -1);

        int ul = -1; // 上方
        int ll = -1; // 左侧
        int[] twoLabels = new int[2];

        int currlabel = 0; // 非连通区域计数

        int p1, p2, p3;
        int yMin = 1;
        int xMin = 1;
        int offset = 0;
        // labels构筑
        for (int row = yMin; row < height; row ++) {
            offset = row * width + xMin;
            for (int col = xMin; col < width; col ++) {
                p1 = ((byte) data[offset]) & 0xff; // 当前
                p2 = ((byte) data[offset - 1]) & 0xff; // left
                p3 = ((byte) data[offset - width]) & 0xff; // upper

                Arrays.fill(twoLabels, -1);
                ll = -1;
                ul = -1;
                if (p1 == 255) {
                    if (p2 == p1) {
                        ll = pixels[offset - 1] < 0 ? -1 : labels[pixels[offset - 1]];
                        twoLabels[0] = ll;
                    }
                    if (p3 == p1) {
                        ul = pixels[offset - width] < 0 ? -1 : labels[pixels[offset - width]];
                        twoLabels[1] = ul;
                    }

                    if (ll < 0 && ul < 0) {
                        pixels[offset] = currlabel;
                        labels[currlabel] = currlabel;

                        currlabel ++;
                    } else {
                        Arrays.sort(twoLabels);
                        int smallestLabel = twoLabels[0];
                        if (twoLabels[0] < 0) {
                            smallestLabel = twoLabels[1];
                        }
                        pixels[offset] = smallestLabel;

                        for (int k = 0; k < twoLabels.length; k ++) {
                            if (twoLabels[k] < 0) {
                                continue;
                            }
                            int tempLabel = twoLabels[k];
                            int oldSmallestLabe = labels[tempLabel];
                            if (oldSmallestLabe > smallestLabel) {
                                labels[oldSmallestLabe] = smallestLabel;
                                labels[tempLabel] = smallestLabel;
                            } else if (oldSmallestLabe < smallestLabel) {
                                labels[smallestLabel] = oldSmallestLabe;
                            }
                        }
                    }
                }
                offset++;
            }
        }

        // labels=>labelSet
        int[] labelSet = new int[currlabel];
        System.arraycopy(labels, 0, labelSet, 0, currlabel);
        labels = null;
        for (int i = 2; i < labelSet.length; i++) {
            int curLabel = labelSet[i];
            int preLabel = labelSet[curLabel];
            while (preLabel != curLabel) {
                curLabel = preLabel;
                preLabel = labelSet[preLabel];
            }
            labelSet[i] = curLabel;
        }

        // 2. second pass
        // aggregation the pixels with same label index
        Map<Integer, List<PixelNode>> aggregationMap = new HashMap<Integer, List<PixelNode>>();
        for (int i = 0; i < height; i ++) {
            offset = i * width;
            List<PixelNode> pixelList = null;
            PixelNode pn = null;
            for (int j = 0; j < width; j ++) {
                int pixelLabel = pixels[offset + j];
                // skip background
                if (pixelLabel < 0) {
                    continue;
                }
                // label each area
                pixels[offset + j] = labelSet[pixelLabel];
                pixelList = aggregationMap.get(labelSet[pixelLabel]);
                if (pixelList == null) {
                    pixelList = new ArrayList<PixelNode>();
                    aggregationMap.put(labelSet[pixelLabel], pixelList);
                }
                pn = new PixelNode();
                pn.row = i;
                pn.col = j;
                pn.index = offset + j;
                pixelList.add(pn);
            }
        }

        // assign labels
        Integer[] keys = aggregationMap.keySet().toArray(new Integer[0]);
        Arrays.fill(labelMask, -1);
        List<PixelNode> pixelList = null;
        int number = 0;
        for (Integer key : keys) {
            pixelList = aggregationMap.get(key);
            if (filterNoise && pixelList.size() < numOfPixels) {
                continue;
            }
            // tag each pixel
            for (PixelNode pnode : pixelList) {
                labelMask[pnode.index] = key;
            }

            // return each label rectangle
            if (drawBounding && rectangles != null) {
                Rect bounding = boundingRect(pixelList);
                bounding.labelIdx = key;
                rectangles.add(bounding);
            }
            number++;
        }

        return number;
    }
    private static Rect boundingRect(List<PixelNode> pixelList) {
        int minX = 10000, maxX = 0;
        int minY = 10000, maxY = 0;
        for (PixelNode pn : pixelList) {
            minX = Math.min(pn.col, minX);
            maxX = Math.max(pn.col, maxX);
            minY = Math.min(pn.row, minY);
            maxY = Math.max(pn.row, maxY);
        }
        int dx = maxX - minX;
        int dy = maxY - minY;
        Rect roi = new Rect();
        roi.x = minX;
        roi.y = minY;
        roi.width = dx;
        roi.height = dy;
        return roi;
    }
}
