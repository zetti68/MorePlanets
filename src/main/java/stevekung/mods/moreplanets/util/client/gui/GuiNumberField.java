package stevekung.mods.moreplanets.util.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.NumberUtil;

@SideOnly(Side.CLIENT)
public class GuiNumberField extends GuiTextField
{
    public GuiNumberField(int id, FontRenderer font, int x, int y, int width, int height)
    {
        super(id, font, x, y, width, height);
    }

    @Override
    public void writeText(String textToWrite)
    {
        for (int i = 0; i < textToWrite.length(); i++)
        {
            if (NumberUtil.isNumber(textToWrite.charAt(i)))
            {
                super.writeText(textToWrite);
            }
        }
    }
}