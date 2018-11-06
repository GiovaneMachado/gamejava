/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weapons;
import ship.Invader;
import ship.Shooter;
import spacejava.Direction;
import java.awt.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Giovane
 */
// Esta classe representa um tiro no jogo.
public class Bullet {
    // Posição do tiro em pixels.
    private int x,y;
     // Direção do tiro.
    private Direction direction;
    // Este tiro está ativo?
    private boolean activated;
    // Tamanho do tiro em pixels.
    private int iw,ih;
    // Imagem do tiro.
    private Image icon;
    // área do painel do jogo (para controlar movimento).
    private Dimension area;
 
    // Construtor, inicializa atributos, cria a bala.
    public Bullet(Dimension a, Direction dir, int x,int y){
        area = a;
        icon = new ImageIcon(getClass().getResource("/sprites/bullet.png")).getImage();
        iw = (int) 30;
        ih = (int) 50;
        // x e y passados direto como argumentos
        this.x = x;
        this.y = y;
        direction = dir;
        activated = true;
    }

    // Método que movimenta a bala.
    public void move(){
        if (!activated) return;
        switch(direction){
            case LEFT:
                {
                    x -= 3;
                    if (x < 0) activated = false;
                    break;
                }
            case RIGHT:
                {
                    x += 3;
                    if (x > area.width) activated = false;
                    break;
                }
            case UP:
                {
                    y -= 3;
                    if (y < 0) activated = false;
                    break;
                }
            case DOWN:
                {
                    y += 3;
                    if (y > area.height-100) activated = false;
                    break;
                }
        } 
    }

    // Método que desenha a bala em um contexto gráfico.
    public void draw(Graphics g){
        if (activated) g.drawImage(icon,x-iw/2,y-ih/2,null);
    }
 
    // Precisamos saber se esta bala está ativa!
    public boolean activate(){ 
        return activated; 
    }
    // Verificamos se a bala está perto de um Invader e desativa A BALA
    public boolean acertouEm(Invader i){
        int ox = i.getX(); 
        int oy = i.getY();
        //Controla a hit box
        if (Math.sqrt((x-ox)*(x-ox)+(y-oy)*(y-oy)) < 50){
            activated = false;
            return true;
        }
    else return false;
    }
    
    // Verificamos se a bala está perto de um Shooter e desativa A BALA
    public boolean acertouEm(Shooter s){
        int ox = s.getX(); 
        int oy = s.getY();
        //Controla a hit box
        if (Math.sqrt((x-ox)*(x-ox)+(y-oy)*(y-oy)) < 50){
            activated = false;
            return true;
        }
    else return false;
    }
}
