/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniban.endpoint.models;

import io.opencensus.contrib.http.jaxrs.JaxrsClientFilter;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import miniban.endpoint.models.dto.Account;
import miniban.endpoint.models.dto.Transaction;
import org.eclipse.microprofile.config.ConfigProvider;

/**
 *
 * @author koduki
 */
@Dependent
public class AccountService {

    public List<Account> getActivitySummary(String userId) {
        return get(new GenericType<List<Account>>() {
        }, "/account/" + userId + "/summary");
    }

    public Map<String, Long> getBalance(String userId) {
        return get(new GenericType<Map<String, Long>>() {
        }, "/account/" + userId + "/balance");
    }

    public void deposit(String userId, Transaction transaction) {
        post("/account/" + userId + "/deposit", transaction);

    }

    public void withdraw(String userId, Transaction transaction) {
        post("/account/" + userId + "/withdraw", transaction);
    }

    private <T> T get(GenericType<T> type, String path) {
        var url = ConfigProvider.getConfig().getValue("miniban.api.core.url", String.class);
        System.out.println("url:" + url);

        var target = ClientBuilder.newClient()
                .target(url)
                .path(path);

        target.register(JaxrsClientFilter.class);
        return target
                .request(MediaType.APPLICATION_JSON)
                .get(type);
    }

    private <T> void post(String path, T data) {
        var url = ConfigProvider.getConfig().getValue("miniban.api.core.url", String.class);
        System.out.println("url:" + url);
        var target = ClientBuilder.newClient()
                .target(url)
                .path(path);

        target.register(JaxrsClientFilter.class);
        target
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(data));

    }
}
