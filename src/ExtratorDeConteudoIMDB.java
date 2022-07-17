import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoIMDB implements ExtratorDeConteudo{
    
    public List<Conteudo> extraiConteudos(String json){
        //extrair só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        //popular a lista de conteudos
        for(Map<String,String> atributos : listaDeAtributos){
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String classificacaoFilme = atributos.get("imDbRating");

            var conteudo = new Conteudo(titulo, urlImagem);
        
            conteudo.setClassificacaoImdb(classificacaoFilme);
            conteudos.add(conteudo);
        }

        return conteudos;
    }

    public void mostraClassificacaoFilme(List<Conteudo> conteudos){
        for (int i = 0; i < conteudos.size(); i++) {
            Conteudo conteudo = conteudos.get(i);
            String nomeFilme = conteudo.getTitulo();
            String imgFilme = conteudo.getUrlImagem();
            String classificacaoFilme = conteudo.getClassificacaoImdb();
            
            System.out.println("------------------------");
            System.out.println("Título: \u001b[1m" + nomeFilme + "\u001b[0m");
            System.out.println("Poster: \u001b[1m " + imgFilme + "\u001b[0m");

            String notaFilme = (classificacaoFilme.isEmpty()) ? Integer.toString(0) : classificacaoFilme;

            System.out.println("\u001b[33m \u001b[45m Classificação: \u001b[1m " + (notaFilme) + "\u001b[0m");
            
            int numEstrelasFilme = Math.round(Float.parseFloat(notaFilme));
            String estrelasFilme = "";
            
            for (int j = 0; j < numEstrelasFilme; j++) {
                estrelasFilme += "⭐";
            }
            
            System.out.println(estrelasFilme);
        }
        System.exit(0);   
        
    }

}   