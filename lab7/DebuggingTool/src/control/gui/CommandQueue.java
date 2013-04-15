package control.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

//import control.communication.CommandMessage;

/**
 * TODO:
 * action listeners
 * format of text in myQueue (putting commands into readable english)
 * @author Steph
 * @author Sarina
 */

public class CommandQueue extends JPanel{
	
	private JLabel commandLabel;
	private JList<Object> myQueue;
	private JButton myStep;
	private JButton myExecute;
	private JButton myDelete;
	private DefaultListModel<Object> myList;
	
	public CommandQueue() {
		
		setPreferredSize(new Dimension(250, 220));
		setLayout(new FlowLayout(FlowLayout.LEADING, 10, 3));
		
		commandLabel = new JLabel("Command Queue");
		commandLabel.setFont(new Font("Arial", Font.BOLD, 15));
		
		myList = new DefaultListModel<Object>();
		
		myQueue = new JList();
		myQueue.setFont(new Font("Arial", Font.PLAIN, 13));
		myQueue.setVisibleRowCount(8);
		//myQueue.setColumns(15);
		//myQueue.setLineWrap(true);
		//myQueue.setEditable(false);
		//myQueue.setPreferredSize(new Dimension(235, 150));
		myQueue.setAutoscrolls(true);
		// add in scroll bars, set auto scroll
		JScrollPane sp = new JScrollPane(myQueue);
		sp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		sp.setPreferredSize(new Dimension(232, 150));
		
		//DefaultCaret caret = (DefaultCaret)myQueue.getCaret();
		//caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		sp.setAutoscrolls(true); 
		

		JPanel buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(235, 40));
		buttons.setLayout(new FlowLayout(FlowLayout.LEADING, 1, 3));
		
		myStep = new JButton("Step");
		myStep.setFont(new Font("Arial", Font.PLAIN, 13));
		myStep.setPreferredSize(new Dimension(77, 35));
		
		myExecute = new JButton("Execute");
		myExecute.setFont(new Font("Arial", Font.PLAIN, 12));
		myExecute.setPreferredSize(new Dimension(77, 35));
		
		myDelete = new JButton("Delete");
		myDelete.setFont(new Font("Arial", Font.PLAIN, 13));
		myDelete.setPreferredSize(new Dimension(77, 35));
		
		buttons.add(myStep);
		buttons.add(myExecute);
		buttons.add(myDelete);
		
		add(commandLabel);
		add(sp);
		add(buttons);
		
	}
	
	public DefaultListModel<Object> getMyList() {
		return myList;
	}
	
	public JList<Object> getQueue() {
		return myQueue;
	}
	
	public JButton getStep() {
		return myStep;
	}
	
	public JButton getExecute() {
		return myExecute;
	}
	
	public JButton getDelete() {
		return myDelete;
	}

	//public void addMessage(CommandMessage s) {
	//	// TODO add command message to displayed JLIST 
	//	myList.addElement(s.getCommand());
	//	myQueue.setModel(myList);
	//}

}
