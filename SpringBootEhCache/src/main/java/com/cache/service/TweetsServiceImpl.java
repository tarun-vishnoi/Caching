package com.cache.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cache.pojo.Tweets;
import com.cache.repo.TweetsRepo;

/**
 * least recently used (LRU)
 * 
 * Note that using @CachePut and @Cacheable annotations on the same method is
 * generally discouraged because they have different behaviors.
 * 
 * @Cacheable : It is used on the method level to let spring know that the
 *            response of the method are cacheable. Spring manages the
 *            request/response of this method to the cache specified in
 *            annotation attribute.
 * 
 * @CachePut : Sometimes we need to manipulate the caching manually to put
 *           (update) cache before method call. This will allow us to update the
 *           cache and will also allow the method to be executed. The method
 *           will always be executed and its result placed into the cache.
 * 
 * @CacheEvict : It is used when we need to evict (remove) the cache previously
 *             loaded of master data. When CacheEvict annotated methods will be
 *             executed, it will clear the cache. We can specify key here to
 *             remove cache, if we need to remove all the entries of the cache
 *             then we need to use allEntries=true.
 * 
 * @Caching : This annotation is required when we need both CachePut and
 *          CacheEvict at the same time.
 * 
 * 
 * @Note : Methods annotated with @Cacheable are not executed again if a value
 *       already exists in the cache for the cache key. If the value does not
 *       exist in the cache, then the method is executed and places its value in
 *       the cache. Now there is also the use case that we always want the
 *       method to be executed and its result to be placed in the cache. This is
 *       done using the @CachePut annotation, which has the same annotation
 *       parameters as @Cachable.
 * 
 * 
 * @author Tarun Vishnoi
 *
 */
@Service
public class TweetsServiceImpl implements TweetsService {
	@Autowired
	private TweetsRepo tweetsRepo;

	@CachePut(value = "allTweets")
	@Override
	public List<Tweets> getTweets() {

		List<Tweets> tweets = new ArrayList<Tweets>();
		try {
			tweets = tweetsRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}

	@Cacheable(value = "tweetById", condition = "#id > 1")
	@Override
	public Optional<Tweets> getTweet(Integer id) {

		Optional<Tweets> tweet = null;
		try {
			tweet = tweetsRepo.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweet;
	}

	// to remove all entries from the below caches
	@CacheEvict(cacheNames = { "allTweets", "tweetById" }, allEntries = true)
	public void flushCache() {
	}

	@Override
	@CacheEvict(cacheNames = { "tweetById" }, allEntries = true)
	public void deleteById(Integer id) {
		tweetsRepo.deleteById(id);
	}

}
