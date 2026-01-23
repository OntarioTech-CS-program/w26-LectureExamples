import javax.swing.*;
import java.awt.*;


public class FirstGUI{
	public static void main(String[] args){
		// create our window
		JFrame frame = new JFrame("My First Swing App");
		//indicate the app should stop running if we close the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// layout manager helps to place objs on screen "organized"
		frame.setLayout(new FlowLayout());
		
		//create UI components
		JButton button = new JButton("Click me!");
		JTextField textField = new JTextField(20);
		JLabel outputText = new JLabel("Placeholder");
		
		// button click function
		//button.addActionListener(e -> System.out.println("Clicked"));
		button.addActionListener(e -> {
			System.out.println("Clicked");
			String inputText = textField.getText();
			if (inputText.isEmpty()){
				// show error dialog
				JOptionPane.showMessageDialog(frame, "Your input should not be empty", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				outputText.setText("You entered: "+inputText);
			}
			
		});
		
		//add components to window
		frame.add(button);
		frame.add(textField);
		frame.add(outputText);
		
		
		// set the window visible
		frame.setSize(300,200);
		frame.setVisible(true);
		System.out.println("Hello World");
		
	}
}