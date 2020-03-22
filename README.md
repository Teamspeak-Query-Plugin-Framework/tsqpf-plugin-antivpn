# AntiVPN
Prevent users of your Teamspeak server from connecting using a VPN service. 

## 💡 How does it work?

If a user connects to your server, the plugin runs a background check if the users IP is listed in a database of VPN server IPs.

## 🚀 Gettings started

Just download the latest release that's compatible with your TSQPF version and copy it into its plugin directory. After you've done that, either reload or restart your framework instance in order to get it loaded and initiated.

## ⚙️ Configuration

Here's a list of all config keys, value datatypes and a description:

KEY | DATATYPE | DESCRIPTION

- **messageUserKick** : [String] Tells the user, that they can't connect to the server if using a VPN.
- **scoreLimit** : [Integer / 0 to 100] Sets the maximum score a user is allowed to have (0 being absolutely no fraud to 100 definitely fraud).
- **useGroupWhitelist** : [Boolean] Enable / disable group whitelist.
- **ignoreLocalhost** : [Boolean] If local connections are allowed or not.
- **whitelistedGroupIds** : [Integers] Sets the whitelisted groups (eg. 6,12,14).


## 📁 Directory Tree

AntiVPN/<br>
└── plugin.conf<br>

## 📜 Vortexdata Certification

This plugin is developed by VortexdataNET for the Teamspeak Query Plugin Framework. You are free to use, modify or redistribute the plugin.
