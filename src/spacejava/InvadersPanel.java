/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacejava;
import weapons.Bullet;
import weapons.Bomb;
import ship.Invader;
import ship.Shooter;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Giovane
 */
// Classe que implementa o painel do jogo. O painel controlará os elementos
// do jogo e a renderização.
public class InvadersPanel extends JPanel implements Runnable, KeyListener {
    // Dimensões da área do jogo.
    private static final int largura = 1000;
    private static final int altura = 800; 
    // Uma thread para controlar a animação.
    private Thread animator;
    private boolean isPaused = false;
    // Teremos alguns UFOs passeando na tela.
    private ArrayList<Invader> invasores;
    private static final int NUM_INVASORES = 10;
    // O shooter e sua direção de movimento.
    private Shooter shooter;
    private Direction dir;
    // Um conjunto de tiros.
    private ArrayList<Bullet> tiros;
    private static final int MAX_TIROS = 3;
    // Uma única bomba.
    private Bomb bomba;
    private int numBombas;
    private static final int NUM_BOMBAS_DISPONIVEL = 3;

    // Construtor, inicializa estruturas, registra interfaces, etc.
    public InvadersPanel(){
        setPreferredSize(new Dimension(largura,altura));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        invasores = new ArrayList<Invader>(NUM_INVASORES);
        for(int i=0;i<NUM_INVASORES;i++)
            invasores.add(new Invader(this.getPreferredSize()));
            shooter = new Shooter(this.getPreferredSize());
            tiros = new ArrayList<Bullet>(MAX_TIROS);
            bomba = null;
            numBombas = NUM_BOMBAS_DISPONIVEL;
    }
    // Avisa que agora temos a interface em um container parente.
    public void addNotify(){
        super.addNotify();
        startGame();
    }
    // Iniciamos o jogo (e a thread de controle)
    private void startGame(){
        if (animator == null){
            animator = new Thread(this);
            animator.start();
        }
    }
    // Laço principal do jogo.
    public void run(){
        while(true){
            gameUpdate();
            repaint();
            try { Thread.sleep(10); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
    
     // Atualizamos os elementos do jogo.
    private synchronized void gameUpdate(){
        if (!isPaused) {
            // Movemos os sprites.
            for(Invader i:invasores) i.move();
            shooter.move(dir);
            for(Bullet b:tiros) b.move();
            if (bomba != null) bomba.move();
            // Temos balas desativadas?
            Iterator<Bullet> it = tiros.iterator();
            while (it.hasNext()){
                if (!(it.next()).estáAtivo()) it.remove(); 
            }
            // Temos invasores desativados?
            Iterator<Invader> ii = invasores.iterator();
            while (ii.hasNext()){
                if (!(ii.next()).estáAtivo()) ii.remove(); 
            }
            // A bomba está desativada?
            if (bomba != null) if (!bomba.estáAtivo()) bomba = null;
            // Temos colisões com balas?
            for(Bullet b:tiros)
            for(Invader i:invasores)
            if (b.acertouEm(i)) i.desativa();
            // Temos colisões com bombas?
            if (bomba != null)
            for(Invader i:invasores)
            if (bomba.acertouEm(i)){ 
                tiros.addAll(bomba.explode()); i.desativa(); 
            }
        }
    }
 
    // Desenhamos o componente (e seus elementos)
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = new ImageIcon(getClass().getResource("/sprites/background.png")).getImage();
        g.drawImage(background, 0, 0, this);
        for(Invader i:invasores) i.draw(g);
        shooter.draw(g);
        for(Bullet b:tiros) b.draw(g);
        if (bomba != null) bomba.draw(g);
        // Pintamos estatísticas
        String s = "Bombas: "+numBombas+" Invasores: "+invasores.size();
        g.drawString(s,5,getHeight()-10);
    }

    // Processamos teclas pressionadas durante o jogo.
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_P) isPaused = !isPaused;
        if (isPaused) return;
        if (keyCode == KeyEvent.VK_LEFT) dir = Direction.LEFT;
        else if (keyCode == KeyEvent.VK_RIGHT) dir = Direction.RIGHT;
        else if (keyCode == KeyEvent.VK_UP) dir = Direction.UP;
        else if (keyCode == KeyEvent.VK_DOWN) dir = Direction.DOWN;
        else if (keyCode == KeyEvent.VK_SPACE){
            if (tiros.size() < MAX_TIROS){
                tiros.add(new Bullet(getPreferredSize(), Direction.UP, shooter.getX(),shooter.getY()-shooter.getHeight()/2));
            }
        }
        else if (keyCode == KeyEvent.VK_B){
            if (numBombas > 0){
                if (bomba == null){
                    bomba = new Bomb(getPreferredSize(),
                    shooter.getX(),shooter.getY()-shooter.getHeight()/2);
                    numBombas--;
                }
            }
        }
    }
    // Estes mÃ©todos servem para satisfazer a interface KeyListener
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
}
