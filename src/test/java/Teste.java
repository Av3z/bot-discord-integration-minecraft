import java.awt.*;

public class Teste {

    public static void main(String[] args) {
        String msg = "teste isso é uma descrição- field- valor- cor-";
        String[] desc = msg.split("-");
        String descResult = desc[0];

        for (int i = 0; i < desc.length; i++){
            System.out.println(desc[i]);
            System.out.println(i);
        }
        System.out.println(Color.BLACK + " " + descResult);



    }
}
