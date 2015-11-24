package tr.edu.atilim.ulas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EncodeDecode {
	MyImage myImage;
	String hiddenText;
	MyImage stegoImage;
	StaticMethods staticMethods;
	int[][] arrayImage;
	String hiddenLetters[];
	private static String path = "/home/efraat/Desktop/";

	public EncodeDecode(BufferedImage image, String hiddenText) {
		this.myImage = new MyImage(image);
		this.hiddenText = hiddenText;
		this.stegoImage = new MyImage(255, 255, 255);
		createHiddenLetters(hiddenText);
	}

	private void createHiddenLetters(String hiddenText2) {
		char[] sArray = hiddenText2.toCharArray();
		hiddenLetters = new String[sArray.length];
		for (int i = 0; i < hiddenText2.length(); i++) {
			hiddenLetters[i] = StaticMethods
					.convertIntToBinary((int) sArray[i]);
		}
	}

	public MyImage run() {
		encodeImage();
		return getStegoImage();
	}

	public void encodeImage() {
		int h = myImage.getHeight();
		int w = myImage.getWidth();
		int[] rgb = new int[3];
		String redBinayString;
		String greenBinayString;
		String blueBinayString;
		BufferedImage mImage = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				redBinayString = StaticMethods.convertIntToBinary(myImage
						.getRedValue(i, j));
				greenBinayString = StaticMethods.convertIntToBinary(myImage
						.getGreenValue(i, j));
				blueBinayString = StaticMethods.convertIntToBinary(myImage
						.getBlueValue(i, j));
				addHiddenText(redBinayString, greenBinayString,
						blueBinayString, hiddenText, rgb);
				int rgbX = rgb[0];
				rgbX = (rgbX << 8) + rgb[1];
				rgbX = (rgbX << 8) + rgb[2];
				mImage.setRGB(i, j, rgbX);
			}
		}
		StaticMethods.creatFile(path, "s3.bmp", mImage);
	}

	public void decodeImage() {

	}

	public MyImage getMyImage() {
		return myImage;
	}

	public String getHiddenText() {
		return hiddenText;
	}

	public MyImage getStegoImage() {
		return stegoImage;
	}

	public int[] addHiddenText(String r, String g, String b, String string,
			int[] rgb) {
		char[] rArray = r.toCharArray();
		char[] gArray = g.toCharArray();
		char[] bArray = b.toCharArray();
		char[] sArray = hiddenLetters[0].toCharArray();
		try {
			rArray[5] = sArray[0];
			rArray[6] = sArray[1];
			rArray[7] = sArray[2];
			gArray[5] = sArray[3];
			gArray[6] = sArray[4];
			gArray[7] = sArray[5];
			bArray[6] = sArray[6];
			bArray[7] = sArray[7];
			rgb[0] = Integer.parseInt(String.valueOf(rArray), 2);
			rgb[1] = Integer.parseInt(String.valueOf(gArray), 2);
			rgb[2] = Integer.parseInt(String.valueOf(bArray), 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rgb;
	}

}
