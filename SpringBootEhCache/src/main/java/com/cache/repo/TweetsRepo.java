package com.cache.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cache.pojo.Tweets;

public interface TweetsRepo extends JpaRepository<Tweets, Integer> {

}
