import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pessoa {
    public static ArrayList<Object> minhasCategorias = new ArrayList<>();
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

    public Pessoa(){}

    public Pessoa(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
}
