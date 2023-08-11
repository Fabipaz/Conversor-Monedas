package com.conversor.col;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InterfazGrafica extends JFrame {
    private JTextField cantidadTextField;
    private JLabel resultadoLabel;
    private JLabel apiStatusLabel;
    private JLabel apiStatusIndicator; // Indicador de estado
    private boolean invertirconversion = false;

    private final List<String> acronimos = new ArrayList<>();
    private final List<String> nombres = new ArrayList<>();

    public InterfazGrafica() {
        iniciandomonedas();
        frame();
    }

    private void iniciandomonedas() {
    	acronimos.add("USD");
        acronimos.add("EUR");
        acronimos.add("BRL");
        acronimos.add("MXN");
        acronimos.add("DKK");
        acronimos.add("GEL");
        acronimos.add("ILS");
        acronimos.add("RSD");
        
        nombres.add("Dólares");
        nombres.add("Euros");
        nombres.add("Real Brasileño");
        nombres.add("Yenes");
        nombres.add("Corona danesa");
        nombres.add("Lari georgiano");
        nombres.add("Shekel");
        nombres.add("Dinar Serbio");
    }


    private void frame() {
        setSize(600, 450);
        setTitle("Conversor Moneda Alura");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel panel = new JPanel();
        mostrarMenu();
        panel.add(menuBar);
        add(panel);

        cantidadTextField = new JTextField(10);
        panel.add(cantidadTextField);

        resultadoLabel = new JLabel("Resultado:");
        resultadoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        resultadoLabel.setForeground(Color.BLUE);
        resultadoLabel.setAlignmentY(CENTER_ALIGNMENT);
        panel.add(resultadoLabel);

        apiStatusLabel = new JLabel("Sin conexión");
        apiStatusLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        apiStatusLabel.setForeground(Color.RED);

        apiStatusIndicator = new JLabel();
        apiStatusIndicator.setPreferredSize(new Dimension(20, 20));
        estadoapi(false);
        
        JCheckBox invertirCheckBox = new JCheckBox("Invertir Conversión");
        invertirCheckBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        invertirCheckBox.addActionListener(e -> invertirconversion = invertirCheckBox.isSelected());

        JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkBoxPanel.add(apiStatusIndicator);
        checkBoxPanel.add(invertirCheckBox);
        add(checkBoxPanel, BorderLayout.SOUTH);
    }

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem opcion1;
    private JMenuItem opcion2;
    private JMenuItem opcion3;
    private JMenuItem opcion4;
    private JMenuItem opcion5;
    private JMenuItem opcion6;
    private JMenuItem opcion7;
    private JMenuItem opcion8;
    private JMenuItem opcion9;

    private void mostrarMenu() {
   	menuBar = new JMenuBar();
        menu = new JMenu("Elija una opción válida");
        menu.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        for (int i = 0; i < acronimos.size(); i++) {
            JMenuItem menuItem = new JMenuItem("Pesos a " + nombres.get(i));
            menuItem.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            menuItem.addActionListener(creandoconversion(acronimos.get(i), nombres.get(i)));
            menu.add(menuItem);
        }

        opcion9 = new JMenuItem("Salir");
        opcion9.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        opcion9.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Saliendo del programa...");
            System.exit(0);
        });

        menu.add(opcion9);
        menuBar.add(menu);
    }


    private void estadoapi(boolean isConnected) {
        if (isConnected) {
            apiStatusIndicator.setBackground(Color.GREEN);
        } else {
            apiStatusIndicator.setBackground(Color.RED);
        }
        apiStatusIndicator.setOpaque(true);
    }

    private ActionListener creandoconversion(String currencyCode, String currencyName) {
        return e -> conversion(currencyCode, currencyName);
    }

    private void conversion(String currencyCode, String currencyName) {
          try {
            double cantidadMoneda = Double.parseDouble(cantidadTextField.getText());

            Api api = new Api();
            double exchangeRate = api.getExchangeRate(currencyCode);
            double copExchangeRate = api.getExchangeRate("COP");

            double moneda;
            if (invertirconversion) {
                moneda = cantidadMoneda * copExchangeRate / exchangeRate;
                resultadoLabel.setText("Resultado: Tienes " + String.format("%.2f", moneda) + " Pesos Colombianos");
            } else {
                moneda = cantidadMoneda / copExchangeRate * exchangeRate;
                moneda = (double) Math.round(moneda * 100d) / 100;
                resultadoLabel.setText("Resultado: Tienes $" + String.format("%.2f", moneda) + " " + currencyName);
            }

            apiStatusLabel.setText("Conexión OK");
            apiStatusLabel.setForeground(Color.GREEN);
            estadoapi(true); 

        } catch (NumberFormatException ex) {
             resultadoLabel.setText("Error: Ingrese una cantidad válida");

            apiStatusLabel.setText("Sin conexión");
            apiStatusLabel.setForeground(Color.RED);
            estadoapi(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazGrafica ventana = new InterfazGrafica();
            ventana.setVisible(true);
        });
    }
}

