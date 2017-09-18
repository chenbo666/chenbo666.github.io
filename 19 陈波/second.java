package secondhomework;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class second extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	 public second() {
		// TODO Auto-generated constructor stub
		 
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 JPanel panel = new JPanel();
		 getContentPane().add(panel, BorderLayout.CENTER);
			setBounds(100, 100, 400, 400);
		 panel.setLayout(new GridLayout(3, 1, 0, 0));
		 
		 JPanel panel_1 = new JPanel();
		 panel.add(panel_1);
		 
		 JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		 panel_1.add(lblNewLabel);
		 
		 textField = new JTextField();
		 panel_1.add(textField);
		 textField.setColumns(10);
		 
		 JPanel panel_2 = new JPanel();
		 panel.add(panel_2);
		 
		 JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		 panel_2.add(lblNewLabel_1);
		 
		 textField_1 = new JTextField();
		 panel_2.add(textField_1);
		 textField_1.setColumns(10);
		 
		 JPanel panel_3 = new JPanel();
		 panel.add(panel_3);
		 
		 JButton btnNewButton = new JButton("\u67E5\u8BE2");
		 btnNewButton.setBackground(new Color(240, 240, 240));
		 btnNewButton.setFont(new Font("ËÎÌו", Font.PLAIN, 19));
		 btnNewButton.setForeground(Color.GREEN);
		 panel_3.add(btnNewButton);
		 setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       new second();
	}

}
