package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.fit.backend.models.Invitation;
import vn.edu.iuh.fit.backend.repositories.InvitationRepository;

import java.util.List;

@Controller
public class InvitationController {
    @Autowired
    private InvitationRepository invitationRepository;

    @GetMapping("/invitations")
    public String showInvitationList(Model model) {
        List<Invitation> invitations = invitationRepository.findAll();
        model.addAttribute("invitations", invitations);
        return "invitations/list";
    }
}


