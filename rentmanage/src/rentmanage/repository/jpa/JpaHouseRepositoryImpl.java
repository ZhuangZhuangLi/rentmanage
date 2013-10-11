/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rentmanage.repository.jpa;


import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import rentmanage.model.House;
import rentmanage.repository.HouseRepository;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link HouseRepository} interface.
 *
 * @author Mike Keith
 * @author Rod Johnson
 * @author Sam Brannen
 * @author Michael Isvy
 * @since 22.4.2006
 */
@Repository
public class JpaHouseRepositoryImpl implements HouseRepository {

    @PersistenceContext
    private EntityManager em;

    public House findById(int id) {
        return this.em.find(House.class, id);
    }
    
    public void save(House house) throws DataAccessException {
		// TODO Auto-generated method stub
		this.em.merge(house);
	}
    @SuppressWarnings("unchecked")
    public Collection<House> findhouseByName(String name) throws DataAccessException {
    	// TODO Auto-generated method stub
    	Query query = this.em.createQuery("SELECT house FROM House house  WHERE house.name LIKE :lastName");
        query.setParameter("lastName", name + "%");
        return query.getResultList();
    }
    
    public void deleteById(int id) {
        this.em.remove(this.em.find(House.class, id));
    }

}
