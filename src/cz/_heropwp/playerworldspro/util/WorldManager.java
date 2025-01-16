package cz._heropwp.playerworldspro.util;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.OldMethods;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerPortalEvent;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WorldManager {
    private final Main instance;
    private final OldMethods oldMethods;
    private Boolean needTpSpawn;
    private final HashMap<String, Long> cooldown;

    public WorldManager(Main var1) {
        this.instance = var1;
        this.oldMethods = new OldMethods();
        this.setSpawns();
        this.cooldown = new HashMap<>();
    }

    private void setSpawns() {
        try {
            Iterator var1 = Bukkit.getWorlds().iterator();
            if (var1.hasNext()) {
                World var2 = (World) var1.next();
                Method var3 = World.class.getMethod("setSpawnLocation", Location.class);
                var3.invoke(var2, var2.getSpawnLocation());
            }

            this.needTpSpawn = false;
        } catch (Throwable var3) {
            this.needTpSpawn = true;
        }
    }

    public List<String> getOwnWorlds(String var1) {
        ArrayList var2 = new ArrayList();
        var2.add(var1);
        var2.add(var1 + "_nether");
        var2.add(var1 + "_the_end");
        return var2;
    }

    public int MAYBE_GetPlayerNum(String var1, boolean var2) {
        int var3 = 0;
        ArrayList<World> var4 = new ArrayList();

        for (String var6 : this.getOwnWorlds(var1)) {
            World var7 = Bukkit.getWorld(var6);
            if (var7 != null) {
                var4.add(var7);
            }
        }

        boolean var10 = this.instance.getConfig().getBoolean("Permissions.Player-Count");

        for (World var12 : var4) {
            for (Player var9 : var12.getPlayers()) {
                if (!var10 || !var9.hasPermission("PlayerWorldsPro.bypass.PlayerCount") || var9.getGameMode() != GameMode.SPECTATOR) {
                    var3++;
                }
            }
        }

        return var2 && var3 < 1 ? 1 : var3;
    }

    public void loadOnStartup() {
        String var1 = this.instance.getBasicManager().getWorldPrefix();
        if (this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds") && this.instance.getConfig().getBoolean("Basic.Load-On-Startup")) {
            boolean var2 = this.instance.getConfig().getBoolean("Basic.Portals");
            Bukkit.getConsoleSender().sendMessage(this.instance.getBasicManager().getLogPrefix() + "§eLoading player worlds...");

            for (String var4 : this.instance.getConfigManager().get(ConfigManager.Type.DATA).getConfigurationSection("Worlds").getKeys(false)) {
                this.d(var4, false);
                if (var2) {
                    this.ensureWorldExists(var1 + var4 + "_nether", true);
                    this.c(var1 + var4 + "_the_end", true);
                }

                Bukkit.getConsoleSender()
                        .sendMessage(this.instance.getBasicManager().getLogPrefix() + "§aPlayer world of player " + var4 + " has been successfully loaded!");
            }
        }
    }

    private void handlePortal(PlayerPortalEvent var1) {
        if (this.needTpSpawn) {
            this.oldMethods.handlePortle(var1);
        } else {
            try {
                Method setCanCreatePortal = PlayerPortalEvent.class.getMethod("setCanCreatePortal", boolean.class);
                setCanCreatePortal.invoke(var1, true);
            }catch (Throwable t){
                throw new RuntimeException(t);
            }
        }
    }

    public void worldToNether(Player var1, PlayerPortalEvent var2) {
        String var3 = var1.getWorld().getName() + "_nether";
        this.ensureWorldExists(var3, false);
        Location var4 = new Location(
                Bukkit.getWorld(var3), (double) var2.getFrom().getBlockX() / 8.0, (double) var2.getFrom().getBlockY(), (double) var2.getFrom().getBlockZ() / 8.0
        );
        this.handlePortal(var2);
        var2.setTo(var4);
    }

    public void netherToWorld(Player var1, PlayerPortalEvent var2) {
        this.d(this.instance.getBasicManager().getOwnerFromWorldName(var1.getWorld().getName()), false);
        String[] var3 = var1.getWorld().getName().split("_nether");
        String var4 = var3[var3.length - 1];
        Location var5 = new Location(
                Bukkit.getWorld(var4), (double) var2.getFrom().getBlockX() * 8.0, (double) var2.getFrom().getBlockY(), (double) var2.getFrom().getBlockZ() * 8.0
        );
        this.handlePortal(var2);
        var2.setTo(var5);
    }

    public void worldToEnd(Player var1) {
        String var2 = var1.getWorld().getName() + "_the_end";
        this.c(var2, false);
        Location var3 = new Location(Bukkit.getWorld(var2), 100.0, 50.0, 0.0);
        Block var4 = var3.getBlock();

        for (int var5 = var4.getX() - 2; var5 <= var4.getX() + 2; var5++) {
            for (int var6 = var4.getZ() - 2; var6 <= var4.getZ() + 2; var6++) {
                Block var7 = var3.getWorld().getBlockAt(var5, var4.getY() - 1, var6);
                if (var7.getType() != Material.OBSIDIAN) {
                    var7.setType(Material.OBSIDIAN);
                }

                for (int var8 = 1; var8 <= 3; var8++) {
                    Block var9 = var7.getRelative(BlockFace.UP, var8);
                    if (var9.getType() != Material.AIR) {
                        var9.setType(Material.AIR);
                    }
                }
            }
        }

        this.instance.getBasicManager().teleport(var1, var3, false);
    }

    public void ensureWorldExists(String var1, boolean var2) {
        if (Bukkit.getWorld(var1) == null) {
            if (var2) {
                File var3 = new File(Bukkit.getWorldContainer(), var1);
                if (!var3.exists()) {
                    return;
                }
            }

            Bukkit.createWorld(new WorldCreator(var1).environment(Environment.NETHER));
        }
    }

    public void c(String var1, boolean var2) {
        if (Bukkit.getWorld(var1) == null) {
            if (var2) {
                File var3 = new File(Bukkit.getWorldContainer(), var1);
                if (!var3.exists()) {
                    return;
                }
            }

            Bukkit.createWorld(new WorldCreator(var1).environment(Environment.THE_END));
        }
    }

    public void d(String var1, boolean var2) {
        if (Bukkit.getWorld(var1) == null) {
            String var3 = this.instance.getBasicManager().getWorldPrefix() + var1;
            WorldManager.Generator var4 = WorldManager.Generator.valueOf(
                    this.instance.getConfigManager().get(ConfigManager.Type.DATA).getString("Worlds." + var1 + ".Type")
            );
            WorldCreator var5 = null;
            switch (var4) {
                case NORMAL:
                    var5 = new WorldCreator(var3).type(WorldType.NORMAL).generateStructures(true);
                    break;
                case FLAT:
                    var5 = new WorldCreator(var3).type(WorldType.FLAT).generateStructures(false);
                    break;
                case EMPTY:
                    var5 = new WorldCreator(var3).generator(new EmptyChunkGenerator());
                    break;
                default:
                    if (this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Generator")) {
                        String var6 = this.instance.getConfigManager().get(ConfigManager.Type.DATA).getString("Worlds." + var1 + ".Generator");
                        var5 = new WorldCreator(var3).generator(var6);
                    }
            }

            if (var5 != null) {
                World var7 = Bukkit.createWorld(var5);
                if (var2 && var7 != null) {
                    var7.getBlockAt(var7.getSpawnLocation()).setType(Material.BEDROCK);
                }
            }
        }
    }

    public void createWorld(Player var1, WorldManager.Generator var2, String var3, String var4, Integer var5, Integer var6, boolean var7) {
        var1.closeInventory();
        String var8 = var1.getName().toLowerCase();
        if (var8.endsWith("_nether") || var8.endsWith("_the_end")) {
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Username").replace("&", "§")
            );
        } else if (this.worldExists(var1.getName())) {
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Already-Have").replace("&", "§")
            );
        } else {
            if (var6 != null) {
                if (Main.getEconomy().getBalance(var1) < (double) var6.intValue()) {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Not-Enough-Money").replace("&", "§")
                    );
                    return;
                }

                Main.getEconomy().withdrawPlayer(var1, (double) var6.intValue());
            }

            if (this.instance.getConfig().getBoolean("Cooldown.Enabled") && this.cooldown.containsKey(var1.getName())) {
                long var9 = System.currentTimeMillis() - this.cooldown.get(var1.getName());
                int var11 = this.instance.getConfig().getInt("Cooldown.Interval");
                if (TimeUnit.MILLISECONDS.toSeconds(var9) < (long) var11) {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance
                                    .getConfigManager()
                                    .get(ConfigManager.Type.MESSAGES)
                                    .getString("Messages.Create-Cooldown")
                                    .replace("&", "§")
                                    .replace("%time%", String.valueOf((long) var11 - TimeUnit.MILLISECONDS.toSeconds(var9)))
                    );
                    return;
                }
            }

            this.cooldown.put(var1.getName(), System.currentTimeMillis());
            if (var5 != null) {
                long var18 = System.currentTimeMillis() + TimeUnit.DAYS.toMillis((long) var5.intValue());
                this.instance.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var1.getName() + ".Expiration", var18);
            }

            if (var7) {
                this.instance.getConfigManager().get(ConfigManager.Type.PLAYERS).set("Claim." + var1.getName(), "");
                this.instance.getConfigManager().save(ConfigManager.Type.PLAYERS);
                this.instance.getConfigManager().load(ConfigManager.Type.PLAYERS);
            }

            this.instance.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var1.getName() + ".Type", var2.toString());
            if (var4 != null) {
                this.instance.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var1.getName() + ".Generator", var4);
            }

            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".WeatherCycle", this.instance.getConfig().getBoolean("Default-Settings.WeatherCycle"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".Difficulty", this.instance.getConfig().getString("Default-Settings.Difficulty"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".Block-Breaking", this.instance.getConfig().getBoolean("Default-Settings.Block-Breaking"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".Block-Placing", this.instance.getConfig().getBoolean("Default-Settings.Block-Placing"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".PvP", this.instance.getConfig().getBoolean("Default-Settings.PvP"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".Item-Pickup", this.instance.getConfig().getBoolean("Default-Settings.Item-Pickup"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".Drop-Item", this.instance.getConfig().getBoolean("Default-Settings.Drop-Item"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".Player-Damage", this.instance.getConfig().getBoolean("Default-Settings.Player-Damage"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".Hunger", this.instance.getConfig().getBoolean("Default-Settings.Hunger"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".Bucket", this.instance.getConfig().getBoolean("Default-Settings.Hunger"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".GameMode", this.instance.getConfig().getString("Default-Settings.GameMode"));
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var1.getName() + ".Access", this.instance.getConfig().getString("Default-Settings.Access"));
            this.instance.getConfigManager().save(ConfigManager.Type.DATA);
            this.instance.getConfigManager().load(ConfigManager.Type.DATA);
            String var19 = this.instance.getBasicManager().getWorldPrefix() + var1.getName();
            if (Bukkit.getWorld(var19) != null) {
                this.c(var19);
                Bukkit.unloadWorld(var19, true);
            }

            File var10 = new File(Bukkit.getWorldContainer(), var19);
            if (var10.exists()) {
                try {
                    FileUtils.deleteDirectory(var10);
                    var10.mkdir();
                } catch (Throwable var17) {
                    var17.printStackTrace();
                }
            }

            if (var3 != null) {
                File var20 = new File(this.instance.getDataFolder() + "/maps/" + var3);
                if (var20.exists() && var20.isDirectory()) {
                    for (File var15 : var20.listFiles()) {
                        if (var15.getName().equals("uid.dat") || var15.getName().equals("session.dat") || var15.getName().equals("session.lock")) {
                            var15.delete();
                        }
                    }

                    try {
                        FileUtils.copyDirectory(var20, var10);
                    } catch (Throwable var16) {
                        var16.printStackTrace();
                    }
                }
            }

            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Creating-World").replace("&", "§")
            );
            boolean var21 = var2 == WorldManager.Generator.EMPTY && var3 == null;
            Bukkit.getScheduler().runTask(this.instance, () -> this.d(var1.getName(), var21));
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.World-Created").replace("&", "§")
            );
            this.instance.j().b().remove(var1.getName());
            Bukkit.getScheduler().runTaskLater(this.instance, () -> {
                World var3x = Bukkit.getWorld(var19);
                if (var3x != null) {
                    int var4x = this.instance.getConfig().getInt("Default-Settings.World-Border");
                    if (var4x > 0) {
                        var3x.getWorldBorder().setSize((double) var4x);
                    }
                }

                if (this.instance.getConfig().getBoolean("Basic.Teleport-On-Create")) {
                    this.moveTo(var1, var1.getName(), true);
                    if (var3x != null) {
                        Location var6x = var1.getLocation().subtract(0.0, 0.5, 0.0);
                        if (this.needTpSpawn) {
                            this.oldMethods.setSpawn(var3x, var6x);
                        } else {
                            try {
                                Method m = World.class.getMethod("setSpawnLocation", Location.class);
                                m.invoke(var3x, var6x);
                            } catch (Throwable ignored) {
                            }
                        }
                    }
                }

                if (this.instance.getConfig().getBoolean("Commands.Create.Enabled")) {
                    for (String var5x : this.instance.getConfig().getStringList("Commands.Create.List")) {
                        var5x = var5x.replace("%owner%", var1.getName());
                        var5x = var5x.replace("%world%", var19);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var5x);
                    }
                }
            }, 20L);
        }
    }

    public void cmdHandleExtend(CommandSender var1, String var2, String var3, boolean var4) {
        if (!this.systemExipreEnabled()) {
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Expiration-Disabled").replace("&", "§")
            );
        } else if (!this.worldExists(var2)) {
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance
                            .getConfigManager()
                            .get(ConfigManager.Type.MESSAGES)
                            .getString("Messages.Doesnt-Have")
                            .replace("&", "§")
                            .replace("%player%", var2)
            );
        } else {
            Integer var5 = null;
            if (var3 != null) {
                if (var1 instanceof Player && var4) {
                    Player var6 = (Player) var1;
                    int var7 = this.instance.getConfig().getInt("GUI.Extend-Player-World.Items." + var3 + ".Price");
                    if (Main.getEconomy().getBalance(var6) < (double) var7) {
                        var1.sendMessage(
                                this.instance.getBasicManager().getMessagePrefix()
                                        + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Not-Enough-Money").replace("&", "§")
                        );
                        return;
                    }

                    Main.getEconomy().withdrawPlayer(var6, (double) var7);
                    var5 = this.instance.getConfig().getInt("GUI.Extend-Player-World.Items." + var3 + ".Length");
                } else {
                    var5 = Integer.valueOf(var3);
                }
            }

            if (var5 != null) {
                long var8 = this.getExpiration(var2) + TimeUnit.DAYS.toMillis((long) var5.intValue());
                this.instance.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var2 + ".Expiration", var8);
                this.instance.getConfigManager().save(ConfigManager.Type.DATA);
                this.instance.getConfigManager().load(ConfigManager.Type.DATA);
                var1.sendMessage(
                        this.instance.getBasicManager().getMessagePrefix()
                                + this.instance
                                .getConfigManager()
                                .get(ConfigManager.Type.MESSAGES)
                                .getString("Messages.Successfully-Extended")
                                .replace("&", "§")
                                .replace("%length%", String.valueOf(var5))
                );
            }
        }
    }

    public void cmdHandleDelete(CommandSender var1, String var2) {
        if (this.worldExists(var2)) {
            String var3 = this.instance.getBasicManager().getWorldPrefix() + var2;
            if (var1 != null && var1 instanceof Player) {
                this.instance.j().b().remove(var1.getName());
            }

            this.instance.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var2, null);
            this.instance.getConfigManager().save(ConfigManager.Type.DATA);
            this.instance.getConfigManager().load(ConfigManager.Type.DATA);
            Bukkit.getScheduler()
                    .runTaskLater(
                            this.instance,
                            () -> {
                                for (String var5 : this.getOwnWorlds(var3)) {
                                    this.c(var5);
                                    if (Bukkit.getWorld(var5) != null && !Bukkit.unloadWorld(var5, true)) {
                                        if (var1 != null) {
                                            var1.sendMessage(
                                                    this.instance.getBasicManager().getMessagePrefix()
                                                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.World-Delete-Error").replace("&", "§")
                                            );
                                        }

                                        return;
                                    }
                                }

                                for (String var11 : this.getOwnWorlds(var3)) {
                                    File var6 = new File(Bukkit.getWorldContainer(), var11);
                                    if (var6.exists()) {
                                        try {
                                            FileUtils.deleteDirectory(var6);
                                        } catch (Throwable var8) {
                                            if (var1 != null) {
                                                var1.sendMessage(
                                                        this.instance.getBasicManager().getMessagePrefix()
                                                                + this.instance
                                                                .getConfigManager()
                                                                .get(ConfigManager.Type.MESSAGES)
                                                                .getString("Messages.World-Delete-Error")
                                                                .replace("&", "§")
                                                );
                                            }

                                            var8.printStackTrace();
                                            return;
                                        }
                                    }
                                }

                                if (var1 != null) {
                                    var1.sendMessage(
                                            this.instance.getBasicManager().getMessagePrefix()
                                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.World-Deleted").replace("&", "§")
                                    );
                                }

                                if (this.instance.getConfig().getBoolean("Commands.Delete.Enabled")) {
                                    for (String var12 : this.instance.getConfig().getStringList("Commands.Delete.List")) {
                                        var12 = var12.replace("%owner%", var2);
                                        var12 = var12.replace("%world%", var3);
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var12);
                                    }
                                }
                            },
                            3L
                    );
        } else if (var1 != null) {
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance
                            .getConfigManager()
                            .get(ConfigManager.Type.MESSAGES)
                            .getString("Messages.Doesnt-Have")
                            .replace("&", "§")
                            .replace("%player%", var2)
            );
        }
    }

    public void moveTo(Player var1, String var2, boolean var3) {
        String var4 = this.instance.getBasicManager().getWorldPrefix() + var2;
        if (!this.worldExists(var2)) {
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance
                            .getConfigManager()
                            .get(ConfigManager.Type.MESSAGES)
                            .getString("Messages.Doesnt-Have")
                            .replace("&", "§")
                            .replace("%player%", var2)
            );
        } else if (this.d(var1, var2)) {
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Ban.Teleport").replace("&", "§")
            );
        } else if (this.ruleAccessibility(var2).equals("PRIVATE") && !this.canVisit(var1, var2)) {
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Access.Only-For-Members").replace("&", "§")
            );
        } else {
            if (Bukkit.getWorld(var4) == null) {
                this.d(var2, false);
            }

            if (Bukkit.getWorld(var4) != null) {
                this.instance.getBasicManager().teleport(var1, this.getSpawn(var2), false);
                if (var3) {
                    var1.sendMessage(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Teleported").replace("&", "§")
                    );
                }
            } else {
                var1.sendMessage(
                        this.instance.getBasicManager().getMessagePrefix()
                                + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Unloaded-World").replace("&", "§")
                );
            }
        }
    }

    public void setSpawn(Player var1, String var2) {
        String var3 = this.instance.getBasicManager().getWorldPrefix() + var2;
        if (var3.equals(var1.getWorld().getName())) {
            double var4 = var1.getLocation().getX();
            double var6 = var1.getLocation().getY();
            double var8 = var1.getLocation().getZ();
            float var10 = var1.getLocation().getYaw();
            float var11 = var1.getLocation().getPitch();
            this.instance
                    .getConfigManager()
                    .get(ConfigManager.Type.DATA)
                    .set("Worlds." + var2 + ".Spawn", var4 + ";" + var6 + ";" + var8 + ";" + var10 + ";" + var11);
            this.instance.getConfigManager().save(ConfigManager.Type.DATA);
            this.instance.getConfigManager().load(ConfigManager.Type.DATA);
            var1.getWorld().getWorldBorder().setCenter(var1.getLocation());
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Spawn-Setup").replace("&", "§")
            );
        } else {
            var1.sendMessage(
                    this.instance.getBasicManager().getMessagePrefix()
                            + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Same-World").replace("&", "§")
            );
        }
    }

    public Location getSpawn(String var1) {
        if (this.worldExists(var1)) {
            String var2 = this.instance.getBasicManager().getWorldPrefix();
            World var3 = Bukkit.getWorld(var2 + var1);
            if (var3 != null) {
                if (this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Spawn")) {
                    String var14 = this.instance.getConfigManager().get(ConfigManager.Type.DATA).getString("Worlds." + var1 + ".Spawn");
                    String[] var5 = var14.split(";");
                    double var6 = Double.parseDouble(var5[0]);
                    double var8 = Double.parseDouble(var5[1]);
                    double var10 = Double.parseDouble(var5[2]);
                    float var12 = Float.parseFloat(var5[3]);
                    float var13 = Float.parseFloat(var5[4]);
                    return new Location(var3, var6, var8, var10, var12, var13);
                }

                Location var4 = var3.getSpawnLocation();
                if (var4.getBlock().isLiquid()) {
                    var4 = var4.getWorld().getHighestBlockAt(var4).getLocation();
                }

                var4.add(0.5, 0.5, 0.5);
                return var4;
            }
        }

        return null;
    }

    public void c(String var1) {
        World var2 = Bukkit.getWorld(var1);
        if (var2 != null) {
            for (Player var4 : var2.getPlayers()) {
                if (this.instance.getBasicManager().hasLobby()) {
                    this.instance.getBasicManager().teleport(var4, this.instance.getBasicManager().getLobby(), true);
                } else {
                    var4.kickPlayer(
                            this.instance.getBasicManager().getMessagePrefix()
                                    + this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Lobby-Is-Not-Configured").replace("&", "§")
                    );
                }
            }
        }
    }

    public boolean worldExists(String var1) {
        return var1 != null ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1) : false;
    }

    public boolean canVisitOp(Player var1, String var2) {
        if (this.worldExists(var2)) {
            return var1.getName().equals(var2) ? true : var1.hasPermission("PlayerWorldsPro.other");
        } else {
            return false;
        }
    }

    public boolean canVisit(Player var1, String var2) {
        if (this.canVisitOp(var1, var2)) {
            return true;
        } else {
            return this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var2 + ".Members")
                    ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getStringList("Worlds." + var2 + ".Members").contains(var1.getName())
                    : false;
        }
    }

    public boolean systemExipreEnabled() {
        return this.instance.getConfig().getBoolean("Expiration.Enabled") && Main.getEconomy() != null;
    }

    public boolean ruleWeather(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".WeatherCycle")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getBoolean("Worlds." + var1 + ".WeatherCycle")
                : true;
    }

    public void ruleWeather(String var1, boolean var2) {
        if (this.worldExists(var1)) {
            this.instance.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var1 + ".WeatherCycle", var2);
            this.instance.getConfigManager().save(ConfigManager.Type.DATA);
            this.instance.getConfigManager().load(ConfigManager.Type.DATA);
        }
    }

    public String ruleDifficulty(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Difficulty")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getString("Worlds." + var1 + ".Difficulty")
                : "NORMAL";
    }

    public boolean ruleBlockBreaking(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Block-Breaking")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getBoolean("Worlds." + var1 + ".Block-Breaking")
                : true;
    }

    public boolean ruleBlockPlacing(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Block-Placing")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getBoolean("Worlds." + var1 + ".Block-Placing")
                : true;
    }

    public boolean rulePvP(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".PvP")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getBoolean("Worlds." + var1 + ".PvP")
                : true;
    }

    public String ruleDefaultGamemode(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".GameMode")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getString("Worlds." + var1 + ".GameMode")
                : "SURVIVAL";
    }

    public boolean rulePickup(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Item-Pickup")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getBoolean("Worlds." + var1 + ".Item-Pickup")
                : true;
    }

    public boolean ruleDrop(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Drop-Item")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getBoolean("Worlds." + var1 + ".Drop-Item")
                : true;
    }

    public boolean ruleDamage(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Player-Damage")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getBoolean("Worlds." + var1 + ".Player-Damage")
                : true;
    }

    public boolean ruleHunger(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Hunger")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getBoolean("Worlds." + var1 + ".Hunger")
                : true;
    }

    public boolean ruleBucket(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Bucket")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getBoolean("Worlds." + var1 + ".Bucket")
                : true;
    }

    public String ruleAccessibility(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Access")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getString("Worlds." + var1 + ".Access")
                : "PUBLIC";
    }

    public Long getExpiration(String var1) {
        return this.worldExists(var1) && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var1 + ".Expiration")
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getLong("Worlds." + var1 + ".Expiration")
                : 0L;
    }

    public String formatExpiration(String var1) {
        Long var2 = this.getExpiration(var1);
        if (var2 > 0L) {
            try {
                SimpleDateFormat var3 = new SimpleDateFormat(this.instance.getConfig().getString("Expiration.Format"));
                Date var4 = new Date(var2);
                return var3.format(var4);
            } catch (IllegalArgumentException var5) {
                return "Error - Invalid Expiration Format";
            }
        } else {
            return "-";
        }
    }

    public void c() {
        Bukkit.getScheduler().runTaskTimer(this.instance, () -> {
            if (this.systemExipreEnabled() && this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds")) {
                for (String var2 : this.instance.getConfigManager().get(ConfigManager.Type.DATA).getConfigurationSection("Worlds").getKeys(false)) {
                    if (this.getExpiration(var2) < System.currentTimeMillis()) {
                        this.cmdHandleDelete(null, var2);
                    }
                }
            }
        }, 60L, 1200L);
    }

    public boolean d(Player var1, String var2) {
        return !this.canVisitOp(var1, var2)
                ? this.instance.getConfigManager().get(ConfigManager.Type.DATA).getStringList("Worlds." + var2 + ".Banned").contains(var1.getName())
                : false;
    }

    public int worldCount() {
        int var1 = 0;
        if (this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds")) {
            var1 = this.instance.getConfigManager().get(ConfigManager.Type.DATA).getConfigurationSection("Worlds").getKeys(false).size();
        }

        return var1;
    }

    public int getWorldPlayers() {
        int var1 = 0;
        if (this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds")) {
            for (String var3 : this.instance.getConfigManager().get(ConfigManager.Type.DATA).getConfigurationSection("Worlds").getKeys(false)) {
                var1 += this.MAYBE_GetPlayerNum(this.instance.getBasicManager().getWorldPrefix() + var3, false);
            }
        }

        return var1;
    }

    public static enum Generator {
        NORMAL,
        FLAT,
        EMPTY,
        CUSTOM;
    }
}
