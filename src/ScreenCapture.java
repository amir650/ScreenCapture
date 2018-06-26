import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ScreenCapture {

    private final JFrame mainFrame;
    private final JLabel statusLabel;
    private final JPanel controlPanel;

    private ScreenCapture() {
        this.mainFrame = new JFrame("Screen Capture");
        this.mainFrame.setPreferredSize(new Dimension(800, 200));
        this.mainFrame.setLayout(new GridLayout(2, 1));
        this.statusLabel = new JLabel("", JLabel.CENTER);
        this.controlPanel = new JPanel();
        this.controlPanel.setLayout(new FlowLayout());
        this.mainFrame.add(this.controlPanel);
        this.mainFrame.add(this.statusLabel);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    private void show() {
        final JButton okButton = new JButton("Take ScreenCapture!");
        okButton.addActionListener(e -> {
            ScreenCapture.this.mainFrame.setVisible(false);
            try {
                final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
                final Robot robot = new Robot();
                final BufferedImage image = robot.createScreenCapture(new Rectangle(screenDimension));
                final File savePath = new File("/Users/amir.afghani/Desktop/amir_screen_cap.jpg");
                ImageIO.write(image, "JPG", savePath);
                ScreenCapture.this.statusLabel.setText("Saved to : " +savePath.getAbsolutePath());
                ScreenCapture.this.mainFrame.setVisible(true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
        this.controlPanel.add(okButton);
    }

    public static void main(String[] args) {
        final ScreenCapture screenCapture = new ScreenCapture();
        screenCapture.show();
    }
}
