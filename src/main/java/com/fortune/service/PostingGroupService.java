package com.fortune.service;

import com.fortune.model.PostingGroup;


import java.util.List;

/**
 * Created by fortune on 7/26/17.
 */


public interface PostingGroupService {
  void savePostingGroup(PostingGroup postingGroup);
  void delete(PostingGroup postingGroup);
  List<PostingGroup> findAllPostingGroups();
}
