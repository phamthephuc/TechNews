package library;


/*
 * Lớp này được viết để hỗ trợ các bạn yêu thích lập trình Java. 
 * Các bạn được phép sử dụng miễn phí. Tuy nhiên nếu có lỗi xảy 
 * ra chúng tôi sẽ không chịu trách nhiệm. 
 * Tác giả: Nguyễn Nghiệm - 0913745789 - nnghiem@yahoo.com 
 */


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Lớp này chứa một số hàm tiện ích giúp gửi email 
 * thông qua tài khoản google. 
 * Mail sẽ không được gửi ngay mà được đưa vào hàng đợi 
 * và sẽ được một thread đọc và gửi vào thời gian rỗi.
 * 
 * @see send(String, String, String)
 * @see send(String, String, String, String)
 * @see send(String, String, String, String, String, String, String)
 * @author nnghiem@yahoo.com
 */
public class GMailUtil
{
	// thread chuyên dụng đọc mail từ hàng đợi và gửi đi
	static MailThread thread = new MailThread();
	
	// your google email
    private static String email = "lycatostore@gmail.com";
    // your google password
    private static String password = "songlong";
    
    private static Session session;
    private static Properties config = new Properties();
    static
    {
        config.setProperty("mail.smtp.host", "smtp.gmail.com");
        config.setProperty("mail.smtp.port", "465");
        config.setProperty("mail.smtp.starttls.enable","true");
        config.setProperty("mail.smtp.auth", "true");
        config.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        config.setProperty("mail.smtp.socketFactory.fallback", "false");
        Authenticator authenticator = new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        };
        session = Session.getInstance(config, authenticator);
    }
    
    /**
     * Gửi email từ hệ thống đến một hay một số người.
     * 
     * @param to chứa danh sách email của người nhận. Mỗi email cách nhau dấu phẩy, chấm phẩy hay khoản trắng
     * @param subject tiêu đờ của email
     * @param body nội dung email
     * @return MimeMessage đã gửi
     */
    public static void send(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException
    {
        String from = String.format("Web Master <%s>", email);
        GMailUtil.send(from, to, subject, body);
    }
    /**
     * Gửi email từ một người đến một hay một số người.
     * 
     * @param from email của người gửi (ví dụ: <b>Nguyen Nghiem &lt;nnghiem@yahoo.com&gt;</b>)
     * @param to chứa danh sách email của người nhận. Mỗi email cách nhau dấu phẩy, chấm phẩy hay khoản trắng
     * @param subject tiêu đờ của email
     * @param body nội dung email
     */
    public static void send(String from, String to, String subject, String body)
    {
    	String cc = "", bcc = "";
        GMailUtil.send(from, to, cc, bcc, subject, body, "");
    }
    /**
     * Gửi email từ một người đến một hay một số người, có cc, bcc và attached files
     * 
     * @param from email của người gửi (ví dụ: <b>Nguyen Nghiem &lt;nnghiem@yahoo.com&gt;</b>)
     * @param to chứa danh sách email của người nhận. Mỗi email cách nhau dấu phẩy, chấm phẩy hay khoản trắng
     * @param cc chứa danh sách email của những người đồng nhận. Mỗi email cách nhau dấu phẩy, chấm phẩy hay khoản trắng. Danh sách email này sẽ được nhìn thấy trên email.
     * @param bcc chứa danh sách email của những người đồng nhận. Mỗi email cách nhau dấu phẩy, chấm phẩy hay khoản trắng. Danh sách email này sẽ không được nhìn thấy trên email.
     * @param subject tiêu đề của email
     * @param body nội dung email
     * @param attachments danh sách đường dẫn các tập tin đính kèm. Mỗi đường dẫn cách nhau dấu phẩy hoặc chấm phẩy.
     */
    public static void send(String from, String to, String cc, String bcc, String subject, String body, String attachments)
    {
    	try{
	        MimeMessage mail = new MimeMessage(session);
	
	        InternetAddress fromAddress = toInternetAddress(from);
	        mail.setFrom(fromAddress);
	        
	        mail.setReplyTo(new InternetAddress[]{fromAddress});
	        
	        final String toEmails = to.trim().replaceAll("[,;\\s]+", ",");
	        mail.addRecipients(Message.RecipientType.TO, toEmails);
	
	        if(cc != null && cc.trim().length() > 0)
	        {
	            final String ccEmails = cc.trim().replaceAll("[,;\\s]+", ",");
	            mail.addRecipients(Message.RecipientType.CC, ccEmails);
	            System.out.println(ccEmails);
	        }
	
	        if(bcc != null && bcc.trim().length() > 0)
	        {
	            final String bccEmails = bcc.trim().replaceAll("[,;\\s]+", ",");
	            mail.addRecipients(Message.RecipientType.BCC, bccEmails);
	            System.out.println(bccEmails);
	        }
	        mail.setSubject(subject, "utf8");
	        mail.setContent(body, "text/html; charset=\"UTF-8\"");
	        mail.setSentDate(new Date());
	
	        if(attachments != null && attachments.trim().length() > 0)
	        {
	            MimeMultipart multiPart = new MimeMultipart();
	            
	            MimeBodyPart textBodyPart = new MimeBodyPart();
	            textBodyPart.setContent(body, "text/html; charset=\"UTF-8\"");
	            multiPart.addBodyPart(textBodyPart);
	
	            String[] paths = attachments.split("[,;\\s]+");
	            for(String path : paths)
	            {
	                File file = new File(path.trim());
	                multiPart.addBodyPart(GMailUtil.createMimeBodyPart(file));
	            }
	            mail.setContent(multiPart);
	        }
	        
	        //Transport.send(mail);
	        thread.queue(mail);
    	}
    	catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    private static MimeBodyPart createMimeBodyPart(File file) throws MessagingException
    {
        MimeBodyPart bodyPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(file);
        bodyPart.setDataHandler(new DataHandler(fds));
        bodyPart.setFileName(file.getName());
        return bodyPart;
    }
    
    private static InternetAddress toInternetAddress(String emailAddress) throws UnsupportedEncodingException
    {
        String[] addresses = emailAddress.split("[<>]");
        String namePart = addresses[0].trim();
        String emailPart = (addresses.length > 1 ? addresses[1] : addresses[0]).trim();
        return new InternetAddress(emailPart, namePart, "utf8");
    }
}

class MailThread extends Thread{
	List<MimeMessage> mails = new ArrayList<MimeMessage>();
	boolean running = false, stopped = false;
	
	public MailThread(){
		this.start();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(!stopped){
			if(mails.size() > 0){
				MimeMessage mail = mails.remove(0);
				try {
					Transport.send(mail);
				} 
				catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			else{
				this.suspend();
				running = false;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void queue(MimeMessage mail){
		mails.add(mail);
		if(running == false){
			this.resume();
			running = true;
		}
	}
	
	public void stopThread(){
		stopped = true;
	}
}