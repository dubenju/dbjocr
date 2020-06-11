package dbjocr.sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import dbjocr.DbjOcr;
import sudokudbj.ProcSudoku;
import sudokudbj.model.MdlSudoku;

public class Webcam2 extends JFrame implements Runnable, ThreadFactory {


    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -2705092086872698561L;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    private Webcam webcam = null;
    private WebcamPanel panel = null;
//    private JTextArea textarea = null;
    private Webcam webcam2 = null;
    private WebcamPanel panel2 = null;

    public static void main(String[] args) {
        new Webcam2();
    }

    public Webcam2() {
        super();

        setLayout(new FlowLayout());
        setTitle("Solve Readed Sudoku With Webcam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dimension size = WebcamResolution.HD.getSize();
        Dimension size = WebcamResolution.PAL.getSize();
        Dimension[] nonStandardResolutions = new Dimension[] {
            WebcamResolution.PAL.getSize(),
            WebcamResolution.HD.getSize(),
            new Dimension(720, 480),
            WebcamResolution.HD.getSize(),
            new Dimension(1920, 1080),
            new Dimension(1000, 500),
        };

        webcam = Webcam.getWebcams().get(0);
        webcam.setCustomViewSizes(nonStandardResolutions);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        WebcamPanel.DefaultPainter painter = new SudokuPainter(panel);
        panel.setPainter(painter);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

//        textarea = new JTextArea();
//        textarea.setEditable(false);
//        textarea.setPreferredSize(size);

        add(panel);
//        add(textarea);

        webcam2 = Webcam.getWebcams().get(1);
        webcam2.setCustomViewSizes(nonStandardResolutions);
        webcam2.setViewSize(size);

        panel2 = new WebcamPanel(webcam2);
        WebcamPanel.DefaultPainter painter2 = new SudokuPainter(panel2);
        panel2.setPainter(painter2);
        panel2.setPreferredSize(size);
        panel2.setFPSDisplayed(true);
        panel2.setDisplayDebugInfo(true);
        panel2.setImageSizeDisplayed(true);
        panel2.setMirrored(true);

        add(panel2);

        pack();
        setVisible(true);

        executor.execute(this);
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

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
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
