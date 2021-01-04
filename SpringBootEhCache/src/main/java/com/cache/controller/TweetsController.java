package com.cache.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cache.pojo.Tweets;
import com.cache.service.TweetsService;

@RestController
public class TweetsController {

	@Autowired
	private TweetsService tweetsService;

	@RequestMapping(value = "/getTweets", method = RequestMethod.GET)
	public List<Tweets> getTweets() {
		List<Tweets> tweets = new ArrayList<Tweets>();
		try {
			tweets = tweetsService.getTweets();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}

	@RequestMapping(value = "/getTweet/{id}", method = RequestMethod.GET)
	public Optional<Tweets> getTweet(@PathVariable String id) {
		Optional<Tweets> tweets = null;
		try {
			tweets = tweetsService.getTweet(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
	
	@DeleteMapping("/{id}")
	public void deletetweetByID(@PathVariable Integer id) {
		tweetsService.deleteById(id);
	}

}
