package rentmanage.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "house")
public class House extends BaseEntity{
	

	 @Column(name = "address")
	 @NotEmpty
	 private String address;
	 
	 @Column(name = "name")
	 @NotEmpty
	 private String name;
	 
	 @Column(name = "createdate")
	 @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	 @DateTimeFormat(pattern = "yyyy/MM/dd")
	 private DateTime createdate;
	 
	 @Column(name = "price")
	 private Integer price;
	 

	 @ManyToOne
	 @JoinColumn(name = "owner_id")
	 private Owner owner;
	 
	 @Override
	public String toString() {
		// TODO Auto-generated method stub
		 return new ToStringCreator(this)
         .append("id", this.getId())
         .append("new", this.isNew())
         .append("name", this.getName())
         .append("price", this.getPrice())
         .append("address", this.address)
         .toString();
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(DateTime createdate) {
		this.createdate = createdate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	
}
