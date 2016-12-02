package Models;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DOMParser {
	private static ArrayList<Author> authors = new ArrayList<Author>();
	private static ArrayList<Chair> chairs = new ArrayList<Chair>();
	private static ArrayList<Reviewer> reviewers = new ArrayList<Reviewer>();
	private static ArrayList<Paper> papers = new ArrayList<Paper>();
	private static ArrayList<Review> reviews = new ArrayList<Review>();
	private static ArrayList<Conference> conferences = new ArrayList<Conference>();

	public DOMParser() throws ParserConfigurationException, SAXException, IOException {
		File inputFile = new File("db.xml");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);

		doc.getDocumentElement().normalize();

		// Authors
		NodeList nList = doc.getElementsByTagName("Author");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				Author author = new Author(eElement.getAttribute("id"),
						eElement.getElementsByTagName("name").item(0).getTextContent(),
						eElement.getElementsByTagName("surname").item(0).getTextContent(),
						eElement.getElementsByTagName("city").item(0).getTextContent(),
						eElement.getElementsByTagName("country").item(0).getTextContent(),
						eElement.getElementsByTagName("mail").item(0).getTextContent(),
						eElement.getElementsByTagName("password").item(0).getTextContent());
				authors.add(author);
			}
		}
		// Chair
		NodeList nList3 = doc.getElementsByTagName("Chair");
		for (int temp = 0; temp < nList3.getLength(); temp++) {
			Node nNode = nList3.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				Chair chair = new Chair(eElement.getAttribute("id"),
						eElement.getElementsByTagName("name").item(0).getTextContent(),
						eElement.getElementsByTagName("surname").item(0).getTextContent(),
						eElement.getElementsByTagName("city").item(0).getTextContent(),
						eElement.getElementsByTagName("country").item(0).getTextContent(),
						eElement.getElementsByTagName("mail").item(0).getTextContent(),
						eElement.getElementsByTagName("password").item(0).getTextContent());
				chairs.add(chair);
			}
		}

		// Paper
		NodeList nList5 = doc.getElementsByTagName("Paper");
		for (int temp = 0; temp < nList5.getLength(); temp++) {
			Node nNode = nList5.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				Element eElement2 = (Element) eElement.getElementsByTagName("author").item(0);
				String id = eElement2.getAttribute("ref");

				Author a = null;
				for (Author author : authors) {
					if (id.equals(author.getID()))
						a = author;
				}
				;

				Paper paper = new Paper(eElement.getAttribute("id"), a,
						eElement.getElementsByTagName("name").item(0).getTextContent(), null,
						eElement.getElementsByTagName("topic").item(0).getTextContent(),
						eElement.getElementsByTagName("file").item(0).getTextContent());
				papers.add(paper);
				a.getPapers().add(paper);
			}
		}
		// Reviewers
		NodeList nList2 = doc.getElementsByTagName("Reviewer");
		for (int temp = 0; temp < nList2.getLength(); temp++) {
			Node nNode = nList2.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				ArrayList<Paper> pendingPaperList = new ArrayList<Paper>();

				// add pending papers
				for (int i = 0; i < eElement.getElementsByTagName("paper").getLength(); i++) {

					Element eElement3 = (Element) eElement.getElementsByTagName("paper").item(i);
					String id3 = eElement3.getAttribute("ref");

					Paper p = null;
					for (Paper paper : papers) {
						if (id3.equals(paper.getID()))
							p = paper;
					}
					if (p != null) {
						pendingPaperList.add(p);
					}
				}

				Reviewer reviewer = new Reviewer(eElement.getAttribute("id"),
						eElement.getElementsByTagName("topic").item(0).getTextContent(),
						eElement.getElementsByTagName("cv").item(0).getTextContent(),
						Integer.parseInt(eElement.getElementsByTagName("salary").item(0).getTextContent()),
						eElement.getElementsByTagName("name").item(0).getTextContent(),
						eElement.getElementsByTagName("surname").item(0).getTextContent(),
						eElement.getElementsByTagName("city").item(0).getTextContent(),
						eElement.getElementsByTagName("country").item(0).getTextContent(),
						eElement.getElementsByTagName("mail").item(0).getTextContent(),
						eElement.getElementsByTagName("password").item(0).getTextContent());
				reviewers.add(reviewer);
				reviewer.setPendingPapers(pendingPaperList);
			}
		}

		// Reviews
		NodeList nList6 = doc.getElementsByTagName("Review");
		for (int temp = 0; temp < nList6.getLength(); temp++) {
			Node nNode = nList6.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				// Search reviewer
				Element eElement2 = (Element) eElement.getElementsByTagName("reviewer").item(0);
				String id = eElement2.getAttribute("ref");

				Reviewer r = null;
				for (Reviewer reviewer : reviewers) {
					if (id.equals(reviewer.getID()))
						r = reviewer;
				}

				// Search paper
				Element eElement3 = (Element) eElement.getElementsByTagName("paper").item(0);
				String id2 = eElement3.getAttribute("ref");

				Paper p = null;
				for (Paper paper : papers) {
					if (id2.equals(paper.getID()))
						p = paper;
				}

				Review review = new Review(eElement.getAttribute("id"),
						Integer.parseInt(eElement.getElementsByTagName("grade").item(0).getTextContent()), null,
						eElement.getElementsByTagName("comment").item(0).getTextContent(), r, p);
				reviews.add(review);
				r.getReviews().add(review);
				p.setReview(review);
			}
		}

		// Conference
		NodeList nList4 = doc.getElementsByTagName("Conference");
		for (int temp = 0; temp < nList4.getLength(); temp++) {
			Node nNode = nList4.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				Element eElement2 = (Element) eElement.getElementsByTagName("chair").item(0);
				String id = eElement2.getAttribute("ref");

				Chair c = null;
				for (Chair chair : chairs) {
					if (id.equals(chair.getID()))
						c = chair;
				}

				ArrayList<Paper> paperList = new ArrayList<Paper>();

				ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();

				ArrayList<Author> authorList = new ArrayList<Author>();

				// add reviewers
				for (int i = 0; i < eElement.getElementsByTagName("reviewer").getLength(); i++) {

					Element eElement3 = (Element) eElement.getElementsByTagName("reviewer").item(i);
					String id3 = eElement3.getAttribute("ref");

					Reviewer r = null;
					for (Reviewer reviewer : reviewers) {
						if (id3.equals(reviewer.getID()))
							r = reviewer;
					}
					if (r != null) {
						reviewerList.add(r);
					}
				}

				// add authors
				for (int i = 0; i < eElement.getElementsByTagName("author").getLength(); i++) {

					Element eElement3 = (Element) eElement.getElementsByTagName("author").item(i);
					String id3 = eElement3.getAttribute("ref");

					Author a = null;
					for (Author author : authors) {
						if (id3.equals(author.getID()))
							a = author;
					}
					if (a != null) {
						authorList.add(a);
					}
				}

				// add papers
				for (int i = 0; i < eElement.getElementsByTagName("paper").getLength(); i++) {

					Element eElement3 = (Element) eElement.getElementsByTagName("paper").item(i);
					String id3 = eElement3.getAttribute("ref");

					Paper p = null;
					for (Paper paper : papers) {
						if (id3.equals(paper.getID()))
							p = paper;
					}
					if (p != null) {
						paperList.add(p);
					}
				}

				Conference conference = new Conference(eElement.getAttribute("id"),
						eElement.getElementsByTagName("name").item(0).getTextContent(), null,
						Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent()),
						eElement.getElementsByTagName("city").item(0).getTextContent(), null, null,
						Integer.parseInt(eElement.getElementsByTagName("capacity").item(0).getTextContent()), c);
				conferences.add(conference);

				conference.setPaperList(paperList);
				conference.setReviewerList(reviewerList);
				conference.setAuthorlist(authorList);

				c.getConferenceList().add(conference);
				// TODO
				// p.setConference(conference);
			}
		}

	}

	public void saveDB() throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();

		// root element
		Element rootElement = doc.createElement("OneConference");
		doc.appendChild(rootElement);

		// Authors
		Element as = doc.createElement("Authors");
		rootElement.appendChild(as);
		// Author
		for (Author author : this.authors) {
			Element a = doc.createElement("Author");
			as.appendChild(a);

			Attr id = doc.createAttribute("id");
			id.setValue(author.getID());
			a.setAttributeNode(id);

			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(author.getName()));
			a.appendChild(name);

			Element surname = doc.createElement("surname");
			surname.appendChild(doc.createTextNode(author.getSurname()));
			a.appendChild(surname);

			Element pass = doc.createElement("password");
			pass.appendChild(doc.createTextNode(author.getPassword()));
			a.appendChild(pass);

			Element city = doc.createElement("city");
			city.appendChild(doc.createTextNode(author.getCity()));
			a.appendChild(city);

			Element country = doc.createElement("country");
			country.appendChild(doc.createTextNode(author.getCountry()));
			a.appendChild(country);

			Element mail = doc.createElement("mail");
			mail.appendChild(doc.createTextNode(author.getMail()));
			a.appendChild(mail);
		}

		// Chairs
		Element cs = doc.createElement("Chairs");
		rootElement.appendChild(cs);
		// Chair
		for (Chair author : this.chairs) {
			Element a = doc.createElement("Chair");
			cs.appendChild(a);

			Attr id = doc.createAttribute("id");
			id.setValue(author.getID());
			a.setAttributeNode(id);

			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(author.getName()));
			a.appendChild(name);

			Element surname = doc.createElement("surname");
			surname.appendChild(doc.createTextNode(author.getSurname()));
			a.appendChild(surname);

			Element pass = doc.createElement("password");
			pass.appendChild(doc.createTextNode(author.getPassword()));
			a.appendChild(pass);

			Element city = doc.createElement("city");
			city.appendChild(doc.createTextNode(author.getCity()));
			a.appendChild(city);

			Element country = doc.createElement("country");
			country.appendChild(doc.createTextNode(author.getCountry()));
			a.appendChild(country);

			Element mail = doc.createElement("mail");
			mail.appendChild(doc.createTextNode(author.getMail()));
			a.appendChild(mail);

			Element confs = doc.createElement("conferences");
			a.appendChild(confs);

			for (Conference c : author.getConferenceList()) {
				Element cc = doc.createElement("conference");
				confs.appendChild(cc);
				Attr ref = doc.createAttribute("ref");
				ref.setValue(c.getID());
				cc.setAttributeNode(ref);
			}
		}

		// Reviewers
		Element rs = doc.createElement("Reviewers");
		rootElement.appendChild(rs);
		// Reviewer
		for (Reviewer author : this.reviewers) {
			Element a = doc.createElement("Reviewer");
			rs.appendChild(a);

			Attr id = doc.createAttribute("id");
			id.setValue(author.getID());
			a.setAttributeNode(id);

			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(author.getName()));
			a.appendChild(name);

			Element surname = doc.createElement("surname");
			surname.appendChild(doc.createTextNode(author.getSurname()));
			a.appendChild(surname);

			Element pass = doc.createElement("password");
			pass.appendChild(doc.createTextNode(author.getPassword()));
			a.appendChild(pass);

			Element city = doc.createElement("city");
			city.appendChild(doc.createTextNode(author.getCity()));
			a.appendChild(city);

			Element country = doc.createElement("country");
			country.appendChild(doc.createTextNode(author.getCountry()));
			a.appendChild(country);

			Element mail = doc.createElement("mail");
			mail.appendChild(doc.createTextNode(author.getMail()));
			a.appendChild(mail);

			Element cv = doc.createElement("cv");
			cv.appendChild(doc.createTextNode(author.getCv()));
			a.appendChild(cv);

			Element topic = doc.createElement("topic");
			topic.appendChild(doc.createTextNode(author.getTopic()));
			a.appendChild(topic);

			Element salary = doc.createElement("salary");
			salary.appendChild(doc.createTextNode(Integer.toString(author.getSalaryPerReview())));
			a.appendChild(salary);

			Element r_s = doc.createElement("reviews");
			a.appendChild(r_s);

			for (Review r_ : author.getReviews()) {
				Element cc = doc.createElement("review");
				r_s.appendChild(cc);
				Attr ref = doc.createAttribute("ref");
				ref.setValue(r_.getID());
				cc.setAttributeNode(ref);
			}

			Element r_p = doc.createElement("pendingReviews");
			a.appendChild(r_p);
			for (Paper r_ : author.getPendingPapers()) {
				Element cc = doc.createElement("paper");
				r_p.appendChild(cc);
				Attr ref = doc.createAttribute("ref");
				ref.setValue(r_.getID());
				cc.setAttributeNode(ref);
			}
		}

		// Conferences
		Element conference_s = doc.createElement("Conferences");
		rootElement.appendChild(conference_s);
		// Conference
		for (Conference author : this.conferences) {
			Element a = doc.createElement("Conference");
			conference_s.appendChild(a);

			Attr id = doc.createAttribute("id");
			id.setValue(author.getID());
			a.setAttributeNode(id);

			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(author.getName()));
			a.appendChild(name);

			Element city = doc.createElement("city");
			city.appendChild(doc.createTextNode(author.getCity()));
			a.appendChild(city);

			Element cityy = doc.createElement("price");
			cityy.appendChild(doc.createTextNode(Integer.toString(author.getPrice())));
			a.appendChild(cityy);

			Element capacity = doc.createElement("capacity");
			capacity.appendChild(doc.createTextNode(Integer.toString(author.getCapacity())));
			a.appendChild(capacity);

			Element mail = doc.createElement("chair");
			a.appendChild(mail);
			Attr chair_id = doc.createAttribute("ref");
			chair_id.setValue(author.getChair().getID());
			mail.setAttributeNode(chair_id);

			Element r_s = doc.createElement("paperList");
			a.appendChild(r_s);

			for (Paper r_ : author.getPaperList()) {
				Element cc = doc.createElement("paper");
				r_s.appendChild(cc);
				Attr ref = doc.createAttribute("ref");
				ref.setValue(r_.getID());
				cc.setAttributeNode(ref);
			}

			Element r_p = doc.createElement("reviewerList");
			a.appendChild(r_p);
			for (Reviewer r_ : author.getReviewerList()) {
				Element cc = doc.createElement("reviewer");
				r_p.appendChild(cc);
				Attr ref = doc.createAttribute("ref");
				ref.setValue(r_.getID());
				cc.setAttributeNode(ref);
			}

			Element r_d = doc.createElement("authorList");
			a.appendChild(r_d);
			for (Author r_ : author.getAuthorlist()) {
				Element cc = doc.createElement("author");
				r_d.appendChild(cc);
				Attr ref = doc.createAttribute("ref");
				ref.setValue(r_.getID());
				cc.setAttributeNode(ref);
			}

		}

		// Papers
		Element paper_s = doc.createElement("Papers");
		rootElement.appendChild(paper_s);
		// Paper
		for (Paper author : this.papers) {
			Element a = doc.createElement("Paper");
			paper_s.appendChild(a);

			Attr id = doc.createAttribute("id");
			id.setValue(author.getID());
			a.setAttributeNode(id);

			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(author.getName()));
			a.appendChild(name);

			Element city = doc.createElement("topic");
			city.appendChild(doc.createTextNode(author.getTopic()));
			a.appendChild(city);

			if (author.getReview() != null) {
				Element mail = doc.createElement("review");
				a.appendChild(mail);
				Attr chair_id = doc.createAttribute("ref");
				chair_id.setValue(author.getReview().getID());
				mail.setAttributeNode(chair_id);

			}

			Element maill = doc.createElement("author");
			a.appendChild(maill);
			Attr chair_idd = doc.createAttribute("ref");
			chair_idd.setValue(author.getAuthor().getID());
			maill.setAttributeNode(chair_idd);

			Element cv = doc.createElement("file");
			cv.appendChild(doc.createTextNode(author.getFile()));
			a.appendChild(cv);

		}

		// Reviews
		Element review_s = doc.createElement("Reviews");
		rootElement.appendChild(review_s);
		// Paper
		for (Review author : this.reviews) {
			Element a = doc.createElement("Review");
			review_s.appendChild(a);

			Attr id = doc.createAttribute("id");
			id.setValue(author.getID());
			a.setAttributeNode(id);

			Element name = doc.createElement("grade");
			name.appendChild(doc.createTextNode(Integer.toString(author.getGrade())));
			a.appendChild(name);

			Element city = doc.createElement("comment");
			city.appendChild(doc.createTextNode(author.getComment()));
			a.appendChild(city);

			Element mail = doc.createElement("reviewer");
			a.appendChild(mail);
			Attr chair_id = doc.createAttribute("ref");
			chair_id.setValue(author.getReviewer().getID());
			mail.setAttributeNode(chair_id);

			Element maill = doc.createElement("paper");
			a.appendChild(maill);
			Attr chair_idd = doc.createAttribute("ref");
			chair_idd.setValue(author.getPaper().getID());
			maill.setAttributeNode(chair_idd);

		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("db.xml"));
		transformer.transform(source, result);

	}

	public void addToParser(Object object) {
		if (object.getClass() == Author.class) {
			authors.add((Author) object);
		} else if (object.getClass() == Paper.class) {
			papers.add((Paper) object);
		} else if (object.getClass() == Chair.class) {
			chairs.add((Chair) object);
		} else if (object.getClass() == Reviewer.class) {
			reviewers.add((Reviewer) object);
		} else if (object.getClass() == Review.class) {
			reviews.add((Review) object);
		} else if (object.getClass() == Conference.class) {
			conferences.add((Conference) object);
		}
	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}

	public ArrayList<Chair> getChairs() {
		return chairs;
	}

	public void setChairs(ArrayList<Chair> chairs) {
		this.chairs = chairs;
	}

	public ArrayList<Reviewer> getReviewers() {
		return reviewers;
	}

	public void setReviewers(ArrayList<Reviewer> reviewers) {
		this.reviewers = reviewers;
	}

	public ArrayList<Paper> getPapers() {
		return papers;
	}

	public void setPapers(ArrayList<Paper> papers) {
		this.papers = papers;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public ArrayList<Conference> getConferences() {
		return conferences;
	}

	public void setConferences(ArrayList<Conference> conferences) {
		this.conferences = conferences;
	}

	public static void main(String[] args) {
		try {
			DOMParser parser = new DOMParser();
			for (Conference conference : conferences) {
				for (Author author : conference.getAuthorlist()) {
					System.out.println("Author ID: " + author.getID() + " Conference ID: " + conference.getID());
				}
				for (Reviewer author : conference.getReviewerList()) {
					System.out.println("Reviewer ID: " + author.getID() + " Conference ID: " + conference.getID());
				}
				for (Paper author : conference.getPaperList()) {
					System.out.println("Paper ID: " + author.getID() + " Conference ID: " + conference.getID()
							+ " has review " + author.hasReview());
				}
			}
			parser.saveDB();
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
