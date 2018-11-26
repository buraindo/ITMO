package ru.itmo.wm4.service;

import org.springframework.stereotype.Service;
import ru.itmo.wm4.domain.Notice;
import ru.itmo.wm4.domain.User;
import ru.itmo.wm4.repository.NoticeRepository;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<Notice> findByOrderByCreationTimeDesc() {
        return noticeRepository.findByOrderByCreationTimeDesc();
    }

    public void save(Notice notice, User user) {
        notice.setAuthor(user);
        noticeRepository.save(notice);
    }

    public Notice findById(Long id) {
        return id == null ? null : noticeRepository.findById(id).orElse(null);
    }
}
