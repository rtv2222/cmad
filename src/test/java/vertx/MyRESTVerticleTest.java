package vertx;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class MyRESTVerticleTest {

    Vertx vertx;
    String name;

    @Before
    public void before(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(new MyRESTVerticle(), did -> {name = did.result();});
    }

    @After
    public void after(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
        vertx.undeploy(name);
    }

    @Test
    public void testRequest(TestContext context) {
        // Send a request and get a response
        HttpClient client = vertx.createHttpClient();
        Async async = context.async();

        client.getNow(8080, "localhost", "/services/users/1", resp -> {
            resp.bodyHandler(body -> context.assertEquals(new JsonObject().put("id", "1").put("name", "Test User 1"), new JsonObject(body.toString())));
            client.close();
            async.complete();
        });
    }
}
