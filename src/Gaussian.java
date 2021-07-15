


import java.awt.*;
import java.applet.Applet;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;

public class Gaussian extends Applet{
 private BufferedImage bi;
    float[] elements = { 1/16f, 1/8f, 1/16f,
                         1/8f, 1/4f, 1/8f,
                         1/16f, 1/8f, 1/16f};
    public Gaussian() {
        setBackground(Color.white);
        Image img = getToolkit().getImage("D:\\img.jpg");
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {}
        int iw = img.getWidth(this);
        int ih = img.getHeight(this);
        bi = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB);
        Graphics2D big = bi.createGraphics();
        big.drawImage(img,0,0,this);    
        }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int w = getSize().width;
        int h = getSize().height;
        int bw = bi.getWidth(this);
        int bh = bi.getHeight(this);
        
        AffineTransform at = new AffineTransform();
        at.scale(w/2.0/bw, h/1.0/bh);// pemetaan gamabar lebar jadi 2 tinggi jadi 1
        
        BufferedImageOp biop = null;
        BufferedImage bimg = new BufferedImage(bw, bh, BufferedImage.TYPE_INT_RGB);
        
        Kernel kernel = new Kernel(3,3,elements);
        ConvolveOp cop = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        cop.filter(bi, bimg);
        biop = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        
        g2.drawImage(bi, biop, 0,0);//0 utk jarak kesampingkanan ,, 0 ut jarak kebawah
        g2.drawImage(bimg, biop, w/2+5,0); //w/2+5 utk jarak anatar gmbr 1 dan 2
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WindowListener l = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {System.exit(0);}
    };
    Frame f = new Frame("Sharpen");
    f.addWindowListener(l);
    f.add("Center", new Gaussian());
    f.pack();
    f.setSize(new Dimension(600, 300));
    f.show();
    }
    
}
