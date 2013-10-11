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
package rentmanage.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;

import rentmanage.model.Admin;
import rentmanage.model.House;
import rentmanage.model.Owner;
import rentmanage.repository.HouseRepository;
import rentmanage.repository.OwnerRepository;
import rentmanage.repository.jpa.JpaOwnerRepositoryImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClinicServiceImpl implements ClinicService {

    private OwnerRepository ownerRepository;
    private HouseRepository houseRepository;

    @Autowired
    public ClinicServiceImpl(OwnerRepository ownerRepository,HouseRepository houseRepository) {
        this.ownerRepository = ownerRepository;
        this.houseRepository = houseRepository;
    }


    @Transactional(readOnly = true)
    public Owner findOwnerById(int id) throws DataAccessException {
        return ownerRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException {
        return ownerRepository.findByLastName(lastName);
    }

    @Transactional
    public void saveOwner(Owner owner) throws DataAccessException {
        ownerRepository.save(owner);
    }


	public Owner findOwnerLogin(String loginName, String password)
			throws DataAccessException {
		return ownerRepository.findOwnerLogin(loginName, password);
	}


	public Admin findAdminLogin(String loginName, String password)
			throws DataAccessException {
		return ownerRepository.findAdminLogin(loginName, password);
	}

	@Transactional
	public void savehouse(House house) throws DataAccessException {
		// TODO Auto-generated method stub
		houseRepository.save(house);
	}


	public House findhouseById(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return houseRepository.findById(id);
	}


	public Collection<House> findhouseByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return houseRepository.findhouseByName(name);
	}

	@Transactional
	public void deleteById(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		houseRepository.deleteById(id);
	}



}
