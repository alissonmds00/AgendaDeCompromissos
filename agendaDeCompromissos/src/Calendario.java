import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Calendario {
    private static int hora, min, dia, mes, ano; // interno
    // private static String horaF, minF, diaF, mesF, anoF; usado em compromissos, printado (OLD)
    final static int anoAtual = YearMonth.now().getYear();
    public static String dataFormatada; // strings acima, formatadas juntas
//    public static ZonedDateTime zdtHora;
//
//    public static ZonedDateTime getZdt() { return zdtHora; } ;

    public static int getHora() {
        return hora;
    }

    public static void setHora(int Hora) {
        if (Hora >= 0 && Hora < 24) {
            hora = Hora;
        } else {
            System.out.println("A hora informada é inválida. Tente novamente: ");
            Hora = Input.setNum();
            setHora(Hora);
        }
    }

    public static int getMin() {
        return min;
    }

    public static void setMin(int Min) {
        if (Min >= 0 && Min < 60) {
            min = Min;
        } else {
            System.out.println("O minuto informado é inválido. Tente novamente: ");
            Min = Input.setNum();
            setMin(Min);
        }
    }

    public static int getDia() {
        return dia;
    }

    public static void setDia(int Dia) {
        if (Dia > 0 && Dia < 32) {
            dia = Dia;
        } else {
            System.out.println("O dia informado é inválido. Tente novamente: ");
            Dia = Input.setNum();
            setDia(Dia);
        }
    }

    public static int getMes() {
        return mes;
    }

    public static void setMes(int Mes) {
        if (Mes > 0 && Mes < 13) { // o mês precisa estar contido no intervalo de [1, 12] {
            mes = Mes;
        } else {
            System.out.println("O mês inserido é invalido. Informe o mês novamente: ");
            Mes = Input.setNum();
            setMes(Mes);
        }
    }

    public static int getAno() {
        return ano;
    }

    public static void setAno(int Ano) {
        if (Ano >= getAnoAtual()) {
            ano = Ano;
        } else {
            System.out.println("Não é possível agendar um compromisso para anos anteriores a " + getAnoAtual() +
                    "\nDigite novamente: ");
            Ano = Input.setNum();
            setAno(Ano);
        }
    }

    public static int getAnoAtual() {
        return anoAtual;
    }

    public static void setDataFormatada(String d, String mes, String a, String h, String min) {
        dataFormatada = d + "/" + mes + "/" + a + " às " + h + ":" + min;
    }

    ;

    public String getDataFormatada() {
        return dataFormatada;
    }

    ;


    public ZonedDateTime parseInput() {
        String diaF;
        if (getDia() < 10) { // checagem para formatacao
            diaF = "0" + getDia();
        } else diaF = Integer.toString(getDia());
        String mesF;
        if (getMes() < 10) { // checagem para formatacao
            mesF = "0" + getMes();
        } else mesF = Integer.toString(getMes());
        String horaF;
        if (getHora() < 10) { // checagem para formatacao
            horaF = "0" + getHora();
        } else horaF = Integer.toString(getHora());
        String minF;
        if (getMin() < 10) { // checagem para formatacao
            minF = "0" + getMin();
        } else minF = Integer.toString(getMin());
        String anoF;
        anoF = Integer.toString(getAno());

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd LL yyyy kk:mm X"); // definir formato de parse
        String input = diaF + " " + mesF + " " + anoF + " " + horaF + ":" + minF + " -03"; // adequar input a nosso formato
        setDataFormatada(diaF, mesF, anoF, horaF, minF);

        ZonedDateTime zdt = ZonedDateTime.parse(input, formato); // realizando parse, convertemos nosso horario da string

        System.out.println(zdt); // DEBUG

        return zdt; // depois trocar para return 4 linhas acima
    }

    public boolean validarHorario(ZonedDateTime horaCompromisso) {
        ZoneId tz = ZoneId.of("America/Bahia"); // definir fuso horario
        ZonedDateTime curr = ZonedDateTime.now(tz); // hora atual com fuso horario considerado
        if (horaCompromisso.compareTo(curr) > 0) // passa caso horario do compromisso seja depois do horario atual
        {
            System.out.println("Compromisso agendado para o dia: " + getDataFormatada());
            return true;
        } else {
            System.out.println("Horario do compromisso ja passou!");
            return false;
        }
    }

    public Calendario() {
    }

    public Calendario agendar() {
        System.out.println("Informe o dia para agendar o compromisso: ");
        setDia(Input.setNum());
        System.out.println("Informe o mês para agendar o compromisso: ");
        setMes(Input.setNum());
        System.out.println("Informe o ano para agendar o compromisso: ");
        setAno(Input.setNum());
        System.out.println("Informe a hora do compromisso: ");
        setHora(Input.setNum());
        System.out.println("Informe os minutos do compromisso: ");
        setMin(Input.setNum());
        if (this.validarHorario(this.parseInput())) return this;
        else {
            agendar();
            return null;
        }
    }
}
