package com.myblog.myblog1.repository;

import com.myblog.myblog1.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import java.awt.print.Pageable;
public interface PostRepository extends JpaRepository<Post,Long> {


}
