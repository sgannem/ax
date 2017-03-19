package com.appstore.issuance.system.ui;

import static com.appstore.issuance.system.utils.AppstoreProperties.getInstance;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appstore.issuance.system.utils.Constants;
import com.appstore.issuance.system.websocket.utils.AppstoreClient;
import com.appstore.issuance.system.websocket.utils.ThirdPartyAppproviderClient;

public class AppstoreApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppstoreApplication.class);
	private TapFrame frame;
	private TerminalFactory factory = TerminalFactory.getDefault();
	private List<CardTerminal> terminals = null;
	private Card card = null;
	private CardChannel cardChannel = null;
	private boolean isCardPresent = false;
	JTextPane txtpnStatus = new JTextPane();
	JTextPane textPane = new JTextPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LOGGER.info("#Application is started....");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppstoreApplication window = new AppstoreApplication();
					LOGGER.info("#Application window is opened");
				} catch (Exception e) {
					LOGGER.info("#Error occurred during Appstore accessing" + e.toString());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppstoreApplication() {
		initialize();
		String command = "CARD_STATE";
//		String command = "INSTALL";
		doAppstoreCall(command);
	}

	private void doAppstoreCall(final String command) {
		LOGGER.info("#doAppstoreCall()");
		new Thread() {
			public void run() {
				initializeReader();
				AppstoreClient.cardChannel = cardChannel;
				AppstoreClient.card = card;
				try {
					if (command.equals("REDIRECT") || command.equals("UNINSTALL") || command.equals("UPDATE") || command.equals("STORE")) {
						for(int i=0;i<10;i++) {
						ThirdPartyAppproviderClient.card = card;
						ThirdPartyAppproviderClient.cardChannel = cardChannel;
						String result = ThirdPartyAppproviderClient.run(command);
						textPane.setText(result);
						txtpnStatus.setVisible(false);
						}
					} else {
						String result = AppstoreClient.run(command);
						textPane.setText(result);
						txtpnStatus.setVisible(false);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LOGGER.info("#Error ocurred during doAppstoreCall():" + e.toString());
				}
			}
		}.start();
	}

	private void initializeReader() {
		try {
			terminals = factory.terminals().list();
			// final int terminalIndex = isWindows() ? 1 : 0;
			// int terminalIndex = 2;
			// CardTerminal terminal = terminals.get(terminalIndex);
			CardTerminal terminal = null;
			boolean isTerminalFound = false;
			String nfcReaderName = getInstance().getValue(Constants.EXTERNAL_NFC_READER);
			LOGGER.info("#Got NFC Reader Name from the Properties:"+nfcReaderName);
			for (CardTerminal temp : terminals) {
				LOGGER.info("#Got Terminal:" + temp);
				if (temp.toString().equalsIgnoreCase(nfcReaderName) || temp.getName().equalsIgnoreCase(nfcReaderName)) {
					LOGGER.info("#Terminal is matched which is configured in the property file");
					terminal = temp;
					isTerminalFound = true;
					break;
				} else {
					continue;
				}
			}
			if (isTerminalFound) {
				System.out.println("Chosen terminal:" + terminal.getName());
				LOGGER.info("Chosen terminal:" + terminal.getName());
				int CARD_TERMINAL_CONNECTION_TIMEOUT_IN_SECONDS = 5;
				while (true) {
					isCardPresent = terminal.waitForCardPresent(CARD_TERMINAL_CONNECTION_TIMEOUT_IN_SECONDS * 1000);
					if (!isCardPresent) {
						// throw new RuntimeException("no card present");
						System.out.println("#card not present, please tap the card ....");
						LOGGER.info("#card not present, please tap the card ....");
						txtpnStatus.setText("card not present, please tap the card ....");
					} else {
						break;
					}
				}
				if (isCardPresent) {
					card = terminal.connect("*");
					cardChannel = card.getBasicChannel();
					System.out.println("#Got card channel:" + cardChannel);
					LOGGER.info("#Got card channel:" + cardChannel);
					txtpnStatus.setText("#Card is present in pc/sc reader. please wait processing furthur....");
					System.out.println(card);
					LOGGER.info(card.toString());
					// System.out.println(getUID(card));
				}
			} else {
				System.out.println("#Did not find any terminal named with:"
						+ getInstance().getValue(Constants.EXTERNAL_NFC_READER));
				LOGGER.info("#Did not find any terminal named with:"
						+ getInstance().getValue(Constants.EXTERNAL_NFC_READER));
				System.exit(0);
			}
		} catch (CardException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			LOGGER.info("#Error occured during reader selection:" + e.toString());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new TapFrame("APPSTORE SAMPLE ISSUANCE SYSTEM");
		frame.getContentPane().setLayout(null);

		JButton btnHello = new JButton("View All Applets");
		btnHello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				txtpnStatus.setVisible(true);
				textPane.setText("Initializing.....");
				final String command = "CARD_STATE";
				doAppstoreCall(command);
			}
		});
		btnHello.setBounds(12, 24, 139, 25);
		frame.getContentPane().add(btnHello);

		// txtpnStatus = new JTextPane();
		txtpnStatus.setText("checking any card present on the PC/SC reader....");
		txtpnStatus.setBounds(0, 1033, 867, 22);
		frame.getContentPane().add(txtpnStatus);

		// JTextPane textPane = new JTextPane();
		textPane.setBounds(0, 62, 867, 966);
		frame.getContentPane().add(textPane);
		textPane.setText("processing....");

		JButton btnUpdateRequest = new JButton("Install Applet");
		btnUpdateRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnStatus.setVisible(true);
				textPane.setText("Initializing.....");
				final String command = "INSTALL";
				doAppstoreCall(command);
			}
		});
		btnUpdateRequest.setBounds(163, 24, 129, 25);
		frame.getContentPane().add(btnUpdateRequest);

		JButton btnDeleteRequest = new JButton("Remove Applet");
		btnDeleteRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnStatus.setVisible(true);
				textPane.setText("Initializing.....");
				final String command = "UNINSTALL";
				doAppstoreCall(command);
			}
		});
		btnDeleteRequest.setBounds(304, 24, 121, 25);
		frame.getContentPane().add(btnDeleteRequest);

		JButton button = new JButton("Update Applet");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnStatus.setVisible(true);
				textPane.setText("Initializing.....");
				final String command = "UPDATE";
				doAppstoreCall(command);
			}
		});
		button.setBounds(437, 24, 129, 25);
		frame.getContentPane().add(button);
		
		JButton buttonRedirect = new JButton("Redirect");
		buttonRedirect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnStatus.setVisible(true);
				textPane.setText("Initializing.....");
				final String command = "REDIRECT";
				doAppstoreCall(command);
			}
		});
		buttonRedirect.setBounds(574, 24, 137, 25);
		frame.getContentPane().add(buttonRedirect);
		
		JButton buttonStoreTicket = new JButton("StoreTicket");
		buttonStoreTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnStatus.setVisible(true);
				textPane.setText("Initializing.....");
				final String command = "STORE";
				doAppstoreCall(command);
			}
		});
		buttonStoreTicket.setBounds(720, 24, 145, 25);
		frame.getContentPane().add(buttonStoreTicket);

	}

	private void showDialog() {
		// JDialog dialog = new JDialog(this,
		// Dialog.ModalityType.APPLICATION_MODAL);
		// OR, you can do the following...
		JDialog dialog = new TapDialog("Please tap card");
		dialog.setBounds(350, 350, 250, 200);
		dialog.setVisible(true);

	}
}
