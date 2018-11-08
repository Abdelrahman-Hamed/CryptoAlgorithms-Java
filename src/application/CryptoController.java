package application;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class CryptoController implements Initializable {
	@FXML
	private JFXTextArea plain;
	@FXML
	private JFXTextField key;
	@FXML
	private JFXTextArea encrypted;
	@FXML
	private JFXComboBox<String> algorithm;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		algorithm.getItems().add("Caesar Cipher");
		key.setStyle("-fx-text-fill:#c29400");

	}

	public void encryptDecrypt(ActionEvent e) {
		/*switch (algorithm.getSelectionModel().getSelectedIndex()) {
		case 0:
			caeserCipher();
			break;
		default:
			caeserCipher();
		}*/
		playFair();
	}

	public void caeserCipher() {
		short keyVal = 0;
		short charVal = 0;
		if (plain.getText().length() > 0) {
			StringBuilder enc = new StringBuilder();
			char c;

			keyVal = Short.parseShort(key.getText());
			if (keyVal > 25)
				keyVal %= 25; // 0->25
			for (int i = 0; i < plain.getText().length(); i++) {
				charVal = (short) Character.toLowerCase(plain.getText().charAt(i));

				if ((charVal < 97 || charVal > 122) && charVal != 32) {
					System.out.println("Invalid Char at index : " + i);
					continue;
				} else if (charVal == 32)
					c = (char) charVal;
				else {
					charVal = (short) ((short) (Character.toLowerCase(plain.getText().charAt(i)) + keyVal) % 122);
					c = (char) ((charVal == 0) ? 122 : charVal);
					c = (char) ((charVal < 97 && charVal > 0) ? charVal + 96 : c);
				}
				enc.append(c);
			}
			encrypted.setText(enc.toString());
		}
		if (encrypted.getText().length() > 0) {
			StringBuilder pl = new StringBuilder();
			char c;
			keyVal = Short.parseShort(key.getText());
			if (keyVal > 25)
				keyVal %= 25;
			for (int i = 0; i < encrypted.getText().length(); i++) {
				charVal = (short) Character.toLowerCase(encrypted.getText().charAt(i));

				if ((charVal < 97 || charVal > 122) && charVal != 32) {
					System.out.println("Invalid Char at index : " + i);
					continue;
				} else if (charVal == 32)
					c = (char) charVal;
				else {
					charVal = (short) ((short) (Character.toLowerCase(encrypted.getText().charAt(i)) - keyVal + 26)
							% 122);
					c = (char) ((charVal == 0) ? 122 : charVal);
					c = (char) ((charVal < 97 && charVal > 0) ? charVal + 96 : c);
				}
				pl.append(c);
			}
			plain.setText(pl.toString());
		}

	}

	public void playFair() {
		StringBuilder plain = new StringBuilder(this.plain.getText());

		String keyWord = this.key.getText();

		StringBuilder cipher = new StringBuilder();
		StringBuilder matrix = new StringBuilder(keyWord + "abcdefghijklmnopqrstuvwxyz");
		Set<Character> seen = new HashSet<Character>();
		StringBuilder tempMatrix = new StringBuilder();
		for (int i = 0; i < matrix.length(); i++) {
			if (matrix.charAt(i) == 'i')
				continue;
			if (!seen.contains(matrix.charAt(i))) {
				seen.add(matrix.charAt(i));
				tempMatrix.append(matrix.charAt(i));
			}
		}

		matrix = tempMatrix;
		for (int i = 0; i < plain.length() - 1; i += 2) {
			if (plain.charAt(i) == plain.charAt(i + 1)) {
				plain.insert(i + 1, 'x');
			}
		}
		if (plain.length() % 2 != 0)
			plain.append('x');
		for (int i = 0; i < plain.length(); i += 2) {
			int r1 = matrix.indexOf(String.valueOf(plain.charAt(i)), 0) / 5;
			int c1 = matrix.indexOf(String.valueOf(plain.charAt(i)), 0) % 5;
			int r2 = matrix.indexOf(String.valueOf(plain.charAt(i + 1)), 0) / 5;
			int c2 = matrix.indexOf(String.valueOf(plain.charAt(i + 1)), 0) % 5;
			if (r1 == r2) {
				cipher.append(matrix.charAt(r1 * 5 + (c1 + 1) % 5));
				cipher.append(matrix.charAt(r2 * 5 + (c2 + 1) % 5));
			} else if (c1 == c2) {
				int index = matrix.indexOf(String.valueOf(plain.charAt(i)), 0) - 5;
				if (index > 0)
					cipher.append(matrix.charAt(index));
				else
					cipher.append(matrix.charAt(index + 25));
				index = matrix.indexOf(String.valueOf(plain.charAt(i + 1)), 0) - 5;
				if (index > 0)
					cipher.append(matrix.charAt(index));
				else
					cipher.append(matrix.charAt(index + 25));
			} else {
				cipher.append(matrix.charAt(r1 * 5 + c2));
				cipher.append(matrix.charAt(r2 * 5 + c1));
			}
		}
		encrypted.setText(cipher.toString());
	}

}
