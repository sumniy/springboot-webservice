package com.sumniy.book.springboot.service.posts;

import com.sumniy.book.springboot.domain.posts.Posts;
import com.sumniy.book.springboot.domain.posts.PostsRepository;
import com.sumniy.book.springboot.web.dto.PostsListResponseDto;
import com.sumniy.book.springboot.web.dto.PostsResponseDto;
import com.sumniy.book.springboot.web.dto.PostsSaveRequestDto;
import com.sumniy.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // entity로 삭제하는 방법말고도 postsRepository.deleteById(id) 처럼 id를 통해 바로 삭제할 수도 있지만
        // 존재하는 entity인지 먼저 확인하기 위해 조회 후 entity로 삭제
        postsRepository.delete(posts);
    }

}
