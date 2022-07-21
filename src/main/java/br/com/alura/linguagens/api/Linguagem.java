package br.com.alura.linguagens.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "linguagens")
public class Linguagem {
    @Id
    private String id;
    private String title;
    private String urlImagem;
    private int ranking;

    public Linguagem(String title, String urlImagem, int ranking) {
        this.title = title;
        this.urlImagem = urlImagem;
        this.ranking = ranking;
    }
    public Linguagem() {

    }
    
    public String getTitle() {
        return title;
    }
    public String getUrlImagem() {
        return urlImagem;
    }
    public int getRanking() {
        return ranking;
    }

        
    public String getId() {
        return id;
    }
}
