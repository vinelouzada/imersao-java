import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //fazer uma conexão HTTP
        String url = "https://imdb-api.com/en/API/Top250Movies/k_0ysnlc3t";
        String url2 = "https://imdb-api.com/en/API/MostPopularMovies/k_0ysnlc3t";
        
        URI endereco = URI.create(url2);

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
    }
}
