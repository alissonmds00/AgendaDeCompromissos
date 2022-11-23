import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Menu {
    private static String nome, login, senha, verificarSenha, password, username;

    private static void opcoes() {
        System.out.println("        Agenda de compromissos" + "\n-=-=-=-=-=-=-=-=- MENU -=-=-=-=-=-=-=-=-" + "\n Escolha entre as seguintes opções:"
                + "\n-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=--=-=-=-"
                + "\n 1- Registrar nova pessoa"
                + "\n 2- Agendar um novo compromisso"
                + "\n 3- Listar todos meus compromissos"
                + "\n 4- Editar um compromisso existente"
                + "\n 5- Excluir um compromisso existente"
                + "\n 6- Fazer logoff"
                + "\n 0- Sair e encerrar o programa"
                + "\n-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=--=-=-=-");
    }

    private static void sair() {
        System.exit(0);
    }

    public static void start() {
        Scanner input = new Scanner(System.in);

        System.out.println("1- Fazer login");
        System.out.println("2- Encerrar");

        String command = input.nextLine();
        switch (command) {
            case "1" -> login();
            case "2" -> sair();
            default -> {
                System.out.println("Comando inválido. Tente novamente:");
                start();
            }
        }
    }

    private static void login() {
        try {
            Scanner keyboard = new Scanner(System.in);

            System.out.println("Usuário:");
            String inputUser = keyboard.nextLine();

            Scanner account = new Scanner(new File("agendaDeCompromissos/src/contas/" + inputUser + ".txt"));
            String pass = account.nextLine();

            System.out.println("Senha:");
            String inputPwd = keyboard.nextLine();

            if (inputPwd.equals(pass)) {
                System.out.println("Autenticado com sucesso.");

                String nome = account.nextLine();

                System.out.println("Bem-vindo, " + nome + "!");
                System.out.println("Logado como: " + inputUser);
                mainMenu(inputUser);
            } else {
                System.out.println("Senha incorreta, tente novamente:");
                login();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Usuário não encontrado, tente novamente:");
            login();
        }
    }

    private static void mainMenu(String inputUser) {
        Scanner input = new Scanner(System.in);

        opcoes();

        Pessoa p = new Pessoa();

        String escolha = input.nextLine();

        switch (escolha) {
            case "1":
                Scanner detalhesConta = new Scanner(System.in);
                System.out.println("Insira username:");
                String user = detalhesConta.nextLine();
                System.out.println("Insira senha:");
                String senha = detalhesConta.nextLine();
                System.out.println("Insira nome:");
                String nome = detalhesConta.nextLine();

                p.registrarConta(user, senha, nome);
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                login();
                break;
            case "0":
                sair();
            default:
                System.out.println("Comando inválido. Tente novamente:");
                mainMenu(username);
                break;
        }
        mainMenu(username);
    }

    private static String getNome() {
        return nome;
    }

    private static void setNome(String nome) {
        Menu.nome = nome;
    }

    private static String getLogin() {
        return login;
    }

    private static void setLogin(String login) {
        Menu.login = login;
    }

    private static String getSenha() {
        return senha;
    }

    private static void setSenha(String senha) {
        Menu.senha = senha;
    }

    private static String getVerificarSenha() {
        return verificarSenha;
    }

    private static void setVerificarSenha(String verificarSenha) {
        Menu.verificarSenha = verificarSenha;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Menu.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Menu.username = username;
    }
}
