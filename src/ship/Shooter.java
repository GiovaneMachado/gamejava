/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ship;
import spacejava.Direction;
import java.awt.*;
import javax.swing.ImageIcon;


/**
 *
 * @author Giovane
 */
public class Shooter {   

    // Posição do defensor em pixels. horizontal/vertical
    private int x,y;
    // Tamanho do defensor em pixels. largura/altura
    private int iw,ih;
    // Imagem do defensor.
    private Image icon;
    // área do painel do jogo (para controlar movimento).
    private Dimension area;
    
     // Construtor, inicializa atributos e posiciona o shooter.
    public Shooter(Dimension a){
        area = a;
        icon = new ImageIcon(getClass().getResource("/sprites/nave.png")).getImage();
        iw = (int) 100;
        ih = (int) 88;
        // x e y iniciais centrados na área de movimentação. Define a posição inicial do Shooter
        x = (int)(iw/2+(a.width-iw)/2);
        y = (int)(a.height-100+ih/2);
    }
    
    // Método que movimenta o shooter, verificando se está na área válida (pro shooter)
    public void move(Direction dir){
        if (dir == null) return;
        switch(dir){
            case LEFT:
                { 
                    x -= 3; 
                    if (x < iw/2) x = iw/2; 
                    break; 
                }
            case RIGHT:
                { 
                    x += 3; 
                    if (x > area.width-iw/2) x = area.width-iw/2; 
                    break; 
                }
            case UP:
                { 
                    y -= 3; 
                    if(y < ih/2) y = ih/2;
                    break; 
                }
            case DOWN:
                { 
                    y += 3; 
                    if (y > area.height-ih/2) y = area.height-ih/2; 
                    break; 
                }
        }
    }
    
    // Método que desenha o shooter em um contexto gráfico.
    public void draw(Graphics g){
        g.drawImage(icon,x-iw/2,y-ih/2,null);
    }
    
    public int getX(){ 
        return x; 
    }
    public int getY(){
        return y; 
    }
    public int getWidth(){
        return iw; 
    }
    public int getHeight(){ 
        return ih; 
    }
}
