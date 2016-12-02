package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Models.*;
import Views.MainView;

public class ChairController implements ActionListener {
	private MainView mainView;
	private MainController mainController;
	private Chair chair;
	private Conference conference;

	public ChairController(Conference c, MainView mv, MainController mc) {
		this.conference = c;
		this.mainView = mv;
		this.mainController = mc;
		this.chair = c.getChair();

		clearTables();
		updateTables();

	}

	private void clearTables() {
		DefaultTableModel model = (DefaultTableModel) mainView.getCandidatesTable().getModel();
		model.setRowCount(0);

		DefaultTableModel model2 = (DefaultTableModel) mainView.getPapersTable().getModel();
		model2.setRowCount(0);

		DefaultTableModel model3 = (DefaultTableModel) mainView.getReviewersTable().getModel();
		model3.setRowCount(0);
	}

	public void updateTables() {
		DefaultTableModel model = (DefaultTableModel) mainView.getCandidatesTable().getModel();
		for (Reviewer reviewer : mainController.getParser().getReviewers()) {
			if (!conference.getReviewerList().contains(reviewer)) {
				model.addRow(new Object[] { reviewer.getID(), reviewer.getName(), reviewer.getSurname(),
						reviewer.getTopic(), reviewer.getSalaryPerReview() + "$" });
			}
		}
		DefaultTableModel model2 = (DefaultTableModel) mainView.getPapersTable().getModel();
		for (Paper paper : conference.getPaperList()) {
			boolean assigned = false;
			if (!paper.hasReview()) {
				for (Reviewer reviewer : conference.getReviewerList()) {
					if (reviewer.getPendingPapers().contains(paper))
						assigned = true;
				}
				if (!assigned)
					model2.addRow(new Object[] { paper.getID(), paper.getName(), paper.getTopic(),
							paper.getAuthor().getName(), paper.getAuthor().getSurname() });
			}
		}
		DefaultTableModel model3 = (DefaultTableModel) mainView.getReviewersTable().getModel();
		for (Reviewer reviewer : conference.getReviewerList()) {
			model3.addRow(new Object[] { reviewer.getID(), reviewer.getName(), reviewer.getSurname(),
					reviewer.getTopic(), reviewer.getSalaryPerReview() + "$" });
		}
	}

	public void assignPaper(Paper p, Reviewer r) {
		r.getPendingPapers().add(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		switch (command) {

		case "ASSIGN PAPER":
			String paperID = this.mainView.getPapersTable()
					.getValueAt(this.mainView.getPapersTable().getSelectedRow(), 0).toString();
			String reviewerID = this.mainView.getReviewersTable()
					.getValueAt(this.mainView.getReviewersTable().getSelectedRow(), 0).toString();

			Paper p = null;
			Reviewer r = null;

			for (Paper paper : conference.getPaperList()) {
				if (paperID.equals(paper.getID()))
					p = paper;
			}
			for (Reviewer reviewer : conference.getReviewerList()) {
				if (reviewerID.equals(reviewer.getID()))
					r = reviewer;
			}
			if (p != null && r != null)
				assignPaper(p, r);
			JOptionPane.showMessageDialog(null,
					("The paper with ID: " + p.getID() + " has been assigned to the reviewer with ID: " + r.getID()));
			clearTables();
			updateTables();
			break;

		case "CHANGE TO HIRE":
			this.mainView.getChairAssignPaperView().hide();
			this.mainView.getChairHireView().show();
			break;

		case "HIRE":
			String candidateID = this.mainView.getCandidatesTable()
					.getValueAt(this.mainView.getCandidatesTable().getSelectedRow(), 0).toString();
			for (Reviewer reviewer : mainController.getParser().getReviewers()) {
				if (candidateID.equals(reviewer.getID()) && !conference.getReviewerList().contains(reviewer)) {
					conference.getReviewerList().add(reviewer);
					JOptionPane.showMessageDialog(null,
							("The candidate with ID: " + reviewer.getID()
									+ " has been assigned to the conference with ID: " + conference.getID()
									+ " as a reviewer"));
					clearTables();
					updateTables();
				}
			}
			break;

		case "VIEW CV":
			String candidateIDD = this.mainView.getCandidatesTable()
					.getValueAt(this.mainView.getCandidatesTable().getSelectedRow(), 0).toString();
			for (Reviewer reviewer : mainController.getParser().getReviewers()) {
				if (candidateIDD.equals(reviewer.getID())) {
					JOptionPane.showMessageDialog(null,
							("CV of the candidate with ID: " + reviewer.getID() + "\n" + reviewer.getCv()));
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
