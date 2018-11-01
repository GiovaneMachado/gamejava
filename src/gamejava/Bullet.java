/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejava;
import gamejava.Direcao;
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
    private Direcao direção;
    // Este tiro está ativo?
    private boolean estáAtivo;
    // Tamanho do tiro em pixels.
    private int iw,ih;
    // Imagem do tiro.
    private Image icon;
    // área do painel do jogo (para controlar movimento).
    private Dimension area;
 
    // Construtor, inicializa atributos, cria a bala.
    public Bullet(Dimension a, Direcao dir, int x,int y){
        area = a;
        icon = new ImageIcon(getClass().getResource("/gamejava/Sprites/bullet.png")).getImage();
        iw = icon.getWidth(null);
        ih = icon.getHeight(null);
        // x e y passados direto como argumentos
        this.x = x;
        this.y = y;
        direção = dir;
        estáAtivo = true;
    }

    // Método que movimenta a bala.
    public void move(){
        if (!estáAtivo) return;
        switch(direção){
            case LEFT:
                {
                    x -= 3; if (x < 0) estáAtivo = false; break;
                }
            case RIGHT:
                {
                    x += 3; if (x > area.width) estáAtivo = false; break;
                }
            case UP:
                {
                    y -= 3; if (y < 0) estáAtivo = false; break;
                }
            case DOWN:
                {
                    y += 3; if (y > area.height-100) estáAtivo = false; break;
                }
        } 
    }

    // Método que desenha a bala em um contexto gráfico.
    public void draw(Graphics g){
        if (estáAtivo) g.drawImage(icon,x-iw/2,y-ih/2,null);
    }
 
    // Precisamos saber se esta bala está ativa!
    public boolean estáAtivo() { return estáAtivo; }
    // Verificamos se a bala está perto de um Invader
    public boolean acertouEm(Invader i){
        int ox = i.getX(); 
        int oy = i.getY();
        if (Math.sqrt((x-ox)*(x-ox)+(y-oy)*(y-oy)) < 10){
            estáAtivo = false;
            return true;
        }
    else return false;
    }
}
