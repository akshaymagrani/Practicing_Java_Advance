/*
Create a GUI Application:

1. Create a Java Swing application with the following elements:
A JFrame titled "Simple Calculator".
Two JTextField components for input.
A JButton labeled "Add".
A JLabel to display the result.
Implement Event Handling:

2. Use ActionListener to handle the button click event.
Fetch the text from both JTextField components, convert it to integers, perform addition, and display the result in the JLabel.
Customize the GUI:

3. Set appropriate sizes and positions for all components.
Use setSize(), setLayout(null), and setBounds() methods to manage component layout.
 */
package advance_tasks;

import javax.swing.*;

public class T1_simple_Swing_Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Simple Calculator");
		
		JLabel l1 = new JLabel("Number 1: ");
		l1.setBounds(50, 100, 150, 20);
		JTextField t1 = new JTextField();
		t1.setBounds(50, 130, 150, 20);
		
		JLabel l2 = new JLabel("Number 2: ");
		l2.setBounds(50, 150, 150, 20);
		JTextField t2 = new JTextField();
		t2.setBounds(50, 180, 150, 20);
		
		JButton b1 = new JButton("Add");
		b1.setBounds(50, 220, 150, 20);
		JLabel l3 = new JLabel();
		l3.setBounds(50, 250, 150, 20);
		
		b1.addActionListener(e -> {
			try {
				int a = Integer.parseInt(t1.getText());
				int b = Integer.parseInt(t2.getText());
				l3.setText("Result: " + (a + b));
			} catch (NumberFormatException ex) {
                l3.setText("Invalid input. Please enter numbers.");
            }
		});
		
		
		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(t2);
		f.add(b1);
		f.add(l3);
		f.setSize(500,500);
		f.setLayout(null);
		f.setVisible(true);
	}
}
