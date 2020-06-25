package dbjocr.opencv.demo;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

import dbjocr.DbjOcr;
import dbjocr.sudoku.SudokuPainter;
import image.Image;
import sudokudbj.ProcSudoku;
import sudokudbj.model.MdlSudoku;

public class Demo02 extends JFrame implements Runnable, ThreadFactory {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -2705092086872698561L;
//    private Executor executor = Executors.newSingleThreadExecutor(this);

    private JScrollPane jsp = null;
    private JPanel panel = null;
    private Webcam webcam1 = null;
    private WebcamPanel wpanel1 = null;
    private Webcam webcam2 = null;
    private WebcamPanel wpanel2 = null;

    public static void main(String[] args) {
        new Demo02();
    }

    public Demo02() {
        super();

        setLayout(new FlowLayout());
//        setLayout(new BorderLayout());
        setTitle("Solve Readed Sudoku With Webcam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1920, 900));

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
         Dimension size = WebcamResolution.HD.getSize();
        Dimension[] nonStandardResolutions = new Dimension[] {
            WebcamResolution.VGA.getSize(), // 640, 480
            WebcamResolution.PAL.getSize(), // 768, 576
            WebcamResolution.HD.getSize(),  // 1280, 720
            WebcamResolution.HDP.getSize(), // 1600, 900
            WebcamResolution.FHD.getSize(), // 1920, 1080
        };

        this.webcam1 = Webcam.getWebcams().get(0);
        this.webcam1.setCustomViewSizes(nonStandardResolutions);
        this.webcam1.setViewSize(size);

        this.wpanel1 = new WebcamPanel(this.webcam1);
        WebcamPanel.DefaultPainter painter = new SudokuPainter(this.wpanel1);
        this.wpanel1.setPainter(painter);
        this.wpanel1.setPreferredSize(size);
        this.wpanel1.setFPSDisplayed(true);
        this.wpanel1.setDisplayDebugInfo(true);
        this.wpanel1.setImageSizeDisplayed(true);
        this.wpanel1.setMirrored(true);

        this.panel.add(this.wpanel1);

        this.webcam2 = Webcam.getWebcams().get(1);
        this.webcam2.setCustomViewSizes(nonStandardResolutions);
        this.webcam2.setViewSize(size);

        this.wpanel2 = new WebcamPanel(this.webcam2);
        WebcamPanel.DefaultPainter painter2 = new SudokuPainter(this.wpanel2);
        this.wpanel2.setPainter(painter2);
        this.wpanel2.setPreferredSize(size);
        this.wpanel2.setFPSDisplayed(true);
        this.wpanel2.setDisplayDebugInfo(true);
        this.wpanel2.setImageSizeDisplayed(true);
        this.wpanel2.setMirrored(true);

        this.panel.add(this.wpanel2);

        this.jsp = new JScrollPane(this.panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.jsp.setPreferredSize(new Dimension(1900, 750));
        JScrollBar jsb= this.jsp.getHorizontalScrollBar();//
        jsb.setMinimum(1);
        jsb.setValue((int)(jsb.getMaximum() - this.jsp.getPreferredSize().getWidth()) / 2);//
        add(this.jsp);

        final JButton button = new JButton("拍照");
        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button.setEnabled(false);  //设置按钮不可点击
                if (!webcam1.isOpen()) {
                    webcam1.open();
                }
                try {
                    ImageIO.write(Image.imageMisro(webcam1.getImage(), 1)
                            , ImageUtils.FORMAT_JPG, new File("./output/" + System.currentTimeMillis() + "L.jpg"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
//                WebcamUtils.capture(webcam2, "./output/" + System.currentTimeMillis() + "R", ImageUtils.FORMAT_JPG);
                if (!webcam2.isOpen()) {
                    webcam2.open();
                }
                try {
                    ImageIO.write(Image.imageMisro(webcam2.getImage(), 1)
                            , ImageUtils.FORMAT_JPG, new File("./output/" + System.currentTimeMillis() + "R.jpg"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(null, "拍照成功");
                        button.setEnabled(true);    //设置按钮可点击
                        return;
                    }
                });
            }
        });

        add(button);
        pack();
        setVisible(true);

//        executor.execute(this);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "sudoku-runner");
        t.setDaemon(true);
        return t;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            MdlSudoku result = null;
            BufferedImage image = null;

            if (webcam1.isOpen()) {
                if ((image = webcam1.getImage()) == null) {
                    continue;
                }

                // check image;
                try {
                    result = new MdlSudoku(DbjOcr.proc(image));
                    ProcSudoku proc = new ProcSudoku(1);
                    proc.proc(result);
                } catch (Exception e) {
                }
            }

            if (result != null) {
//                textarea.setText(result.getText());
            }

        } while (true);
    }

}
