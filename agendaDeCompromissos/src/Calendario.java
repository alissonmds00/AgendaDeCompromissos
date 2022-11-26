import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;

public class Calendario {
    private int dia, mes, ano;
    final int anoAtual = YearMonth.now().getYear();


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
        if (ano < getAnoAtual()) {
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

    public Calendario(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
}
