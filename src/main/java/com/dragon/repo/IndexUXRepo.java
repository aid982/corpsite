package com.dragon.repo;

import com.dragon.model.Index;
import com.dragon.model.IndexUX;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

/**
 * Created by osetskiy on 26.12.2016.
 */
public interface IndexUXRepo extends MongoRepository<IndexUX, String> {
}
