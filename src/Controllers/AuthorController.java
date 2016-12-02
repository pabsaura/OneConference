package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Models.*;
import Views.MainView;

public class AuthorController implements ActionListener {
	private MainView mainView;
	private Author author;
	private File file;
	private MainController mainController;

	public AuthorController(Author author, MainView mainView, MainController mainController) {
		this.author = author;
		this.mainView = mainView;
		this.file = null;
		this.mainController = mainController;

		// Fill conferences availables
		this.mainView.getConferenceComboBox().addItem("Choose a conference");
		for (Conference conference : mainController.getParser().getConferences()) {
			mainView.getConferenceComboBox().addItem(conference.getName() + " ID:" + conference.getID());
		}
	}

	public void submitPaper() {
		Paper p = null;
		if (this.file != null) {
			p = new Paper(UUID.randomUUID().toString(), this.author, this.mainView.getTextFieldName().getText(), null,
					this.mainView.getTextFieldTopic().getText(), this.file.getPath());
		}
		String[] parts = this.mainView.getConferenceComboBox().getSelectedItem().toString().split(":");
		String conferenceInput = parts[1];

		if (p != null) {
			for (Conference conference : this.mainController.getParser().getConferences()) {
				if (conference.getID().equals(conferenceInput)) {
					conference.getPaperList().add(p);
					this.author.getPapers().add(p);
					this.mainController.getParser().addToParser(p);
					JOptionPane.showMessageDialog(null, ("The paper has been submited\nPaper ID: " + p.getID()));
				}
			}
		}
	}

	public File openFile() {
		JFileChooser file = new JFileChooser();
		file.showOpenDialog(file);
		File opened = file.getSelectedFile();
		return opened;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("SELECT FILE")) {
			this.file = openFile();
		}
		if (e.getActionCommand().equals("SUBMIT")) {
			if ((!this.mainView.getTextFieldName().getText().isEmpty())
					&& (!this.mainView.getTextFieldTopic().getText().isEmpty())) {
				submitPaper();
			}
			else{
				JOptionPane.showMessageDialog(null, ("ERROR\nTo upload a paper you need to enter name, topic and a file cotaining the paper"));
			}
		}
		if (e.getActionCommand().equals("CANCEL")) {
			mainController.showMainView();
		}
	}

}
