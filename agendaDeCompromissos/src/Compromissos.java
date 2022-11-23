public final class Compromissos {
    /*Descrição, data-hora, categoria*/
    private String descricao;
    //private Categoria categoria;
    private Pessoa compromissado;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao() {
        this.descricao = Input.setChar();
    }

    /*public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }*/

    public Compromissos() {

    }
}
