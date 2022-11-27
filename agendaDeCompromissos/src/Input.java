import java.util.Scanner;
import java.util.InputMismatchException;

public class Input {
    static Scanner dignum = new Scanner(System.in);
    static Scanner digchar = new Scanner(System.in);

    public static String setChar() {
        return digchar.next();
    }

    public static int setNum() {
        try {
            return dignum.nextInt();
        } catch (InputMismatchException e) {
            do {
                System.out.println("O valor digitado deve ser numérico.");
                setNum();
            } while (true);
        }
    }
}
