package stevekung.mods.moreplanets.module.planets.nibiru.world.gen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.*;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.*;
import stevekung.mods.moreplanets.util.helper.DecorateHelper;
import stevekung.mods.moreplanets.util.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenFlowersMP;

public class BiomeDecoratorNibiru extends BiomeDecoratorMP
{
    public int infectedTallGrassPerChunk;
    public int infectedFernPerChunk;
    public int infectedCactusPerChunk;
    public int deadBushPerChunk;
    public int infectedTreesPerChunk;
    public int pureHurbPerChunk;
    public int terrapuffHurbPerChunk;
    public int greenVeinTallGrassPerChunk;
    public int orangeBushPerChunk;
    public int batasiaDandelionPerChunk;
    public int pyoloniaPerChunk;
    public int philipyPerChunk;
    public int whiteTailPerChunk;
    public int vealiumVinePerChunk;
    public int seaweedPerChunk;

    @Override
    protected void generate(Biome biome, World world, Random rand)
    {
        int i;

        for (i = 0; i < this.infectedTallGrassPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_TALL_GRASS.getDefaultState().withProperty(BlockNibiruTallGrass.VARIANT, BlockNibiruTallGrass.BlockType.INFECTED_TALL_GRASS)), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.pureHurbPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.PURE_HURB)), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.terrapuffHurbPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.TERRAPUFF_HURB)), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.batasiaDandelionPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.BATASIA_DANDELION)), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.pyoloniaPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.PYOLONIA_FLOWER)), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.philipyPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.PHILIPY_FLOWER)), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.whiteTailPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.WHITE_TAIL)), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.vealiumVinePerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.VEALIUM_VINE_FLOWER)), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.seaweedPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenNibiruSeaweed(NibiruBlocks.NIBIRU_SEAWEED.getDefaultState().withProperty(BlockNibiruSeaweed.VARIANT, BlockNibiruSeaweed.BlockType.NIBIRU_SEAWEED)), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.infectedCactusPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenInfectedCactus(), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.deadBushPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenDeadBush(), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.infectedFernPerChunk; ++i)
        {
            DecorateHelper.generatePlants(biome.getRandomWorldGenForGrass(rand), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.greenVeinTallGrassPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_TALL_GRASS.getDefaultState().withProperty(BlockNibiruTallGrass.VARIANT, BlockNibiruTallGrass.BlockType.GREEN_VEIN_TALL_GRASS)), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.waterlilyPerChunk; ++i)
        {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y = world.getHeight(this.chunkPos.add(x, 0, z)).getY() * 2;

            if (y > 0)
            {
                int y1 = rand.nextInt(y);
                BlockPos blockpos4;
                BlockPos blockpos7;

                for (blockpos4 = this.chunkPos.add(x, y1, z); blockpos4.getY() > 0; blockpos4 = blockpos7)
                {
                    blockpos7 = blockpos4.down();

                    if (!world.isAirBlock(blockpos7))
                    {
                        break;
                    }
                }
                new WorldGenSporelily().generate(world, rand, blockpos4);
            }
        }
        for (i = 0; i < this.clayPerChunk; ++i)
        {
            int l1 = rand.nextInt(16) + 8;
            int i6 = rand.nextInt(16) + 8;
            new WorldGenNibiruClay(4).generate(world, rand, world.getTopSolidOrLiquidBlock(this.chunkPos.add(l1, 0, i6)));
        }
        for (i = 0; i < this.reedsPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenInfectedSugarCane(), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.sandPerChunk2; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            new WorldGenNibiruSand(NibiruBlocks.INFECTED_SAND.getDefaultState(), 7).generate(world, rand, world.getTopSolidOrLiquidBlock(this.chunkPos.add(j, 0, k)));
        }
        for (i = 0; i < this.sandPerChunk; ++i)
        {
            int i2 = rand.nextInt(16) + 8;
            int j6 = rand.nextInt(16) + 8;
            new WorldGenNibiruSand(NibiruBlocks.INFECTED_GRAVEL.getDefaultState(), 6).generate(world, rand, world.getTopSolidOrLiquidBlock(this.chunkPos.add(i2, 0, j6)));
        }

        int treesPerChunk = this.infectedTreesPerChunk;

        if (rand.nextInt(10) == 0)
        {
            ++treesPerChunk;
        }

        for (i = 0; i < treesPerChunk; ++i)
        {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = world.getHeight(this.chunkPos.add(k6, 0, l));
            WorldGenAbstractTree worldgenabstracttree = biome.genBigTreeChance(rand);

            if (worldgenabstracttree.generate(world, rand, blockpos))
            {
                worldgenabstracttree.generateSaplings(world, rand, blockpos);
            }
        }

        if (rand.nextInt(1000) == 0)
        {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            new WorldGenCrystalObelisk().generate(world, rand, world.getTopSolidOrLiquidBlock(this.chunkPos.add(x, 0, z)));
        }

        if (this.orangeBushPerChunk > 0)
        {
            int roseBushPerChunk = rand.nextInt(5) - this.orangeBushPerChunk;

            for (i = 0; i < roseBushPerChunk; ++i)
            {
                for (int i2 = 0; i2 < 5; ++i2)
                {
                    int y = rand.nextInt(world.getHeight(this.chunkPos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8)).getY() + 32);

                    if (new WorldGenNibiruDoublePlant(BlockNibiruDoublePlant.BlockType.INFECTED_ORANGE_ROSE_BUSH).generate(world, rand, new BlockPos(this.chunkPos.getX() + rand.nextInt(16) + 8, y, this.chunkPos.getZ() + rand.nextInt(16) + 8)))
                    {
                        break;
                    }
                }
            }
        }

        for (i = 0; i < 4; i++)
        {
            new WorldGenMultalicCrystal().generate(world, rand, this.chunkPos.add(rand.nextInt(16) + 8, rand.nextInt(36), rand.nextInt(16) + 8));
        }
    }
}