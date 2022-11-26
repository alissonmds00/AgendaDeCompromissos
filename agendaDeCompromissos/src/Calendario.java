import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Calendario {
    private int hora, min, dia, mes, ano;
    final int anoAtual = YearMonth.now().getYear();

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        if (hora >= 0 && hora < 24) {
            this.hora = hora;
        } else {
            do {
                System.out.println("A hora informada é inválida. Tente novamente: ");
                hora = Input.setNum();
            } while (hora >= 0 && hora < 24);
        }
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        if (min >= 0 && min < 60) {
            this.min = min;
        } else {
            do {
                System.out.println("O minuto informado é inválido. Tente novamente: ");
                min = Input.setNum();
            } while (min >= 0 && min < 60);
        }
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        if (dia > 0 && dia < 32) {
            this.dia = dia;
        } else {
            do {
                System.out.println("O dia informado é inválido. Tente novamente: ");
                dia = Input.setNum();
            } while (dia > 0 || dia < 32);
        }
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        if (mes > 0 && mes < 13) { // o mês precisa estar contido no intervalo de [1, 12] {
            this.mes = mes;
        } else {
            do {
                System.out.println("O mês inserido é invalido. Informe o mês novamente: ");
                mes = Input.setNum();
            } while (mes < 0 || mes > 12);
        }
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if (ano >= getAnoAtual()) {
            this.ano = ano;
        } else {
            do {
                System.out.println("Não é possível agendar um compromisso para anos anteriores a " + getAnoAtual() +
                        "\nDigite novamente: ");
                ano = Input.setNum();
            } while (ano < getAnoAtual());
        }
    }

    public int getAnoAtual() {
        return anoAtual;
    }

    public ZonedDateTime parseInput() {
        String dia;
        if (getDia() < 10) { // checagem para formatacao
            dia = "0" + getDia();
        } else dia = Integer.toString(getDia());
        String mes;
        if (getMes() < 10) { // checagem para formatacao
            mes = "0" + getMes();
        } else mes = Integer.toString(getMes());
        String ano = Integer.toString(getAno());

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd LL yyyy kk:mm X"); // definir formato de parse
        String input = dia + " " + mes + " " + ano + " " + hora + ":" + min + " -03"; // adequar input a nosso formato

        ZonedDateTime zdt = ZonedDateTime.parse(input, formato); // realizando parse, convertemos nosso horario da string

        System.out.println(zdt); // DEBUG

        return zdt; // depois trocar para return 4 linhas acima
    }

    public void validarHorario(ZonedDateTime horaCompromisso) {
        ZoneId tz = ZoneId.of("America/Bahia"); // definir fuso horario
        ZonedDateTime curr = ZonedDateTime.now(tz); // hora atual com fuso horario considerado
        if(horaCompromisso.compareTo(curr) > 0) // passa caso horario do compromisso seja depois do horario atual
        {
            System.out.println("setar compromisso etc");
        }
        else { System.out.println("Horario do compromisso ja passou!"); }
    }

    public Calendario() {}

    public Calendario(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
}
