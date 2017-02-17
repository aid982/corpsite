package com.dragon.repo;


import com.dragon.model.Security;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * Created by osetskiy on 26.12.2016.
 */

public interface SecurityRepo extends MongoRepository<Security, String> {
    List<Security> findAllByType(String type);
}
