import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.*;
/*
 * JFrame vastaa siitä, ketä haluu olla ikkuna, eli luo mahdollisuuden olla ikkuna.
 */
public class MainWindow extends JFrame {

    private Frame window;
/*
 * Tässä säädetään ikkunan nimi(setTitle), ikkunalle mahdollisuus poistua ikkunasta(EXIT_ON_CLOSE), 
 * ikkunan koko (pikselikooltaan korkeus ja leveys), sijainti näytölle (setLocation).
 * Siihen lisätään myös GameField joka on varsinaisen pelin sisältö. 
 * Resiziable on mahdollisuus venyttää ikkunaa.
 * 
 */
	public MainWindow(){
        setTitle("Matopeli");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
        window.setResizable(false);
        
       
    }
/*
 * Luodaan MainWindow metodi, jota käytetään ikkunan avaamiseen. 
 */
    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }
}
