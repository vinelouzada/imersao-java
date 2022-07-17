import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //fazer uma conexão HTTP
        String url1 = "https://imdb-api.com/en/API/Top250Movies/k_0ysnlc3t";
        String url2 = "https://imdb-api.com/en/API/MostPopularMovies/k_0ysnlc3t";
        String url3 = "https://api.nasa.gov/planetary/apod?api_key=mG9S1uoK2Mf00BZktadFdhUfNBwBakupkevUqwBl&start_date=2022-06-12&end_date=2022-06-14";

        new ClienteHttp();
        String json = ClienteHttp.buscaDados(url1);



        ExtratorDeConteudoIMDB extrator = new ExtratorDeConteudoIMDB();
        //ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        //extrator.mostraClassificacaoFilme(conteudos);

        var geradora = new GeradoraDeFigurinhas();
        
        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);
            //deve-se mudar o caractere : pois no windows não é permitido ':' esse caractere no título
            String nomeArquivo = conteudo.getTitulo().replace(":", "-")  + ".png";

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            System.out.println("Gerando imagem - [" + conteudo.getTitulo() + "]");

            geradora.cria(inputStream, nomeArquivo);
        }

        System.out.println("Imagens concluídas!");
    }
}
