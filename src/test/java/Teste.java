import java.awt.*;

public class Teste {

    public static void main(String[] args) {
        String msg = "titulo-isso é uma descrição-field-valor";
        String[] desc = msg.split("-");
        String descResult = desc[0];



        String title = desc[1];
        String field= desc[2];
        String valor = desc[3];

        System.out.println(descResult);
        System.out.println(title);
        System.out.println(field);
        System.out.println(valor);


    }
}
