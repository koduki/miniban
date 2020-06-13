/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniban.util.profile;

import jdk.jfr.Category;
import jdk.jfr.Event;
import jdk.jfr.Label;

/**
 *
 * @author koduki
 */
@Category({"Application Profile"})
@Label("HTTP Request")
public class HttpRequestEvent extends Event {

    @Label("Method")
    String method;

    @Label("URL")
    String url;
}
