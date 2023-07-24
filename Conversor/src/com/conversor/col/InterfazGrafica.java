package com.conversor.col;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;







	public class InterfazGrafica extends  JFrame   {
	    private JTextField cantidadTextField;
	    private JLabel resultadoLabel;
		

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem opcion1;
	private JMenuItem opcion2;
	private JMenuItem opcion3;
	private JMenuItem opcion4;
	private JMenuItem opcion5;
	
	
	
	public InterfazGrafica() {
		setSize(600, 400);
		setTitle("Conversor Moneda Alura");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color (240,240,240));
		new Conversor();
		
		JPanel panel = new JPanel();
		mostrarMenu();
		panel.add(menuBar);
		add(panel);
		
	    cantidadTextField = new JTextField(10);
	    
        panel.add(cantidadTextField);
        
        resultadoLabel = new JLabel("Resultado:");
        resultadoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        resultadoLabel.setForeground(Color.BLUE);
        panel.add(resultadoLabel);

		
	}

	
	public void mostrarMenu() {
		
		menuBar = new JMenuBar();
		menu = new JMenu("Elija una opción valida");
		menu.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		opcion1 = new JMenuItem("Pesos a Dólares");
		opcion2 = new JMenuItem("Pesos a Euros");
		opcion3 = new JMenuItem("Pesos a Real Brasilero");
		opcion4 = new JMenuItem("Pesos a Yenes");
		opcion5 = new JMenuItem("Salir");
		
	    opcion1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16)); 
	    opcion2.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
	    opcion3.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
	    opcion4.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
	    opcion5.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		
		menu.add(opcion1);
		menu.add(opcion2);
		menu.add(opcion3);
		menu.add(opcion4);
		menu.add(opcion5);
		
		menuBar.add(menu);
		
		
		

        opcion1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conversion(3975.96, "Dólares");
            }
        });

        opcion2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conversion(4426.58, "Euros");
            }
        });

        opcion3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conversion(829.15, "Real Brasileño");
            }
        });

        opcion4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conversion(28.04, "Yenes");
            }
        });

        opcion5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JOptionPane.showConfirmDialog(null, "Saliendo del programa .....");
					System.exit(0);
				}
			});
		
		
	}

    private void conversion(double valorMoneda, String pais) {
        try {
            double cantidadMoneda = Double.parseDouble(cantidadTextField.getText());
            double moneda = cantidadMoneda / valorMoneda;

            moneda = (double) Math.round(moneda * 100d) / 100;
            resultadoLabel.setText("Resultado: Tienes $" + moneda + " " + pais);
        } catch (NumberFormatException ex) {
            resultadoLabel.setText("Error: Ingrese una cantidad válida");
        }
    }






	public static void main (String[] args) {
		InterfazGrafica ventana = new InterfazGrafica();
		ventana.setVisible(true);
		
		
		
		
	
}

}
