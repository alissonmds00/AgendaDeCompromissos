import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

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
            writeUser.write(pwd + '\n' + name);
            writeUser.close();
            System.out.println("Registrada conta: " + user + '.');

        } catch (IOException ignored) {
            System.out.println("ERRO ao acessar arquivo da conta.");
        }
    }

    public void criarCategorias() {
        // permite o usuário criar categorias
        String categoria;
        System.out.println("Informe o nome da categoria que quer criar: ");
        categoria = Input.setChar();
        minhasCategorias.add(categoria);

    }

/*    public String selecionarCategorias(String usuario) {
        // exibir todas as categorias do usuário
        int categoria = 0;
        for (int c = 0; c <= minhasCategorias.size()-1; c++) {
            System.out.println(c + " - " + minhasCategorias.get(c));
        }

        System.out.println("Selecione a categoria que quer utilizar: ");

        try {
            categoria = Input.setNum();

        } catch (IndexOutOfBoundsException e) {
            while (categoria >= minhasCategorias.size() || categoria <= 0) {
                System.out.println("Por favor selecione um número entre " + 0 + " e " + minhasCategorias.size());
                categoria = Input.setNum();
            }
        } catch (InputMismatchException e) {
            System.out.println("O valor digitado deve ser numérico, tente novamente");

        }
        return minhasCategorias.get(categoria);
    }

 */
    public void listarCategorias(){
        if (minhasCategorias.size() > 0) {
            for (String c : minhasCategorias) {
                System.out.println(c);
            }
        }
    }
    public Pessoa() {
    }



    public Pessoa(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        minhasCategorias.add("Defaul: " + nome); // A categoria padrão do usuário será o próprio nome dele
    }
}
