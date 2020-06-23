package miniban.endpoint.rs;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import miniban.endpoint.models.AccountService;
import miniban.endpoint.models.dto.Account;
import miniban.endpoint.models.dto.Transaction;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import static miniban.fw.TelemetryHelper.*;

@Path("/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class AccountResource {

    @Inject
    AccountService service;

    @Schema(description = "Deposit/Withdraw Information")
    public static class TransactionReuqest {

        public String who;
        public String name;
        public long amount;

    }

    @GET
    @Path("/{userId}/summary")
    public List<Account> summary(@PathParam("userId") String userId) {
        return trace(() -> {
            return service.getActivitySummary(userId);
        });
    }

    @GET
    @Path("/{userId}/balance")
    public Map<String, Long> getBalance(@PathParam("userId") String userId) {
        return trace(() -> {
            var balance = service.getBalance(userId);
            return balance;
        });
    }

    @POST
    @Path("/{userId}/deposit")
    public Response deposit(@PathParam("userId") String userId, TransactionReuqest transaction) {
        return trace(() -> {
            service.deposit(userId, new Transaction(transaction.who, transaction.name, transaction.amount));
            return Response
                    .status(Response.Status.OK)
                    .build();
        });
    }

    @POST
    @Path("/{userId}/withdraw")
    public Response withdraw(@PathParam("userId") String userId, TransactionReuqest transaction) {
        return trace(() -> {
            service.withdraw(userId, new Transaction(transaction.who, transaction.name, transaction.amount));
            return Response
                    .status(Response.Status.OK)
                    .build();
        });
    }
}
