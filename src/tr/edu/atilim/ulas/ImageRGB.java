package tr.edu.atilim.ulas;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageRGB {

	private BufferedImage bufferedImage;
	private Color color;

	public ImageRGB(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;

	}

	public int getRedValue(int x, int y) {
		color = new Color(bufferedImage.getRGB(x, y));
		return color.getRed();

	}

	public int getGreenValue(int x, int y) {
		color = new Color(bufferedImage.getRGB(x, y));
		return color.getGreen();

	}

	public int getBlueValue(int x, int y) {
		color = new Color(bufferedImage.getRGB(x, y));
		return color.getBlue();

	}

}
