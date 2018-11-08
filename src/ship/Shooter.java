package ship;

// Importações
import spacejava.Direction;
import java.awt.*;
import javax.swing.ImageIcon;

// Atributos da classe Invader
public class Shooter {   
    // Boolean que mostra se o Shooter existe ou não
    public boolean activated;
    // Posição do Shooter em pixels. horizontal/vertical
    private int x,y;
    // Tamanho do Shooter em pixels. largura/altura
    private int iw,ih;
    // Imagem do Shooter.
    private Image icon;
    // Área do painel do jogo (para controlar movimento).
    private Dimension area;
    
    // Métodos
    // Construtor, inicializa atributos e posiciona o shooter.
    public Shooter(Dimension a){
        // Área recebida como parâmetro
        area = a;
        //Ícone pego na pasta do projeto
        icon = new ImageIcon(getClass().getResource("/sprites/nave1.png")).getImage();
        // Tamanho
        iw = (int) 50;
        ih = (int) 44;
        // Posição calculada usando a área do jogo e random().
        x = (int)(iw/2+(a.width-iw)/2);
        y = (int)(a.height-100+ih/2);
        // Faz o Shooter existir quando criado
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
    
    // Verificamos se o Shooter bateu em um Invader e desativa o Shooter
    public boolean acertouEm(Invader i){
        int ox = i.getX(); 
        int oy = i.getY();
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
    
    // Retorna sua posição x
    public int getX(){ 
        return x; 
    } 
    
    // Retorna sua posição y
    public int getY(){
        return y; 
    }
    
    // Retorna a largura
    public int getWidth(){
        return iw; 
    }
    
    // Retorna a altura
    public int getHeight(){ 
        return ih; 
    } 
}
