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
    private boolean estáAtivo;
    // Tamanho da bomba em pixels.
    private int iw,ih;
    // Imagem da bomba.
    private Image icon;
    // área do painel do jogo (para controlar movimento).
    private Dimension area;
    // Construtor, inicializa atributos, cria a bomba.
    
    public Bomb(Dimension a,int x,int y){
        area = a;
        icon = new ImageIcon(getClass().getResource("/sprites/bomb.png")).getImage();
        iw = icon.getWidth(null);
        ih = icon.getHeight(null);
        // x e y passadas diretamente como parâmetros
        this.x = x;
        this.y = y;
        estáAtivo = true;
    }
    
    // Método que movimenta o shooter, verificando se está na área válida.
    public void move(Direction dir){
        if (dir == null) return;
        switch(dir){
            case LEFT:
                { x--; 
                if (x < iw/2) x = iw/2; 
                break; }
            case RIGHT:
                { x++; if (x > area.width-iw/2) x = area.width-iw/2; break; }
            case UP:
                { y--; if (y < area.height-100+ih/2) y = area.height-100+ih/2; break; }
            case DOWN:
                { y++; if (y > area.height-ih/2) y = area.height-ih/2; break; }
        }
    }
    // Método que movimenta a bomba.
    public void move(){
        if (!estáAtivo) return;
        y = y-3;
        if (y <= 0) estáAtivo = false;
    }
    // Método que desenha a bomba em um contexto gráfico.
    public void draw(Graphics g){
        if (estáAtivo) g.drawImage(icon,x-iw/2,y-ih/2,null);
    }
    // Precisamos saber se esta bomba está ativa!
    public boolean estáAtivo() {
        return estáAtivo; 
    }
    // Verificamos se a bomba está perto de um Invader
    public boolean acertouEm(Invader i){
        int ox = i.getX(); 
        int oy = i.getY();
        return (Math.sqrt((x-ox)*(x-ox)+(y-oy)*(y-oy)) < 25);
    }

    // Explodimos a bomba (retornando bullets).
    public ArrayList<Bullet> explode(){
        ArrayList<Bullet> novasBalas = new ArrayList<Bullet>(4);
        novasBalas.add(new Bullet(area, Direction.LEFT, x, y));
        novasBalas.add(new Bullet(area, Direction.RIGHT, x, y));
        novasBalas.add(new Bullet(area, Direction.UP, x, y));
        novasBalas.add(new Bullet(area, Direction.DOWN, x, y));
        estáAtivo = false;
        return novasBalas;
    }
}
