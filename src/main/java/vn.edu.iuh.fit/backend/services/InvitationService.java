package vn.edu.iuh.fit.backend.services;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationService {

    @Autowired
    private EmailService emailService;

    public void sendApplicationEmail(String recipientEmail, String jobTitle, String candidateName) throws MessagingException {
        String subject = "Đơn ứng tuyển vị trí tại công ty";
        String body = "<h3>Chào quý công ty,</h3>" +
                "<p>Tôi là " + candidateName + ", và tôi muốn bày tỏ sự quan tâm đến vị trí tuyển dụng tại công ty quý công ty.</p>" +
                "<p>Xin vui lòng nhấp vào <a href=''>đây</a> để xem hồ sơ của tôi và các thông tin ứng tuyển chi tiết.</p>" +
                "<p>Mong nhận được phản hồi từ quý công ty.</p>" +
                "<p>Trân trọng,</p>" +
                "<p>" + candidateName + "</p>";



        emailService.sendInvitationEmail(recipientEmail, subject, body);
    }
}

