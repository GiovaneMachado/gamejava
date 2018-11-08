package ship;

// Importações
import java.awt.*;
import javax.swing.ImageIcon;

// Atributos da classe Invader
public class Invader {
    // Boolean que mostra se o Invader existe ou não
    private boolean activated;
    // Posição e “velocidade” do Invader em pixels.
    private int x,y;
    private int dx,dy;
    // Tamanho do Invader em pixels.
    private int iw,ih;
    // Imagem do Invader.
    private Image icon;
    // Área do painel do jogo (para controlar movimento).
    private Dimension area;   
    
    //Métodos
    // Construtor, inicializa atributos e posiciona o Invader.
    public Invader(Dimension a){
        // Área recebida como parâmetro
        area = a;
        // Ícone sendo pego dentro da pasta do projeto
        icon = new ImageIcon(getClass().getResource("/sprites/naveinimiga1.png")).getImage();
        // Tamanho
        iw = (int) 75;
        ih = (int) 108;
        // Posição calculada usando a área do jogo e random().
        x = (int)(iw/2+Math.random()*(a.width-iw));
        y = (int)(ih/2+Math.random()*(a.height-100-ih));
        // Velocidade calculada aleatoriamente.
        while(dx == 0 || dy == 0){
            dx = 3-(int)(Math.random()*6);
            dy = 2-(int)(Math.random()*4); 
        }
        // Faz o Invader existir quando criado
        activated = true;

    }
    // Método que movimenta o Invader, verificando se está na área válida.
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
    
    // Método que desenha o Invader em um contexto gráfico.
    public void draw(Graphics g){
        g.drawImage(icon,x-iw/2,y-ih/2,null);
    }
    
    // Desativa o Invader
    public void deactivate(){
        activated = false;
    }
    
    // Ativa o Invader
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
}