package dicotomica.proyecto2;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Interfaz gráfica principal
 */
public class InterfazGrafica extends JFrame {
    private final ClaveDicotomica clave = new ClaveDicotomica();

    public InterfazGrafica() {
        configurarVentana();
        agregarComponentes();
    }

    private void configurarVentana() {
        setTitle("Clave Dicotómica");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void agregarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        String[] botones = {
            "Cargar JSON", "Mostrar Árbol", 
            "Identificar Especie", "Buscar por Hash", "Buscar en Árbol"
        };

        for (String texto : botones) {
            JButton btn = new JButton(texto);
            btn.addActionListener(this::manejarEventos);
            panel.add(btn);
        }

        add(panel);
    }

    private void manejarEventos(ActionEvent e) {
        String comando = ((JButton) e.getSource()).getText();
        switch (comando) {
            case "Cargar JSON":
                cargarJSON();
                break;
            case "Mostrar Árbol":
                VisualizadorArbol.mostrarArbol(clave.getArbol().getRaiz());
                break;
            case "Identificar Especie":
                JOptionPane.showMessageDialog(this, clave.identificarEspecie());
                break;
            case "Buscar por Hash":
                String nombreHash = JOptionPane.showInputDialog("Nombre de especie:");
                if (nombreHash != null) 
                    JOptionPane.showMessageDialog(this, clave.buscarPorHash(nombreHash));
                break;
            case "Buscar en Árbol":
                String nombreArbol = JOptionPane.showInputDialog("Nombre de especie:");
                if (nombreArbol != null)
                    JOptionPane.showMessageDialog(this, clave.buscarEnArbol(nombreArbol));
                break;
        }
    }

    private void cargarJSON() {
        JFileChooser selector = new JFileChooser();
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            clave.cargarDesdeJSON(selector.getSelectedFile().getPath());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGrafica().setVisible(true));
    }
}