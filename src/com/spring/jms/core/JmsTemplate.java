package com.spring.jms.core;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.JmsException;
import org.springframework.jms.connection.ConnectionFactoryUtils;
import org.springframework.jms.connection.JmsResourceHolder;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.core.ProducerCallback;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.core.JmsTemplate.JmsTemplateResourceFactory;
import org.springframework.jms.support.JmsAccessor;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.util.Assert;

public class JmsTemplate extends JmsAccessor implements JmsOperations{
	
	/** Internal ResourceFactory adapter for interacting with ConnectionFactoryUtils */
	private final JmsTemplateResourceFactory transactionalResourceFactory = new JmsTemplateResourceFactory();

	/** 默认目的地址 */
	private Object defaultDestination;
	
	/** 消息转换器 */
	private MessageConverter messageConverter;
	
	
	private int deliveryMode = Message.DEFAULT_DELIVERY_MODE;
	
	
	private int priority = Message.DEFAULT_PRIORITY;
	
	
	private long timeToLive = Message.DEFAULT_TIME_TO_LIVE;
	
	
	/**
	 * 构造函数
	 */
	public JmsTemplate() {
		initDefaultStrategies();
	}
	
	
	/**
	 * 构造函数
	 * @param connectionFactory
	 */
	public JmsTemplate(ConnectionFactory connectionFactory) {
		this();
		setConnectionFactory(connectionFactory);
		afterPropertiesSet();
	}
	
	/**
	 * 初始化默认策略
	 */
	public void initDefaultStrategies() {
		setMessageConverter(new SimpleMessageConverter());
	}
	
	
	
	public Destination getDefaultDestination() {
		return (this.defaultDestination instanceof Destination ? (Destination)this.defaultDestination : null);
	}



	public void setDefaultDestination(Destination destination) {
		this.defaultDestination = destination;
	}

	
	private Queue getDefaultQueue() {
		Destination defaultDestination = getDefaultDestination();
		if (defaultDestination != null && !(defaultDestination instanceof Queue)) {
			throw new IllegalStateException(
					"defaultDestination does not correspond to a Queue. Check Configuration of JmsTemplate");
		}
		return (Queue)defaultDestination;
	}
	
	


	public MessageConverter getMessageConverter() {
		return messageConverter;
	}



	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}

	
	private MessageConverter getRequiredMessageConverter() throws IllegalStateException {
		MessageConverter converter = getMessageConverter();
		if (converter == null) {
			throw new IllegalStateException("No 'MessageConverter' specified. Check configuration of JmsTemplate");
		}
		return converter;
	}
	
	


	public int getDeliveryMode() {
		return deliveryMode;
	}



	public void setDeliveryMode(int deliveryMode) {
		this.deliveryMode = deliveryMode;
	}



	public int getPriority() {
		return priority;
	}



	public void setPriority(int priority) {
		this.priority = priority;
	}



	public long getTimeToLive() {
		return timeToLive;
	}



	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	
	
	//----------------------------------------------------------------------------------------
	//JmsOperations execute methods
	//----------------------------------------------------------------------------------------
	
	
	
	@Override
	public <T> T execute(SessionCallback<T> action) throws JmsException {
		// TODO Auto-generated method stub
		return execute(action, false);
	}
	
	
	
	public <T> T execute(SessionCallback<T> action, boolean startConnection) throws JmsException {
		Assert.notNull(action, "Callback object must not be null");
		Connection conToClose = null;
		Session sessionToClose = null;
		try {
			Session sessionToUse = ConnectionFactoryUtils.doGetTransactionalSession(
					getConnectionFactory(), this.transactionalResourceFactory, startConnection);
			if (sessionToUse == null) {
				conToClose = createConnection();
				sessionToClose = createSession(conToClose);
				if (startConnection) {
					conToClose.start();
				}
				sessionToUse = sessionToClose;
			}
			return action.doInJms(sessionToUse);
		} catch (JMSException ex) {
			throw convertJmsAccessException(ex);
		} finally {
			JmsUtils.closeSession(sessionToClose);
			ConnectionFactoryUtils.releaseConnection(conToClose, getConnectionFactory(), startConnection);
		}
	}
	
	

	@Override
	public <T> T execute(ProducerCallback<T> action) throws JmsException {
		
		return null;
	}

	@Override
	public <T> T execute(Destination destination, ProducerCallback<T> action)
			throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T execute(String destinationName, ProducerCallback<T> action)
			throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send(MessageCreator messageCreator) throws JmsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(Destination destination, MessageCreator messageCreator)
			throws JmsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(String destinationName, MessageCreator messageCreator)
			throws JmsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convertAndSend(Object message) throws JmsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convertAndSend(Destination destination, Object message)
			throws JmsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convertAndSend(String destinationName, Object message)
			throws JmsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convertAndSend(Object message,
			MessagePostProcessor postProcessor) throws JmsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convertAndSend(Destination destination, Object message,
			MessagePostProcessor postProcessor) throws JmsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convertAndSend(String destinationName, Object message,
			MessagePostProcessor postProcessor) throws JmsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message receive() throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message receive(Destination destination) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message receive(String destinationName) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message receiveSelected(String messageSelector) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message receiveSelected(Destination destination,
			String messageSelector) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message receiveSelected(String destinationName,
			String messageSelector) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object receiveAndConvert() throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object receiveAndConvert(Destination destination)
			throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object receiveAndConvert(String destinationName) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object receiveSelectedAndConvert(String messageSelector)
			throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object receiveSelectedAndConvert(Destination destination,
			String messageSelector) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object receiveSelectedAndConvert(String destinationName,
			String messageSelector) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendAndReceive(MessageCreator messageCreator)
			throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendAndReceive(Destination destination,
			MessageCreator messageCreator) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendAndReceive(String destinationName,
			MessageCreator messageCreator) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T browse(BrowserCallback<T> action) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T browse(Queue queue, BrowserCallback<T> action)
			throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T browse(String queueName, BrowserCallback<T> action)
			throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T browseSelected(String messageSelector,
			BrowserCallback<T> action) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T browseSelected(Queue queue, String messageSelector,
			BrowserCallback<T> action) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T browseSelected(String queueName, String messageSelector,
			BrowserCallback<T> action) throws JmsException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/**
	 * Fetch an appropriate Connection from the given JmsResourceHolder.
	 * <p>This implementation accepts any JMS 1.1 Connection.
	 * @param holder the JmsResourceHolder
	 * @return an appropriate Connection fetched from the holder,
	 * or {@code null} if none found
	 */
	protected Connection getConnection(JmsResourceHolder holder) {
		return holder.getConnection();
	}

	/**
	 * Fetch an appropriate Session from the given JmsResourceHolder.
	 * <p>This implementation accepts any JMS 1.1 Session.
	 * @param holder the JmsResourceHolder
	 * @return an appropriate Session fetched from the holder,
	 * or {@code null} if none found
	 */
	protected Session getSession(JmsResourceHolder holder) {
		return holder.getSession();
	}
	
	
	/**
	 * ResourceFactory implementation that delegates to this template's protected callback methods.
	 */
	private class JmsTemplateResourceFactory implements ConnectionFactoryUtils.ResourceFactory {

		@Override
		public Connection getConnection(JmsResourceHolder holder) {
			return JmsTemplate.this.getConnection(holder);
		}

		@Override
		public Session getSession(JmsResourceHolder holder) {
			return JmsTemplate.this.getSession(holder);
		}

		@Override
		public Connection createConnection() throws JMSException {
			return JmsTemplate.this.createConnection();
		}

		@Override
		public Session createSession(Connection con) throws JMSException {
			return JmsTemplate.this.createSession(con);
		}

		@Override
		public boolean isSynchedLocalTransactionAllowed() {
			return JmsTemplate.this.isSessionTransacted();
		}
	}

	
	
	
	
}
