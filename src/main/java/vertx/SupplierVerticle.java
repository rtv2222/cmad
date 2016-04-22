package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class SupplierVerticle extends AbstractVerticle{
	
	@Override
	public void start(Future<Void> startFuture){
		System.out.println("SupplierVerticle started");
		vertx.eventBus().consumer("reachMeHere", message -> {
			System.out.println("message body: " + message.body());			
		});
		startFuture.complete();
	}
	
	@Override
	public void stop(Future<Void> stopFuture){
		System.out.println("SupplierVerticle stopped");
	}
	
	public static void main (String args[]) {
		VertxOptions options = new VertxOptions().setWorkerPoolSize(10);
		DeploymentOptions depOptions = new DeploymentOptions();
		Vertx vertx = Vertx.vertx(options);
		
		vertx.deployVerticle("vertx.SupplierVerticle", new Handler<AsyncResult<String>>(){
			@Override
			public void handle(AsyncResult<String> stringAsyncResult){
				System.out.println("vertx.SupplierVerticle");
				
				vertx.deployVerticle("vertx.ConsumerVerticle", new Handler<AsyncResult<String>>(){
					@Override
					public void handle(AsyncResult<String> stringAsyncResult){
						System.out.println("vertx.ConsumerVerticle");
					}
				});
			}
		});
	}
}
