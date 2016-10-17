多线程的异常捕获
1：对于没有设置“未捕获异常处理器”的多线程程序，在run()方法中抛出的异常，在主线程中用try-catch块是捕获不到的。程序代码如：ExceptionRunnable
2：如果对所有的线程采用同一个“未捕获异常处理器”，可以使用 Thread.setDefaultUncaughtExceptionHandler(new TestExceptionHandler());
3：如果想要“未捕获异常处理器”有个性，那可以创建一个新的ThreadFactory、UncaughtExceptionHandler,利用Executros的
	Executors.newCachedThreadPool(new ExceptionHandlerThreadFactory());方法为这个线程工厂创建的每个线程添加一个“未捕获异常处理器”。
	代码如：
