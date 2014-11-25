package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

public class AddNote extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public AddNote() {
		setLayout(null);
		setSize(new Dimension(1366, 768));
         
        JLabel lblAddNote = new JLabel("Add Note");
        lblAddNote.setForeground(Color.WHITE);
        lblAddNote.setFont(new Font("Arial", Font.BOLD, 78));
        lblAddNote.setBounds(549, 118, 347, 91);
        add(lblAddNote);
        
        JLabel lblCBSlogo = new JLabel("");
        lblCBSlogo.setIcon(new ImageIcon(UserList.class.getResource("/Images/CBSLogo3.png")));
        lblCBSlogo.setBounds(36, 695, 223, 67);
        add(lblCBSlogo);
        
        JLabel lblNote = new JLabel("Note:");
        lblNote.setForeground(Color.WHITE);
        lblNote.setFont(new Font("Arial", Font.BOLD, 30));
        lblNote.setHorizontalAlignment(SwingConstants.CENTER);
        lblNote.setBounds(511, 379, 106, 34);
        add(lblNote);
        
        JLabel lblEvent = new JLabel("EventID:");
        lblEvent.setHorizontalAlignment(SwingConstants.CENTER);
        lblEvent.setForeground(Color.WHITE);
        lblEvent.setFont(new Font("Arial", Font.BOLD, 30));
        lblEvent.setBounds(484, 315, 122, 36);
        add(lblEvent);
        
        JEditorPane editorPane = new JEditorPane();
        editorPane.setBounds(616, 389, 325, 135);
        add(editorPane);
        
        textField = new JTextField();
        textField.setBounds(618, 314, 236, 39);
        add(textField);
        textField.setColumns(10);
		
        JLabel lblBackground = new JLabel("Background");
        lblBackground.setIcon(new ImageIcon(UserList.class.getResource("/Images/MetalBackground.jpg")));
        lblBackground.setBackground(new Color(245, 245, 245));
        lblBackground.setForeground(new Color(245, 255, 250));
        lblBackground.setOpaque(true);
        lblBackground.setBounds(0, 0, 1376, 768);
        add(lblBackground);
        


        
	}
}