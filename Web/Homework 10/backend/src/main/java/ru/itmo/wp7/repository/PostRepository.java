package ru.itmo.wp7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.wp7.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE post set title=?2, text=?3 WHERE id=?1", nativeQuery = true)
    void update(long id, String title, String text);
}
