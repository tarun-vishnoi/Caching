package com.cache.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tweets")
public class Tweets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweet_generator")
	@SequenceGenerator(name = "tweet_generator", sequenceName = "tweet_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@Column(name = "tweetId")
	private String tweetId;
	@Column(name = "tweet")
	private String tweet;
	@Column(name = "createdDate")
	private Date createdDate;

	public Tweets() {
	}

	public Tweets(Integer id, String tweetId, String tweet, Date createdDate) {
		this.id = id;
		this.tweetId = tweetId;
		this.tweet = tweet;
		this.createdDate = createdDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Tweets [id=" + id + ", tweetId=" + tweetId + ", tweet=" + tweet + ", createdDate=" + createdDate + "]";
	}

}
