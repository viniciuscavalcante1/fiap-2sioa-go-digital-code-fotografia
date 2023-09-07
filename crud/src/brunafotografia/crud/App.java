package brunafotografia.crud;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteManager clienteManager = new ClienteManager();

            JFrame frame = new JFrame("Bruna Fotografia");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Cadastro", new ClienteUI(clienteManager));
            frame.add(tabbedPane);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
