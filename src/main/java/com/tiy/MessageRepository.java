package com.tiy;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jessicatracy on 9/23/16.
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
}
