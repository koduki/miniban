package miniban.rs;

import java.util.HashMap;
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
import miniban.models.AccountService;
import miniban.models.vo.Account;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Path("/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class AccountResource {

    @Inject
    AccountService service;

    @Schema(description = "Deposit/Withdrwa Information")
    public static class TransactionReuqest {

        public String who;
        public String name;
        public long amount;

        public TransactionReuqest() {
        }

    }

    @GET
    @Path("/{userId}/summary")
    public List<Account> summary(@PathParam("userId") String userId) {
        return service.getActivitySummary(userId);
    }

    @GET
    @Path("/{userId}/balance")
    public Map<String, Long> getBalance(@PathParam("userId") String userId) {
        var balance = service.getBalance(userId);
        return new HashMap<>() {
            {
                put("balance", balance);
            }
        };
    }

    @POST
    @Path("/{userId}/deposit")
    public Response deposit(@PathParam("userId") String userId, TransactionReuqest request) {
        service.deposit(userId, request.who, request.name, request.amount);
        return Response
                .status(Response.Status.OK)
                .build();
    }

    @POST
    @Path("/{userId}/withdraw")
    public Response withdraw(@PathParam("userId") String userId, TransactionReuqest request) {
        service.withdraw(userId, request.who, request.name, request.amount);
        return Response
                .status(Response.Status.OK)
                .build();
    }
}
