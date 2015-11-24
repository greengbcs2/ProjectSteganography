package tr.edu.atilim.ulas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;

public class MyImage {
	private BufferedImage image;
 
	private ImageRGB imageRGB;
	private Color color;

	public MyImage(BufferedImage image) {
		this.image = image;
	}
	public MyImage(int r, int g, int b) {
		
	}

	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public int getWidth() {
		return image.getWidth();
	}
 
	public int getHeight() {
		return image.getHeight();
	}
 
	public ImageRGB getImageRGB() {
		return imageRGB;
	}
	public void setImageRGB(ImageRGB imageRGB) {
		this.imageRGB = imageRGB;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int[][] convertImageTo2DArray() {
		int width = image.getWidth();
		int height = image.getHeight();
		int[][] imageOfArray = new int[width][height];

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				imageOfArray[i][j] = image.getRGB(i, j);
		return imageOfArray;

	}

	public int[] convertToPixels() {

		int[] pixel = new int[getWidth() * getHeight()];

		PixelGrabber pg = new PixelGrabber(image, 0, 0, getWidth(), getHeight(), pixel,
				0, getWidth());
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			throw new IllegalStateException(
					"Error: Interrupted Waiting for Pixels");
		}
		if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
			throw new IllegalStateException("Error: MyImage Fetch Aborted");
		}
		return pixel;

	}

	public int getRedValue(int x, int y) {
		color = new Color(image.getRGB(x, y));
		return color.getRed();

	}

	public int getGreenValue(int x, int y) {
		color = new Color(image.getRGB(x, y));
		return color.getGreen();

	}

	public int getBlueValue(int x, int y) {
		color = new Color(image.getRGB(x, y));
		return color.getBlue();

	}
}
