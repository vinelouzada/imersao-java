import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("../properties/conf.properties");
        properties.load(file);


        //fazer uma conexão HTTP
        String url = "https://imdb-api.com/en/API/Top250Movies/" + properties.getProperty("chave.imdb");
        //String url2 = "https://imdb-api.com/en/API/MostPopularMovies/" + properties.getProperty("chave.imdb");
        //temporária https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060
        
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

    
        //extrair só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        
        for (Map<String,String> filme : listaDeFilmes) {
            String nomeFilme = filme.get("title");
            String imgFilme = filme.get("image");
            String classificacaoFilme = filme.get("imDbRating");
            
            System.out.println("------------------------");
            System.out.println("Título: \u001b[1m" + nomeFilme + "\u001b[0m");
            System.out.println("Poster: \u001b[1m " + imgFilme + "\u001b[0m");

            String notaFilme = (classificacaoFilme.isEmpty()) ? Integer.toString(0) : classificacaoFilme;

            System.out.println("\u001b[33m \u001b[45m Classificação: \u001b[1m " + (notaFilme) + "\u001b[0m");
            
            int numEstrelasFilme = Math.round(Float.parseFloat(notaFilme));
            String estrelasFilme = "";

            for (int i = 0; i < numEstrelasFilme; i++) {
                estrelasFilme += "⭐";
            }
            
            System.out.println(estrelasFilme);
        }

        //Pedindo avaliação
        Scanner ler = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            Map<String,String> filme = listaDeFilmes.get(i);
        
            String nomeFilme = filme.get("title");
            String imgFilme = filme.get("image");

            System.out.println("------------------------");
            System.out.println("Título: \u001b[1m" + nomeFilme + "\u001b[0m");
            System.out.println("Poster: \u001b[1m " + imgFilme + "\u001b[0m");
            System.out.println("Qual a sua avaliação para este filme? (0 a 10)");
            Float notaFilme = ler.nextFloat();
            
            System.out.println("\u001b[33m \u001b[45m Classificação: \u001b[1m " + (notaFilme) + "\u001b[0m");

            int numEstrelasFilme = Math.round(notaFilme);
            String estrelasFilme = "";

            for (int j = 0; j < numEstrelasFilme; j++) {
                estrelasFilme += "⭐";
            }
            
            System.out.println(estrelasFilme);
        }
    }
}

