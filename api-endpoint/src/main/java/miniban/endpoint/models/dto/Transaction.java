/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniban.endpoint.models.dto;

/**
 *
 * @author koduki
 */
public class Transaction {

    public String who;
    public String name;
    public long amount;

    public Transaction() {
    }

    public Transaction(String who, String name, long amount) {
        this.who = who;
        this.name = name;
        this.amount = amount;
    }

}
