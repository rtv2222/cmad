package vertx;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MyRESTVerticle extends AbstractVerticle{
	
	@Override
	public void start(Future<Void> startFuture){
		System.out.println("MyRESTVerticle started");
		VertxOptions options = new VertxOptions().setWorkerPoolSize(10);
		DeploymentOptions depOptions = new DeploymentOptions();
		Vertx vertx = Vertx.vertx(options);
		
		System.out.println("vertx.MyRESTVerticle");
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.post("/user").handler(rc -> {
			String jsonStr = rc.getBodyAsString();
			ObjectMapper mapper = new ObjectMapper();
		    try {
				UserDTO dto = mapper.readValue(jsonStr, UserDTO.class);
				System.out.println("dto" + dto.getUserName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		router.get("/services/users/:id").handler(MyRESTVerticle::handleGetProduct);
		router.route().handler(StaticHandler.create()::handle);
		server.requestHandler(router::accept).listen(8080);	
		startFuture.complete();
	}
	
	@Override
	public void stop(Future<Void> stopFuture){
		System.out.println("MyRESTVerticle stopped");
	}
	
	private static void handleGetProduct(RoutingContext routingContext) {
	    String productID = routingContext.request().getParam("id");
	    HttpServerResponse response = routingContext.response();
	    response.putHeader("content-type", "application/json").end(new JsonObject().put("id", "1").put("name", "Test User 1").encode());
	  }
}
