package cz._heropwp.playerworldspro;

import cz._heropwp.playerworldspro.api.API;
import cz._heropwp.playerworldspro.command.PlayerWoldsPro;
import cz._heropwp.playerworldspro.event.BasicEvents;
import cz._heropwp.playerworldspro.event.SettingsEvents;
import cz._heropwp.playerworldspro.gui.*;
import cz._heropwp.playerworldspro.util.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private API api;
    private PlayerWoldsPro command;
    private GUI_Buy_PlayerWorld c;
    private GUI_CreateWorld d;
    private GUI_CustomGenerators e;
    private GUI_Extend_PlayerWorld f;
    private GUI_Main g;
    private GUI_PreBuiltMaps h;
    private GUI_Settings i;
    private GUI_Settings_AddMember j;
    private GUI_Settings_BanPlayer k;
    private GUI_Settings_ChangeDefaultGameMode l;
    private GUI_Settings_ChangeDifficulty m;
    private GUI_Settings_ChangeGameMode n;
    private GUI_Settings_ChangeGameMode_SelectPlayer o;
    private GUI_Settings_ChangeTime p;
    private GUI_Settings_ChangeWeather q;
    private GUI_Settings_Fly r;
    private GUI_Settings_KickPlayer s;
    private GUI_Settings_RemoveMember t;
    private GUI_Settings_Teleport u;
    private GUI_Settings_TeleportHere v;
    private GUI_Settings_UnbanPlayer w;
    private GUI_Settings_WorldBorder x;
    private BasicEvents y;
    private SettingsEvents z;
    private BasicManager basicManager;
    private ConfigManager configManager;
    private MaterialManager materialManager;
    private WorldManager worldManager;
    private static Economy economy = null;

    public void onEnable() {
        if (this.isEnabled()) {
            this.saveDefaultConfig();
            this.configManager = new ConfigManager(this);
            this.configManager.init();
            this.api = new API(this);
            this.command = new PlayerWoldsPro(this);
            this.c = new GUI_Buy_PlayerWorld(this);
            this.d = new GUI_CreateWorld(this);
            this.e = new GUI_CustomGenerators(this);
            this.f = new GUI_Extend_PlayerWorld(this);
            this.g = new GUI_Main(this);
            this.h = new GUI_PreBuiltMaps(this);
            this.i = new GUI_Settings(this);
            this.j = new GUI_Settings_AddMember(this);
            this.k = new GUI_Settings_BanPlayer(this);
            this.l = new GUI_Settings_ChangeDefaultGameMode(this);
            this.m = new GUI_Settings_ChangeDifficulty(this);
            this.n = new GUI_Settings_ChangeGameMode(this);
            this.o = new GUI_Settings_ChangeGameMode_SelectPlayer(this);
            this.p = new GUI_Settings_ChangeTime(this);
            this.q = new GUI_Settings_ChangeWeather(this);
            this.r = new GUI_Settings_Fly(this);
            this.s = new GUI_Settings_KickPlayer(this);
            this.t = new GUI_Settings_RemoveMember(this);
            this.u = new GUI_Settings_Teleport(this);
            this.v = new GUI_Settings_TeleportHere(this);
            this.w = new GUI_Settings_UnbanPlayer(this);
            this.x = new GUI_Settings_WorldBorder(this);
            this.y = new BasicEvents(this);
            this.z = new SettingsEvents(this);
            this.basicManager = new BasicManager(this);
            this.materialManager = new MaterialManager();
            this.worldManager = new WorldManager(this);
            this.registerCommand();
            this.registerGUI();
            this.worldManager.loadOnStartup();
            this.setupEconomy();
            this.worldManager.c();
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                new PlaceholderHook(this).register();
            }
            this.checkForUpdate();
        }
    }

    private void registerCommand() {
        this.getCommand("PlayerWorldsPro").setExecutor(this.command);
    }

    private void registerGUI() {
        PluginManager var1 = Bukkit.getPluginManager();
        var1.registerEvents(this.c, this);
        var1.registerEvents(this.d, this);
        var1.registerEvents(this.e, this);
        var1.registerEvents(this.f, this);
        var1.registerEvents(this.g, this);
        var1.registerEvents(this.h, this);
        var1.registerEvents(this.i, this);
        var1.registerEvents(this.j, this);
        var1.registerEvents(this.k, this);
        var1.registerEvents(this.l, this);
        var1.registerEvents(this.m, this);
        var1.registerEvents(this.n, this);
        var1.registerEvents(this.o, this);
        var1.registerEvents(this.p, this);
        var1.registerEvents(this.q, this);
        var1.registerEvents(this.r, this);
        var1.registerEvents(this.s, this);
        var1.registerEvents(this.t, this);
        var1.registerEvents(this.u, this);
        var1.registerEvents(this.v, this);
        var1.registerEvents(this.w, this);
        var1.registerEvents(this.x, this);
        var1.registerEvents(this.y, this);
        var1.registerEvents(this.z, this);
    }

    private boolean setupEconomy() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        } else {
            RegisteredServiceProvider var1 = this.getServer().getServicesManager().getRegistration(Economy.class);
            if (var1 == null) {
                return false;
            } else {
                economy = (Economy) var1.getProvider();
                return economy != null;
            }
        }
    }

    public static Economy getEconomy() {
        return economy;
    }

    private void checkForUpdate() {
        if (this.getConfig().getBoolean("Basic.Check-For-Updates")) {
            this.getLogger().info("Checking for Updates...");
            Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            });
        }
    }

    public API b() {
        return this.api;
    }

    public PlayerWoldsPro c() {
        return this.command;
    }

    public GUI_Buy_PlayerWorld d() {
        return this.c;
    }

    public GUI_CreateWorld e() {
        return this.d;
    }

    public GUI_CustomGenerators f() {
        return this.e;
    }

    public GUI_Extend_PlayerWorld g() {
        return this.f;
    }

    public GUI_Main h() {
        return this.g;
    }

    public GUI_PreBuiltMaps i() {
        return this.h;
    }

    public GUI_Settings j() {
        return this.i;
    }

    public GUI_Settings_AddMember k() {
        return this.j;
    }

    public GUI_Settings_BanPlayer l() {
        return this.k;
    }

    public GUI_Settings_ChangeDefaultGameMode m() {
        return this.l;
    }

    public GUI_Settings_ChangeDifficulty n() {
        return this.m;
    }

    public GUI_Settings_ChangeGameMode o() {
        return this.n;
    }

    public GUI_Settings_ChangeGameMode_SelectPlayer p() {
        return this.o;
    }

    public GUI_Settings_ChangeTime q() {
        return this.p;
    }

    public GUI_Settings_ChangeWeather r() {
        return this.q;
    }

    public GUI_Settings_Fly s() {
        return this.r;
    }

    public GUI_Settings_KickPlayer t() {
        return this.s;
    }

    public GUI_Settings_RemoveMember u() {
        return this.t;
    }

    public GUI_Settings_Teleport v() {
        return this.u;
    }

    public GUI_Settings_TeleportHere w() {
        return this.v;
    }

    public GUI_Settings_UnbanPlayer x() {
        return this.w;
    }

    public GUI_Settings_WorldBorder y() {
        return this.x;
    }

    public BasicEvents z() {
        return this.y;
    }

    public SettingsEvents A() {
        return this.z;
    }

    public BasicManager getBasicManager() {
        return this.basicManager;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public MaterialManager getMaterialManager() {
        return this.materialManager;
    }

    public WorldManager getWorldManager() {
        return this.worldManager;
    }
}
