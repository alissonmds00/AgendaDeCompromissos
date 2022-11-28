import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;

public class Pessoa {
    public static ArrayList<String> minhasCategorias = new ArrayList<>();

    private String nome, login, senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome() {
        this.nome = Input.setChar();
    }


    public void registrarConta(String user, String pwd, String name) {
        try {
            File newUser = new File("agendaDeCompromissos/src/contas/" + user + ".txt");
            // System.out.println("Criado txt: " + newUser.getAbsolutePath()); DEBUG
            if (!newUser.createNewFile()) {
                System.out.println("Conta já registrada.");
                return;
            }
        } catch (IOException ignored) {
            System.out.println("ERRO ao criar arquivo da conta.");
        }
        try {
            FileWriter writeUser = new FileWriter("agendaDeCompromissos/src/contas/" + user + ".txt");
            writeUser.write(pwd + '\n' + name + "\n<CATEG_SEP>\n<COMP_SEP>");
            writeUser.close();
            System.out.println("Registrada conta: " + user + '.');

        } catch (IOException ignored) {
            System.out.println("ERRO ao acessar arquivo da conta.");
        }
    }

    public void criarCategorias() {
        // permite o usuário criar categorias novas.
        String categoria;
        System.out.println("Informe o nome da categoria que quer criar: ");
        categoria = Input.setChar();
        int duplicado = 0;
        for (int c = 0; c < minhasCategorias.size(); c++)
            if (minhasCategorias.get(c).equals(categoria)) {
                duplicado++;
            }
        if (duplicado == 0) {
            minhasCategorias.add(categoria);
        } else {
            System.out.println("Categoria já existente");
            criarCategorias();
        } // Não permite a criação de uma categoria que já existe.
    }


/*    public void listarCategorias() {
        if (minhasCategorias.size() > 0) {
            for (String c : minhasCategorias) {
                System.out.println(c);
            }
        }
    } //Lista as categorias existentes.
    */

    public void listarCategorias() {
        System.out.println("Qual a categoria do seu compromisso? ");
        for (int c = 0; c < minhasCategorias.size(); c++) {
            System.out.println(c + " - " + minhasCategorias.get(c));
        }
        System.out.println("Ou digite o nome da categoria para criar uma nova");
    }

    public String selecionarCategoria() {
        listarCategorias();
        try {
            String escolha = Input.setChar();
            System.out.println("A categoria " + minhasCategorias.get(Integer.parseInt(escolha)));
            return minhasCategorias.get(Integer.parseInt(escolha));
        } catch (Exception e) {
            System.out.println("Categoria não encontrada." + "\n Deseja criar uma nova? [S/N]: ");
            String sn = Input.setChar().toUpperCase().strip();
            if (sn.startsWith("S")) {
                criarCategorias();
                System.out.println("A categoria " + minhasCategorias.get(minhasCategorias.size() -1) + " foi selecionada");
                return (minhasCategorias.get(minhasCategorias.size() - 1));
            } else {
                System.out.println("A categoria " + minhasCategorias.get(0) + " foi selecionada");
                return minhasCategorias.get(0);
            }
        }
    }

    public Pessoa() {}

    public Pessoa(String nome) {
        this.nome = nome;

        minhasCategorias.add("Default: " + nome); // A categoria padrão do usuário será o próprio nome dele
    }
}
