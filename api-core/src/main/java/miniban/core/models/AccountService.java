/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniban.core.models;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import miniban.core.models.entity.AccountEntity;
import miniban.core.models.vo.Account;
import static miniban.fw.TelemetryHelper.*;

/**
 *
 * @author koduki
 */
@ApplicationScoped
public class AccountService {

    @Inject
    EntityManager em;

    @Transactional
    public List<Account> getActivitySummary(String userId) {
        return trace(() -> {
            try (var stream = AccountEntity.<AccountEntity>stream(
                    "user_id = :user_id",
                    Sort.by("used_datetime").descending(),
                    Parameters.with("user_id", userId))) {
                return stream
                        .map(x -> new Account(x.usedDateTime, x.method, x.name, x.amount))
                        .collect(Collectors.toList());
            }
        });
    }

    @Transactional
    public long getBalance(String userId) {
        return trace(() -> {
            var deposit = em
                    .createQuery("select sum(a.amount) from account a where a.userId=:userId and a.method='deposit'")
                    .setParameter("userId", userId)
                    .getSingleResult();

            var withdraw = em
                    .createQuery("select sum(a.amount) from account a where a.userId=:userId and a.method='withdraw'")
                    .setParameter("userId", userId)
                    .getSingleResult();

            var wrap = (Function<Object, Long>) (x) -> (x == null) ? 0 : (Long) x;
            return wrap.apply(deposit) - wrap.apply(withdraw);
        });
    }

    @Transactional
    public void deposit(String userId, String who, String name, long amount) {
        trace(() -> {
            var account = new AccountEntity(userId, "deposit", who, name, amount);
            AccountEntity.persist(account);
        });
    }

    @Transactional
    public void withdraw(String userId, String who, String name, long amount) {
        trace(() -> {
            var account = new AccountEntity(userId, "withdraw", who, name, amount);
            AccountEntity.persist(account);
        });
    }
}
