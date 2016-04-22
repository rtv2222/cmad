package vertx;

import java.util.logging.Handler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class MyVerticle extends AbstractVerticle{
	
	@Override
	public void start(Future<Void> startFuture){
		System.out.println("MyVerticle started");
	}
	
	@Override
	public void stop(Future<Void> stopFuture){
		System.out.println("MyVerticle stopped");
	}
	
	public static void main (String args[]) {
		VertxOptions options = new VertxOptions().setWorkerPoolSize(10);
		DeploymentOptions depOptions = new DeploymentOptions();
		Vertx vertx = Vertx.vertx(options);
	}

}
