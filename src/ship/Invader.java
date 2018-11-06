/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ship;

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
    private boolean activated;
    // Tamanho do UFO em pixels.
    private int iw,ih;
    // Imagem do UFO.
    private Image icon;
    // área do painel do jogo (para controlar movimento).
    private Dimension area;   
    
    // Construtor, inicializa atributos e posiciona o UFO.
    public Invader(Dimension a){
        area = a;
        icon = new ImageIcon(getClass().getResource("/sprites/naveinimiga.png")).getImage();
        iw = (int) 150;
        ih = (int) 215;
        // x e y calculados usando a área do jogo.
        x = (int)(iw/2+Math.random()*(a.width-iw));
        y = (int)(ih/2+Math.random()*(a.height-100-ih));
        // dx e dy aleatórios velocidade
        while(dx == 0 || dy == 0){
            dx = 3-(int)(Math.random()*6);
            dy = 2-(int)(Math.random()*4); 
        }
        activated = true;

    }
    // Método que movimenta o UFO, verificando se está na área válida.
    public void move(){
        if(activated){
            x += dx;
            y += dy;
           
            if (x < iw/2){ 
                dx = -dx; x += dx; 
            }
            if (y < ih/2){
                dy = -dy; y += dy; 
            }
            if (x > area.width-iw/2){
                dx = -dx; x += dx; 
            }
            if (y > area.height-ih/2){ 
                dy = -dy; y += dy; 
            }
            
        }
    }
    


    // Método que desenha o UFO em um contexto gráfico.
    public void draw(Graphics g){
        g.drawImage(icon,x-iw/2,y-ih/2,null);
    }
    
    public void deactivate(){
        activated = false;
    }
    public boolean activate(){ 
        return activated; 
    }
    public int getX(){ 
        return x; 
    }
    public int getY(){ 
        return y; 
    }
}