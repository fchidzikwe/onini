package com.fortune.service.impl;

import com.fortune.model.PostingGroup;
import com.fortune.repository.PostingGroupRepository;
import com.fortune.service.PostingGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fortune on 7/26/17.
 */

@Service
public class PostingGroupServiceImpl implements PostingGroupService {
  @Autowired
  PostingGroupRepository postingGroupRepository;

  @Override
  public void savePostingGroup(PostingGroup postingGroup) {
    postingGroupRepository.save(postingGroup);
  }

  @Override
  public void delete(PostingGroup postingGroup) {
    postingGroupRepository.delete(postingGroup);
  }

  @Override
  public List<PostingGroup> findAllPostingGroups() {
    return postingGroupRepository.findAll();
  }
}
