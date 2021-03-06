package stevekung.mods.moreplanets.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox.ICheckBoxCallback;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.inventory.ContainerDarkEnergyReceiver;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.util.client.gui.GuiContainerMP;
import stevekung.mods.moreplanets.util.client.gui.GuiElementInfoRegionMP;

public class GuiDarkEnergyReceiver extends GuiContainerMP implements ICheckBoxCallback
{
    private ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/dark_energy_receiver.png");
    private TileEntityDarkEnergyReceiver tile;
    private GuiElementInfoRegionMP electricInfoRegion;
    private GuiButton buttonEnable;
    private GuiElementCheckbox checkboxRender;

    public GuiDarkEnergyReceiver(InventoryPlayer inv, TileEntityDarkEnergyReceiver tile)
    {
        super(new ContainerDarkEnergyReceiver(inv, tile));
        this.tile = tile;
        this.ySize = 180;
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
        case 0:
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionID(this.tile.getWorld()), new Object[] { this.tile.getPos(), 0 }));
            break;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        List<String> batterySlotDesc = new ArrayList<>();
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.0"));
        batterySlotDesc.add(GCCoreUtil.translate("gui.battery_slot.desc.1"));
        this.infoRegions.add(new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 31, (this.height - this.ySize) / 2 + 26, 18, 18, batterySlotDesc, this.width, this.height, this));

        if (!this.tile.successful && !this.tile.activated)
        {
            this.infoRegions.add(new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 155, (this.height - this.ySize) / 2 + 87, 13, 13, Arrays.asList(GCCoreUtil.translate("gui.multiblock_guide.desc")), this.width, this.height, this));
        }

        this.electricInfoRegion = new GuiElementInfoRegionMP((this.width - this.xSize) / 2 + 107, (this.height - this.ySize) / 2 + 31, 56, 9, new ArrayList<>(), this.width, this.height, this);
        this.infoRegions.add(this.electricInfoRegion);
        this.buttonList.add(this.buttonEnable = new GuiButton(0, this.width / 2 - 36, this.height / 2 - 19, 72, 20, !this.tile.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name")));
        this.buttonEnable.enabled = this.tile.disableCooldown == 0 && !this.tile.successful;
        int width = (this.width - this.xSize) / 2;
        int height = (this.height - this.ySize) / 2;
        this.checkboxRender = new GuiElementCheckbox(1, this, width + 155, height + 87, "");
        this.checkboxRender.visible = !this.tile.successful && !this.tile.activated;
        this.buttonList.add(this.checkboxRender);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.buttonEnable.enabled = this.tile.disableCooldown == 0 && !this.tile.successful;
        this.buttonEnable.displayString = !this.tile.getDisabled(0) ? GCCoreUtil.translate("gui.button.disable.name") : GCCoreUtil.translate("gui.button.enable.name");
        this.fontRendererObj.drawString(this.tile.getName(), 8, 10, 4210752);
        GCCoreUtil.drawStringCentered(GCCoreUtil.translate("gui.message.status.name") + ": " + this.tile.getGUIStatus(), this.xSize / 2, 50, 4210752, this.fontRendererObj);
        GCCoreUtil.drawStringCentered(GCCoreUtil.translate("gui.status.machine_status.name") + ": " + this.getStatus(), this.xSize / 2, 60, 4210752, this.fontRendererObj);
        this.fontRendererObj.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 90 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(this.texture);
        int width = (this.width - this.xSize) / 2;
        int height = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(width, height + 5, 0, 0, this.xSize, 181);
        int scale = this.tile.getScaledElecticalLevel(54);
        this.drawTexturedModalRect(width + 107, height + 31, 197, 0, Math.min(scale, 54), 7);

        if (this.tile.getEnergyStoredGC() > 0)
        {
            this.drawTexturedModalRect(width + 93, height + 30, 176, 0, 11, 10);
        }
        if (this.tile.activated && !this.tile.successful)
        {
            this.drawTexturedModalRect(width + 64, height + 16, 176, 10, 16, 16);
        }
        List<String> electricityDesc = new ArrayList<>();
        electricityDesc.add(GCCoreUtil.translate("gui.energy_storage.desc.0"));
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.tile.getEnergyStoredGC(), this.tile.getMaxEnergyStoredGC(), electricityDesc);
        this.electricInfoRegion.tooltipStrings = electricityDesc;
        this.checkboxRender.isSelected = ClientEventHandler.receiverRenderPos.contains(this.tile.getPos());
    }

    @Override
    public void onSelectionChanged(GuiElementCheckbox checkbox, boolean newSelected)
    {
        if (ClientEventHandler.receiverRenderPos.contains(this.tile.getPos()))
        {
            ClientEventHandler.receiverRenderPos.remove(this.tile.getPos());
        }
        else
        {
            ClientEventHandler.receiverRenderPos.add(this.tile.getPos());
        }
    }

    @Override
    public boolean canPlayerEdit(GuiElementCheckbox checkbox, EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean getInitiallySelected(GuiElementCheckbox checkbox)
    {
        return ClientEventHandler.receiverRenderPos.contains(this.tile.getPos());
    }

    @Override
    public void onIntruderInteraction() {}

    private String getStatus()
    {
        if (this.tile.activated && !this.tile.successful)
        {
            if (this.tile.activatedTick > 0 && this.tile.activatedTick < this.tile.getSuccessfulTick())
            {
                return GCCoreUtil.translateWithFormat("gui.status.dark_energy_percent0.name", this.tile.activatedTick * 100 / this.tile.getSuccessfulTick()) + GCCoreUtil.translate("gui.status.dark_energy_percent1.name");
            }
        }
        if (this.tile.successful)
        {
            return TextFormatting.DARK_GREEN + GCCoreUtil.translate("gui.status.dark_energy_core_created.name");
        }
        return this.tile.getEnergyStoredGC() > 0.0F ? TextFormatting.DARK_GREEN + GCCoreUtil.translate("gui.status.ready.name") : TextFormatting.DARK_RED + GCCoreUtil.translate("gui.status.dark_energy_offline.name");
    }
}