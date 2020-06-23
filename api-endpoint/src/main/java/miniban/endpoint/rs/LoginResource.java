/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniban.endpoint.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static miniban.fw.TelemetryHelper.trace;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 *
 * @author koduki
 */
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Schema(description = "Login Information")
    public static class LoginResponse {

        @Schema(description = "This is User ID")
        public final String userId;
        @Schema(description = "This is token")
        public final String token;

        public LoginResponse(String userId, String token) {
            this.userId = userId;
            this.token = token;
        }
    }

    @Schema(description = "Login request")
    public static class LoginRequest {

        public String userId;
        public String password;
    }

    @POST
    @Operation(
            summary = "Login",
            description = "Login with User ID and Password.")
    public LoginResponse login(LoginRequest request) {
        return trace(() -> {
            return new LoginResponse(request.userId.toUpperCase(), "dummy token");
        });
    }
}
