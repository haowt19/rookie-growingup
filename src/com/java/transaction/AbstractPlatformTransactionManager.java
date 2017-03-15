package com.java.transaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.InvalidTimeoutException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

public abstract class AbstractPlatformTransactionManager implements PlatformTransactionManager{

	protected transient Log logger = LogFactory.getLog(getClass());
	

	@Override
	public TransactionStatus getTransaction(TransactionDefinition definition)
			throws TransactionException {
		Object transaction = doGetTransaction();
		
		boolean debugEnabled = logger.isDebugEnabled();
		
		if (definition == null) {
			definition = new DefaultTransactionDefinition();
		}
		
		if (isExistingTransaction(transaction)) {
			//如果事务存在，检查传播行为
			return handleExistingTransaction(definition, transaction, debugEnabled);
		}
		
		// Check definition settings for new transaction.
		if (definition.getTimeout() < TransactionDefinition.TIMEOUT_DEFAULT) {
			throw new InvalidTimeoutException("Invalid transaction timeout", definition.getTimeout());
		}
		
		//如果事务不存在，那就查看传播行为是什么，根据传播行为决定接下来如何处理
		if (definition.getPropagationBehavior() == TransactionDefinition.PROPAGATION_MANDATORY) {
			throw new IllegalTransactionStateException(
					"No existing transaction found for transaction marked with propagation 'mandatory'");
		} else if (definition.getPropagationBehavior() == TransactionDefinition.PROPAGATION_REQUIRED ||
				definition.getPropagationBehavior() == TransactionDefinition.PROPAGATION_REQUIRES_NEW || 
				definition.getPropagationBehavior() == TransactionDefinition.PROPAGATION_NESTED) {
			SuspendedResourcesHolder suspendedResources = suspend(null);
			if (debugEnabled) {
				logger.debug("Creating new transaction with name [" + definition.getName() + "]: " + definition);
			}
			try {
				boolean newSynchronization = (getTransactionSynchronization() != SYNCHRONIZATION_NEVER);
				DefaultTransactionStatus status = newTransactionStatus(
						definition, transaction, true, newSynchronization, debugEnabled, suspendedResources);
				doBegin(transaction, definition);
				prepareSynchronization(status, definition);
				return status;
			} catch (RuntimeException ex) {
				resume(null, suspendedResources);
				throw ex;
			} catch (Error err) {
				resume(null, suspendedResources);
				throw err;
			}
		} else {
			// Create "empty" transaction: no actual transaction, but potentially synchronization.
			boolean newSynchronization = (getTransactionSynchronization() == SYNCHRONIZATION_ALWAYS);
			return prepareTransactionStatus(definition, null, true, newSynchronization, debugEnabled, null);
		}
	}
	
	
	@Override
	public void commit(TransactionStatus arg0) throws TransactionException {
		
	}
	

	@Override
	public void rollback(TransactionStatus arg0) throws TransactionException {
		
	}
	
	
	
	protected abstract Object doGetTransaction() throws TransactionException;
	
	
	protected boolean isExistingTransaction(Object transaction) throws TransactionException {
		return false;
	}
	
	/**
	 * 为已经存在的事务创建一个TransactionStatus
	 * @param definition
	 * @param transaction
	 * @param debugEnabled
	 * @return
	 * @throws TransactionException
	 */
	private TransactionStatus handleExistingTransaction(TransactionDefinition definition,
			Object transaction, boolean debugEnabled) throws TransactionException {
		if (definition.getPropagationBehavior() == TransactionDefinition.PROPAGATION_NEVER) {
			throw new IllegalTransactionStateException(
					"Existing transaction found for transaction marked with propagation 'never'");
		}
		
		
		
		
		
		
	}
	

}
