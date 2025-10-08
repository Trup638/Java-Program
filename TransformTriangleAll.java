import java.awt.*;
import javax.swing.*;

public class TransformTriangleAll extends JPanel {
    double angle;     // rotation angle in radians
    double scaleX, scaleY;
    int tx, ty;       // translation
    double shx, shy;  // shear

    public TransformTriangleAll() {
        this.angle = Math.toRadians(45); // rotate 45 degrees
        this.scaleX = 1.5;               // scale X
        this.scaleY = 1.2;               // scale Y
        this.tx = 100;                   // translate X
        this.ty = 50;                    // translate Y
        this.shx = 0.5;                  // shear X
        this.shy = 0.3;                  // shear Y
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Original triangle
        int[] xPoints = {100, 150, 200};
        int[] yPoints = {200, 100, 200};

        // === Original (Blue) ===
        g2.setColor(Color.BLUE);
        g2.drawPolygon(xPoints, yPoints, 3);

        // === Translation (Green) ===
        int[] xTranslated = new int[3];
        int[] yTranslated = new int[3];
        for (int i = 0; i < 3; i++) {
            xTranslated[i] = xPoints[i] + tx;
            yTranslated[i] = yPoints[i] + ty;
        }
        g2.setColor(Color.GREEN);
        g2.drawPolygon(xTranslated, yTranslated, 3);

        // === Scaling (Orange) ===
        int[] xScaled = new int[3];
        int[] yScaled = new int[3];
        for (int i = 0; i < 3; i++) {
            xScaled[i] = (int) (xPoints[i] * scaleX);
            yScaled[i] = (int) (yPoints[i] * scaleY);
        }
        g2.setColor(Color.ORANGE);
        g2.drawPolygon(xScaled, yScaled, 3);

        // === Rotation around centroid (Red) ===
        int[] xRotated = new int[3];
        int[] yRotated = new int[3];
        double cx = (xPoints[0] + xPoints[1] + xPoints[2]) / 3.0;
        double cy = (yPoints[0] + yPoints[1] + yPoints[2]) / 3.0;

        for (int i = 0; i < 3; i++) {
            double xShifted = xPoints[i] - cx;
            double yShifted = yPoints[i] - cy;
            double xRot = xShifted * Math.cos(angle) - yShifted * Math.sin(angle);
            double yRot = xShifted * Math.sin(angle) + yShifted * Math.cos(angle);
            xRotated[i] = (int) (xRot + cx);
            yRotated[i] = (int) (yRot + cy);
        }
        g2.setColor(Color.RED);
        g2.drawPolygon(xRotated, yRotated, 3);

        // === Shear (Magenta) ===
        int[] xShear = new int[3];
        int[] yShear = new int[3];
        for (int i = 0; i < 3; i++) {
            xShear[i] = (int) (xPoints[i] + shx * yPoints[i]);
            yShear[i] = (int) (yPoints[i] + shy * xPoints[i]);
        }
        g2.setColor(Color.MAGENTA);
        g2.drawPolygon(xShear, yShear, 3);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Transformations - Triangle (All)");
        TransformTriangleAll panel = new TransformTriangleAll();
        frame.add(panel);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
