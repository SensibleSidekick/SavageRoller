package com.savageroller.poc_savageroller.data;

import com.savageroller.poc_savageroller.models.PlayerCharacter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCharacterRepository extends CrudRepository<PlayerCharacter, Integer> {
}
