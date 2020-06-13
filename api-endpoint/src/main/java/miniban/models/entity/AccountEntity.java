/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniban.models.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author koduki
 */
@Entity(name = "account")
public class AccountEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    public UUID id;
    @Column(name = "user_id", nullable = false)
    public String userId;
    @Column(name = "used_datetime", nullable = false)
    public ZonedDateTime usedDateTime;
    @Column(name = "method", nullable = false)
    public String method;
    @Column(name = "other_side", nullable = false)
    public String otherSide;
    @Column(name = "name", nullable = false)
    public String name;
    @Column(name = "amount", nullable = false)
    public long amount;

    public AccountEntity() {
    }

    public AccountEntity(String userId, String method, String otherSide, String name, long amount) {

        this.userId = userId;
        this.usedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        this.method = method;
        this.otherSide = otherSide;
        this.name = name;
        this.amount = amount;
    }

}
