import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class Menu {
    private static String nome, login, senha, verificarSenha, password, user;
    private static ArrayList<Pessoa> cadastrados = new ArrayList<>();

    Pessoa adal = new Pessoa("Adalberto", "adal", "1");

    private static void opcoes() {
        System.out.println("        Agenda de compromissos" + "\n-=-=-=-=-=-=-=-=- MENU -=-=-=-=-=-=-=-=-" + "\n Escolha entre as seguintes opções:"
                + "\n-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=--=-=-=-"
                + "\n 1- Agendar um novo compromisso"
                + "\n 2- Listar todas as categorias"
                + "\n 3- Editar um compromisso existente"
                + "\n 4- Excluir um compromisso existente"
                + "\n 5- Fazer logoff"
                + "\n 0- Sair e encerrar o programa"
                + "\n-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=--=-=-=-");
    }

    private static void sair() {
        System.exit(0);
    }

    public static void start() {
        Scanner input = new Scanner(System.in);
        System.out.println("1- Fazer login");
        System.out.println("2- Registrar nova pessoa");
        System.out.println("3- Encerrar");

        String command = input.nextLine();
        switch (command) {
            case "1" -> login();
            case "2" -> {
                Pessoa p = new Pessoa();
                cadastrar(p);
                start();
            }
            case "3" -> sair();
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

                mainMenu(encontrarPessoa(inputUser));
            } else {
                System.out.println("Senha incorreta, tente novamente:");
                login();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Usuário não encontrado, tente novamente:");
            login();
        }
    }

    public static Pessoa encontrarPessoa(String login) {
        Pessoa pessoa = null;
        if(cadastrados.size() > 0){
            for(Pessoa c: cadastrados) {
                if(Objects.equals(c.getLogin(), login)) {
                    pessoa = c;
                }
            }
        }else{
            System.out.println("Não há pessoas cadastradas");
        }
        return pessoa;
    }

    private static void cadastrar(Pessoa p) {
        System.out.println("Insira nome: ");
        setNome(Input.setChar());
        System.out.println("Insira username: ");
        setUser(Input.setChar());
        System.out.println("Insira senha: ");
        setSenha(Input.setChar());
        System.out.println("Confirme a senha: ");
        setVerificarSenha(Input.setChar());

        p.registrarConta(user, senha, nome);
        Pessoa pessoa = new Pessoa(nome, user, senha);
        cadastrados.add(pessoa);
    }

    private static void mainMenu(Pessoa user) {
        Scanner input = new Scanner(System.in);

        opcoes();

        Pessoa p = new Pessoa();
        String escolha = input.nextLine();

        switch (escolha) {
            case "1":
                cadastrar(p);
                break;
            case "2":
                Calendario.agendar();// DEBUG fim
                break;
            case "3":
                System.out.println("Categorias criadas até o momento: ");
                user.listarCategorias(); // lista as categorias do usuário específico
                break;
            case "4":
                user.selecionarCategoria();
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
                mainMenu(user);
                break;
        }
        mainMenu(user);
    }

    private static String getNome() {
        return nome;
    }

    private static void setNome(String nome) {
        // verificando se há algum número ou caractere inválido no nome
        if (nome.strip().matches("[a-zA-ZááÁéÉíÍóÓúÚâÂêÊôÔãÃõÕçÇ]+") == true) {
            Menu.nome = nome.strip(); // uso do strip para remover possíveis erros de digitação
        }
        else {
            System.out.println("O seu nome não pode conter números. Informe-o novamente.");
            System.out.println("Insira nome: ");
            setNome(Input.setChar());
        }
    }

    // foram utilizados métodos "strip" para corrigir possíveis erros de digitação por parte do usuário
    private static String getLogin() {
        return login;
    }

    private static void setLogin(String login) {
        Menu.login = login.strip();
    }

    private static String getSenha() {
        return senha;
    }

    private static void setSenha(String senha) {
        Menu.senha = senha.strip();
    }

    private static String getVerificarSenha() {
        return verificarSenha;
    }

    private static void setVerificarSenha(String verificarSenha) {
        // se a senha digitada e a senha de confirmação são diferentes, então o usuário digita novamente
        if (verificarSenha.strip().equals(getSenha())) {
            Menu.verificarSenha = verificarSenha.strip();
        } else {
            do {
                System.out.println("As senhas digitas não são iguais. Informe-as novamente");
                System.out.println("Insira senha: ");
                setSenha(Input.setChar());
                System.out.println("Confirme a senha: ");
                setVerificarSenha(Input.setChar());
            } while (!getSenha().equals(getVerificarSenha()));
        }
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Menu.password = password;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String username) {
        Menu.user = username;
    }
}
