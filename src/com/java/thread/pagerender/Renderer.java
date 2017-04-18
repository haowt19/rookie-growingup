package com.java.thread.pagerender;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.sun.scenario.effect.ImageData;

/**
 * 页面加载文本和图片
 * 每加载完一个图片就显示一个图片
 * @author 001244
 *
 */
public class Renderer {

	private final ExecutorService executor;
	
	public Renderer(ExecutorService exec) {
		this.executor = exec;
	}
	
	void rederPage(CharSequence source) {
		final List<ImageInfo> info = scanForImageInfo(source);
		
		CompletionService<ImageData> completionService = 
				new ExecutorCompletionService<ImageData>(executor);
		
		for (final ImageInfo imageInfo : info) {
			completionService.submit(new Callable<ImageData>(){

				@Override
				public ImageData call() throws Exception {
					// TODO Auto-generated method stub
					return ImageInfo.downloadImage();
				}
				
			});
		}
		
		renderText(source);
		
		try {
			for (int t=0, n = info.size(); t<n ;t++) {
				Future<ImageData> f = completionService.take();
				ImageData imageData = f.get();
				renderImage(imageData);
			} 
		}catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			throw e;
		}
		
	}
	
	
}
