package dbjocr.model;

public class Rect {

    public int x;
    public int y;
    public int width;
    public int height;
    public int labelIdx; // just use it for ccl
    public int cut_x;
    public int cut_y;
    public int cut_width;
    public int cut_height;

    public Point tl() {
        return new Point(x, y);
    }

    public Point br() {
        return new Point(x+width, y+height);
    }

    public void makeCutRect() {
    	this.cut_x = this.x - 1;
    	this.cut_width = this.width + 2;
    	if (this.cut_x < 0) {
    		this.cut_x = 0;
    		this.cut_width = this.width + 1;
    	}
    	this.cut_y = this.y - 1;
    	this.cut_height = this.height + 2;
    	if (this.cut_y < 0) {
    		this.cut_y = 0;
    		this.cut_height = this.height + 1;
    	}
    }
    public void makeCutRect2() {
    	this.cut_x = this.x;
    	this.cut_width = this.width;
    	this.cut_y = this.y;
    	this.cut_height = this.height;
    }

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Rect [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", labelIdx=" + labelIdx + "]");
		buf.append("Rect [cx=" + cut_x + ", cy=" + cut_y + ", cwidth=" + cut_width + ", cheight=" + cut_height + "]");
		return buf.toString();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
