package net.vortexdata.tsqpf_plugin_antivpn;

import com.github.theholywaffle.teamspeak3.api.event.*;
import net.vortexdata.tsqpf.plugins.*;

import java.util.*;
import java.util.stream.*;

/**
 * @author Sandro Kierner
 * @since 1.0.0
 */
public class AntiVPN extends TeamspeakPlugin {

    private boolean flagUseGroupWhitelist;
    private List<Integer> whitelistedGroups;

    @Override
    public void onEnable() {
        getConfig().setDefault("scoreLimit", "90");
        getConfig().setDefault("useGroupWhitelist", "false");
        getConfig().setDefault("ignoreLocalhost", "false");
        getConfig().setDefault("whitelistedGroupIds", "1");
        getConfig().setDefault("messageUserKick", "VPN connections are not allowed.");
        getConfig().saveAll();

        try {
            flagUseGroupWhitelist = Boolean.parseBoolean(getConfig().readValue("useGroupWhitelist"));
            if (flagUseGroupWhitelist) {
                whitelistedGroups = new ArrayList<>();
                Arrays.stream(getConfig().readValue("whitelistedGroupIds").split(","))
                        .forEach(x -> whitelistedGroups.add(Integer.parseInt(x)));

            }
        } catch (Exception e) {
            getLogger().printWarn("Failed to parse config value 'useGroupWhitelist', falling back to false.");
            flagUseGroupWhitelist = false;
        }

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {

        String nick = getAPI().getDatabaseClientByUId(clientJoinEvent.getUniqueClientIdentifier()).getNickname();

        // Check if connection is localhost
        if (getConfig().readValue("ignoreLocalhost").equalsIgnoreCase("true") && getAPI().getClientInfo(clientJoinEvent.getClientId()).getIp().equalsIgnoreCase("127.0.0.1"))
            return;

        try {

            if (Score.getScore(
                    getAPI().getClientInfo(clientJoinEvent.getClientId()).getIp(),
                    getLogger()
            ) >= Integer.parseInt(getConfig().readValue("scoreLimit"))) {

                if (flagUseGroupWhitelist) {

                    getLogger().printDebug("UID: "+clientJoinEvent.getUniqueClientIdentifier());

                    List<Integer> clientGroups = Arrays.stream(
                            getAPI().getClientByUId(clientJoinEvent.getUniqueClientIdentifier()).getServerGroups())
                            .boxed()
                            .collect(Collectors.toList());

                    for (Integer group : clientGroups) {

                        if (whitelistedGroups.contains(group))
                            return;

                    }

                }

                getLogger().printInfo("Client " + nick + " was flagged and removed from server.");
                getAPI().kickClientFromServer(getConfig().readValue("messageUserKick"), clientJoinEvent.getClientId());

            }
        } catch (Exception e) {
            getLogger().printError("An unexpected error occurred whilst trying to check background of user, appending info: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
