package monpackageclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadT1Client;
import com.sun.javacard.apduio.CadTransportException;

public class Maclasse extends JFrame implements ActionListener {
	/******************** Constants ************************/ 
	public static final byte CLA_MONAPPLET= (byte) 0xB0; 
	public static final byte INS_INCREMENTER_COMPTEUR= 0x00; 
	public static final byte INS_DECREMENTER_COMPTEUR= 0x01; 
	public static final byte INS_INTERROGER_COMPTEUR= 0x02; 
	public static final byte INS_INITIALISER_COMPTEUR= 0x03; 
	
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton b5;
	JButton okay,cancel;
	JLabel l,lb1,lb2,l3,l4,lb3,lb4,ok;
	JPanel paninfo,pain,result ;
	private JTextField pin;
	
	
	CadT1Client cad; 
	
	public Maclasse() throws IOException, CadTransportException{
		
		setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Mazen Sghaier\\eclipse-workspace\\Gestion_allocation_voitures\\src\\b.jpg")));
    	setLayout(new FlowLayout(FlowLayout.CENTER, 100,25));
		setBackground(Color.white);
		
		//Panel
		paninfo = new JPanel( ) ;
		paninfo. setBackground(new Color(150,185,240)) ;
		paninfo. setPreferredSize( new Dimension( 420, 370) ) ;
		
		pain = new JPanel() ;
		pain. setBackground( new Color(150,185,240)) ;
		pain. setPreferredSize( new Dimension( 420, 370) ) ;
		
		result = new JPanel( ) ;
		result. setBackground( new Color(150,185,240)) ;
		result. setPreferredSize( new Dimension( 420, 370) ) ;
		
		
		paninfo.setBorder( BorderFactory. createTitledBorder( "Les opérations  : ") ) ;
		pain.setBorder( BorderFactory. createTitledBorder( "Bienvenue sur notre application  : ") ) ;
		
		//Pain Panel
		pin  = new JTextField();
		pin.setPreferredSize(new Dimension(150, 25));
		ok = new JLabel( "Pin du client : ") ;
		ok.setPreferredSize(new Dimension( 150, 25));
		
		
		Socket sckCarte;
		setSize(435, 190);
		
		JSeparator seprator =new JSeparator();
		seprator.setOrientation(SwingConstants.VERTICAL);
		
		
		//paninfo.setLayout(new GridLayout(0,2));
		
		
		l=new JLabel("");
		JLabel l2=new JLabel("");
		JLabel l1=new JLabel("");
		l1.setPreferredSize(new Dimension( 700, 15));
		l.setPreferredSize(new Dimension( 700, 15));
		l2.setPreferredSize(new Dimension( 700, 15));
		l3=new JLabel("");
		l3.setPreferredSize(new Dimension( 700, 15));
		l4=new JLabel("");
		l4.setPreferredSize(new Dimension( 700, 15));
		JLabel l5=new JLabel("");
		l5.setPreferredSize(new Dimension( 700, 15));
		JLabel l6=new JLabel("");
		l6.setPreferredSize(new Dimension( 50, 15));
		
		b1=new JButton(" Incrementer ");
		b1.addActionListener(this);
		b1.setBackground(Color.white);
		b1.setPreferredSize(new Dimension(190,30));

		paninfo.add(l3);
		paninfo.add(b1);
		paninfo.add(l5);
		
		
		b2=new JButton(" Decrementer ");
		b2.addActionListener(this);
		b2.setBackground(Color.white);
		b2.setPreferredSize(new Dimension(190,30));
		
		
		paninfo.add(b2);
		paninfo.add(l1);
		
		b3=new JButton(" Interroger Compteur ");
		b3.addActionListener(this);
		b3.setBackground(Color.white);
		b3.setPreferredSize(new Dimension(190,30));
		
		
		
		paninfo.add(b3);
		paninfo.add(l2);
		
		
		b4=new JButton(" Reinitialiser Compteur ");
		b4.addActionListener(this);
		b4.setBackground(Color.white);
		b4.setPreferredSize(new Dimension(190,30));
		
		
		paninfo.add(b4);
		paninfo.add(l4);
		
		b5=new JButton(" Quitter ");
		b5.addActionListener(this);
		b5.setBackground(Color.white);
		b5.setPreferredSize(new Dimension(190,30));
		
		paninfo.add(b5);
		
		
		
		okay = new JButton();
		okay.setText("Valider");
		okay.setBackground(Color.white);
		okay.setPreferredSize(new Dimension( 100, 20));
		
		cancel = new JButton();
		cancel.setText("Annuler");
		cancel.setPreferredSize(new Dimension( 100, 20));
		cancel.setBackground(Color.white);
		cancel.addActionListener( new ActionListener( ) {
			public void actionPerformed( ActionEvent arg0) {
				 setVisible(false);
			}});
		
		okay.addActionListener(this);
		
		
		pain.add(ok);
		pain.add(pin);
		pain.add(okay);
		pain.add(cancel);
		
		
		
		//paninfo.add(okay);
		//paninfo.add(cancel);
		
		paninfo.setVisible(false);
		result.setVisible(false);
		
		add(pain, BorderLayout.CENTER);
		add(paninfo);
		add(result);
	
	
	/*@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(!(pin.getText().isEmpty()) && (pin.getText().length()== 4) ) {
			if(b==okay) {
			pain.setVisible(false);
			paninfo.setVisible(true);
			setSize(435, 390);
			}
		}
		else {
			JOptionPane.showMessageDialog(null,
	                "Veulliez le code PIN ",
	                "PopUp Dialog",
	                JOptionPane.INFORMATION_MESSAGE); 
		} 
		
	} */
		
		try {
			sckCarte = new Socket("localhost",9025);
			sckCarte.setTcpNoDelay(true);
			BufferedInputStream input = new
			BufferedInputStream(sckCarte.getInputStream());
			 BufferedOutputStream output = new
			BufferedOutputStream(sckCarte.getOutputStream());
			 cad = new CadT1Client(input, output);
			 } catch (Exception e) {
			 System.out.println("Erreur : impossible de seconnecter a la Javacard");
			 return;
			 }
			 /* Mise sous tension de la carte */
			 try {
			 cad.powerUp();
			 } catch (Exception e) {
				 System.out.println("Erreur lors de l'envoi de lacommande Powerup a la Javacard");
						  return;
						  }
			 
			 /* S lection de l'applet */
			 Apdu apdu = new Apdu();
			 apdu.command[Apdu.CLA] = 0x00;
			 apdu.command[Apdu.INS] = (byte) 0xA4;
			 apdu.command[Apdu.P1] = 0x04;
			 apdu.command[Apdu.P2] = 0x00;
			 byte[] appletAID = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06,
			0x07, 0x08, 0x09, 0x00, 0x00 };
			 apdu.setDataIn(appletAID);
			 cad.exchangeApdu(apdu);
			 			 
			 /* Menu principal */
			 boolean fin = false;
			
			 
			 
			
			 
			 /* Mise hors tension de la carte 
			 try {
			 cad.powerDown();
			 } catch (Exception e) {
			 System.out.println("Erreur lors de l'envoi de lacommande Powerdown a la Javacard");
			 return;
			 }*/
  

			

	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton) e.getSource();
		JButton bb=(JButton) e.getSource();
		Apdu apdu = new Apdu();
		apdu.command[Apdu.CLA] = CLA_MONAPPLET;
		apdu.command[Apdu.P1] = 0x00;
		apdu.command[Apdu.P2] = 0x00;
		apdu.setLe(0x7f);
		
		if(!(pin.getText().isEmpty()) && (pin.getText().length()== 4) ) {
			
			pain.setVisible(false);
			paninfo.setVisible(true);
			setSize(435, 390);
			 
	    	

		if(bb==b1) 
		{	
			System.out.print("Maclasse1");
		 	String pin = JOptionPane.showInputDialog( "Montant   deposer (DT)" );
		    int v=Integer.parseInt(pin);
		    int i=0;
		    for(i=0;i<v;i++) {    
		apdu.command[Apdu.INS] =INS_INCREMENTER_COMPTEUR;
		
		 try {
			cad.exchangeApdu(apdu);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CadTransportException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 }
		if (apdu.getStatus() != 0x9000) {
		 	JOptionPane.showMessageDialog(null,"Erreur :status word different de 0x9000","PopUp Dialog",
	                JOptionPane.ERROR_MESSAGE);System.out.print("aze");
		 } else {
			JOptionPane.showMessageDialog(null,
			"L'opération de d'incrimentation a été éffectué avec succés",
			"PopUp Dialog",
			JOptionPane.INFORMATION_MESSAGE);
			
		 }
		   
		
          }  
		
		else if (bb==b2){	System.out.print("Maclasse2");
		String pin = JOptionPane.showInputDialog( "Montant à Débiter (DT)" );
	    int v=Integer.parseInt(pin);
	    int i=0;
	    
	    if(v > apdu.dataOut[0]) {
	   
	  for(i=0;i<v;i++) {
			apdu.command[Apdu.INS] = INS_DECREMENTER_COMPTEUR;
			 try {
				cad.exchangeApdu(apdu);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 }
			 if (apdu.getStatus() != 0x9000) {
			 	JOptionPane.showMessageDialog(null,"Erreur :status word different de 0x9000","PopUp Dialog",
	                JOptionPane.ERROR_MESSAGE);
			 } else {
				JOptionPane.showMessageDialog(null,
				"L'opération de décrimentation a été éffectué avec succés",
				"PopUp Dialog",
				JOptionPane.INFORMATION_MESSAGE);
				
			 }
			 
			
	    }
	    else {	
	    	JOptionPane.showMessageDialog(null,
					"Votre solde est insuffisant pour effectuer cette operation ",
					"PopUp Dialog",
					JOptionPane.ERROR_MESSAGE);
	    }
		}
		
		
		else if (bb==b3) {	
			System.out.print("Maclasse3");
			apdu.command[Apdu.INS] =INS_INTERROGER_COMPTEUR;
					 try {
						cad.exchangeApdu(apdu);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CadTransportException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (apdu.getStatus() != 0x9000) {
					 	JOptionPane.showMessageDialog(null,"Erreur :status word different de 0x9000","PopUp Dialog",
	                JOptionPane.ERROR_MESSAGE);
					 } else {
						JOptionPane.showMessageDialog(null,
						"Valeur du compteur : " +apdu.dataOut[0] +"DT" ,
						"PopUp Dialog",
						JOptionPane.INFORMATION_MESSAGE);
					 }
					 
		} 
		
		
		
		
		else if (bb==b4) {
			System.out.print("Maclasse4");
			apdu.command[Apdu.INS] =INS_INITIALISER_COMPTEUR;
					 byte[] donnees = new byte[1];
					 donnees[0] = 0;
					 apdu.setDataIn(donnees);
					try {
						cad.exchangeApdu(apdu);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CadTransportException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (apdu.getStatus() != 0x9000) {
					 	JOptionPane.showMessageDialog(null,"Erreur :status word different de 0x9000","",JOptionPane.INFORMATION_MESSAGE);
					 } else {
						 System.out.println("ok");
						JOptionPane.showMessageDialog(null,
						"L'opération d'initialisation a été éffectué avec succés",
						"PopUp Dialog",
						JOptionPane.INFORMATION_MESSAGE);
					 }
					 
		}
		
		else if (bb==b5) {
			
	    	 /* Mise hors tension de la carte */
		 		try {
		 			cad.powerDown();
		 			} catch (Exception ee) {
		 			System.out.println("Erreur lors de l'envoi de la commande Powerdown a la Javacard");
		 			Maclasse.this.dispose();
		 			return;
		 			}
			
			
		}
			
		}
		else {
			JOptionPane.showMessageDialog(null,
	                "Veulliez le code PIN ",
	                "PopUp Dialog",
	                JOptionPane.ERROR_MESSAGE); 
		} 

	}
}