import java.util.Scanner;

public class Input {
    static Scanner dignum = new Scanner(System.in);
    static Scanner digchar = new Scanner(System.in);

    public static String setChar() {
        return digchar.next();
    }

    public static int setNum() {
        return  dignum.nextInt();
    }
}
