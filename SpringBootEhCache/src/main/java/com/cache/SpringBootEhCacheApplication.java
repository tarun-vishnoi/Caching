package com.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Note that using @CachePut and @Cacheable annotations on the same method is
 * generally discouraged because they have different behaviors.
 * 
 * @author Tarun Vishnoi
 *
 */
@SpringBootApplication
@EnableCaching
public class SpringBootEhCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEhCacheApplication.class, args);
	}

}
