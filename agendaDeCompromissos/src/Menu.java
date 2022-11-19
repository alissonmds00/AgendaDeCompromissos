public abstract class Menu {
    private static int escolha;
    private static String nome, login, senha, verificarSenha;
    private static void opcoes() {
        System.out.println(
                "        Agenda de compromissos" +
                        "\n-=-=-=-=-=-=-=-=- MENU -=-=-=-=-=-=-=-=-" +
                        "\n Escolha entre as seguintes opções:" +
                        "\n-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=--=-=-=-" +
                        "\n 1- Efetuar o login" +
                        "\n 2- Registrar nova pessoa" +
                        "\n 3- Agendar um novo compromisso" +
                        "\n 4- Listar todos meus compromissos" +
                        "\n 5- Editar um compromisso existente" +
                        "\n 6- Excluir um compromisso existente" +
                        "\n 0- Sair e encerrar o programa" +
                        "\n-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=--=-=-=-"
        );
    }
    public static void start() {
            do {
               opcoes();
               escolha = Input.setNum();

               switch (escolha) {
                   case 1:
                       try {

                       } catch (Exception e) {
                           return ;
                   } case 2:
                       try {
                           System.out.println("Informe seu nome: ");
                           setNome(Input.setChar());
                           System.out.println("Olá " + getNome() + ". Informe seu nome de usuário: ");
                           setLogin(Input.setChar());
                           System.out.println("Escreva uma senha: ");
                           setSenha(Input.setChar());
                           System.out.println("Confirme sua senha: ");
                           setVerificarSenha(Input.setChar());

                           do {
                               System.out.println(getNome() + ", As senhas informadas são diferentes. Digite novamente: ");
                               System.out.println("Escreva uma senha: ");
                               setSenha(Input.setChar());
                               System.out.println("Confirme sua senha: ");
                               setVerificarSenha(Input.setChar());
                           } while (getSenha().equals(getVerificarSenha()) == false);

                           new Pessoa(getNome(), getLogin(), getSenha());
                       } catch (Exception e) {

                       }

                   case 3:
               }


            } while (escolha != 0);
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
}
