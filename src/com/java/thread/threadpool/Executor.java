import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class Executor {
	
	
	
	/**
	 * ����һ���̶��߳��������̳߳�
	 * @param nThreads
	 * @return
	 */
	public static ExecutorService newFixedThreadPool(int nThreads) {
		return new ThreadPoolExecutor(nThreads, nThreads, 
										0L, TimeUnit.MILLISECONDS,
										new LinkedBlockingQueue<Runnable>());
	}
	
	
	public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
		return new ThreadPoolExecutor(nThreads, nThreads, 
										0L, TimeUnit.MILLISECONDS,
										new LinkedBlockingQueue<Runnable>(),
										threadFactory);
	}
	
	
	/**
	 * ������Ҫ��ϵͳ��Դ�����̳߳�
	 * @return
	 */
	public static ExecutorService newCachedThreadPool() {
		return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
										60L, TimeUnit.SECONDS,
										new SynchronousQueue<Runnable>());
	}
	
	
	public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
		return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
				60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(),
				threadFactory);
	}
	
	
	public static ExecutorService newSingleThreadExecutor() {
		return new FinalizableDelegatedExecutorService
			(new ThreadPoolExecutor(1, 1,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>()));
	}
	
	
	public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory) {
		return new FinalizableDelegatedExecutorService
			(new ThreadPoolExecutor(1, 1,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(),
				threadFactory));
	}
	
	
}


