
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Penghalusan extends JFrame {
    BufferedImage citra;        /*variabelcitra yg menngunakan buffed java*/
    int lebarGbr, tinggiGbr;    /*variable menampung nilai lebar dan panjang citra*/
    String warna;               /*variabke menampung jenis warna yg diminta*/
    int wMerah,wHijau,wBiru;    /*variable menampung nilao Merah hijau biru pada citra*/
    private JLabel gambar;
    private JLabel gambar1;
    
    public Penghalusan() {
        super ("Penghalusan Citra");
        setLayout(new FlowLayout());
        
        try {
            File masukan = new File("D:\\img.jpg");
            citra = ImageIO.read(masukan);
            ImageIcon imageicon1 = new ImageIcon(citra);
            gambar1 = new JLabel(imageicon1);
            lebarGbr = citra.getWidth();
            tinggiGbr = citra.getHeight();
                for (int p = 0; p < lebarGbr; p++) {
                    for (int q = 0; q < tinggiGbr; q++) {
                        Color warnaPiksel = new Color(citra.getRGB(p,q));
                        wMerah = hitungRataNilai(p,q,"MERAH",warnaPiksel);
                        wHijau = hitungRataNilai(p,q,"HIJAU",warnaPiksel);
                        wBiru = hitungRataNilai(p,q,"BIRU",warnaPiksel);
                        citra.setRGB(p,q,new Color(wMerah,wHijau,wBiru).getRGB());
                    }
                }
                
            ImageIcon imageicon = new ImageIcon(citra);
            gambar = new JLabel(imageicon);
            
            add(gambar1);
            add(gambar);
            setVisible(true);
            setSize(650,400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (IOException entok) { }
        }
   private int hitungRataNilai(int p, int q, String namaWarna,Color warnaPiksel) {
       int nilaiRerata= 0;
       nilaiRerata= (ambilNilaiPiksel(p-1,q,namaWarna,warnaPiksel)+
       ambilNilaiPiksel(p,q,namaWarna,warnaPiksel)+
       ambilNilaiPiksel(p+1,q,namaWarna,warnaPiksel)+
       ambilNilaiPiksel(p,q-1,namaWarna,warnaPiksel)+
       ambilNilaiPiksel(p,q+1,namaWarna,warnaPiksel))/5;
       return nilaiRerata;
   }
   
   public int ambilNilaiPiksel (int p, int q, String namaWarna,Color warnaPiksel)
   {
       int nilaiPiksel = 0;
       
       if(q>citra.getHeight() || p<0 || p>citra.getWidth() || q<0) {
           return 0;
       } else {
           switch (namaWarna) {
               case "MERAH":
               nilaiPiksel = warnaPiksel.getRed();
               break;
               case "HIJAU":
               nilaiPiksel = warnaPiksel.getGreen();
               break;
               case "BIRU":
               nilaiPiksel = warnaPiksel.getBlue();
               break;
           }
       }
                return nilaiPiksel;
                
    }
    public static void main (String[] args) {
     Penghalusan smuting = new Penghalusan();
    }
}
