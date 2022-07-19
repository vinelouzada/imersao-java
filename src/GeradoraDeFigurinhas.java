import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{
        
        //realizar a leitura da imagem
        //FileInputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
       
        //criar uma nova imagem em memória com transparencia e com novo tamnho
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int alturaFundoParaTexto = 200;

        int novaAltura = altura + alturaFundoParaTexto;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        //copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);


        //configurar a font
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 72);
        graphics.setFont(fonte);
        graphics.setColor(Color.BLUE);
        
        //tamanho da fonte altura e largura
        FontMetrics medidasFonte = graphics.getFontMetrics(fonte);
        int alturaFonte = medidasFonte.getHeight();
        int larguraFonte = medidasFonte.stringWidth("TOPZERA");

        
        
        // escrever uma frase na nova imagem
        int meioHorizontal = (largura - larguraFonte)/2;
        int meioVertical = (novaAltura - (alturaFundoParaTexto - alturaFonte/2)/2) ;
        //System.out.println(alturaFonte + " a "+meioVertical);

        graphics.drawString("TOPZERA", meioHorizontal, meioVertical);

        //escrever a nova imagem em um arquivo
        File figurinha = new File("../saida/" + nomeArquivo);
        figurinha.mkdirs();
        ImageIO.write(novaImagem, "png", figurinha);
    }
}
