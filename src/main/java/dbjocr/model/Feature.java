package dbjocr.model;

import java.awt.image.BufferedImage;

public class Feature {
    /* feature define */
    public int[] fd_offset_x_block;
    public int[] fd_offset_y_block;
    public int[] fd_offset_x_line;
    public int[] fd_offset_y_line;
	public int[] features;
	public BufferedImage image;
	public double hwR;
	public Rect rect;
	public int x;
	public int y;
	public String strImg;
	
	public double[] rate;
	public int[] offset;
	public double[] rw;
	public double[] rh;
	
	public int[] bgblock;
	public int[] across;
	
	public Feature() {
		/*
		 * 0:total
		 * 1-25: 5*5grid
		 * 26-29: block纵向不含与横向交点。
		 * 30-33: blockW横向包含与纵向的交点。
		 */
		this.features = new int[34];
		this.image = null;
		this.hwR = -1.0D;
	}
	public Feature(BufferedImage image) {
		this.features = new int[34];
		this.image = image;
		this.hwR = this.image.getHeight() * 1.0D / this.image.getWidth();
		this.rect = null;
	}
   public Feature(BufferedImage image, Rect rc) {
        this.features = new int[34];
        this.image = image;
        this.hwR = this.image.getHeight() * 1.0D / this.image.getWidth();
        this.rect = rc;
    }
   
	@Override
	public String toString() {
	    StringBuilder buf = new StringBuilder();
	    buf.append(this.rect);
	    buf.append("图像宽高:Width=" + image.getWidth() + ",Height=" + image.getHeight() + ",高宽比:" + this.hwR + "\n");
//	    buf.append("x=" + x + ",y=" + y + "\n");
//	    buf.append(Arrays.toString(features).replaceAll(",", "\t"));
//	    buf.append("\n1x块w像素");
//	    buf.append(Arrays.toString(fd_offset_x_block).replaceAll(",", "\t"));
//	    buf.append("\n2y块h像素");
//	    buf.append(Arrays.toString(fd_offset_y_block).replaceAll(",", "\t"));
//        buf.append("\n3x线w像素");
//        buf.append(Arrays.toString(fd_offset_x_line).replaceAll(",", "\t"));
//        buf.append("\n4y线h像素");
//        buf.append(Arrays.toString(fd_offset_y_line).replaceAll(",", "\t"));
//        buf.append("\n5\n");
        buf.append(strImg);
		return buf.toString();
	}
}
