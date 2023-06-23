import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EditorTexto extends JFrame implements ActionListener {
    
    JTextArea areaTexto;
    JMenuBar menuBar;
    JMenu menuArchivo;
    JMenuItem menuItemNuevo, menuItemAbrir, menuItemGuardar, menuItemSalir;
    File archivo;

    public EditorTexto() {
        super("Editor de Texto");

        // Creamos el área de texto
        areaTexto = new JTextArea();
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        // Creamos la barra de menú
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Creamos el menú de archivo
        menuArchivo = new JMenu("Archivo");
        menuBar.add(menuArchivo);

        // Creamos los items del menú archivo
        menuItemNuevo = new JMenuItem("Nuevo");
        menuItemNuevo.addActionListener(this);
        menuArchivo.add(menuItemNuevo);

        menuItemAbrir = new JMenuItem("Abrir");
        menuItemAbrir.addActionListener(this);
        menuArchivo.add(menuItemAbrir);

        menuItemGuardar = new JMenuItem("Guardar");
        menuItemGuardar.addActionListener(this);
        menuArchivo.add(menuItemGuardar);

        menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.addActionListener(this);
        menuArchivo.add(menuItemSalir);

        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new EditorTexto();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemNuevo) {
            areaTexto.setText("");
            archivo = null;
        } else if (e.getSource() == menuItemAbrir) {
            JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(archivo));
                    String linea;
                    areaTexto.setText("");
                    while ((linea = br.readLine()) != null) {
                        areaTexto.append(linea + "\n");
                    }
                    br.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al leer archivo");
                }
            }
        } else if (e.getSource() == menuItemGuardar) {
            if (archivo == null) {
                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showSaveDialog(this);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    archivo = fileChooser.getSelectedFile();
                }
            }
            if (archivo != null) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                    bw.write(areaTexto.getText());
                    bw.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar archivo");
                }
            }
        } else if (e.getSource() == menuItemSalir) {
            System.exit(0);
        }
    }
}
