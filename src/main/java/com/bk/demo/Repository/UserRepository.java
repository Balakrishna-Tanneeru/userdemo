package com.bk.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import com.bk.demo.model.UserRecord;
public interface UserRepository extends CrudRepository<UserRecord, Integer>
{
}