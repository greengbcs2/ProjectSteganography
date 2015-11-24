package tr.edu.atilim.ulas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestClass {

	public static void main(String[] args) throws IOException {
		BufferedImage img = ImageIO.read(new File("/home/efraat/Desktop/sb.jpg"));
		String testString = "UlasOzanCEYHAN";
		EncodeDecode encodeDecode=new EncodeDecode(img, testString);
		encodeDecode.run();

	}
	private void test(){
		BufferedImage img = null;
		String testString = "UlasOzanCEYHAN";

		int[][] testArr;
		try {
			img = ImageIO.read(new File("/home/efraat/Desktop/sb.jpg"));
			testArr = StaticMethods.convertImageTo2DArray(img);

			ImageRGB imageRGB = new ImageRGB(img);
			System.out.println("o zaman renk: "
					+ imageRGB.getRedValue(100, 100));
		} catch (IOException e) {
		}
	}

}
