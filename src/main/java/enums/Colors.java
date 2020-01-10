package enums;

import iface.ColorOperations;

import java.awt.*;

public enum Colors implements ColorOperations {
    BLACK(0X000000),
    WHITE(0XFFFFFF),
    RED(0XFF0000),
    GREEN(0X008000),
    BLUE(0X0000FF),
    YELLOW(0XFFFF00);

    int colorRGB;


    Colors(int colorRGB) {
        this.colorRGB = colorRGB;
    }

    @Override
    public String getHexColor() {
        return "#" + Integer.toHexString(getRGB());
    }


    @Override
    public int getRGB() {
        return new Color(colorRGB).getRGB();
    }

}
