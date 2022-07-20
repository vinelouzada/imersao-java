public record Conteudo(String titulo, String urlImagem, String classificacaoImdb) {


    public Conteudo(String titulo, String urlImagem){
        this(titulo, urlImagem, "0");
    }

}
