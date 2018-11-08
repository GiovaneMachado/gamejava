/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weapons;
import ship.Invader;
import spacejava.Direction;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;



/**
 *
 * @author Giovane
 */

public class Bomb {
    // Posição da bomba em pixels.
    private int x,y;
    // Esta bomba está ativa?
    private boolean activated;
    // Tamanho da bomba em pixels.
    private int iw,ih;
    // Imagem da bomba.
    private Image icon;
    // área do painel do jogo (para controlar movimento).
    private Dimension area;
    // Construtor, inicializa atributos, cria a bomba.
    
    public Bomb(Dimension a,int x,int y){
        area = a;
        icon = new ImageIcon(getClass().getResource("/sprites/bomb1.png")).getImage();
        iw = icon.getWidth(null)/2;
        ih = icon.getHeight(null)/2;
        // x e y passadas diretamente como parâmetros
        this.x = x;
        this.y = y;
        activated = true;
    }
    
    // Método que movimenta a bomba, verificando se está na área válida.
    public void move(Direction dir){
        if (dir == null) return;
        else{
            y--; 
            if (y < area.height-100+ih/2) y = area.height-100+ih/2;  
        }
    }
    // Método que movimenta a bomba.
    public void move(){
        if (!activated) return;
        y = y-3;
        if (y <= 0) activated = false;
    }
    // Método que desenha a bomba em um contexto gráfico.
    public void draw(Graphics g){
        if (activated) g.drawImage(icon,x-iw/2,y-ih/2,null);
    }
    // Precisamos saber se esta bomba está ativa!
    public boolean activate() {
        return activated; 
    }
    // Verificamos se a bomba está perto de um Invader
    public boolean acertouEm(Invader i){
        int ox = i.getX(); 
        int oy = i.getY();
        return (Math.sqrt((x-ox)*(x-ox)+(y-oy)*(y-oy)) < 40);
    }

    // Explodimos a bomba (retornando bullets).
    public ArrayList<Bullet> explode(){
        ArrayList<Bullet> novasBalas = new ArrayList<Bullet>(4);
        novasBalas.add(new Bullet(area, Direction.LEFT, x, y));
        novasBalas.add(new Bullet(area, Direction.RIGHT, x, y));
        novasBalas.add(new Bullet(area, Direction.UP, x, y));
        novasBalas.add(new Bullet(area, Direction.DOWN, x, y));
        activated = false;
        return novasBalas;
    }
}
