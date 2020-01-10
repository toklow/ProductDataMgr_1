package enums;

import iface.ColorOperations;

import java.awt.*;

public enum Colors implements ColorOperations {
    RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255), YELLOW(255, 204, 0);

    private int red;
    private int green;
    private int blue;

    Colors(int red, int green, int blue) {
        this.red=red;
        this.green=green;
        this.blue=blue;
    }

    @Override
    public String getHexColor() {
        return "#"+Integer.toHexString(getRGB());
    }

    @Override
    public int getRGB() {
        return new Color(red, green, blue).getRGB();
    }

}
