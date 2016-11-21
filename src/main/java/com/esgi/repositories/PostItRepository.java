package com.esgi.repositories;

import com.esgi.model.PostIt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arnaud on 21/11/2016.
 */

@Repository
public interface PostItRepository extends JpaRepository<PostIt, Long> {

}
