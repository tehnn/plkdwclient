/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myclient;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 *
 * @author mcherry
 */
public class Client extends JFrame implements Runnable
{
	//Creating CLI
	private JTextField cliUserInput = new JTextField();
	private JTextArea cliTextArea = new JTextArea();


	//Creating I/O Streams
	private DataOutputStream _output;
	private DataInputStream _input;


	public static void main(String[] args)
	{
		new Client();
	}

	public void run()
	{
		//Quick GUI
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel("Command Line:"), BorderLayout.WEST);
		panel.add(cliUserInput, BorderLayout.CENTER);
		cliUserInput.setHorizontalAlignment(JTextField.LEFT);

		setLayout(new BorderLayout());
		add(panel, BorderLayout.NORTH);
		add(new JScrollPane(cliTextArea), BorderLayout.CENTER);
		cliUserInput.addActionListener(new ButtonListener());

		setTitle("Client");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		//attempt to connect to the server
		try
		{
		Socket socket = new Socket("localhost", 2000);
		_input = new DataInputStream(socket.getInputStream());
		_output = new DataOutputStream(socket.getOutputStream());
		cliTextArea.append("Connection Established." + '\n');
		while(true)
		{
			//listening for incoming communication from server
			String instring = _input.readLine();
			cliTextArea.append(instring.toString());
		}

		}
		catch(IOException e)
		{
			cliTextArea.append(e.toString() + '\n');
		}
	}

	public Client()
	{

	}



	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{

			//get command from command line and send to server

			   cliTextArea.append("Launching thread." + '\n');
			   Sender myString = new Sender();
			   Thread send = new Thread(myString);
			   cliTextArea.append("Sending command. . .");
			   send.start();
			   
		}
	}
	
	/*
	 * We don't want to hold up the program when the button listener detects a keypress.
	 * Therefore, we're creating a Sender class that run on a separate thread to fire out a 
	 */
	private class Sender implements Runnable
	{
		public void run()
		{
			
		}
	}
}