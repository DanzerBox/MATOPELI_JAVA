import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.*;
/*
 * JFrame vastaa siit�, ket� haluu olla ikkuna, eli luo mahdollisuuden olla ikkuna.
 */
public class MainWindow extends JFrame {

    private Frame window;
/*
 * T�ss� s��det��n ikkunan nimi(setTitle), ikkunalle mahdollisuus poistua ikkunasta(EXIT_ON_CLOSE), 
 * ikkunan koko (pikselikooltaan korkeus ja leveys), sijainti n�yt�lle (setLocation).
 * Siihen lis�t��n my�s GameField joka on varsinaisen pelin sis�lt�. 
 * Resiziable on mahdollisuus venytt�� ikkunaa.
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
 * Luodaan MainWindow metodi, jota k�ytet��n ikkunan avaamiseen. 
 */
    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }
}
