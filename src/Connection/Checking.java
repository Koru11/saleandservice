package Connection;

import java.util.Vector;

import javax.swing.JOptionPane;

public class Checking {
	@SuppressWarnings("deprecation")
	public static boolean IsValidName(String str) {
		if (!str.isEmpty() &&str.charAt(0) == ' ') {
			return true;
		} else {
			return false;
		}
	}

	public static boolean InvalidEmail(String str) {
		System.out.println(str.contains("@"));
		System.out.println(str.contains(".com"));
		if (!str.contains("@") || !str.contains(".com")) {

			return true;
		}

		int dot = str.lastIndexOf(".");
		int name = str.indexOf("@");

		// something must be between dot and name

		String subName = str.substring(0, name);
		String subDot = str.substring(dot + 1);
		String subDomain = str.substring(name + 1, dot);

		if (subName.isBlank() || subDomain.isBlank()) {
			return true;
		}

//		System.out.println(subDomain);
//		System.out.println(subName);
//		System.out.println(subDot);

		return false;
	}

	public static boolean isPhoneNo(String str) {
		boolean phone = false;

		if (str.length() > 11 || str.length() < 9 || !isAllNum(str) || !str.startsWith("09")) {
			return true;
		}

		return phone;
	}

	public static boolean isAllNum(String str) {
		boolean allNum = false;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				allNum = true;
			} else {
				allNum = false;
				break;
			}
		}
		return allNum;
	}

	public static boolean IsContain(String s, Vector str) {
		for (int i = 0; i < str.size(); i++) {
			if (s.equals((String) str.elementAt(i))) {
//				System.out.println(s+" already contained");
				return true;
			}

		}
		return false;
	}

	public static boolean checktxtquantity(String strqp) {
		if (strqp.equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter the Quantity");
			return false;
		} else if (!isAllNum(strqp)) {
			JOptionPane.showMessageDialog(null, "You must enter NUMBER for Quantity");
			return false;
		} else if (Integer.parseInt(strqp) > 10000) {
			JOptionPane.showMessageDialog(null, "The Quantity you entered is too many to purchase!");
			return false;
		} else {
			return true;
		}
	}

	public static boolean checktxtprice(String strqp) {
		System.out.println(strqp);
		if (strqp.equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter the Price");
			return false;
		} else if (!isAllNum(strqp)) {
			JOptionPane.showMessageDialog(null, "You must enter NUMBER the Price");
			return false;
		} else if (Long.parseLong(strqp) > 1000000000) {
			JOptionPane.showMessageDialog(null, "The Price you entered is too much(more than 1,000,000,000)");
			return false;
		} else {
			return true;
		}
	}

	public static String Sumamount(Vector data, int t) {
		long sum = 0;
		for (int i = 0; i < data.size(); i++) {
			sum += Long.parseLong((String) data.elementAt(i));

		}
		if (t == 1) {
			int len = String.valueOf(sum).length(), index = 0;
			StringBuffer str = new StringBuffer("");
			for (int i = 0; i < len; i++) {
				if (index == 3) {
					str.append(",");
					index = 0;
					i--;
				} else {
					str.append(String.valueOf(sum).charAt(len - i - 1));
					index++;
				}
			}
			return str.reverse().toString();
		} else {
			return String.valueOf(sum);
		}

	}

public static String calculateAmount(Vector data, int t) {
	long sum = 0;
	for (int i = 0; i < data.size(); i++) {
		sum += Long.parseLong((String) data.elementAt(i));

	}
	if (t == 1) {
		int len = String.valueOf(sum).length(), index = 0;
		StringBuffer str = new StringBuffer("");
		for (int i = 0; i < len; i++) {
			if (index == 3) {
				index = 0;
				i--;
			} else {
				str.append(String.valueOf(sum).charAt(len - i - 1));
				index++;
			}
		}
		return str.reverse().toString();
	} else {
		return String.valueOf(sum);
	}

}
}
