import java.util.ArrayList;

public class Categoria implements CriarCategorias {
    private String categoria;
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria() {
        this.categoria = Input.setChar();
    }

    @Override
    public void criarCategoria() {
        setCategoria();
        Pessoa.minhasCategorias.add(getCategoria());
    }

}
