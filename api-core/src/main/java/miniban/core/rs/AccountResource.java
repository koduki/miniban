package miniban.core.rs;

import io.opencensus.contrib.http.jaxrs.Metrics;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import miniban.core.models.AccountService;
import miniban.core.models.vo.Account;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import static miniban.fw.TelemetryHelper.*;

@Path("/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class AccountResource {

    private static final Tracer tracer = Tracing.getTracer();

    @Inject
    AccountService service;

    @Schema(description = "Deposit/Withdrwa Information")
    public static class TransactionReuqest {

        public String who;
        public String name;
        public long amount;

    }

    @GET
    @Path("/{userId}/summary")
    public List<Account> summary(@Context HttpServletRequest request, @PathParam("userId") String userId) {
        return trace(request, () -> {
            return service.getActivitySummary(userId);
        });
    }

    @GET
    @Path("/{userId}/balance")
    @Metrics
    public Map<String, Long> getBalance(@Context HttpServletRequest request, @PathParam("userId") String userId) {
        return trace(request, () -> {
            var balance = service.getBalance(userId);
            return Map.of("balance", balance);
        });
    }

    @POST
    @Path("/{userId}/deposit")
    public Response deposit(@Context HttpServletRequest request, @PathParam("userId") String userId, TransactionReuqest transaction) {
        return trace(request, () -> {
            service.deposit(userId, transaction.who, transaction.name, transaction.amount);
            return Response
                    .status(Response.Status.OK)
                    .build();
        });
    }

    @POST
    @Path("/{userId}/withdraw")
    public Response withdraw(@Context HttpServletRequest request, @PathParam("userId") String userId, TransactionReuqest transaction) {
        return trace(request, () -> {
            service.withdraw(userId, transaction.who, transaction.name, transaction.amount);
            return Response
                    .status(Response.Status.OK)
                    .build();
        });
    }
}
