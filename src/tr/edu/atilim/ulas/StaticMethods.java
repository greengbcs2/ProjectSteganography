package tr.edu.atilim.ulas;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StaticMethods {
	
	public static int[] convertToPixels(Image img) {
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		int[] pixel = new int[width * height];

		PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, pixel, 0,
				width);
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

	public static int[][] convertImageTo2DArray(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		int[][] imageOfArray = new int[width][height];

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				imageOfArray[i][j] = image.getRGB(i, j);
		return imageOfArray;

	}

	public static String convertStringToAsciiBinary(String s) {
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			binary.append(' ');
		}
		return binary.toString();
	}

	public static String convertIntToBinary(int value) {
		String output = Integer.toBinaryString(value);
		int tempSize=output.length();
		if (output.length() < 8) {
			//System.out.println("1th" + output.length());
			for (int i = 0; i < 8 - tempSize; i++) {
				output = "0" + output;
				//System.out.println("cont" + output.length());
			}
			//System.out.println("2th" + output.length());
		}

		return output;// .format("%8s", Integer.toBinaryString(1)).replace(' ',
						// '0');

	}

	private static int convertBinaryToInt(String binaryValue) {
		return Integer.parseInt(binaryValue, 2);
	}

	public static void getRedBlueGreen(BufferedImage image, int red, int blue,
			int green, int x, int y) {
		Color c = new Color(image.getRGB(x, y));
		red = c.getRed();
		green = c.getGreen();
		blue = c.getBlue();
	}

	public static void encodeRGB(String r, String g, String b, String string) {
		char[] rArray = r.toCharArray();
		char[] gArray = g.toCharArray();
		char[] bArray = b.toCharArray();
		char[] sArray = string.toCharArray();
		rArray[5] = sArray[0];
		rArray[6] = sArray[1];
		rArray[7] = sArray[2];
		gArray[5] = sArray[3];
		gArray[6] = sArray[4];
		gArray[7] = sArray[5];
		bArray[6] = sArray[6];
		bArray[7] = sArray[7];

	}

	public static void stringToStringArray(String text, char[] charArray) {
		String str = text;
		charArray = str.toCharArray();

	}
	public static void creatFile(String path,String fileName, RenderedImage mImage) {
		try {
			File outputFile = new File(path+fileName);
			ImageIO.write(mImage, "bmp", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
