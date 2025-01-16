package cz._heropwp.playerworldspro.util;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.ChunkGenerator.BiomeGrid;
import org.bukkit.generator.ChunkGenerator.ChunkData;

public class EmptyChunkGenerator extends ChunkGenerator {
   public ChunkData generateChunkData(World var1, Random var2, int var3, int var4, BiomeGrid var5) {
      return this.createChunkData(var1);
   }
}
