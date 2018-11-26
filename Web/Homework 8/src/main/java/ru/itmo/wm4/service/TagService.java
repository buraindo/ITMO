package ru.itmo.wm4.service;

import org.springframework.stereotype.Service;
import ru.itmo.wm4.domain.Tag;
import ru.itmo.wm4.repository.TagRepository;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag getTag(String tagName) {
        Tag tag = tagRepository.findByName(tagName);
        if (tag == null) {
            tag = new Tag();
            tag.setName(tagName);
            tagRepository.save(tag);
        }
        return tag;
    }

}
