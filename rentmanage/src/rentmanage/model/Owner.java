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
package rentmanage.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
@Entity
@Table(name = "owners")
public class Owner extends Person{
    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;
    
    @Column(name = "login_name")
    @NotEmpty
    private String loginname;
    
    
    @Column(name = "password")
    @NotEmpty
    private String password;
    
    public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String login_name) {
		this.loginname = login_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<House> houses;


    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    protected void setHousesInternal(Set<House> Houses) {
        this.houses = Houses;
    }

    protected Set<House> getHousesInternal() {
        if (this.houses == null) {
            this.houses = new HashSet<House>();
        }
        return this.houses;
    }

    public List<House> getHouses() {
        List<House> sortedHouses = new ArrayList<House>(getHousesInternal());
        PropertyComparator.sort(sortedHouses, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedHouses);
    }

    public void addHouse(House House) {
        getHousesInternal().add(House);
    }

    /**
     * Return the House with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if House name is already in use
     */
    public House getHouse(String name) {
        return getHouse(name, false);
    }

    /**
     * Return the House with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if House name is already in use
     */
    public House getHouse(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (House House : getHousesInternal()) {
            if (!ignoreNew || !House.isNew()) {
                String compName = House.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return House;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("lastName", this.getLastName())
                .append("firstName", this.getFirstName())
                .append("address", this.address)
                .append("city", this.city)
                .append("telephone", this.telephone)
                .toString();
    }
}
