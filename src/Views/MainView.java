package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class MainView {

	public JFrame frmOneconference;
	public JTable candidatesTable;
	public JTable papersTable;
	public JTable reviewersTable;

	private JPanel MainView;

	private JComboBox comboBox;
	private JComboBox authorComboBox;
	private JComboBox chairComboBox;
	private JComboBox reviewerComboBox;
	private JLabel lblAuthor;
	private JLabel lblReviewer;
	private JLabel lblChair;
	private JButton btnSubmitPaperMain;
	private JButton btnAssingPaperMain;
	private JButton btnEvaluatePaperMain;
	private JButton btnHireReviewerMain;
	private JButton btnExit;
	private JButton btnSaveDB;

	private JPanel PaperSubmitView;
	private JTextField textFieldName;
	private JTextField textFieldTopic;
	private JComboBox conferenceComboBox;
	private JLabel lblConference;
	private JLabel lblFile;
	private JLabel lblName;
	private JLabel lblTopic;
	private JButton btnFile;
	private JButton btnSubmit;
	private JButton btnCancelSubmitButton;

	private JPanel PaperDetailedView;
	private JTextField textFieldGrade;
	private JButton btnDownloadPaper;
	private JLabel lblPaperName;
	private JLabel lblAuthorName;
	private JButton btnEvaluate;
	private JButton btnCancelEvaluatePaper;
	private JLabel lblComment;
	private JLabel lblGrade;
	private JComboBox comboBoxPaperSelector;
	private JTextArea evaluatePaperTextArea;

	private JPanel ChairHireView;
	private JLabel lblListOfCandidates;
	private JButton btnViewCv;
	private JButton btnHire;
	private JButton btnCancelHire;

	private JPanel ChairAssignPaperView;
	private JLabel lblPaper;
	private JLabel lblReviewers;
	private JButton btnAssingPaper;
	private JButton btnHireReviewer;
	private JButton btnCancelAssignButton;

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOneconference = new JFrame();
		frmOneconference.setTitle("OneConference");
		frmOneconference.setBounds(100, 100, 800, 600);
		frmOneconference.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOneconference.getContentPane().setLayout(new CardLayout(0, 0));

		MainView = new JPanel();
		frmOneconference.getContentPane().add(MainView, "name_20229845154367");
		MainView.setLayout(null);

		authorComboBox = new JComboBox();
		authorComboBox.setToolTipText("Choose a author");
		authorComboBox.setBounds(84, 119, 223, 20);
		MainView.add(authorComboBox);

		chairComboBox = new JComboBox();
		chairComboBox.setToolTipText("Choose a chair");
		chairComboBox.setBounds(422, 119, 223, 20);
		MainView.add(chairComboBox);

		reviewerComboBox = new JComboBox();
		reviewerComboBox.setToolTipText("Choose a reviewer");
		reviewerComboBox.setBounds(96, 344, 211, 20);
		MainView.add(reviewerComboBox);

		lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(28, 122, 46, 14);
		MainView.add(lblAuthor);

		lblReviewer = new JLabel("Reviewer");
		lblReviewer.setBounds(28, 347, 58, 14);
		MainView.add(lblReviewer);

		lblChair = new JLabel("Chair");
		lblChair.setBounds(390, 122, 46, 14);
		MainView.add(lblChair);

		btnSubmitPaperMain = new JButton("Submit paper");
		btnSubmitPaperMain.setBounds(94, 150, 110, 23);
		MainView.add(btnSubmitPaperMain);

		btnEvaluatePaperMain = new JButton("Evaluate paper");
		btnEvaluatePaperMain.setBounds(106, 375, 149, 23);
		MainView.add(btnEvaluatePaperMain);

		btnHireReviewerMain = new JButton("Hire reviewer");
		btnHireReviewerMain.setBounds(535, 150, 110, 23);
		MainView.add(btnHireReviewerMain);

		btnAssingPaperMain = new JButton("Assign paper");
		btnAssingPaperMain.setBounds(415, 150, 110, 23);
		MainView.add(btnAssingPaperMain);

		JLabel lblNewLabel = new JLabel("Conference");
		lblNewLabel.setBounds(106, 28, 86, 20);
		MainView.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.setToolTipText("Choose a available conference");
		comboBox.setBounds(202, 28, 323, 20);
		MainView.add(comboBox);

		btnExit = new JButton("Exit");
		btnExit.setBounds(394, 527, 89, 23);
		MainView.add(btnExit);

		btnSaveDB = new JButton("Save DB");
		btnSaveDB.setBounds(295, 527, 89, 23);
		MainView.add(btnSaveDB);

		PaperSubmitView = new JPanel();
		frmOneconference.getContentPane().add(PaperSubmitView, "name_20125417952936");
		PaperSubmitView.setLayout(null);

		conferenceComboBox = new JComboBox();
		conferenceComboBox.setToolTipText("Choose a available conference");
		conferenceComboBox.setBounds(127, 51, 311, 20);
		PaperSubmitView.add(conferenceComboBox);

		lblConference = new JLabel("Conference");
		lblConference.setBounds(28, 54, 89, 14);
		PaperSubmitView.add(lblConference);

		lblFile = new JLabel("Please choose a file");
		lblFile.setBounds(28, 223, 141, 14);
		PaperSubmitView.add(lblFile);

		lblName = new JLabel("Name");
		lblName.setBounds(28, 119, 46, 14);
		PaperSubmitView.add(lblName);

		lblTopic = new JLabel("Topic");
		lblTopic.setBounds(28, 144, 46, 14);
		PaperSubmitView.add(lblTopic);

		btnFile = new JButton("File");
		btnFile.setBounds(179, 219, 89, 23);
		PaperSubmitView.add(btnFile);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(80, 302, 89, 23);
		PaperSubmitView.add(btnSubmit);

		textFieldName = new JTextField();
		textFieldName.setToolTipText("Paper name");
		textFieldName.setBounds(94, 116, 344, 20);
		PaperSubmitView.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldTopic = new JTextField();
		textFieldTopic.setToolTipText("Topic of the paper");
		textFieldTopic.setBounds(94, 141, 344, 20);
		PaperSubmitView.add(textFieldTopic);
		textFieldTopic.setColumns(10);

		btnCancelSubmitButton = new JButton("Cancel");
		btnCancelSubmitButton.setBounds(179, 302, 89, 23);
		PaperSubmitView.add(btnCancelSubmitButton);

		PaperDetailedView = new JPanel();
		frmOneconference.getContentPane().add(PaperDetailedView, "name_20130329821312");
		PaperDetailedView.setLayout(null);

		btnDownloadPaper = new JButton("View paper");
		btnDownloadPaper.setBounds(10, 98, 142, 23);
		PaperDetailedView.add(btnDownloadPaper);

		lblPaperName = new JLabel("Paper name:");
		lblPaperName.setBounds(10, 48, 341, 14);
		PaperDetailedView.add(lblPaperName);

		lblAuthorName = new JLabel("Author: ");
		lblAuthorName.setBounds(10, 73, 341, 14);
		PaperDetailedView.add(lblAuthorName);

		btnEvaluate = new JButton("Evaluate");
		btnEvaluate.setBounds(281, 528, 89, 23);
		PaperDetailedView.add(btnEvaluate);

		lblComment = new JLabel("Comment");
		lblComment.setBounds(10, 132, 142, 14);
		PaperDetailedView.add(lblComment);

		lblGrade = new JLabel("Grade");
		lblGrade.setBounds(10, 469, 46, 14);
		PaperDetailedView.add(lblGrade);

		textFieldGrade = new JTextField();
		textFieldGrade.setBounds(66, 466, 86, 20);
		PaperDetailedView.add(textFieldGrade);
		textFieldGrade.setColumns(10);

		btnCancelEvaluatePaper = new JButton("Cancel");
		btnCancelEvaluatePaper.setBounds(380, 528, 89, 23);
		PaperDetailedView.add(btnCancelEvaluatePaper);

		JLabel lblNewLabel_1 = new JLabel("Select paper to evaluate");
		lblNewLabel_1.setBounds(10, 23, 142, 14);
		PaperDetailedView.add(lblNewLabel_1);

		comboBoxPaperSelector = new JComboBox();
		comboBoxPaperSelector.setToolTipText("Select a paper to evaluate");
		comboBoxPaperSelector.setBounds(162, 20, 307, 20);
		PaperDetailedView.add(comboBoxPaperSelector);

		evaluatePaperTextArea = new JTextArea();
		evaluatePaperTextArea.setBounds(10, 157, 764, 301);
		PaperDetailedView.add(evaluatePaperTextArea);

		ChairHireView = new JPanel();
		frmOneconference.getContentPane().add(ChairHireView, "name_20132809983994");
		ChairHireView.setLayout(null);

		lblListOfCandidates = new JLabel("List of candidates");
		lblListOfCandidates.setBounds(10, 41, 105, 14);
		ChairHireView.add(lblListOfCandidates);

		btnViewCv = new JButton("View CV");
		btnViewCv.setBounds(10, 501, 89, 23);
		ChairHireView.add(btnViewCv);

		btnHire = new JButton("Hire");
		btnHire.setBounds(685, 501, 89, 23);
		ChairHireView.add(btnHire);

		btnCancelHire = new JButton("Cancel");
		btnCancelHire.setBounds(336, 501, 89, 23);
		ChairHireView.add(btnCancelHire);

		candidatesTable = new JTable();
		candidatesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		candidatesTable.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Surname", "Topic", "Salary" }

		) {

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
		JScrollPane scrollPane = new JScrollPane(candidatesTable);
		scrollPane.setBounds(10, 66, 764, 400);
		ChairHireView.add(scrollPane);

		ChairAssignPaperView = new JPanel();
		frmOneconference.getContentPane().add(ChairAssignPaperView, "name_20134697620091");
		ChairAssignPaperView.setLayout(null);

		lblPaper = new JLabel("Paper");
		lblPaper.setBounds(10, 50, 46, 14);
		ChairAssignPaperView.add(lblPaper);

		lblReviewers = new JLabel("Reviewers");
		lblReviewers.setBounds(696, 50, 78, 14);
		ChairAssignPaperView.add(lblReviewers);

		papersTable = new JTable();
		papersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		papersTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Topic", "Author name", "Surname" }) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		JScrollPane scrollPane2 = new JScrollPane(papersTable);
		scrollPane2.setBounds(10, 75, 375, 415);
		ChairAssignPaperView.add(scrollPane2);

		reviewersTable = new JTable();
		reviewersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reviewersTable.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Surname", "Topic", "Salary" }) {

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
		JScrollPane scrollPane3 = new JScrollPane(reviewersTable);
		scrollPane3.setBounds(399, 75, 375, 415);
		ChairAssignPaperView.add(scrollPane3);

		btnAssingPaper = new JButton("Assing paper");
		btnAssingPaper.setBounds(265, 513, 120, 23);
		ChairAssignPaperView.add(btnAssingPaper);

		btnHireReviewer = new JButton("Hire reviewer");
		btnHireReviewer.setBounds(624, 513, 150, 23);
		ChairAssignPaperView.add(btnHireReviewer);

		btnCancelAssignButton = new JButton("Cancel");
		btnCancelAssignButton.setBounds(399, 513, 89, 23);
		ChairAssignPaperView.add(btnCancelAssignButton);
	}

	// Getters
	public JFrame getFrmOneconference() {
		return frmOneconference;
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public JTextField getTextFieldTopic() {
		return textFieldTopic;
	}

	public JTextArea getEvaluatePaperTextArea() {
		return evaluatePaperTextArea;
	}

	public JTable getCandidatesTable() {
		return candidatesTable;
	}

	public JTable getPapersTable() {
		return papersTable;
	}

	public JTable getReviewersTable() {
		return reviewersTable;
	}

	public JPanel getMainView() {
		return MainView;
	}

	public JComboBox getAuthorComboBox() {
		return authorComboBox;
	}

	public JComboBox getChairComboBox() {
		return chairComboBox;
	}

	public JComboBox getReviewerComboBox() {
		return reviewerComboBox;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JButton getBtnSubmitPaperMain() {
		return btnSubmitPaperMain;
	}

	public JButton getBtnAssingPaperMain() {
		return btnAssingPaperMain;
	}

	public JButton getBtnEvaluatePaperMain() {
		return btnEvaluatePaperMain;
	}

	public JPanel getPaperSubmitView() {
		return PaperSubmitView;
	}

	public JComboBox getConferenceComboBox() {
		return conferenceComboBox;
	}

	public JButton getBtnFile() {
		return btnFile;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public JPanel getPaperDetailedView() {
		return PaperDetailedView;
	}

	public JButton getBtnDownloadPaper() {
		return btnDownloadPaper;
	}

	public JButton getBtnEvaluate() {
		return btnEvaluate;
	}

	public JPanel getChairHireView() {
		return ChairHireView;
	}

	public JButton getBtnViewCv() {
		return btnViewCv;
	}

	public JButton getBtnHire() {
		return btnHire;
	}

	public JPanel getChairAssignPaperView() {
		return ChairAssignPaperView;
	}

	public JButton getBtnAssingPaper() {
		return btnAssingPaper;
	}

	public JButton getBtnHireReviewer() {
		return btnHireReviewer;
	}

	public JComboBox getComboBoxPaperSelector() {
		return this.comboBoxPaperSelector;
	}

	public JLabel getLblPaperName() {
		return lblPaperName;
	}

	public JLabel getLblAuthorName() {
		return lblAuthorName;
	}

	public JTextField getTextFieldGrade() {
		return textFieldGrade;
	}

	public void setControllerForMainViewObjects(Object c) {
		comboBox.addActionListener((ActionListener) c);
		comboBox.setActionCommand("SELECT CONFERENCE");

		authorComboBox.addActionListener((ActionListener) c);
		chairComboBox.addActionListener((ActionListener) c);
		reviewerComboBox.addActionListener((ActionListener) c);

		btnSubmitPaperMain.addActionListener((ActionListener) c);
		btnSubmitPaperMain.setActionCommand("SUBMIT PAPER");

		btnAssingPaperMain.addActionListener((ActionListener) c);
		btnAssingPaperMain.setActionCommand("ASSIGN PAPER");

		btnEvaluatePaperMain.addActionListener((ActionListener) c);
		btnEvaluatePaperMain.setActionCommand("EVALUATE PAPER");

		btnHireReviewerMain.addActionListener((ActionListener) c);
		btnHireReviewerMain.setActionCommand("HIRE REVIEWER");

		btnExit.addActionListener((ActionListener) c);
		btnExit.setActionCommand("EXIT");

		btnSaveDB.addActionListener((ActionListener) c);
		btnSaveDB.setActionCommand("SAVE");
	}

	public void setControllerForPaperSubmitViewObjects(Object c) {
		btnFile.addActionListener((ActionListener) c);
		btnFile.setActionCommand("SELECT FILE");

		btnSubmit.addActionListener((ActionListener) c);
		btnSubmit.setActionCommand("SUBMIT");

		btnCancelSubmitButton.addActionListener((ActionListener) c);
		btnCancelSubmitButton.setActionCommand("CANCEL");

	}

	public void setControllerForChairViewObjects(Object c) {

		btnAssingPaper.addActionListener((ActionListener) c);
		btnAssingPaper.setActionCommand("ASSIGN PAPER");

		btnHireReviewer.addActionListener((ActionListener) c);
		btnHireReviewer.setActionCommand("CHANGE TO HIRE");

		btnCancelAssignButton.addActionListener((ActionListener) c);
		btnCancelAssignButton.setActionCommand("CANCEL");

		btnViewCv.addActionListener((ActionListener) c);
		btnViewCv.setActionCommand("VIEW CV");

		btnHire.addActionListener((ActionListener) c);
		btnHire.setActionCommand("HIRE");

		btnCancelHire.addActionListener((ActionListener) c);
		btnCancelHire.setActionCommand("CANCEL");

	}

	public void setControllerForEvaluatePaper(Object c) {
		comboBoxPaperSelector.addActionListener((ActionListener) c);
		comboBoxPaperSelector.setActionCommand("SELECT PAPER");

		btnDownloadPaper.addActionListener((ActionListener) c);
		btnDownloadPaper.setActionCommand("DOWNLOAD");

		btnEvaluate.addActionListener((ActionListener) c);
		btnEvaluate.setActionCommand("EVALUATE");

		btnCancelEvaluatePaper.addActionListener((ActionListener) c);
		btnCancelEvaluatePaper.setActionCommand("CANCEL");

	}
}
