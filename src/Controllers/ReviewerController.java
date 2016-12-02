package Controllers;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Models.*;
import Views.MainView;

public class ReviewerController implements ActionListener {
	private MainView mainView;
	private Reviewer reviewer;
	private MainController mainController;

	public ReviewerController(MainView mainView, Reviewer reviewer, MainController mainController) {
		this.mainController = mainController;
		this.reviewer = reviewer;
		this.mainView = mainView;

		this.loadPaperDetailComboBox();

	}

	public void clearFields() {
		this.mainView.getComboBoxPaperSelector().removeAllItems();
		this.mainView.getLblPaperName().setText("Paper name: ");
		this.mainView.getLblAuthorName().setText("Author: ");

	}

	public Review evaluatePaper(Paper p) {
		Review r = new Review(UUID.randomUUID().toString(), Integer.parseInt(mainView.getTextFieldGrade().getText()),
				null, mainView.getEvaluatePaperTextArea().getText(), reviewer, p);
		return r;

	}

	public void loadPaperDetailComboBox() {
		mainView.getComboBoxPaperSelector().removeAllItems();
		mainView.getComboBoxPaperSelector().addItem("Select a paper");
		for (Paper paper : this.reviewer.getPendingPapers()) {
			mainView.getComboBoxPaperSelector().addItem(paper.getName() + " ID:" + paper.getID());
		}
	}

	public void updateLabels(Paper p) {
		this.mainView.getLblPaperName().setText("Paper name: " + p.getName());
		this.mainView.getLblAuthorName()
				.setText("Author name: " + p.getAuthor().getName() + " " + p.getAuthor().getSurname());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		switch (command) {

		case "SELECT PAPER":
			if (mainView.getComboBoxPaperSelector().getSelectedIndex() > 0) {
				String[] part = mainView.getComboBoxPaperSelector().getSelectedItem().toString().split(":");
				String id = part[1];
				for (Paper paper : this.reviewer.getPendingPapers()) {
					if (id.equals(paper.getID())) {
						updateLabels(paper);
					}
				}
			}
			break;

		case "DOWNLOAD":
			if (mainView.getComboBoxPaperSelector().getSelectedIndex() > 0) {
				String[] part = mainView.getComboBoxPaperSelector().getSelectedItem().toString().split(":");
				String id = part[1];
				for (Paper paper : this.reviewer.getPendingPapers()) {
					if (id.equals(paper.getID())) {
						if (Desktop.isDesktopSupported()) {
							try {
								Desktop.getDesktop().open(new File(paper.getFile()));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, ("Download not supported\nYou can find the file in: "+paper.getFile()));
								e1.printStackTrace();
							}
						}
					}
				}
			}

			break;

		case "EVALUATE":
			if (mainView.getComboBoxPaperSelector().getSelectedIndex() > 0) {
				String[] part = mainView.getComboBoxPaperSelector().getSelectedItem().toString().split(":");
				String id = part[1];
				for (Paper paper : this.reviewer.getPendingPapers()) {
					if (id.equals(paper.getID())) {
						Review r = evaluatePaper(paper);
						paper.setReview(r);
						reviewer.getReviews().add(r);
						mainController.getParser().addToParser(r);
						reviewer.getPendingPapers().remove(paper);
						JOptionPane.showMessageDialog(null, ("The paper has been evaluated\nReview ID: " + r.getID()));
						clearFields();
						loadPaperDetailComboBox();
					}
				}
			}
			break;

		case "CANCEL":
			this.mainController.showMainView();
			break;

		default:
			break;
		}

	}
}
