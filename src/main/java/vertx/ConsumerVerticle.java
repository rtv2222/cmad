package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class ConsumerVerticle extends AbstractVerticle{
	
	@Override
	public void start(Future<Void> startFuture){
		System.out.println("ConsumerVerticle started");
		vertx.eventBus().publish("reachMeHere", "Do you roger this!!");
		startFuture.complete();
	}
	
	@Override
	public void stop(Future<Void> stopFuture){
		System.out.println("ConsumerVerticle stopped");
	}
}
