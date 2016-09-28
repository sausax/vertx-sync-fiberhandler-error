import co.paralleluniverse.fibers.Suspendable;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.sync.Sync;
import io.vertx.ext.sync.SyncVerticle;
import io.vertx.ext.web.Router;

public class App extends SyncVerticle{
    
    
	@Override
    @Suspendable
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();
        HttpServer server = vertx.createHttpServer();
        
        Router router = Router.router(vertx);

        
        router.route("/with_fiber").handler(Sync.fiberHandler(routingContext -> {
            throw new RuntimeException();
         }));

         router.route("/without_fiber").handler(routingContext -> {
             throw new RuntimeException();
         });
         
         server.requestHandler(router::accept).listen(8080);
	}

}
