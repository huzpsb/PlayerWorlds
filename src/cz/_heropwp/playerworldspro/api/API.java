package cz._heropwp.playerworldspro.api;

import cz._heropwp.playerworldspro.Main;
import org.bukkit.entity.Player;

public class API {
   private static Main main;

   public API(Main var1) {
      main = var1;
   }

   public static boolean hasPlayerWorld(String var0) {
      return main.getWorldManager().worldExists(var0);
   }

   public static String getPlayerWorldOwner(String var0) {
      String var1 = main.getBasicManager().getOwnerFromWorldName(var0);
      return main.getWorldManager().worldExists(var1) ? var1 : null;
   }

   public static void loadPlayerWorld(String var0) {
      String var1 = main.getBasicManager().getOwnerFromWorldName(var0);
      if (main.getWorldManager().worldExists(var1)) {
         String var2 = main.getBasicManager().getWorldPrefix();
         if (var0.endsWith("_nether")) {
            main.getWorldManager().ensureWorldExists(var2 + var1 + "_nether", true);
         } else if (var0.endsWith("_the_end")) {
            main.getWorldManager().c(var2 + var1 + "_the_end", true);
         } else {
            main.getWorldManager().d(var1, false);
         }
      }
   }

   public static boolean isMember(Player var0, String var1) {
      return main.getWorldManager().canVisit(var0, var1);
   }

   public static boolean isExpirationEnabled() {
      return main.getWorldManager().systemExipreEnabled();
   }

   public static boolean isWeatherCycle(String var0) {
      return main.getWorldManager().ruleWeather(var0);
   }

   public static String getDifficulty(String var0) {
      return main.getWorldManager().ruleDifficulty(var0);
   }

   public static boolean isBlockBreaking(String var0) {
      return main.getWorldManager().ruleBlockBreaking(var0);
   }

   public static boolean isBlockPlacing(String var0) {
      return main.getWorldManager().ruleBlockPlacing(var0);
   }

   public static boolean isPvP(String var0) {
      return main.getWorldManager().rulePvP(var0);
   }

   public static String getGameMode(String var0) {
      return main.getWorldManager().ruleDefaultGamemode(var0);
   }

   public static boolean isItemPickup(String var0) {
      return main.getWorldManager().rulePickup(var0);
   }

   public static boolean isDropItem(String var0) {
      return main.getWorldManager().ruleDrop(var0);
   }

   public static boolean isDamage(String var0) {
      return main.getWorldManager().ruleDamage(var0);
   }

   public static boolean isHunger(String var0) {
      return main.getWorldManager().ruleHunger(var0);
   }

   public static String getAccess(String var0) {
      return main.getWorldManager().ruleAccessibility(var0);
   }

   public static Long getExpiration(String var0) {
      return main.getWorldManager().getExpiration(var0);
   }

   public static String getExpirationDate(String var0) {
      return main.getWorldManager().formatExpiration(var0);
   }

   public static void deletePlayerWorld(String var0) {
      main.getWorldManager().cmdHandleDelete(null, var0);
   }
}
