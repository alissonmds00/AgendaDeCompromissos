import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class Pessoa {
    public static ArrayList<String> minhasCategorias = new ArrayList<>();
    public static ArrayList<Compromissos> meusCompromissos = new ArrayList<>();


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

    // seção categorias

    public void criarCategorias() {
        // permite o usuário criar categorias novas.
        String categoria;
        System.out.println("Informe o nome da categoria que quer criar: ");
        categoria = Input.setChar();
        int duplicado = 0;
        for (int c = 0; c < minhasCategorias.size(); c++) {
            if (minhasCategorias.get(c).equals(categoria)) {
                duplicado++;
            }
        }

        if (duplicado == 0) {
            minhasCategorias.add(categoria);


        } else {
            System.out.println("Categoria já existente");
            criarCategorias();
        } // Não permite a criação de uma categoria que já existe.
    }

    public void autoCriarCategorias(String cat) { // utilizado somente em criarPessoa() pois PULA CHECAGENS, POR PUXAR DIRETO DO .TXT DA CONTA
        minhasCategorias.add(cat);
    }

    public void listarCategorias() {
        for (int c = 0; c < minhasCategorias.size(); c++) {
            System.out.println(c + " - " + minhasCategorias.get(c));
        }
    }

    public String selecionarCategoria() { // editar a categoria que esta no indice certo no array
        try {
            listarCategorias();
            System.out.println("Escolha uma categoria utilizando dígitos numéricos: ");
            String escolha = Input.setChar();
            System.out.println("A categoria " + minhasCategorias.get(Integer.parseInt(escolha)) + " foi selecionada");

            int catformatada = Integer.parseInt(escolha);
            return minhasCategorias.get(catformatada);
        } catch (Exception e) {
            System.out.println("Categoria não encontrada." + "\n Deseja criar uma nova? [S/N]: ");
            String sn = Input.setChar().toUpperCase().strip();
            if (sn.startsWith("S")) {
                criarCategorias();
                System.out.println("A categoria " + minhasCategorias.get(minhasCategorias.size() -1) + " foi selecionada");
                return (minhasCategorias.get(minhasCategorias.size() - 1));
            } else {
                System.out.println("A categoria " + minhasCategorias.get(0) + " foi selecionada"); // default caso decida nao criar
                return minhasCategorias.get(0);
            }
        }
    }

    public void excluirCategoria() {
        try {
            listarCategorias();
            System.out.println("Informe qual o número da categoria que você deseja excluir: ");
            int escolha = Input.setNum();
            while(escolha == 0) {
                System.out.println("Você não pode excluir a categoria padrão.");
                System.out.println("Informe qual o número da categoria que você deseja excluir: ");
                escolha = Input.setNum();
            }
            System.out.println("Categoria " + minhasCategorias.get(escolha) + " foi excluida!");



            minhasCategorias.remove(escolha);
        } catch (Exception e) {
            System.out.println("Categoria não encontrada! Tente novamente usando números:  ");
            excluirCategoria();
        }
    }

    // seção compromissos

    public void agendarCompromisso() {
        // permite ao usuário criar compromissos novos.
        try {
            Compromissos comp = new Compromissos();

            System.out.println("Informe a descrição do compromissos que quer criar: ");
            BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
            String descricao = scan.readLine();

            comp.desc = descricao;
            Calendario c = new Calendario();
            c.agendar();

            comp.data = c;

            comp.categ = this.selecionarCategoria();

            meusCompromissos.add(comp);
            sortCompromissos(comp.data.parseInput());

        } catch (Exception e) {

        }
    }

    public void sortCompromissos(ZonedDateTime date){


    }

/*
List<Object> sortedList = objectList.stream()
           .sorted(Comparator.comparing(Object :: getLocalDateTime).reversed())
           .collect(Collectors.toList());
 */

    public void listarCompromissos() {
        for (int c = 0; c < meusCompromissos.size(); c++) {
            System.out.println((c+1) + " - " + meusCompromissos.get(c).desc + "\n    (" + meusCompromissos.get(c).categ + ")\n    " + meusCompromissos.get(c).data.dataFormatada + "\n");
        }
    }
    public void removerCompromisso() {
        try {
            listarCompromissos();
            System.out.println("Informe qual o número do compromisso que você deseja excluir: ");
            int escolha = Input.setNum();
            System.out.println("Compromisso " + meusCompromissos.get(escolha-1).desc + " foi excluido!");
            meusCompromissos.remove(escolha-1);
        } catch (Exception e) {
            System.out.println("Compromisso não encontrado! Tente novamente usando números:  ");
            removerCompromisso();
        }
    }

    public void editarCompromisso() {
        listarCompromissos();

        Compromissos comp = new Compromissos();

        try {
            System.out.println("Informe qual o número do compromisso que você deseja editar: ");
            int escolhaComp = Input.setNum();
            comp = meusCompromissos.get(escolhaComp-1);
        } catch (Exception e) {
            System.out.println("Compromisso não encontrado! Tente novamente usando números:  ");
            editarCompromisso();
        }

        System.out.println("Informe o que você deseja editar:\n1- Descrição\n2- Categoria\n3- Data");
        Scanner input = new Scanner(System.in);
        String escolhaOp = input.nextLine();

        switch(escolhaOp) {
            case "1":
                try {
                    System.out.println("Informe a nova descrição do compromisso: "); // duplicado?
                    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
                    String descricao = scan.readLine();

                    comp.desc = descricao;
                } catch (Exception e) {
                }
                break;
            case "2":
                try {
                    System.out.println("Informe a nova categoria do compromisso: ");
                    this.listarCategorias();

                    comp.categ = this.selecionarCategoria();
                } catch (Exception e) {

                }
                break;
            case "3":
                    comp.data = comp.data.agendar();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente:");
                editarCompromisso();
                break;
        }
    }

    // construtores

    public Pessoa() {}

    public Pessoa(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;

        minhasCategorias.add(nome); // A categoria padrão do usuário será o próprio nome dele. Utilizado em criarPessoa() para manter a categoria default
    }
}
