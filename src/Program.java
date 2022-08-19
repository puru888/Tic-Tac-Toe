import javax.swing.*;
import java.awt.*;

public class Program {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Program::start);
    }

    private static void start(){
        new GameWindow();
    }
}
