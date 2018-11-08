/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ship;
import spacejava.Direction;
import java.awt.*;
import javax.swing.ImageIcon;
import weapons.Bullet;


/**
 *
 * @author Giovane
 */
public class Shooter {   
    // Método que define se o objeto está "vivo"
    public boolean activated;
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
        icon = new ImageIcon(getClass().getResource("/sprites/nave1.png")).getImage();
        iw = (int) 50;
        ih = (int) 44;
        // x e y iniciais centrados na área de movimentação. Define a posição inicial do Shooter
        x = (int)(iw/2+(a.width-iw)/2);
        y = (int)(a.height-100+ih/2);
        activated = true;
    }
    
    // Método que movimenta o shooter, verificando se está na área válida (pro shooter)
    public void move(Direction dir){
        if(activated){
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
    }
    
    // Método que desenha o shooter em um contexto gráfico.
    public void draw(Graphics g){
        if(activated) g.drawImage(icon,x-iw/2,y-ih/2, null);
    }
    
    // Verificamos se a bala está perto de um Invader e desativa A BALA
    public boolean acertouEm(Invader i){
        int ox = i.getX(); 
        int oy = i.getY();
        //Controla a hit box
        if (Math.sqrt((x-ox)*(x-ox)+(y-oy)*(y-oy)) < 45){
            activated = false;
            return true;
        }
    else return false;
    } 
  
    
    // Método que mata o shooter
    public void deactivate(){
        activated = false;
        
    }
    
    // Método que representa a vida do shooter
    public boolean activate(){ 
        return activated; 
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
