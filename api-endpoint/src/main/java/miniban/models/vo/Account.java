/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniban.models.vo;

import java.time.ZonedDateTime;

/**
 *
 * @author koduki
 */
public class Account {

    private ZonedDateTime usedDateTime;
    private String method;
    private String name;
    private long amount;

    public Account(ZonedDateTime usedDateTime, String method, String name, long amount) {
        this.usedDateTime = usedDateTime;
        this.method = method;
        this.name = name;
        this.amount = amount;
    }

    public String getUsedDateTime() {
        return usedDateTime.toOffsetDateTime().toString();
    }

    public String getMethod() {
        return method;
    }

    public String getName() {
        return name;
    }

    public long getAmount() {
        return amount;
    }

}
