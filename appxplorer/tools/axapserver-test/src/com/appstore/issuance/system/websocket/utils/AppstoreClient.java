package com.appstore.issuance.system.websocket.utils;

import static com.appstore.issuance.system.utils.AppstoreProperties.getInstance;
import static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.REMOVE_APPLICATION_REQUEST;
import static com.nxp.appstore.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.CARD_STATE_REQUEST;
import static com.nxp.appstore.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.CARD_STATE_RESPONSE;
import static com.nxp.appstore.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.DES_FIRE_COMMAND;
import static com.nxp.appstore.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.INSTALL_APPLICATION_REQUEST;
import static com.nxp.appstore.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.INSTALL_APPLICATION_RESPONSE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;

import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;
import org.glassfish.tyrus.client.SslContextConfigurator;
import org.glassfish.tyrus.client.SslEngineConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appstore.issuance.system.utils.Constants;
import com.appstore.issuance.system.utils.Utils;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessage;
import com.nxp.appstore.communication.protobuf.CommunicationProtocol.CardStateRequest;
import com.nxp.appstore.communication.protobuf.CommunicationProtocol.DesFireCommand;
import com.nxp.appstore.communication.protobuf.CommunicationProtocol.DesFireResponse;
import com.nxp.appstore.communication.protobuf.CommunicationProtocol.InstallApplicationRequest;
import com.nxp.appstore.communication.protobuf.CommunicationProtocol.RemoteMessage;
import com.nxp.appstore.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum;

@ClientEndpoint(decoders = RemoteMessageDecoder.class, encoders = RemoteMessageEncoder.class)
public class AppstoreClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppstoreClient.class);
	private static int counter = 0;

	private static final URI SERVER_URI = URI
			.create(getInstance().getValue(Constants.NDEF_MSG));

	public static CardChannel cardChannel;
	public static Card card = null;

	private static final byte[] UID = new byte[] { (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE };

	private static final int SERVER_CONNECTION_TIMEOUT_IN_SECONDS = 7;

	private static CountDownLatch messageLatch = new CountDownLatch(1);
	private static RemoteMessage response;
	private static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage response1;

	@OnMessage
	public void messageReceived(final RemoteMessage response) {
		AppstoreClient.response = response;
		LOGGER.info("#Got incoming message:" + response.toString());
		messageLatch.countDown();
	}

	private static RemoteMessage toRemoteMessage(final TypeEnum type, final GeneratedMessage message) throws Exception {
		final RemoteMessage.Builder remoteMessage = RemoteMessage.newBuilder();
		remoteMessage.setType(type);

		final Class<? extends GeneratedMessage> messageClass = message.getClass();
		final String[] parts = messageClass.getCanonicalName().split("\\.");
		final String messageName = parts[parts.length - 1];
		final Method setter = RemoteMessage.Builder.class.getDeclaredMethod("set" + messageName, messageClass);
		setter.invoke(remoteMessage, message);

		return remoteMessage.build();
	}

	private static RemoteMessage buildInstallApplicationRequest() throws Exception {
		final InstallApplicationRequest.Builder builder = InstallApplicationRequest.newBuilder();
		builder.setApprovedApplicationId(Long.parseLong(getInstance().getValue(Constants.APPROVED_APP_ID)));
		builder.setUid(ByteString.copyFrom(UID));
		return toRemoteMessage(INSTALL_APPLICATION_REQUEST, builder.build());
	}

	private static RemoteMessage buildCardStateRequest() throws Exception {
		final CardStateRequest.Builder builder = CardStateRequest.newBuilder();
		builder.setUid(ByteString.copyFrom(UID));

		return toRemoteMessage(CARD_STATE_REQUEST, builder.build());
	}

	private static RemoteMessage sendAndReceive(final Session session, final RemoteMessage request) {
		try {
			System.out.println("SEND:");
			LOGGER.info("#SEND:");
			System.out.println("-----------------------");
			LOGGER.info("-----------------------");
			System.out.println(request.toString());
			LOGGER.info(request.toString());
			LOGGER.info("-----------------------");
			System.out.println("-----------------------");

			final OutputStream sendStream = session.getBasicRemote().getSendStream();
			request.writeDelimitedTo(sendStream);
			sendStream.close();

			final boolean messageReceived = messageLatch.await(SERVER_CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);

			if (messageReceived) {
				messageLatch = new CountDownLatch(1);

				System.out.println("RECEIVED:");
				System.out.println("-----------------------");
				System.out.println(response.toString());
//				writeToFile(response.toString());
				System.out.println("-----------------------");
				return response;
			}

			throw new RuntimeException("timeout");
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static RemoteMessage convertToDesFireResponse(final byte[] bytes) {
		final RemoteMessage.Builder remoteMessage = RemoteMessage.newBuilder();
		remoteMessage.setType(TypeEnum.DES_FIRE_RESPONSE);

		final DesFireResponse.Builder desFireResponse = DesFireResponse.newBuilder();
		desFireResponse.setResponse(ByteString.copyFrom(bytes));

		remoteMessage.setDesFireResponse(desFireResponse.build());
		return remoteMessage.build();
	}

	private static RemoteMessage convertToDesFireResponse1(final byte[] bytes) {
		final RemoteMessage.Builder remoteMessage = RemoteMessage.newBuilder();
		remoteMessage.setType(TypeEnum.DES_FIRE_RESPONSE);

		final DesFireResponse.Builder desFireResponse = DesFireResponse.newBuilder();
		desFireResponse.setResponse(ByteString.copyFrom(bytes));

		remoteMessage.setDesFireResponse(desFireResponse.build());
		return remoteMessage.build();
	}

	private static RemoteMessage sendDesFireCommandToCard(final RemoteMessage remoteMessage) throws Exception {
		if (!remoteMessage.hasType()) {
			throw new RuntimeException("Illegal remote message: " + remoteMessage);
		}

		final TypeEnum remoteMessageType = remoteMessage.getType();

		if (remoteMessageType != DES_FIRE_COMMAND) {
			throw new RuntimeException("Illegal remote message: " + remoteMessage);
		}

		final DesFireCommand desFireCommand = remoteMessage.getDesFireCommand();
		final byte[] command = desFireCommand.getCommand().toByteArray();

		System.out.println("To Card:   {}" + Utils.getHexString(command));

		final ResponseAPDU responseAPDU = cardChannel.transmit(new CommandAPDU(command));
		final byte[] responseAPDUBytes = responseAPDU.getBytes();

		System.out.println("From Card: {}" + Utils.getHexString(responseAPDUBytes));

		return convertToDesFireResponse(responseAPDUBytes);
	}

	private static RemoteMessage sendDesFireCommandToCard(final RemoteMessage remoteMessage, final String commandName, final int index) throws Exception {
		if (!remoteMessage.hasType()) {
			throw new RuntimeException("Illegal remote message: " + remoteMessage);
		}

		final TypeEnum remoteMessageType = remoteMessage.getType();

		if (remoteMessageType != DES_FIRE_COMMAND) {
			throw new RuntimeException("Illegal remote message: " + remoteMessage);
		}

		final DesFireCommand desFireCommand = remoteMessage.getDesFireCommand();
		byte[] command = desFireCommand.getCommand().toByteArray();

		System.out.println("To Card:   {}" + Utils.getHexString(command));

		final ResponseAPDU responseAPDU = cardChannel.transmit(new CommandAPDU(command));
		final byte[] responseAPDUBytes = responseAPDU.getBytes();
		FileOutputStream fos = null;
		if("CARD_STATE".equals(commandName)) {
			fos = new FileOutputStream(new File("C:\\OSS\\CardResponses\\Client\\CardStateRequest\\cardstate_card_response_byte_"+index+".ser"));
			fos.write(responseAPDUBytes);
			fos.flush();
			fos.close();
		} else if("INSTALL".equals(commandName)) {
			fos = new FileOutputStream(new File("C:\\OSS\\CardResponses\\Client\\InstallApplication\\install_card_response_byte_"+index+".ser"));
			fos.write(responseAPDUBytes);
			fos.flush();
			fos.close();
		}
		
		/*byte[] responseAPDUBytes = null;
		
		if("CARD_STATE".equals(commandName)) {
			Path path = Paths.get("C:\\OSS\\CardResponses\\Client\\CardStateRequest\\cardstate_card_response_byte_"+index+".ser");
			responseAPDUBytes = Files.readAllBytes(path);
		} else if("INSTALL".equals(commandName)) {
			Path path = Paths.get("C:\\OSS\\CardResponses\\Client\\InstallApplication\\install_card_response_byte_"+index+".ser");
			responseAPDUBytes = Files.readAllBytes(path);
		}*/

//		System.out.println("From Card: {}" + Utils.getHexString(responseAPDUBytes));

		return convertToDesFireResponse(responseAPDUBytes);
	}

	private static RemoteMessage sendDesFireCommandToCard1(byte[] bytes) throws Exception {

		final byte[] responseAPDUBytes = bytes;

		System.out.println("From Card: {}" + Utils.getHexString(responseAPDUBytes));

		return convertToDesFireResponse1(responseAPDUBytes);
	}

	private static ClientManager createWebSocketClient() {
		LOGGER.info("#createWebSocketClient() method is called");
		final ClientManager client = ClientManager.createClient();
		final String keyStorePath = getInstance().getValue(Constants.KEYSTORE_FILE_PATH)
				+ getInstance().getValue(Constants.KEYSTORE_FILE_NAME);
		LOGGER.info("#Got keystore path:" + keyStorePath);
		System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, keyStorePath);
		System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, keyStorePath);
		System.getProperties().put(SSLContextConfigurator.KEY_STORE_PASSWORD,
				getInstance().getValue(Constants.KEYSTORE_TRUST_PASSWORD));
		System.getProperties().put(SSLContextConfigurator.TRUST_STORE_PASSWORD,
				getInstance().getValue(Constants.KEYSTORE_TRUST_PASSWORD));
//		System.getProperties().put("javax.net.debug", "all");
		final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();
		defaultConfig.retrieve(System.getProperties());

		SslEngineConfigurator sslEngineConfigurator = new SslEngineConfigurator(new SslContextConfigurator());
		sslEngineConfigurator.setHostVerificationEnabled(false);
		client.getProperties().put(ClientProperties.SSL_ENGINE_CONFIGURATOR, sslEngineConfigurator);

		return client;
	}

	public static String run(String command) throws Exception {
		String result = "";
		LOGGER.info("#AppstoreClient.run() method is called");
		// final String command = "INSTALL";
		System.out.println("Command: " + command);
		LOGGER.info("#Command:" + command);
		final ClientManager client = createWebSocketClient();
		try {
			LOGGER.info("#connecting to websocket...");
			try (final Session session = client.connectToServer(AppstoreClient.class, SERVER_URI)) {
				System.out.println("isSecure:" + session.isSecure());
				System.out.println("isOpen:" + session.isOpen());
				RemoteMessage remoteMessageFromServer = null;
				com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage remoteMessageFromServer1 = null;
				RemoteMessage remoteMessageFromCard = null;
				com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage remoteMessageFromCard1 = null;
				TypeEnum typeOfLastMessage = CARD_STATE_RESPONSE;
				com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum typeOfLastMessage1 = REMOVE_APPLICATION_REQUEST;
				final boolean showLastResponse;

				switch (command) {

				case "INSTALL":
					remoteMessageFromServer = sendAndReceive(session, buildInstallApplicationRequest());
					typeOfLastMessage = INSTALL_APPLICATION_RESPONSE;
					showLastResponse = true;
					break;

				case "CARD_STATE":
					remoteMessageFromServer = sendAndReceive(session, buildCardStateRequest());
					typeOfLastMessage = CARD_STATE_RESPONSE;
					showLastResponse = true;
					break;

				default:
					throw new IllegalArgumentException();
				}

//				int index = 1;
				while (remoteMessageFromServer.getType() != typeOfLastMessage) {
					remoteMessageFromCard = sendDesFireCommandToCard(remoteMessageFromServer);
//					writeToFile("Type:" + remoteMessageFromCard.getType() + ";response:"
//							+ remoteMessageFromCard.getDesFireResponse());
//					
//					FileOutputStream fos = null;
//					if(command.equals("CARD_STATE")) {
//						fos = new FileOutputStream(new File("C:\\OSS\\CardResponses\\Client\\CardStateRequest\\cardstate_card_response_"+index+".ser"));						
//					} else if(command.equals("INSTALL")) {
//						fos = new FileOutputStream(new File("C:\\OSS\\CardResponses\\Client\\InstallApplication\\install_card_response_"+index+".ser"));						
//					}
//					
//					ObjectOutputStream oos = new ObjectOutputStream(fos);
//					oos.writeObject(remoteMessageFromCard);
//					oos.flush();
//					oos.close();
//					fos.flush();
//					fos.close();
					
					/*FileInputStream fis = null;
					if(command.equals("CARD_STATE")) {
						fis = new FileInputStream(new File("C:\\OSS\\CardResponses\\Client\\CardStateRequest\\cardstate_card_response_"+index+".ser"));						
					} else if(command.equals("INSTALL")) {
						fis = new FileInputStream(new File("C:\\OSS\\CardResponses\\Client\\InstallApplication\\install_card_response_"+index+".ser"));						
					}
					
					ObjectInputStream ois = new ObjectInputStream(fis);
					remoteMessageFromCard = (RemoteMessage)ois.readObject();
					ois.close();
					fis.close();*/
//					index++;			
					remoteMessageFromServer = sendAndReceive(session, remoteMessageFromCard);
				}

				final boolean success = remoteMessageFromServer.getStatusCode().getSuccess();

				if (success) {
					System.out.println("SUCCESS!");

					if (showLastResponse) {
						System.out.println(remoteMessageFromServer);
						result = remoteMessageFromServer.toString();
					}
				} else {
					System.out.println("FAILED!");
					System.out.println(remoteMessageFromServer.getStatusCode().getErrorCode());
				}
			} catch (final Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage() + e);
				LOGGER.info("#Error occured during establishing websocket connection" + e.toString());
			}
		} finally {
			if (card != null) {
				card.disconnect(false);
			}
		}
		return result;
	}

//	private static void writeToFile(String data) {
//		File file = new File("C:/logs/test.log");
//		BufferedWriter bw = null;
//		try {
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			FileWriter fw = new FileWriter(file, true);
//			bw = new BufferedWriter(fw);
//			bw.write(data);
//			bw.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void takeBytes(byte[] bytes) {
		System.out.println(bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			System.out.print(bytes[i]);
		}
		System.out.println();
	}

	
	public static void main(String[] args) {
		System.out.println("---------------------------------------------");
		com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.DesFireResponse t = 
				com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.DesFireResponse
				.getDefaultInstance();
		
		System.out.println(t);
	}
	
	public static void readRandA(String hexBytes) {
		
	}
}
