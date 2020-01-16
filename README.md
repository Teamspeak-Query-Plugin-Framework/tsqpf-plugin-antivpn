# AntiVPN
Prevent users of your Teamspeak server from connecting while using a VPN service. 

## 💡 How does it work?

If a user connect your server, the plugin run a background check if the users IP is listed in a database of VPN server IPs.

## 🚀 Gettings started

Just download the latest release that's compatible with your TSQPF version and copy it into its plugin directory. After you've done that, either reload or restart your framework instance in order to get it loaded and initiated.

## ⚙️ Configuration

Here's a list of all config keys, value datatypes and a description:

KEY | DATATYPE | DESCRIPTION

- **messageUserKick** : [String] Tells the user, that they can't connect to the server if using a VPN.


## 📁 Directory Tree

AntiVPN/<br>
└── plugin.conf<br>

## 📜 Vortexdata Certification

This plugin is developed by VortexdataNET for the Teamspeak Query Plugin Framework. Every release is being tested for any bugs, its performance or security issues. You are free to use, modify or redistribute the plugin.
