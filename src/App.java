import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception {
        
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("../properties/conf.properties");
        properties.load(file);


        //fazer uma conexão HTTP
        //fazer uma conexão HTTP
        //String url1 = "https://imdb-api.com/en/API/Top250Movies/" + properties.getProperty("chave.imdb");
        //String url1 = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        //String url2 = "https://imdb-api.com/en/API/MostPopularMovies/" + properties.getProperty("chave.imdb");
        String url3 = "https://api.nasa.gov/planetary/apod?api_key=mG9S1uoK2Mf00BZktadFdhUfNBwBakupkevUqwBl&start_date=2022-06-12&end_date=2022-06-14";

        new ClienteHttp();
        String json = ClienteHttp.buscaDados(url3);



        //ExtratorDeConteudoIMDB extrator = new ExtratorDeConteudoIMDB();
        ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        //extrator.mostraClassificacaoFilme(conteudos);

        var geradora = new GeradoraDeFigurinhas();
        
        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);
            //deve-se mudar o caractere : pois no windows não é permitido ':' esse caractere no título
            String nomeArquivo = conteudo.titulo().replace(":", "-")  + ".png";

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();

            System.out.println("Gerando imagem - [" + conteudo.titulo() + "]");

            geradora.cria(inputStream, nomeArquivo);
        }

        System.out.println("Imagens concluídas!");
    }
}
