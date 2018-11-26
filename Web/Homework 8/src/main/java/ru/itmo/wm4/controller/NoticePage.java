package ru.itmo.wm4.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wm4.domain.Comment;
import ru.itmo.wm4.domain.Notice;
import ru.itmo.wm4.domain.Role;
import ru.itmo.wm4.security.AnyRole;
import ru.itmo.wm4.security.Guest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;

@Controller
public class NoticePage extends Page {
    @AnyRole(Role.Name.ADMIN)
    @GetMapping(path = "/notice")
    public String noticeGet(Model model) {
        model.addAttribute("notice", new Notice());
        //model.addAttribute("")
        return "AddNoticePage";
    }

    @Guest
    @GetMapping(path = "/notice/{id}")
    public String noticeIdGet(@PathVariable(value = "id") Long id, Model model) {
        Notice notice = getNoticeService().findById(id);
        notice.setId(id);
        model.addAttribute("notice", notice);
        model.addAttribute("comment", new Comment());
        return "NoticePage";
    }

    @AnyRole(Role.Name.ADMIN)
    @PostMapping(path = "/notice")
    public String noticePost(@Valid @ModelAttribute("notice") Notice notice,
                             @RequestParam("tags-str") String tagsStr,
                             BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "AddNoticePage";
        }
        String[] tags = tagsStr.split(" ");
        for(String tag : tags) {
            if (!tag.matches("[a-z]*")) {
                return "AddNoticePage";
            }
        }
        Arrays.stream(tags).forEach((tag) -> notice.addTag(getTagService().getTag(tag)));
        getNoticeService().save(notice, getUser(httpSession));
        return "redirect:/notices";
    }

    @AnyRole({Role.Name.ADMIN, Role.Name.USER})
    @PostMapping(path = "/notice/{id}")
    public String noticeAddComment(@PathVariable(value = "id") Long id,
                                   @Valid @ModelAttribute("comment") Comment comment,
                                   @Valid @ModelAttribute("notice") Notice notice,
                             BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "redirect:/notice/{id}";
        }

        getCommentService().save(comment, getUser(httpSession), notice);
        return "redirect:/notice/{id}";
    }
}
