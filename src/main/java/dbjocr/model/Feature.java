package dbjocr.model;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Feature {
    /* feature define */
    public int[] fd_offset_x_block;
    public int[] fd_offset_y_block;
    public int[] fd_offset_x_line;
    public int[] fd_offset_y_line;
	public int[] features;
	public BufferedImage image;
	public Rect rect;
	public int x;
	public int y;
	public String strImg;
	
	public Feature() {
		this.features = new int[34];
		this.image = null;
	}
	public Feature(BufferedImage image) {
		this.features = new int[34];
		this.image = image;
		this.rect = null;
	}
   public Feature(BufferedImage image, Rect rc) {
        this.features = new int[34];
        this.image = image;
        this.rect = rc;
    }
   
	@Override
	public String toString() {
	    StringBuilder buf = new StringBuilder();
	    buf.append(Arrays.toString(features).replaceAll(",", "\t"));
	    buf.append("\n");
	    buf.append(Arrays.toString(fd_offset_x_block).replaceAll(",", "\t"));
	    buf.append("\n");
	    buf.append(Arrays.toString(fd_offset_y_block).replaceAll(",", "\t"));
        buf.append("\n");
        buf.append(Arrays.toString(fd_offset_x_line).replaceAll(",", "\t"));
        buf.append("\n");
        buf.append(Arrays.toString(fd_offset_y_line).replaceAll(",", "\t"));
        buf.append("\n\n");
        buf.append(strImg);
		return buf.toString();
	}
}
