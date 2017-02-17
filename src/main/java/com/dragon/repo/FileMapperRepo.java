package com.dragon.repo;
import com.dragon.model.Excel.FileMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Created by osetskiy on 26.12.2016.
 */
@RepositoryRestResource(collectionResourceRel = "mapper", path = "mapper")
public interface FileMapperRepo extends MongoRepository<FileMapper, String> {

}
