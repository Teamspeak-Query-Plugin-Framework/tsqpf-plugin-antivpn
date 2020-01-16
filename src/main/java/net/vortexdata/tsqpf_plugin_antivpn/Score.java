package net.vortexdata.tsqpf_plugin_antivpn;

import net.vortexdata.tsqpf.plugins.*;
import org.json.*;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;

/**
 * @author Michael Wiesinger
 * @author Sandro Kierner
 * @since 1.0.0
 */
public class Score {

    /**
     *
     * @param ip        IP address of the client that needs to be checked.
     * @param logger    The plugins logger instance.
     * @return          Score evaluated by IP Quality Score.
     */
    public static int getScore(String ip, PluginLogger logger) {

        try {

            URL url = new URL(String.format("https://www.ipqualityscore.com/api/json/ip/fT0dFSciscMOOkw7ddWNdQTvjcTz5hlP/%s?strictness=0&allow_public_access_points=true&fast=true&lighter_penalties=true&mobile=true", ip));
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder respone = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                respone.append(line);
            }

            JSONObject json = new JSONObject(respone.toString());

            return json.getInt("fraud_score");

        } catch (Exception e) {
            logger.printWarn("Failed to get score, appending details: " + e.getMessage());
        }

        return 0;
    }

}
