package vn.edu.iuh.fit.backend.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendInvitationEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);
    }

    public void sendJobInvitationEmail(String to, String senderName, String jobTitle, String jobLink) throws MessagingException {
        String subject = "Mời bạn ứng tuyển vị trí " + jobTitle + " tại công ty";
        String body = "<h3>Chào bạn,</h3>" +
                "<p>Chúng tôi rất vui khi nhận thấy bạn có thể là một ứng viên tiềm năng cho vị trí <strong>" + jobTitle + "</strong> tại công ty chúng tôi.</p>" +
                "<p>Xin vui lòng nhấp vào <a href='" + jobLink + "'>đây</a> để tìm hiểu thêm về công việc và ứng tuyển.</p>" +
                "<p>Mong nhận được sự phản hồi từ bạn.</p>" +
                "<p>Trân trọng,</p>" +
                "<p>" + senderName + "<br>Quản lý tuyển dụng tại Công Ty XYZ</p>";

        sendInvitationEmail(to, subject, body);
    }

}