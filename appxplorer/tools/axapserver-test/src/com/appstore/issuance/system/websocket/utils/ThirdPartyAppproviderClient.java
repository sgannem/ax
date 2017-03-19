package com.appstore.issuance.system.websocket.utils;

import static com.appstore.issuance.system.utils.AppstoreProperties.getInstance;
import static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.DES_FIRE_COMMAND;
import static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.REMOVE_APPLICATION_REQUEST;
import static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.REMOVE_APPLICATION_RESPONSE;
import static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.UPDATE_APPLICATION_REQUEST;
import static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.UPDATE_APPLICATION_RESPONSE;
import static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.REDIRECT_APPLICATION_REQUEST;
import static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.REDIRECT_APPLICATION_RESPONSE;
import static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.STORE_TICKET_REQUEST;
import static com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum.STORE_TICKET_RESPONSE;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URI;
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
import com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.DesFireResponse;
import com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RedirectApplicationRequest;
import com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage;
import com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage.TypeEnum;
import com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoveApplicationRequest;
import com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.StoreTicketRequest;
import com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.UpdateApplicationRequest;

@ClientEndpoint(decoders = RemoteMessageDecoder1.class, encoders = RemoteMessageEncoder1.class)
public class ThirdPartyAppproviderClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartyAppproviderClient.class);

	private static final URI SERVER_URI = URI
//			.create("wss://demoappstore.westeurope.cloudapp.azure.com:8443/appprovider-server/");
//			.create("wss://localhost:8443/appprovider-server/");
			.create(getInstance().getValue(Constants.APPPROVIDER_SERVER));
	public static CardChannel cardChannel;
	public static Card card = null;

	private static final byte[] UID = new byte[] { (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE };

	private static final int SERVER_CONNECTION_TIMEOUT_IN_SECONDS = 7;

	private static CountDownLatch messageLatch = new CountDownLatch(1);
	private static RemoteMessage response;

	
	@OnMessage
	public void messageReceived(final RemoteMessage response) {
		ThirdPartyAppproviderClient.response = response;
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
	
	
	private static RemoteMessage buildUninstallApplicationRequest() throws Exception {
		final RemoveApplicationRequest.Builder builder = RemoveApplicationRequest.newBuilder();
		builder.setAid(Integer.parseInt(getInstance().getValue(Constants.AID_TO_BE_DELETED)));
		builder.setUid(ByteString.copyFrom(UID));
		return toRemoteMessage(REMOVE_APPLICATION_REQUEST, builder.build());
	}
	
	private static RemoteMessage buildUpdateApplicationRequest() throws Exception {
		final UpdateApplicationRequest.Builder builder = UpdateApplicationRequest.newBuilder();
		builder.setAid(Integer.parseInt(getInstance().getValue(Constants.AID_TO_BE_DELETED)));
		builder.setUid(ByteString.copyFrom(UID));
		return toRemoteMessage(UPDATE_APPLICATION_REQUEST, builder.build());
	}
	
	private static RemoteMessage buildRedirectApplicationRequest() throws Exception {
//		final RedirectApplicationRequest.Builder builder = RedirectApplicationRequest.newBuilder();
//		builder.setUid(ByteString.copyFrom(UID));
//		builder.setAid(1052688);
//		builder.setIsPurchased("No");
//		builder.setIsStoredInCard("No");
//		builder.setAppName("monorail");
//		builder.setFirstName("John");
//		builder.setLastName("Doe");
//		builder.setOrderId("urn:tix-smf1:0:1:1:73fb09f3-93c7-4948-92f2-9a4b1f17c7a0:81316b2b-fb44-4af4-ac21-85aa7fd2aa32:95b8da31");
//		builder.setCesItnUniqueId("john@doefamily.com");
//		builder.setPurchaseDate(System.currentTimeMillis()+"");
//		builder.setSessionId(System.nanoTime()+"");
//		builder.setTransactionTimeStamp(System.currentTimeMillis()+"");
		final RedirectApplicationRequest.Builder builder = RedirectApplicationRequest.newBuilder();
		builder.setUid(ByteString.copyFrom(UID));
		builder.setAid(Integer.parseInt(getInstance().getValue(Constants.AID_TO_BE_DELETED)));
//		builder.setIsPurchased("No");
//		builder.setIsStoredInCard("No");
		builder.setAppName("monorail");
//		builder.setFirstName("John");
//		builder.setLastName("Doe");
//		builder.setOrderId("urn:tix-smf1:0:1:1:73fb09f3-93c7-4948-92f2-9a4b1f17c7a0:81316b2b-fb44-4af4-ac21-85aa7fd2aa32:95b8da31");
//		builder.setCesItnUniqueId("john@doefamily.com");
//		builder.setPurchaseDate(System.currentTimeMillis()+"");
//		builder.setSessionId(System.nanoTime()+"");
//		builder.setTransactionTimeStamp(System.currentTimeMillis()+"");
		return toRemoteMessage(REDIRECT_APPLICATION_REQUEST, builder.build());
	}
	
	private static RemoteMessage buildStoreTicketRequest() throws Exception {
		final StoreTicketRequest.Builder builder = StoreTicketRequest.newBuilder();
		builder.setUid(ByteString.copyFrom(UID));
		builder.setAid(Integer.parseInt(getInstance().getValue(Constants.AID_TO_BE_DELETED)));
		builder.setIsPurchased("Yes");
		builder.setIsStoredInCard("No");
		builder.setAppName("monorail");
		builder.setOrderId("urn:tix-smf1:0:1:1:73fb09f3-93c7-4948-92f2-9a4b1f17c7a0:81316b2b-fb44-4af4-ac21-85aa7fd2aa32:95b8da31");
		builder.setCesItnUniqueId("john@doefamily.com");
		return toRemoteMessage(STORE_TICKET_REQUEST, builder.build());
	}



	private static RemoteMessage sendAndReceive(final Session session, final RemoteMessage request) {
		try {
			LOGGER.info("SEND:");
			System.out.println("SEND:");
			System.out.println("-----------------------");
			LOGGER.info("-----------------------");
			System.out.println(request.toString());
			LOGGER.info(request.toString());
			System.out.println("-----------------------");
			LOGGER.info("-----------------------");

			final OutputStream sendStream = session.getBasicRemote().getSendStream();
			request.writeDelimitedTo(sendStream);
			sendStream.close();

			final boolean messageReceived = messageLatch.await(SERVER_CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);

			if (messageReceived) {
				messageLatch = new CountDownLatch(1);
				System.out.println("RECEIVED:");
				LOGGER.info("RECEIVED:");
				System.out.println("-----------------------");
				LOGGER.info("-----------------------");
				System.out.println(response.toString());
				LOGGER.info(response.toString());
				LOGGER.info("-----------------------");
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

	private static RemoteMessage sendDesFireCommandToCard(final RemoteMessage remoteMessage) throws Exception {
		if (!remoteMessage.hasType()) {
			throw new RuntimeException("Illegal remote message: " + remoteMessage);
		}

		final TypeEnum remoteMessageType = remoteMessage.getType();

		if (remoteMessageType != DES_FIRE_COMMAND) {
			throw new RuntimeException("Illegal remote message: " + remoteMessage);
		}

		final com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.DesFireCommand desFireCommand = remoteMessage.getDesFireCommand();
		final byte[] command = desFireCommand.getCommand().toByteArray();

		System.out.println("To Card:   {}" + Utils.getHexString(command));

		final ResponseAPDU responseAPDU = cardChannel.transmit(new CommandAPDU(command));
		final byte[] responseAPDUBytes = responseAPDU.getBytes();

		System.out.println("From Card: {}" + Utils.getHexString(responseAPDUBytes));

		return convertToDesFireResponse(responseAPDUBytes);
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
		final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();
		defaultConfig.retrieve(System.getProperties());

		SslEngineConfigurator sslEngineConfigurator = new SslEngineConfigurator(new SslContextConfigurator());
		sslEngineConfigurator.setHostVerificationEnabled(false);
		client.getProperties().put(ClientProperties.SSL_ENGINE_CONFIGURATOR, sslEngineConfigurator);

		return client;
	}

	public static String run(String command) throws Exception {
		String result = "";
		// final String command = "INSTALL";
		System.out.println("Command: " + command);
		final ClientManager client = createWebSocketClient();
		try {
			try (final Session session = client.connectToServer(ThirdPartyAppproviderClient.class, SERVER_URI)) {
				RemoteMessage remoteMessageFromServer = null;
				RemoteMessage remoteMessageFromCard = null;
				TypeEnum typeOfLastMessage = REMOVE_APPLICATION_REQUEST;
				final boolean showLastResponse;

				switch (command) {
				
				case "STORE":
					remoteMessageFromServer = sendAndReceive(session, buildStoreTicketRequest());
					typeOfLastMessage = STORE_TICKET_RESPONSE;
					showLastResponse = true;
					break;
				
				case "REDIRECT":
					remoteMessageFromServer = sendAndReceive(session, buildRedirectApplicationRequest());
					typeOfLastMessage = REDIRECT_APPLICATION_RESPONSE;
					showLastResponse = true;
					break;
				
				case "UPDATE":
					remoteMessageFromServer = sendAndReceive(session, buildUpdateApplicationRequest());
					typeOfLastMessage = UPDATE_APPLICATION_RESPONSE;
					showLastResponse = true;
					break;
				
				case "UNINSTALL":
					remoteMessageFromServer = sendAndReceive(session, buildUninstallApplicationRequest());
					typeOfLastMessage = REMOVE_APPLICATION_RESPONSE;
					showLastResponse = true;
					break;
				
				default:
					throw new IllegalArgumentException();
				}

				while (remoteMessageFromServer.getType() != typeOfLastMessage) {
					remoteMessageFromCard = sendDesFireCommandToCard(remoteMessageFromServer);
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
				System.out.println(e.getMessage() + e);
			}
		} finally {
			if (card != null) {
				card.disconnect(false);
			}
		}
		return result;
	}
}
