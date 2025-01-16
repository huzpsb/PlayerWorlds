package cz._heropwp.playerworldspro;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.player.PlayerPortalEvent;

public class OldMethods {
   public void handlePortle(PlayerPortalEvent var1) {
      var1.useTravelAgent(true);
      var1.getPortalTravelAgent().setCanCreatePortal(true);
   }

   public void setSpawn(World var1, Location var2) {
      var1.setSpawnLocation((int)var2.getX(), (int)var2.getY(), (int)var2.getZ());
   }
}
