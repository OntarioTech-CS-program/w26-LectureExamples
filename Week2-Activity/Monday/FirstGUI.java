import javax.swing.*;
import java.awt.*;

public class FirstGUI{
	public static void main(String[] args){
		System.out.println("HelloWorld");
		// creating a window
		JFrame frame = new JFrame("My first GUI App");
		// to make sure out app stops running once window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		// Create button
		JButton button = new JButton("Click me");
		//button.setBounds(50,50,120,50);
		JTextField textField = new JTextField(20);
		JLabel lbText = new JLabel("Some text");
		
		// creating a clik event listenter
		//button.addActionListener(e -> System.out.println("Click"));
		button.addActionListener(e -> {
			System.out.println("Click");
			String inputText = textField.getText();
			if(inputText.isEmpty()){
				// show error message
				JOptionPane.showMessageDialog(frame, "Your input is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				lbText.setText("You said: " + inputText);
			}
		});
		
		//add component to UI
		frame.add(button);
		frame.add(textField);
		frame.add(lbText);
		
		// set a size for the window
		frame.setSize(300,200);
		// make window appear
		frame.setVisible(true);
	}
}