package cz._heropwp.playerworldspro.command;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerWoldsPro implements CommandExecutor {
    private final Main instance;

    public PlayerWoldsPro(Main var1) {
        this.instance = var1;
    }

    public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
        if (var4.length == 0) {
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix() + "§2§lPlayerWorldsPro §cv" + this.instance.getDescription().getVersion());
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix() + "§7ID: 家园世界 B2 没汉化版 §fCRACKED_BY_233MC_CN");
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix());
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix() + "§6§lCommands:");
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix() + "§a/PlayerWorldsPro menu §fOpen menu");
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix() + "§a/PlayerWorldsPro join <player> §fTeleport to the player world of the player.");
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix() + "§a/PlayerWorldsPro spawn §fTeleport to spawn in a player world.");
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix() + "§a/PlayerWorldsPro settings [player] §fOpen your player world settings.");
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix() + "§a/PlayerWorldsPro setLobby §fSet Lobby location §c[PlayerWorldsPro.setLobby]");
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix() + "§a/PlayerWorldsPro reload §fReload configuration file §c[PlayerWorldsPro.reload]"
            );
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + "§a/PlayerWorldsPro setWorldBorder <player> <size> §fSet the size of the World Border §c[PlayerWorldsPro.setWorldBorder]"
            );
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + "§a/PlayerWorldsPro expiration <set/extend> <player> <length_in_days> §fSet the expiration of the player world §c[PlayerWorldsPro.expiration.<set/extend>]"
            );
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + "§a/PlayerWorldsPro create <player> <normal/flat/empty> <length> [pre-built/customGenerator] §fCreate a player world §c[PlayerWorldsPro.create]"
            );
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + "§a/PlayerWorldsPro delete <player> §fDelete player world of the player §c[PlayerWorldsPro.delete]"
            );
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix());
            var1.sendMessage(this.instance.getBasicManager().getMessagePrefix() + "§fCreated with §4❤ §fby §a_HeroPwP");
        } else {
            if (var4[0].equalsIgnoreCase("menu")) {
                if (var1 instanceof Player) {
                    Player var29 = (Player) var1;
                    this.instance.h().a(var29);
                } else {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Send-From-Game").replace("&", "§")
                    );
                }

                return false;
            }

            if (var4[0].equalsIgnoreCase("join")) {
                if (var1 instanceof Player) {
                    Player var28 = (Player) var1;
                    if (var4.length >= 2) {
                        this.instance.getWorldManager().moveTo(var28, var4[1], true);
                    } else {
                        var1.sendMessage(
                                this.instance.getBasicManager().getMessagePrefix()
                                        + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Usage.Join").replace("&", "§")
                        );
                    }
                } else {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Send-From-Game").replace("&", "§")
                    );
                }

                return false;
            }

            if (var4[0].equalsIgnoreCase("spawn")) {
                if (var1 instanceof Player) {
                    Player var27 = (Player) var1;
                    String var35 = var27.getWorld().getName();
                    String var42 = this.instance.getBasicManager().getOwnerFromWorldName(var35);
                    if (this.instance.getWorldManager().worldExists(var42)) {
                        this.instance.getBasicManager().teleport(var27, this.instance.getWorldManager().getSpawn(var42), false);
                        var27.sendMessage(
                                this.instance.getBasicManager().getMessagePrefix()
                                        + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Teleported").replace("&", "§")
                        );
                        return false;
                    }

                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.In-Player-World").replace("&", "§")
                    );
                } else {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Send-From-Game").replace("&", "§")
                    );
                }

                return false;
            }

            if (var4[0].equalsIgnoreCase("settings")) {
                if (var4.length > 1) {
                    Player var25 = Bukkit.getPlayer(var4[1]);
                    if (var25 != null) {
                        if (this.instance.getWorldManager().worldExists(var25.getName())) {
                            this.instance.j().a(var25, var25.getName());
                        } else {
                            var1.sendMessage(
                                    this.instance.getBasicManager().getMessagePrefix()
                                            + this.instance
                                            .getConfigManager()
                                            .get(ConfigManager.Type.MESSAGES)
                                            .getString("Messages.Doesnt-Have")
                                            .replace("&", "§")
                                            .replace("%player%", var4[1])
                            );
                        }
                    } else {
                        var1.sendMessage(
                                this.instance.getBasicManager().getMessagePrefix()
                                        + this.instance
                                        .getConfigManager()
                                        .get(ConfigManager.Type.MESSAGES)
                                        .getString("Messages.Not-Online")
                                        .replace("&", "§")
                                        .replace("%player%", var4[1])
                        );
                    }
                } else if (var1 instanceof Player) {
                    Player var26 = (Player) var1;
                    if (this.instance.getWorldManager().worldExists(var26.getName())) {
                        this.instance.j().a(var26, var26.getName());
                    } else {
                        var26.sendMessage(
                                this.instance.getBasicManager().getMessagePrefix()
                                        + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Dont-Have").replace("&", "§")
                        );
                    }
                } else {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Send-From-Game").replace("&", "§")
                    );
                }

                return false;
            }

            if (var4[0].equalsIgnoreCase("setLobby")) {
                if (var1 instanceof Player) {
                    Player var24 = (Player) var1;
                    if (var1.hasPermission("PlayerWorldsPro.setLobby")) {
                        World var34 = Bukkit.getWorld(var24.getWorld().getName());
                        double var41 = var24.getLocation().getX();
                        double var44 = var24.getLocation().getY();
                        double var45 = var24.getLocation().getZ();
                        float var13 = var24.getLocation().getYaw();
                        float var14 = var24.getLocation().getPitch();
                        this.instance
                                .getConfigManager()
                                .get(ConfigManager.Type.DATA)
                                .set("Lobby", var34.getName() + ";" + var41 + ";" + var44 + ";" + var45 + ";" + var13 + ";" + var14);
                        this.instance.getConfigManager().save(ConfigManager.Type.DATA);
                        this.instance.getConfigManager().load(ConfigManager.Type.DATA);
                        var1.sendMessage(
                                this.instance.getBasicManager().getMessagePrefix()
                                        + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Lobby-Setup").replace("&", "§")
                        );
                    } else {
                        var1.sendMessage(
                                this.instance.getBasicManager().getMessagePrefix()
                                        + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Insufficient-Permission").replace("&", "§")
                        );
                    }
                } else {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Send-From-Game").replace("&", "§")
                    );
                }

                return false;
            }

            if (var4[0].equalsIgnoreCase("reload")) {
                if (var1.hasPermission("PlayerWorldsPro.reload")) {
                    this.instance.reloadConfig();

                    for (ConfigManager.Type var43 : ConfigManager.Type.values()) {
                        this.instance.getConfigManager().load(var43);
                    }

                    this.instance.getBasicManager().loadWorldPrefix();
                    this.instance.getBasicManager().loadMessagePrefix();
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Configuration-Reloaded").replace("&", "§")
                    );
                } else {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Insufficient-Permission").replace("&", "§")
                    );
                }

                return false;
            }

            if (var4[0].equalsIgnoreCase("setWorldBorder")) {
                if (var1.hasPermission("PlayerWorldsPro.setWorldBorder")) {
                    if (var4.length > 2) {
                        String var22 = var4[1];
                        if (this.instance.getWorldManager().worldExists(var22)) {
                            try {
                                int var32 = Integer.parseInt(var4[2]);
                                World var39 = Bukkit.getWorld(this.instance.getBasicManager().getWorldPrefix() + var22);
                                if (var39 == null) {
                                    var1.sendMessage(
                                            this.instance.getBasicManager().getMessagePrefix()
                                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Unloaded-World").replace("&", "§")
                                    );
                                } else {
                                    var39.getWorldBorder().setSize((double) var32);
                                    var1.sendMessage(
                                            this.instance.getBasicManager().getMessagePrefix()
                                                    + this.instance
                                                    .getConfigManager()
                                                    .get(ConfigManager.Type.MESSAGES)
                                                    .getString("Messages.World-Border.Set")
                                                    .replace("&", "§")
                                                    .replace("%size%", String.valueOf(var32))
                                    );
                                }
                            } catch (NumberFormatException var15) {
                                var1.sendMessage(
                                        this.instance.getBasicManager().getMessagePrefix()
                                                + this.instance
                                                .getConfigManager()
                                                .get(ConfigManager.Type.MESSAGES)
                                                .getString("Messages.Invalid-Number")
                                                .replace("&", "§")
                                                .replace("%number%", var4[2])
                                );
                            }
                        } else {
                            var1.sendMessage(
                                    this.instance.getBasicManager().getMessagePrefix()
                                            + this.instance
                                            .getConfigManager()
                                            .get(ConfigManager.Type.MESSAGES)
                                            .getString("Messages.Doesnt-Have")
                                            .replace("&", "§")
                                            .replace("%player%", var22)
                            );
                        }
                    } else {
                        var1.sendMessage(
                                this.instance.getBasicManager().getMessagePrefix()
                                        + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Usage.SetWorldBorder").replace("&", "§")
                        );
                    }
                } else {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Insufficient-Permission").replace("&", "§")
                    );
                }

                return false;
            }

            if (var4[0].equalsIgnoreCase("expiration")) {
                if (var4.length > 3) {
                    if (var4[1].equalsIgnoreCase("set")) {
                        if (var1.hasPermission("PlayerWorldsPro.expiration.set")) {
                            String var21 = var4[2];
                            if (this.instance.getWorldManager().worldExists(var21)) {
                                try {
                                    int var31 = Integer.parseInt(var4[3]);
                                    if (var31 > 0) {
                                        if (this.instance.getWorldManager().systemExipreEnabled()) {
                                            if (this.instance.getWorldManager().worldExists(var21)) {
                                                long var38 = System.currentTimeMillis() + TimeUnit.DAYS.toMillis((long) var31);
                                                this.instance.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var21 + ".Expiration", var38);
                                                this.instance.getConfigManager().save(ConfigManager.Type.DATA);
                                                this.instance.getConfigManager().load(ConfigManager.Type.DATA);
                                                var1.sendMessage(
                                                        this.instance.getBasicManager().getMessagePrefix()
                                                                + this.instance
                                                                .getConfigManager()
                                                                .get(ConfigManager.Type.MESSAGES)
                                                                .getString("Messages.Successfully-Set-Expiration")
                                                                .replace("&", "§")
                                                                .replace("%length%", String.valueOf(var31))
                                                                .replace("%player%", var21)
                                                );
                                            } else {
                                                var1.sendMessage(
                                                        this.instance.getBasicManager().getMessagePrefix()
                                                                + this.instance
                                                                .getConfigManager()
                                                                .get(ConfigManager.Type.MESSAGES)
                                                                .getString("Messages.Doesnt-Have")
                                                                .replace("&", "§")
                                                                .replace("%player%", var21)
                                                );
                                            }
                                        } else {
                                            var1.sendMessage(
                                                    this.instance.getBasicManager().getMessagePrefix()
                                                            + this.instance
                                                            .getConfigManager()
                                                            .get(ConfigManager.Type.MESSAGES)
                                                            .getString("Messages.Expiration-Disabled")
                                                            .replace("&", "§")
                                            );
                                        }
                                    } else {
                                        var1.sendMessage(
                                                this.instance.getBasicManager().getMessagePrefix()
                                                        + this.instance
                                                        .getConfigManager()
                                                        .get(ConfigManager.Type.MESSAGES)
                                                        .getString("Messages.Positive-Number")
                                                        .replace("&", "§")
                                                        .replace("%number%", var4[3])
                                        );
                                    }
                                } catch (NumberFormatException var16) {
                                    var1.sendMessage(
                                            this.instance.getBasicManager().getMessagePrefix()
                                                    + this.instance
                                                    .getConfigManager()
                                                    .get(ConfigManager.Type.MESSAGES)
                                                    .getString("Messages.Invalid-Number")
                                                    .replace("&", "§")
                                                    .replace("%number%", var4[3])
                                    );
                                }
                            } else {
                                var1.sendMessage(
                                        this.instance.getBasicManager().getMessagePrefix()
                                                + this.instance
                                                .getConfigManager()
                                                .get(ConfigManager.Type.MESSAGES)
                                                .getString("Messages.Doesnt-Have")
                                                .replace("&", "§")
                                                .replace("%player%", var21)
                                );
                            }
                        } else {
                            var1.sendMessage(
                                    this.instance.getBasicManager().getMessagePrefix()
                                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Insufficient-Permission").replace("&", "§")
                            );
                        }

                        return false;
                    }

                    if (var4[1].equalsIgnoreCase("extend")) {
                        if (var1.hasPermission("PlayerWorldsPro.expiration.extend")) {
                            String var20 = var4[2];
                            if (this.instance.getWorldManager().worldExists(var20)) {
                                try {
                                    int var30 = Integer.parseInt(var4[3]);
                                    if (var30 > 0) {
                                        this.instance.getWorldManager().cmdHandleExtend(var1, var20, String.valueOf(var30), false);
                                    } else {
                                        var1.sendMessage(
                                                this.instance.getBasicManager().getMessagePrefix()
                                                        + this.instance
                                                        .getConfigManager()
                                                        .get(ConfigManager.Type.MESSAGES)
                                                        .getString("Messages.Positive-Number")
                                                        .replace("&", "§")
                                                        .replace("%number%", var4[3])
                                        );
                                    }
                                } catch (NumberFormatException var17) {
                                    var1.sendMessage(
                                            this.instance.getBasicManager().getMessagePrefix()
                                                    + this.instance
                                                    .getConfigManager()
                                                    .get(ConfigManager.Type.MESSAGES)
                                                    .getString("Messages.Invalid-Number")
                                                    .replace("&", "§")
                                                    .replace("%number%", var4[3])
                                    );
                                }
                            } else {
                                var1.sendMessage(
                                        this.instance.getBasicManager().getMessagePrefix()
                                                + this.instance
                                                .getConfigManager()
                                                .get(ConfigManager.Type.MESSAGES)
                                                .getString("Messages.Doesnt-Have")
                                                .replace("&", "§")
                                                .replace("%player%", var20)
                                );
                            }
                        } else {
                            var1.sendMessage(
                                    this.instance.getBasicManager().getMessagePrefix()
                                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Insufficient-Permission").replace("&", "§")
                            );
                        }

                        return false;
                    }
                }

                var1.sendMessage(
                        this.instance.getBasicManager().getMessagePrefix()
                                + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Usage.Expiration").replace("&", "§")
                );
                return false;
            }

            if (var4[0].equalsIgnoreCase("create")) {
                if (var1.hasPermission("PlayerWorldsPro.create")) {
                    if (var4.length > 3) {
                        String var5 = var4[1];
                        Player var6 = Bukkit.getPlayer(var5);
                        if (var6 != null) {
                            try {
                                WorldManager.Generator var7 = WorldManager.Generator.valueOf(var4[2].toUpperCase());
                                int var8 = Integer.parseInt(var4[3]);
                                if (var8 > 0) {
                                    if (var4.length > 4) {
                                        String var9 = var4[4];
                                        File var10 = new File(this.instance.getDataFolder() + "/maps/" + var9);
                                        if (var10.exists() && var10.isDirectory()) {
                                            var7 = WorldManager.Generator.EMPTY;
                                            this.instance.getWorldManager().createWorld(var6, var7, var9, null, var8, null, false);
                                            var1.sendMessage(
                                                    this.instance.getBasicManager().getMessagePrefix()
                                                            + this.instance
                                                            .getConfigManager()
                                                            .get(ConfigManager.Type.MESSAGES)
                                                            .getString("Messages.World-Created")
                                                            .replace("&", "§")
                                            );
                                            return false;
                                        }

                                        List var11 = this.instance.getConfig().getStringList("Custom-Generators");
                                        if (var11.contains(var9)) {
                                            var7 = WorldManager.Generator.CUSTOM;
                                            this.instance.getWorldManager().createWorld(var6, var7, var9, null, var8, null, false);
                                            var1.sendMessage(
                                                    this.instance.getBasicManager().getMessagePrefix()
                                                            + this.instance
                                                            .getConfigManager()
                                                            .get(ConfigManager.Type.MESSAGES)
                                                            .getString("Messages.World-Created")
                                                            .replace("&", "§")
                                            );
                                            return false;
                                        }
                                    }

                                    this.instance.getWorldManager().createWorld(var6, var7, null, null, var8, null, false);
                                    var1.sendMessage(
                                            this.instance.getBasicManager().getMessagePrefix()
                                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.World-Created").replace("&", "§")
                                    );
                                } else {
                                    var1.sendMessage(
                                            this.instance.getBasicManager().getMessagePrefix()
                                                    + this.instance
                                                    .getConfigManager()
                                                    .get(ConfigManager.Type.MESSAGES)
                                                    .getString("Messages.Positive-Number")
                                                    .replace("&", "§")
                                                    .replace("%number%", var4[3])
                                    );
                                }
                            } catch (NumberFormatException var18) {
                                var1.sendMessage(
                                        this.instance.getBasicManager().getMessagePrefix()
                                                + this.instance
                                                .getConfigManager()
                                                .get(ConfigManager.Type.MESSAGES)
                                                .getString("Messages.Invalid-Number")
                                                .replace("&", "§")
                                                .replace("%number%", var4[3])
                                );
                            } catch (IllegalArgumentException var19) {
                                var1.sendMessage(
                                        this.instance.getBasicManager().getMessagePrefix()
                                                + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Usage.Create").replace("&", "§")
                                );
                            }

                            return false;
                        }

                        var1.sendMessage(
                                this.instance.getBasicManager().getMessagePrefix()
                                        + this.instance
                                        .getConfigManager()
                                        .get(ConfigManager.Type.MESSAGES)
                                        .getString("Messages.Not-Online")
                                        .replace("&", "§")
                                        .replace("%player%", var5)
                        );
                        return false;
                    }

                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Usage.Create").replace("&", "§")
                    );
                    return false;
                }

                var1.sendMessage(
                        this.instance.getBasicManager().getMessagePrefix()
                                + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Insufficient-Permission").replace("&", "§")
                );
                return false;
            }

            if (var4[0].equalsIgnoreCase("delete")) {
                if (var1.hasPermission("PlayerWorldsPro.delete")) {
                    if (var4.length > 1) {
                        this.instance.getWorldManager().cmdHandleDelete(var1, var4[1]);
                    } else {
                        var1.sendMessage(
                                this.instance.getBasicManager().getMessagePrefix()
                                        + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Usage.Delete").replace("&", "§")
                        );
                    }
                } else {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Insufficient-Permission").replace("&", "§")
                    );
                }

                return false;
            }

            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance
                            .getConfigManager()
                            .get(ConfigManager.Type.MESSAGES)
                            .getString("Messages.Unknown-Argument")
                            .replace("&", "§")
                            .replace("%argument%", var4[0])
            );
        }

        return false;
    }
}
