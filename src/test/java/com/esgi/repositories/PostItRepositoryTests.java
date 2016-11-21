package com.esgi.repositories;

import com.esgi.DashbeardApiApplication;
import com.esgi.repositories.datasets.PostItDataset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * Created by Arnaud on 21/11/2016.
 */
@ActiveProfiles("tests")
@PostItDataset
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
public class PostItRepositoryTests {

    @Autowired
    private PostItRepository postItRepository;

    @Test
    public void shouldGetAllPostIts() {
        assertThat(postItRepository.findAll(), hasSize(10));
    }
}
