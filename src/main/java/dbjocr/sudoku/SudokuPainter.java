package dbjocr.sudoku;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.WebcamPanel;

public class SudokuPainter extends WebcamPanel.DefaultPainter {
    public SudokuPainter(WebcamPanel panel) {
        panel.super();
    }

    @Override
    public void paintImage(WebcamPanel owner, BufferedImage image, Graphics2D g2) {
        System.out.println(System.currentTimeMillis());
        super.paintImage(owner, image, g2);
        
        int pw = owner.getWidth();
        int ph = owner.getHeight();
        int iw = image.getWidth();
        int ih = image.getHeight();
        int step = ph / 11;
        int start = (pw - ph) / 2 + step;
        //System.out.println("pw=" + pw + ",ph=" + ph  + ",iw=" + iw  + ",ih=" + ih );
        g2.setColor(Color.RED);

        g2.drawRect(start + step * 1, step, step, step * 9);
        g2.drawRect(start + step * 4, step, step, step * 9);
        g2.drawRect(start + step * 7, step, step, step * 9);

        g2.drawRect(start, step * 2, step * 9, step);
        g2.drawRect(start, step * 5, step * 9, step);
        g2.drawRect(start, step * 8, step * 9, step);

        Stroke stroke=new BasicStroke(3.0f);//设置线宽为3.0
        g2.setStroke(stroke);
        g2.drawRect(start, step, step * 9, step * 9);
        g2.drawLine(start + step * 3, step, start + step * 3, step * 10);
        g2.drawLine(start + step * 6, step, start + step * 6, step * 10);
        g2.drawLine(start, step * 4, start + step * 9, step * 4);
        g2.drawLine(start, step * 7, start + step * 9, step * 7);
        
        g2.setColor(Color.GREEN);
        g2.drawLine(start - 10, step - 10, start + 10, step - 10);
        g2.drawLine(start - 10, step - 10, start - 10, step + 10);

        g2.drawLine(start - 10, step + step * 9 + 10, start - 10, step + step * 9 - 10);
        g2.drawLine(start - 10, step + step * 9 + 10, start + 10, step + step * 9 + 10);
        
        g2.drawLine(start + step * 9 - 10, step - 10, start + step * 9 + 10, step - 10);
        g2.drawLine(start + step * 9 + 10, step - 10, start + step * 9 + 10, step + 10);

       g2.drawLine(start + step * 9 - 10, step + step * 9 + 10, start + step * 9 + 10, step + step * 9 + 10);
       g2.drawLine(start + step * 9 + 10, step + step * 9 - 10, start + step * 9 + 10, step + step * 9 + 10);

       g2.drawLine(start + step * 3 - 10, step + step * 3 - 10, start + step * 3 - 20, step + step * 3 - 10);
       g2.drawLine(start + step * 3 - 10, step + step * 3 - 10, start + step * 3 - 10, step + step * 3 - 20);
       g2.drawLine(start + step * 3 + 10, step + step * 3 - 10, start + step * 3 + 20, step + step * 3 - 10);
       g2.drawLine(start + step * 3 + 10, step + step * 3 - 10, start + step * 3 + 10, step + step * 3 - 20);
       g2.drawLine(start + step * 3 - 10, step + step * 3 + 10, start + step * 3 - 20, step + step * 3 + 10);
       g2.drawLine(start + step * 3 - 10, step + step * 3 + 10, start + step * 3 - 10, step + step * 3 + 20);
       g2.drawLine(start + step * 3 + 10, step + step * 3 + 10, start + step * 3 + 20, step + step * 3 + 10);
       g2.drawLine(start + step * 3 + 10, step + step * 3 + 10, start + step * 3 + 10, step + step * 3 + 20);

       g2.drawLine(start + step * 3 - 10, step + step * 6 - 10, start + step * 3 - 20, step + step * 6 - 10);
       g2.drawLine(start + step * 3 - 10, step + step * 6 - 10, start + step * 3 - 10, step + step * 6 - 20);
       g2.drawLine(start + step * 3 + 10, step + step * 6 - 10, start + step * 3 + 20, step + step * 6 - 10);
       g2.drawLine(start + step * 3 + 10, step + step * 6 - 10, start + step * 3 + 10, step + step * 6 - 20);
       g2.drawLine(start + step * 3 - 10, step + step * 6 + 10, start + step * 3 - 20, step + step * 6 + 10);
       g2.drawLine(start + step * 3 - 10, step + step * 6 + 10, start + step * 3 - 10, step + step * 6 + 20);
       g2.drawLine(start + step * 3 + 10, step + step * 6 + 10, start + step * 3 + 20, step + step * 6 + 10);
       g2.drawLine(start + step * 3 + 10, step + step * 6 + 10, start + step * 3 + 10, step + step * 6 + 20);

       g2.drawLine(start + step * 6 - 10, step + step * 3 - 10, start + step * 6 - 20, step + step * 3 - 10);
       g2.drawLine(start + step * 6 - 10, step + step * 3 - 10, start + step * 6 - 10, step + step * 3 - 20);
       g2.drawLine(start + step * 6 + 10, step + step * 3 - 10, start + step * 6 + 20, step + step * 3 - 10);
       g2.drawLine(start + step * 6 + 10, step + step * 3 - 10, start + step * 6 + 10, step + step * 3 - 20);
       g2.drawLine(start + step * 6 - 10, step + step * 3 + 10, start + step * 6 - 20, step + step * 3 + 10);
       g2.drawLine(start + step * 6 - 10, step + step * 3 + 10, start + step * 6 - 10, step + step * 3 + 20);
       g2.drawLine(start + step * 6 + 10, step + step * 3 + 10, start + step * 6 + 20, step + step * 3 + 10);
       g2.drawLine(start + step * 6 + 10, step + step * 3 + 10, start + step * 6 + 10, step + step * 3 + 20);

       g2.drawLine(start + step * 6 - 10, step + step * 6 - 10, start + step * 6 - 20, step + step * 6 - 10);
       g2.drawLine(start + step * 6 - 10, step + step * 6 - 10, start + step * 6 - 10, step + step * 6 - 20);
       g2.drawLine(start + step * 6 + 10, step + step * 6 - 10, start + step * 6 + 20, step + step * 6 - 10);
       g2.drawLine(start + step * 6 + 10, step + step * 6 - 10, start + step * 6 + 10, step + step * 6 - 20);
       g2.drawLine(start + step * 6 - 10, step + step * 6 + 10, start + step * 6 - 20, step + step * 6 + 10);
       g2.drawLine(start + step * 6 - 10, step + step * 6 + 10, start + step * 6 - 10, step + step * 6 + 20);
       g2.drawLine(start + step * 6 + 10, step + step * 6 + 10, start + step * 6 + 20, step + step * 6 + 10);
       g2.drawLine(start + step * 6 + 10, step + step * 6 + 10, start + step * 6 + 10, step + step * 6 + 20);
       System.out.println(System.currentTimeMillis());
    }
}
