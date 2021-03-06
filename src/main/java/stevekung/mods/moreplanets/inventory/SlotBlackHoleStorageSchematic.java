package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;

public class SlotBlackHoleStorageSchematic extends Slot
{
    private int index;
    private BlockPos pos;
    private EntityPlayer player;

    public SlotBlackHoleStorageSchematic(IInventory inventory, int index, int x, int y, BlockPos pos, EntityPlayer player)
    {
        super(inventory, index, x, y);
        this.index = index;
        this.pos = pos;
        this.player = player;
    }

    @Override
    public void onSlotChanged()
    {
        if (this.player instanceof EntityPlayerMP)
        {
            int dimID = GCCoreUtil.getDimensionID(this.player.world);
            GCCoreUtil.sendToAllAround(new PacketSimple(EnumSimplePacket.C_SPAWN_SPARK_PARTICLES, dimID, new Object[] { this.pos }), this.player.world, dimID, this.pos, 20);
        }
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        switch (this.index)
        {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
            return itemStack.getItem() == DionaItems.DIONA_ITEM && itemStack.getItemDamage() == 7;
        case 10:
        case 11:
        case 12:
            return itemStack.getItem() == GCItems.flagPole;
        case 13:
        case 15:
        case 17:
        case 18:
        case 22:
            return itemStack.getItem() == DionaItems.DIONA_ITEM && itemStack.getItemDamage() == 6;
        case 14:
        case 16:
        case 19:
        case 21:
            return itemStack.getItem() == DionaItems.TIER_4_ROCKET_PART && itemStack.getItemDamage() == 0;
        case 20:
            return itemStack.getItem() == Items.ENDER_EYE;
        }
        return false;
    }

    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }
}