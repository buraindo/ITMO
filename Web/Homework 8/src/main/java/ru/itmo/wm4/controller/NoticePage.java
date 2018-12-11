package ru.itmo.wm4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wm4.domain.Comment;
import ru.itmo.wm4.domain.Notice;
import ru.itmo.wm4.domain.Role;
import ru.itmo.wm4.form.CommentCredentials;
import ru.itmo.wm4.form.NoticeCredentials;
import ru.itmo.wm4.security.AnyRole;
import ru.itmo.wm4.security.Guest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class NoticePage extends Page {

    @AnyRole(Role.Name.ADMIN)
    @GetMapping(path = "/notice")
    public String noticeGet(Model model) {
        model.addAttribute("noticeForm", new NoticeCredentials());
        return "AddNoticePage";
    }

    @Guest
    @GetMapping(path = "/notice/{id}")
    public String noticeIdGet(@PathVariable(value = "id") Long id, Model model) {
        Notice notice = getNoticeService().findById(id);
        notice.setId(id);
        model.addAttribute("notice", notice);
        model.addAttribute("commentForm", new CommentCredentials());
        return "NoticePage";
    }

    @AnyRole(Role.Name.ADMIN)
    @PostMapping(path = "/notice")
    public String noticePost(@Valid @ModelAttribute("noticeForm") NoticeCredentials noticeCredentials,
                             BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "AddNoticePage";
        }
        Notice notice = new Notice();
        notice.setText(noticeCredentials.getText());
        String[] tags = noticeCredentials.getTagsStr().trim().split(" ");
        Arrays.stream(tags).forEach((tag) -> notice.addTag(getTagService().getTag(tag)));
        getNoticeService().save(notice, getUser(httpSession));
        return "redirect:/notices";
    }

    @AnyRole({Role.Name.ADMIN, Role.Name.USER})
    @PostMapping(path = "/notice/{id}")
    public String noticeAddComment(@PathVariable(value = "id") Long id,
                                   @Valid @ModelAttribute("commentForm") CommentCredentials commentCredentials,
                             BindingResult bindingResult, HttpSession httpSession, Model model) {
        Notice notice = getNoticeService().findById(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("notice", notice);
            return "NoticePage";
        }
        Comment comment = new Comment();
        comment.setText(commentCredentials.getText());
        getCommentService().save(comment, getUser(httpSession), notice);
        return "redirect:/notice/{id}";
    }
}
