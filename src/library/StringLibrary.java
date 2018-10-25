package library;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringLibrary {

	public static String md5(String str) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			BigInteger bigInteger = new BigInteger(1, md.digest());
			result = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String md5ForForgotPassword(String str) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			BigInteger bigInteger = new BigInteger(1, md.digest());
			result = bigInteger.toString(10);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String removeAccent(String s) {
		  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		  return pattern.matcher(temp).replaceAll("").toLowerCase().replace('đ', 'd');
	}
	
	public static String imDam(String chuoi,String tuKhoa)
	{
		boolean isTuKhoa = false;
		String kq = "";
		String mangChuoi[] = chuoi.split("\\s");
		String chuoiTam = removeAccent(chuoi);
		String mangchuoiTam[] = chuoiTam.split("\\s");
		String tuKhoaTam = removeAccent(tuKhoa);
		String chuoituKhoa[] = tuKhoaTam.split("\\s");
		for(int i=0;i< mangchuoiTam.length ;i++)
		{
			for (String tk : chuoituKhoa) {
				if(tk.equals(mangchuoiTam[i]))
				{
					isTuKhoa = true;
					
				}
			}
			if(isTuKhoa == true)
			{
				kq = kq + "<span style='color:red;font-style:italic'>" + mangChuoi[i] + "</span>";
			}
			else
			{
				kq = kq + mangChuoi[i];
			}
			isTuKhoa = false;
			if(i < mangchuoiTam.length - 1)
			{
				kq = kq + " ";
			}
		}
		
		System.out.println(kq);
		return kq;
	}
	
	public static String makeSlug(String title) {
		String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		slug = pattern.matcher(slug).replaceAll("");
		slug = slug.toLowerCase();
		// Thay đ thành d
		slug = slug.replaceAll("đ", "d");
		// Xóa các ký tự đặt biệt
		slug = slug.replaceAll("([^0-9a-z-\\s])", "");
		// Thay space thành dấu gạch ngang
		slug = slug.replaceAll("[\\s]", "-");
		// Đổi nhiều ký tự gạch ngang liên tiếp thành 1 ký tự gạch ngang
		slug = slug.replaceAll("(-+)", "-");
		// Xóa các ký tự gạch ngang ở đầu và cuối
		slug = slug.replaceAll("^-+", "");
		slug = slug.replaceAll("-+$", "");
		return slug;
	}
}
