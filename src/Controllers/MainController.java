package Controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import Models.*;
import Views.MainView;

public class MainController implements ActionListener {
	private MainView mainView;

	private DOMParser parser;

	private AuthorController authorController;
	private ChairController chairController;
	private ReviewerController reviewerController;

	public MainController() throws ParserConfigurationException, SAXException, IOException {
		// Load data
		parser = new DOMParser();

		// create the view
		this.mainView = new MainView();

		// link the controller to view elements
		mainView.setControllerForMainViewObjects(this);

		// Fill the fields of the view
		mainView.getComboBox().addItem("Choose a conference");
		for (Conference conference : parser.getConferences()) {
			mainView.getComboBox().addItem(conference.getName() + " ID:" + conference.getID());
		}
	}

	public void clearAndFill() {
		if (mainView.getComboBox().getSelectedIndex() > 0) {
			String[] partt = mainView.getComboBox().getSelectedItem().toString().split(":");
			String conferenceID = partt[1];
			for (Conference conference : parser.getConferences()) {
				if (conferenceID.equals(conference.getID()))
					loadMainComboBoxesForAConference(conference);
			}
		}
	}

	public void loadMainComboBoxesForAConference(Conference c) {

		mainView.getAuthorComboBox().removeAllItems();
		mainView.getAuthorComboBox().addItem("Select a author");
		for (Author author : c.getAuthorlist()) {
			mainView.getAuthorComboBox()
					.addItem(author.getName() + " " + author.getSurname() + " ID:" + author.getID());
		}

		mainView.getReviewerComboBox().removeAllItems();
		mainView.getReviewerComboBox().addItem("Select a reviewer");
		for (Reviewer reviewer : c.getReviewerList()) {
			mainView.getReviewerComboBox()
					.addItem(reviewer.getName() + " " + reviewer.getSurname() + " ID:" + reviewer.getID());
		}

		mainView.getChairComboBox().removeAllItems();
		mainView.getChairComboBox().addItem("Select a chair");
		mainView.getChairComboBox()
				.addItem(c.getChair().getName() + " " + c.getChair().getSurname() + " ID:" + c.getChair().getID());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		switch (command) {

		case "SUBMIT PAPER":
			if (mainView.getComboBox().getSelectedIndex() > 0) {
				String[] part = mainView.getAuthorComboBox().getSelectedItem().toString().split(":");
				String id = part[1];

				Author a = null;
				for (Author author : parser.getAuthors()) {
					if (id.equals(author.getID()))
						a = author;
				}
				if (a != null) {
					authorController = new AuthorController(a, mainView, this);
					mainView.setControllerForPaperSubmitViewObjects(authorController);
					mainView.getMainView().hide();
					mainView.getPaperSubmitView().show();

				}
			}

			break;

		case "ASSIGN PAPER":
			if (mainView.getChairComboBox().getSelectedIndex() > 0) {
				String[] partt = mainView.getComboBox().getSelectedItem().toString().split(":");
				String conferenceID = partt[1];
				for (Conference conference : parser.getConferences()) {
					if (conferenceID.equals(conference.getID())) {
						this.chairController = new ChairController(conference, this.mainView, this);
						mainView.setControllerForChairViewObjects(chairController);
						mainView.getMainView().hide();
						mainView.getChairAssignPaperView().show();
					}

				}
			}
			break;

		case "HIRE REVIEWER":
			if (mainView.getChairComboBox().getSelectedIndex() > 0) {
				String[] partt = mainView.getComboBox().getSelectedItem().toString().split(":");
				String conferenceID = partt[1];
				for (Conference conference : parser.getConferences()) {
					if (conferenceID.equals(conference.getID())) {
						this.chairController = new ChairController(conference, this.mainView, this);
						mainView.setControllerForChairViewObjects(chairController);
						mainView.getMainView().hide();
						mainView.getChairHireView().show();
					}

				}
			}
			break;

		case "EVALUATE PAPER":
			if (mainView.getComboBox().getSelectedIndex() > 0) {
				String[] part = mainView.getReviewerComboBox().getSelectedItem().toString().split(":");
				String id = part[1];

				Reviewer r = null;
				for (Reviewer reviewer : parser.getReviewers()) {
					if (id.equals(reviewer.getID()))
						r = reviewer;
				}
				if (r != null) {
					reviewerController = new ReviewerController(mainView, r, this);
					mainView.setControllerForEvaluatePaper(reviewerController);
					mainView.getMainView().hide();
					mainView.getPaperDetailedView().show();
				}
			}
			break;

		case "SELECT CONFERENCE":
			clearAndFill();
			break;

		case "SAVE":
			try {
				this.parser.saveDB();
				JOptionPane.showMessageDialog(null, ("The data base has been saved"));
			} catch (ParserConfigurationException | TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case "EXIT":
			System.exit(0);
			break;

		default:
			break;
		}
	}

	public DOMParser getParser() {
		return this.parser;
	}

	public void showMainView() {
		clearAndFill();
		this.mainView.getChairAssignPaperView().hide();
		this.mainView.getChairHireView().hide();
		this.mainView.getPaperDetailedView().hide();
		this.mainView.getPaperSubmitView().hide();
		this.mainView.getMainView().show();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainController mainController = new MainController();
					mainController.mainView.frmOneconference.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
