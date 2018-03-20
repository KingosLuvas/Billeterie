package billeterie;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class Panel extends JPanel implements ActionListener {
	
	private JFrame frame;
	private JTextField tNom;
	private JTextField tPrenom;
	private JTextField tMail;
	private ButtonGroup bg;
	private JRadioButton bDebout;
	private JRadioButton bAssis;
	
	private ArrayList<Billet> billets = new ArrayList<Billet>();
	private JComboBox liste;
	
	public Panel (JFrame f) {
		
		this.frame = f;
		
		JLabel nom = new JLabel("Nom");
		nom.setBounds(172, 41, 46, 14);
		this.add(nom);
		
		tNom = new JTextField();
		tNom.setBounds(213, 38, 300, 23);
		this.add(tNom);
		tNom.setColumns(10);
		
		JLabel prenom = new JLabel("Prénom");
		prenom.setBounds(152, 98, 46, 14);
		this.add(prenom);
		
		tPrenom = new JTextField();
		tPrenom.setBounds(213, 96, 300, 23);
		this.add(tPrenom);
		tPrenom.setColumns(10);
		
		JLabel Email = new JLabel("Email");
		Email.setBounds(167, 155, 46, 14);
		this.add(Email);
		
		tMail = new JTextField();
		tMail.setBounds(213, 152, 300, 23);
		this.add(tMail);
		tMail.setColumns(10);
		
		JLabel assis = new JLabel("Place assise (+10€)");
		assis.setBounds(220, 324, 130, 14);
		this.add(assis);
		
		JLabel debout = new JLabel("Place debout");
		debout.setBounds(380, 324, 100, 14);
		this.add(debout);
		
		bAssis = new JRadioButton("");
		bAssis.setBounds(335, 322, 20, 20);
		this.add(bAssis);
		
		bDebout = new JRadioButton("");
		bDebout.setBounds(455, 322, 20, 20);
		this.add(bDebout);
		bDebout.setSelected(true);
		
		bg = new ButtonGroup();
		bg.add(bDebout);
		bg.add(bAssis);
		
		billets.add(new Billet("Orelsan",20.00,"11 Mai 2018"));
		billets.add(new Billet("BigFlo & Oli",30.00,"15 Mai 2018"));
		billets.add(new Billet("Victor & Léo",90.99,"29 Juin 2018"));
		billets.add(new Billet("Babor Lelefan",50.15,"01 Juillet 2018"));
		
		liste = new JComboBox(this.billets.toArray());
		liste.setBackground(Color.WHITE);
		liste.setBounds(120, 224, 450, 20);
		this.add(liste);
		
		JButton reserve = new JButton("Réserver");
		reserve.setBounds(312, 387, 89, 23);
		reserve.addActionListener(this);
		this.add(reserve);		
	}

	@Override
	public void actionPerformed(ActionEvent e) throws FormulaireException {
		
		if (this.tNom.getText().isEmpty() || this.tPrenom.getText().isEmpty() || this.tMail.getText().isEmpty()) {
			throw new FormulaireException();
		}
		
		 try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.newDocument();
				
				Element rootElement = doc.createElement("Commande");
				doc.appendChild(rootElement);
				
				Element client = doc.createElement("Client");
				rootElement.appendChild(client);
				
				Element billet = doc.createElement("Billet");
				rootElement.appendChild(billet);
				
				Element nom = doc.createElement("Nom");
				nom.appendChild(doc.createTextNode(this.tNom.getText()));
				client.appendChild(nom);
				
				Element prenom = doc.createElement("Prénom");
				prenom.appendChild(doc.createTextNode(this.tPrenom.getText()));
				client.appendChild(prenom);
				
				Element email = doc.createElement("E-mail");
				email.appendChild(doc.createTextNode(this.tMail.getText()));
				client.appendChild(email);
				
				Element artiste = doc.createElement("Artiste");
				artiste.appendChild(doc.createTextNode(((Billet) this.liste.getSelectedItem()).getArtiste()));
				billet.appendChild(artiste);
				
				Element date = doc.createElement("Date");
				date.appendChild(doc.createTextNode(((Billet) this.liste.getSelectedItem()).getDate()));
				billet.appendChild(date);
			
				double bPrix = ((Billet) this.liste.getSelectedItem()).getPrix();
				if (bAssis.isSelected()) {
					bPrix = bPrix+10;
				}
				Element prix = doc.createElement("Prix");
				prix.appendChild(doc.createTextNode(String.valueOf(bPrix)+"€"));
				billet.appendChild(prix);
				
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("H:\\PERSO\\file.xml"));
				
				transformer.transform(source, result);

				System.out.println("File saved!");
				frame.dispose();
				
		 } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
		 } catch (TransformerException tfe) {
			 	tfe.printStackTrace();
		 }
	}          
}
