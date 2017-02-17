package com.dragon.repo;

import com.dragon.model.IndexSPX;
import com.dragon.model.IndexUX;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by osetskiy on 26.12.2016.
 */
public interface IndexSPXRepo extends MongoRepository<IndexSPX, String> {
}
