package com.cache.service;

import java.util.List;
import java.util.Optional;

import com.cache.pojo.Tweets;

public interface TweetsService {
	public List<Tweets> getTweets();

	public Optional<Tweets> getTweet(Integer id);

	public void deleteById(Integer id);
}
