/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejava;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Giovane
 */
public class Invader {
    // Posição e “velocidade” do UFO em pixels.
    private int x,y;
    private int dx,dy;
    private boolean estáAtivo;
    // Tamanho do UFO em pixels.
    private int iw,ih;
    // Imagem do UFO.
    private Image icon;
    // área do painel do jogo (para controlar movimento).
    private Dimension area;   
    
    // Construtor, inicializa atributos e posiciona o UFO.
    public Invader(Dimension a){
        area = a;
        icon = new ImageIcon(getClass().getResource("/gamejava/Sprites/naveinimiga.png")).getImage();
        iw = icon.getWidth(null);
        ih = icon.getHeight(null);
        // x e y calculados usando a área do jogo.
        x = (int)(iw/2+Math.random()*(a.width-iw));
        y = (int)(ih/2+Math.random()*(a.height-100-ih));
        // dx e dy aleatórios velocidade
        while(dx == 0 || dy == 0){
            dx = 3-(int)(Math.random()*6);
            dy = 2-(int)(Math.random()*4); 
        }
        estáAtivo = true;

    }
    // Método que movimenta o UFO, verificando se está na área válida.
    public void move(){
        if(estáAtivo){
            x += dx;
            y += dy;
            if (x < iw/2) { dx = -dx; x += dx; }
            if (y < ih/2) { dy = -dy; y += dy; }
            if (x > area.width-iw/2) { dx = -dx; x += dx; }
            if (y > area.height-100-ih/2) { dy = -dy; y += dy; }
        }
    }

    // Método que desenha o UFO em um contexto gráfico.
    public void draw(Graphics g){
        if(estáAtivo) g.drawImage(icon,x-iw/2,y-ih/2,null);
    }
    
    public void desativa(){
        estáAtivo = false;
    }
    public boolean estáAtivo() { return estáAtivo; }
    public int getX() { return x; }
    public int getY() { return y; }
}