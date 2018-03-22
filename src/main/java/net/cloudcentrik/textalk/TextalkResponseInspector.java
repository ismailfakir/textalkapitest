package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
import com.thetransactioncompany.jsonrpc2.client.RawResponse;
import com.thetransactioncompany.jsonrpc2.client.RawResponseInspector;

public class TextalkResponseInspector implements RawResponseInspector {
    private static final Logger log = AppLogger.getLogger( App.class.getName() );
    public void inspect(RawResponse rawResponse) {
        // print the HTTP status code
        log.debug("HTTP status: " + rawResponse.getStatusCode());
        log.debug("Content: "+rawResponse.getContent());
    }
}
