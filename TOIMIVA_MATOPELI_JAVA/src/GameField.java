import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/*
 * T‰ss‰ s‰‰det‰‰n pelikent‰n kokoa, eli 320 on kent‰n koko, itse k‰‰rmeen koko on 16px, ALL_DOTS s‰‰t‰‰ kuinka monta pikseli‰ voi
 * olla ylip‰‰t‰‰n kent‰ll‰, imagella lis‰t‰‰n elementit kuten omena ja itse k‰‰rmeen osat.
 * XY sijainnit omenalle ja ALL_DOTS XY sijainnit k‰‰rmelle. Timer vastaa liikkumisesta paikasta toiseen.
 * boolean vastaa mist‰ k‰‰rme aloittaa liikkua kun peli alkaa, eli oikealle se sitten suuntaa. 
 */
public class GameField extends JPanel implements ActionListener{
    private static final java.awt.Color Color = null;
	private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private Image ball;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
   
/*
 * GameField kutsuu muita metodeja, jotka sis‰lt‰‰ pelikent‰n v‰ri‰, LoadImages avulla lis‰‰ kuvia kansioista,
 * initGame vastaa siis pelin aloituksesta. kutsutaan pelin alku, initGame:lla. FieldKeyListener ottaa n‰pp‰imien hallintaa vastaan.
 * setFocusable vastaa, ett‰ toiminta on p‰‰ll‰ n‰pp‰mistˆn kanssa.
 */
  
    public GameField(){
    	

    	setBackground(Color.white);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    
    
    /*
     * initGame vastaa siit‰ miten peli alkaa. luku 48 tarkoittaa, ett‰ 16bit k‰‰rmeen h‰nt‰ seuraa sen p‰‰t‰. T‰m‰ on s‰‰detty niin
     * ett‰ se tapahtuu luontevasti ruudulla.
     * Liikkuvuus aloitetaan vasemmalta oikealle vaakaasennossa.
     */
    public void initGame(){
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 48;
        }
        /*
         * Timer vaikuttaa siihen, mill‰ nopeudella k‰‰rme liikkuu eli tˆkkii eteenp‰in, ajaksi on laitettu 200ms.
         * samalla metodi "timer.start" aloittaa prosessin ja createApple luo omenan pelikent‰lle.
         */
        timer = new Timer(200,this);
        timer.start();
        createApple();
    }
    	/*
    	 * createApple metodilla kutsutaan edellisess‰ initGame:ssa, omenan koko on asetettu 20 pikseliksi XY tasoissa.
    	 */
    public void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }
/*
 * LoadImages lataa kysesiset dot, eli p‰‰n, ball per‰pallerot (h‰nt‰) ja  omenan.
 */
    public void loadImages(){
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
        ImageIcon iih = new ImageIcon("ball.png");
         ball = iih.getImage();
    }

    /*
     * paintComponent vastaa komponentien piirrosta n‰ytˆlle, eli jos pelin sis‰lle piirrett‰‰n omena, niin 
     * se vastaa pelikent‰n koon mukaista sijaintia. 
     * 
     */
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < dots; i++) {
            	  if (i == 0) {
                g.drawImage(dot,x[i],y[i],this);
                
                    } else {
                        g.drawImage(ball, x[i], y[i], this);
                    }
            }
            /*
             * Myˆs jos peli ei en‰‰ jatku, n‰ytˆlle ilmestyy "Peli Loppui", t‰ss‰ voi myˆs s‰‰t‰‰ sen fonttia. 
             */
        } else{
            String str = "Peli Loppui";
            Font f = new Font("Arial", Font.BOLD, 16);
            g.setColor(Color.black);
            g.setFont(f);
            g.drawString(str,125,SIZE/2);
        }
    }
    	/*
    	 * move metodi vastaaa liikkumisesta, for s‰‰t‰‰ hypyist‰ 2:en v‰lein.
    	 * if kohdalla s‰‰det‰‰n ehtoina jos menn‰‰n ylˆs, alas, oikealle tai vasemalle.
    	 * 
    	 */
    public void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        } if(up){
            y[0] -= DOT_SIZE;
        } if(down){
            y[0] += DOT_SIZE;
        }
    }
/*
 * jos x[0], eli k‰‰rmeen p‰‰ osuu omenaan, niin k‰‰rme saa sen, sek‰ pituutta h‰nn‰lle.
 * Itse omena h‰vi‰‰ ja tulee uudestaan esille createApple metodin avulla.
 */
    
    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            createApple();
        }
    }

    /*
     * T‰ss‰ varmistetaan se, ett‰ jos sin‰ osut pelikent‰n ulkorajaan niin peli loppuu
     * myˆs k‰y niin jos itse osut omaan h‰nt‰‰n niin siin‰kin tapauksessa peli loppuu.
     */
    public void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }

        if(x[0]>SIZE){
            inGame = false;
        }
        if(x[0]<0){
            inGame = false;
        }
        if(y[0]>SIZE){
            inGame = false;
        }
        if(y[0]<0){
            inGame = false;
        }
    }
    /*
     * actionListener MainWindowsissa, tarkistaa actionPerformedilla mit‰ tulee tapahtumaan jokaisen 200ms j‰lkeen.
     * Jos ollaan peliss‰, eli inGame, tarkistetaan Omenan (Apple), Osuminen (Collisions)ja liikkuminen (move)
     * repaint metodi, piirt‰‰ uudelleen grafiikkaa ikkunalle.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            move();

        }
        repaint();
    
    }
/* Alhaalla s‰‰det‰‰n n‰pp‰imistˆˆn nuolin‰pp‰imien toimintaa, eli FieldKeyListener laajentaa keyAdapter:ia
 * KeyPressed vastaa jos jokin nappi on painettu. T‰ss‰ myˆs on tehty se, ett‰ jos liikut oikealle tai vasemalle
 * et voi k‰‰nty‰ yht‰kki‰ vastapuoliseen suuntaan, eli on p‰‰tett‰v‰ liikkua joko oikealle tai vasemalle liikkuvasta suunnasta.
 * 
 * */
    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }

            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }
        }
    }


}
