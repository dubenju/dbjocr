package image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;

import image.model.Rect;

public class Image {
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
    public static List<BufferedImage> cutImage(BufferedImage in, List<Rect> rects) {
        List<BufferedImage> result = new ArrayList<BufferedImage>();
        for (Rect rc : rects) {
            BufferedImage im = new BufferedImage(rc.width, rc.height, BufferedImage.TYPE_INT_RGB);
            im.getGraphics().drawImage(in, 0 - rc.x, 0 -  rc.y, null);
            result.add(im);
        }
        return result;
    }

    public static BufferedImage[] cutImage(BufferedImage image, int rows, int cols) {
        int chunks = rows * cols;
        
        // 计算每个小图的宽度和高度
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;
 
        int count = 0;
        BufferedImage[] imgs = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //设置小图的大小和类型
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
 
                //写入图像内容
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0,
                        chunkWidth, chunkHeight,
                        chunkWidth* y, chunkHeight * x,
                        chunkWidth * y + chunkWidth,
                        chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        return imgs;
    }

    /**
     * 
     * @param bufferedImage 图片
     * @param angel 旋转角度
     * @return
     */
    public static BufferedImage rotateImage(BufferedImage bufferedImage, int angel) {
        if (bufferedImage == null) {
            return null;
        }
        if (angel < 0) {
            // 将负数角度，纠正为正数角度
            angel = angel + 360;
        }
        int imageWidth = bufferedImage.getWidth(null);
        int imageHeight = bufferedImage.getHeight(null);
        // 计算重新绘制图片的尺寸
        Rectangle rectangle = calculatorRotatedSize(new Rectangle(new Dimension(imageWidth, imageHeight)), angel);
        // 获取原始图片的透明度
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage newImage = null;
        newImage = new BufferedImage(rectangle.width, rectangle.height, type);

        Graphics2D graphics = newImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        // 平移位置
        graphics.translate((rectangle.width - imageWidth) / 2, (rectangle.height - imageHeight) / 2);
        // 旋转角度
        graphics.rotate(Math.toRadians(angel), imageWidth / 2, imageHeight / 2);
        // 绘图
        graphics.drawImage(bufferedImage, null, null);
        graphics.dispose();
        return newImage;
    }
    /**
     * 计算旋转后的尺寸
     * 
     * @param src
     * @param angel
     * @return
     */
    private static Rectangle calculatorRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }
    /**
     * 图片翻转时，计算图片翻转到正常显示需旋转角度
     */
    public static int getRotateAngleForPhoto(File file) {
//        File file = new File(fileName);
        int angel = 0;
        try {
            //核心对象操作对象
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            //获取所有不同类型的Directory，如ExifSubIFDDirectory, ExifInteropDirectory, ExifThumbnailDirectory等，这些类均为ExifDirectoryBase extends Directory子类
            //分别遍历每一个Directory，根据Directory的Tags就可以读取到相应的信息
//            int orientation = 0;
//            Iterable<Directory> iterable = metadata.getDirectories();
//            for (Iterator<Directory> iter = iterable.iterator(); iter.hasNext(); ) {
//                Directory dr = iter.next();
//                if (dr.getString(ExifIFD0Directory.TAG_ORIENTATION) != null) {
//                    orientation = dr.getInt(ExifIFD0Directory.TAG_ORIENTATION);
//                }
//                /*Collection<Tag> tags = dr.getTags();
//                for (Tag tag : tags) {
//               System.out.println(tag.getTagName() + "： " + tag.getDescription());
//            }*/
//            }
            Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            int orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
            if (orientation == 0 || orientation == 1) {
                angel = 360;
            } else if (orientation == 3) {
                angel = 180;
            } else if (orientation == 6) {
                angel = 90;
            } else if (orientation == 8) {
                angel = 270;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return angel;
    }
    /**
     * 对图片进行放大
     * @param originalImage 原始图片
     * @param times 放大倍数
     * @return
     */

    public static BufferedImage  zoomInImage(BufferedImage  originalImage, double times){

        int width = (int) (originalImage.getWidth()* times);
        int height = (int) (originalImage.getHeight()*times);

        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());

        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();

        return newImage;

    }
    /**
     * 对图片进行缩小
     * @param originalImage 原始图片
     * @param times 缩小倍数
     * 
     * @param reducewidth 缩放后减小宽度(当times=-1时此参数及是图片宽度)
     * @param reduceheight缩放后减小高度(当times=-1时此参数及是图片宽度)
     * @return 缩小后的Image
     */
    public static BufferedImage  zoomOutImage(BufferedImage  originalImage, Integer times,int reducewidth,int reduceheight){

        int width = originalImage.getWidth()/times-reducewidth;
        if(width < 0){
            width=originalImage.getWidth();
        }
        int height = originalImage.getHeight()/times-reduceheight;
        if(height < 0){
            height=originalImage.getHeight();
        }
        if(times == -1){
            width= reducewidth;
            height =reduceheight;
        }
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());

        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();

        return newImage;

    }
    /*
   *
   * 镜像处理，输入image和方式，返回翻转的新image
   * type = 0 表示上下翻转，type = 1 表示左右翻转
   */
   public static BufferedImage imageMisro(BufferedImage bufferedimage,int type ) {
       int w = bufferedimage.getWidth();
       int h = bufferedimage.getHeight();

       int[][] datas = new int[w][h];
       for (int i = 0; i < h; i++) {
         for (int j = 0; j < w; j++) {
           datas[j][i] = bufferedimage.getRGB(j, i);
         }
       }
       int[][] tmps = new int[w][h];
       if (type == 0) {
         for (int i = 0, a = h - 1; i < h; i++, a--) {
           for (int j = 0; j < w; j++) {
             tmps[j][a] = datas[j][i];
           }
         }
       } else if (type == 1) {
           for (int i = 0; i < h; i++) {
               for (int j = 0, b = w - 1; j < w; j++, b --) {
                   tmps[b][i] = datas[j][i];
               }
           }
       }
       for (int i = 0; i < h; i++){
         for (int j = 0; j<w ;j++){
           bufferedimage.setRGB(j, i, tmps[j][i]);
         }
       }
       return bufferedimage;
   }

}
