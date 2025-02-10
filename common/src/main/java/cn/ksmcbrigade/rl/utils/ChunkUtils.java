package cn.ksmcbrigade.rl.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.Objects;
import java.util.stream.Stream;

public class ChunkUtils {

    public static Stream<BlockEntity> getLoadedBlockEntities()
    {
        return getLoadedChunks().flatMap(chunk -> chunk.getBlockEntities().values().stream());
    }

    public static Stream<LevelChunk> getLoadedChunks()
    {
        int radius = Math.max(2, Minecraft.getInstance().options.getEffectiveRenderDistance()) + 3;
        int diameter = radius * 2 + 1;

        ChunkPos center = Minecraft.getInstance().player.chunkPosition();
        ChunkPos min = new ChunkPos(center.x - radius, center.z - radius);
        ChunkPos max = new ChunkPos(center.x + radius, center.z + radius);

        Stream<LevelChunk> stream = Stream.iterate(min, pos -> {

                    int x = pos.x;
                    int z = pos.z;

                    x++;

                    if(x > max.x)
                    {
                        x = min.x;
                        z++;
                    }

                    if(z > max.z)
                        throw new IllegalStateException("Stream limit didn't work.");

                    return new ChunkPos(x, z);

                }).limit(diameter * diameter)
                .filter(c -> Minecraft.getInstance().level.hasChunk(c.x, c.z))
                .map(c -> Minecraft.getInstance().level.getChunk(c.x, c.z)).filter(Objects::nonNull);

        return stream;
    }
}
